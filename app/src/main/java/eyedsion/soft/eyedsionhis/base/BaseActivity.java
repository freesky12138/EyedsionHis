package eyedsion.soft.eyedsionhis.base;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import eyedsion.soft.eyedsionhis.R;
import eyedsion.soft.eyedsionhis.tools.AppManager;

/**
 * Created by Administrator on 2017/2/9.
 */

public abstract class BaseActivity extends RxAppCompatActivity {

    private boolean TopTouMing=true;
    //true android:fitsSystemWindows="true" 为 顶部色块
    //false (android:fitsSystemWindows="true"  不写) 为 透明到顶部

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        //添加Activity到 AppManager
        AppManager.getAppManager().addActivity(this);
        setStatusBar();

    }

    public void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }

        if (TopTouMing) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                        | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.parseColor("#37c067"));
                window.setNavigationBarColor(Color.TRANSPARENT);
            }

            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.main_person_top_bg);//通知栏所需颜色*/
        } else {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                        | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
                window.setNavigationBarColor(Color.TRANSPARENT);
            } else {
                SystemBarTintManager tintManager = new SystemBarTintManager(this);
                tintManager.setStatusBarTintEnabled(true);
                tintManager.setStatusBarTintDrawable(getResources().getDrawable(R.drawable.transparent));
            }
            //通知栏所需颜色*/
        }
    }


    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

}
