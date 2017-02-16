package eyedsion.soft.eyedsionhis.tools;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import eyedsion.soft.eyedsionhis.entity.ImgSubEntity;
import eyedsion.soft.eyedsionhis.tools.retrofit.RetrofitManage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author yhy
 * @time 2017/2/9 9:11
 */
public class CameraUtils {
    private static CameraUtils instance;
    private static Uri imageUri;
    private static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";

    private CameraUtils() {
    }


    public static CameraUtils getCameraUtils() {
        if (instance == null) {
            synchronized (CameraUtils.class) {
                if (instance == null) {
                    instance = new CameraUtils();
                }
            }
        }
        return instance;
    }

    public static void chooseImg(Activity activity) {
        Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
        pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        activity.startActivityForResult(pickIntent, PHOTO_REQUEST_GALLERY);
    }

    public static void openCamera(Activity context) {
        File outputImage = new File(context.getExternalCacheDir(), System.currentTimeMillis() + ".jpg");
        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= 24) {
            imageUri = FileProvider.getUriForFile(context, "eyedsion.soft.eyedsionhis.fileprovider", outputImage);
        } else {
            imageUri = Uri.fromFile(outputImage);
        }
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        context.startActivityForResult(intent, PHOTO_REQUEST_CAREMA);

    }


    /**
     * 调用系统的图片裁剪
     *
     * @param data
     */
    private void startPhotoZoom(Activity activity, Uri data) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(data, "image/*");
        intent.putExtra("crop", true);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("scale", true);//黑边
        intent.putExtra("scaleUpIfNeeded", true);//黑边
        intent.putExtra("return-data", true);
        intent.putExtra("noFaceDetection", true);
        activity.startActivityForResult(intent, PHOTO_REQUEST_CUT);

    }


    public boolean OnResult(Activity activity, int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST_CAREMA) {
            startPhotoZoom(activity, imageUri);
            return true;
        } else if (requestCode == PHOTO_REQUEST_GALLERY) {
            if (data != null && data.getData() != null)
                startPhotoZoom(activity, data.getData());
            return true;
        } else if (requestCode == PHOTO_REQUEST_CUT) {
            if (data != null && data.getData() != null)
                submitImg(activity, data);
            return true;
        }
        return false;


    }

    private void submitImg(Activity activity, Intent data) {
        if (data != null) {
            Bitmap bitmap = data.getParcelableExtra("data");
            if (bitmap != null) {


                String name = new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date()) + ".jpg";

                FileOutputStream b = null;
                String pathName = "";
                if (SdCardTools.hasSdcard()) {
                    pathName = Environment.getExternalStorageDirectory() + "/eyedsion/";
                    File file = new File(pathName);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    pathName += "photo/";
                } else {
                    pathName = SdCardTools.getAbsolutePath(activity) + "/photo/";
                }
                File file = new File(pathName);
                if (!file.exists()) {
                    file.mkdirs();// 创建文件夹
                }
                pathName += name;

                try {
                    b = new FileOutputStream(pathName);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件


                if (!"".equals(pathName) && (pathName != null)) {


                    RetrofitManage.getInstance().doFileDeal(pathName, new Callback<ImgSubEntity>() {
                        @Override
                        public void onResponse(Call<ImgSubEntity> call, Response<ImgSubEntity> response) {
                            LogUtils.showLog("url", response.body().getAccess_url());
                        }

                        @Override
                        public void onFailure(Call<ImgSubEntity> call, Throwable t) {

                        }
                    });


                }


            }


        }
        try {
            // 将临时文件删除
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
