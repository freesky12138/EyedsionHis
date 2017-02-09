package eyedsion.soft.eyedsionhis.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eyedsion.soft.eyedsionhis.R;
import eyedsion.soft.eyedsionhis.entity.ImgSubEntity;
import eyedsion.soft.eyedsionhis.entity.MovieEntity;
import eyedsion.soft.eyedsionhis.tools.LogUtils;
import eyedsion.soft.eyedsionhis.tools.SdCardTools;
import eyedsion.soft.eyedsionhis.tools.retrofit.HttpOnNextListener;
import eyedsion.soft.eyedsionhis.tools.retrofit.RetrofitManage;
import eyedsion.soft.eyedsionhis.widget.DialogFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static eyedsion.soft.eyedsionhis.tools.SdCardTools.hasSdcard;

public class MainActivity extends RxAppCompatActivity {

    @BindView(R.id.hellow)
    TextView hellow;
    @BindView(R.id.get_data)
    Button getData;
    @BindView(R.id.ima_sub)
    Button imaSub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DialogFactory.showDeleteDialog(this, "是否删除", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    @OnClick(R.id.get_data)
    public void onClick() {

        RetrofitManage.getInstance()
                .doHttpDeal(RetrofitManage.httpService.getTopMovie(0, 10), this, new HttpOnNextListener<MovieEntity>() {
                    @Override
                    public void onNext(MovieEntity movieEntity) {
                        hellow.setText(movieEntity.getSubjects().get(0).getTitle());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);

                    }
                });
    }


    @OnClick(R.id.ima_sub)
    public void subImg() {
        Intent intent_pick = new Intent(Intent.ACTION_PICK);
        intent_pick.setType("image/*");
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
        startActivityForResult(intent_pick, PHOTO_REQUEST_GALLERY);


        /*
          Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                        // 判断存储卡是否可以用，可用进行存储
                        if (hasSdcard()) {
                            tempFile = new File(Environment.getExternalStorageDirectory(),
                                    PHOTO_FILE_NAME);
                            // 从文件中创建uri
                            Uri uri = Uri.fromFile(tempFile);
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                        }
                        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
                        startActivityForResult(intent, PHOTO_REQUEST_CAREMA);

        * */
    }

    private static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private File tempFile;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                crop(uri);
            }

        } else if (requestCode == PHOTO_REQUEST_CAREMA) {

            if (hasSdcard()) {
                crop(Uri.fromFile(tempFile));
            } else {
                Toast.makeText(this, "未找到存储卡，无法存储照片！", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == PHOTO_REQUEST_CUT) {
            // 从剪切图片返回的数据
            if (data != null) {
                Bitmap bitmap = data.getParcelableExtra("data");
                if (bitmap != null) {


                    String name = new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date()) + ".jpg";

                    FileOutputStream b = null;
                    String pathName = "";
                    if (hasSdcard()) {
                        pathName = Environment.getExternalStorageDirectory() + "/eyedsion/";
                        File file = new File(pathName);
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        pathName += "photo/";
                    } else {
                        pathName = SdCardTools.getAbsolutePath(this) + "/photo/";
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
                tempFile.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    /*
* 剪切图片
*/
    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);

        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }
}
