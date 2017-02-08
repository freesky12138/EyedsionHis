package eyedsion.soft.eyedsionhis.tools;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import eyedsion.soft.eyedsionhis.R;
import eyedsion.soft.eyedsionhis.widget.DialogFactory;

/**
 * Created by Administrator on 2016/6/2.
 */
public class VisionUpdateTolls {


    public static void downloadAPK(String url, Context context) {
        VisionUpdateTolls.context = context;
        final DownloadManager dManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        // 此方法表示在下载过程中通知栏会一直显示该下载，在下载完成后仍然会显示，
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        // 设置下载文件存放的路径，同样你可以选择以下方法存放在你想要的位置。
        request.setDestinationInExternalFilesDir(context, Environment.DIRECTORY_DOWNLOADS, "eyedsion.apk");

        // 设置状态栏显示信息
        request.setTitle("爱迪健康");
        request.setDescription("版本更新");
        // 设置文件类型，确保安装时不会出错
        request.setMimeType("application/vnd.android.package-archive");

//		// 获取此次下载的ID
        final long refernece = dManager.enqueue(request);

        // 把当前下载的ID保存起来
        // 下载完后会通知自定义广播com.sc.SGPhone.Broadcast.UpdataBroadcastReceiver
        SharedPreferences sPreferences = context.getSharedPreferences("downloadplato", 0);

        sPreferences.edit().putLong("plato", refernece).commit();

        // 此处用ApplicationContext， 页面销毁后也可以提示
        Toast.makeText(context, "后台下载中...", Toast.LENGTH_SHORT).show();


    }

    public static class DownLoadBroadcastReceiver extends BroadcastReceiver {


        @SuppressLint("NewApi")

        public void onReceive(Context context, Intent intent) {

            long myDwonloadID = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);

            SharedPreferences sPreferences = context.getSharedPreferences("downloadplato", 0);

            long refernece = sPreferences.getLong("plato", 0);

            if (refernece == myDwonloadID) {

                String serviceString = Context.DOWNLOAD_SERVICE;

                DownloadManager dManager = (DownloadManager) context.getSystemService(serviceString);

                Intent install = new Intent(Intent.ACTION_VIEW);

                Uri downloadFileUri = dManager.getUriForDownloadedFile(myDwonloadID);

                install.setDataAndType(downloadFileUri, "application/vnd.android.package-archive");

                install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                try {
                    VisionUpdateTolls.context.startActivity(install);
                } catch (Exception e) {
                    Toast.makeText(context, "下载完成,请点击通知栏安装...", Toast.LENGTH_SHORT).show();
                }
            }

        }


    }


    private boolean interceptFlag = false;

    private static final int DOWN_UPDATE = 1;

    private static final int DOWN_OVER = 2;

    private Dialog downloadDialog;
    private int progress;
    private ProgressBar mProgress;
    private static final String savePath = "/sdcard/update/";

    private static final String saveFileName = savePath + "eyedsion.apk";
    private static Context context;
    private Thread downLoadThread;

    private void showDownloadDialog(Context context) {
        this.context = context;
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
//		builder.setTitle("软件版本更新");

        DialogFactory.showDeleteDialog(context, "检查到新版本，是否更新？", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                downloadApk();
            }
        });


    }

    private Handler mHandler4 = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWN_UPDATE:
                    mProgress.setProgress(progress);
                    break;
                case DOWN_OVER:
                    downloadDialog.dismiss();
                    installApk();
                    break;
                default:
                    break;
            }
        }

        ;
    };

    private void showDownloadDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
//		builder.setTitle("软件版本更新");

        final LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.update_progress, null);
        mProgress = (ProgressBar) v.findViewById(R.id.progress);
        TextView textView = (TextView) v.findViewById(R.id.cancel);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadDialog.dismiss();
                interceptFlag = true;
            }
        });
        builder.setView(v);
//		builder.setNegativeButton("取消", new OnClickListener() {
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				dialog.dismiss();
//				interceptFlag = true;
//			}
//		});
        downloadDialog = builder.create();
        downloadDialog.show();

        downloadApk();
    }

    private void downloadApk() {
        downLoadThread = new Thread(mdownApkRunnable);
        downLoadThread.start();
    }

    /**
     * 下载新版本
     */
    private Runnable mdownApkRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                String url = "";
                URL url1 = new URL(url);
                //更新版本号
                HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
                conn.connect();
                int length = conn.getContentLength();
                InputStream is = conn.getInputStream();

                File file = new File(savePath);
                if (!file.exists()) {
                    file.mkdir();
                }
                String apkFile = saveFileName;
                File ApkFile = new File(apkFile);
                FileOutputStream fos = new FileOutputStream(ApkFile);

                int count = 0;
                byte buf[] = new byte[1024];

                do {
                    int numread = is.read(buf);
                    count += numread;
                    progress = (int) (((float) count / length) * 100);
                    //更新进度
                    mHandler4.sendEmptyMessage(DOWN_UPDATE);
                    if (numread <= 0) {
                        //下载完成通知安装
                        mHandler4.sendEmptyMessage(DOWN_OVER);
                        break;
                    }
                    fos.write(buf, 0, numread);
                } while (!interceptFlag);

                fos.close();
                is.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };


    /**
     * 下载成功后安装
     */
    private void installApk() {
        File apkfile = new File(saveFileName);
        if (!apkfile.exists()) {
            return;
        }
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    /**
     * 获取版本号
     */
    private int getVersionCode() {
        int versionCode = 0;
        PackageManager pm = context.getPackageManager();// 获取包管理器
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);// 根据包名获取应用版本信息
            versionCode = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // 找不到包名异常
            e.printStackTrace();
        }
        return versionCode;
    }

}
