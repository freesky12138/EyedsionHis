package eyedsion.soft.eyedsionhis.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.zhy.autolayout.utils.ScreenUtils;

import java.util.Timer;
import java.util.TimerTask;

import eyedsion.soft.eyedsionhis.R;

/**
 * Created by Huppert on 2016/9/21.
 * 带进度条的webview
 */
public class ProgressWebView extends WebView{

    private ProgressBar progressbar;

    public ProgressWebView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        progressbar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        progressbar.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, ScreenUtils.getScreenSize(context,true)[1]/220, 0, 0));

        Drawable drawable=getResources().getDrawable(R.drawable.web_view_progress);

        progressbar.setProgressDrawable(drawable);
        addView(progressbar);
        //        setWebViewClient(new WebViewClient(){});
        setWebChromeClient(new WebChromeClient());


        final Handler handler=new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                progressbar.setVisibility(GONE);
                return true;
            }
        });

        final TimerTask task = new TimerTask(){
            public void run() {
                if(curryP<progress){
                    curryP++;
                    progressbar.setProgress(curryP);
                }
                if(curryP==100){
                    handler.sendMessage(new Message());
                }

            }
        };

        Timer timer = new Timer(true);
        timer.schedule(task,0, 6);

    }

    int curryP=0;
    int progress=0;

    public class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            progress=newProgress;

          /*  if (newProgress == 100) {
                progressbar.setVisibility(GONE);
            } else {
                if (progressbar.getVisibility() == GONE)
                    progressbar.setVisibility(VISIBLE);
                progressbar.setProgress(newProgress);
            }*/
            super.onProgressChanged(view, newProgress);
        }

    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        LayoutParams lp = (LayoutParams) progressbar.getLayoutParams();
        lp.x = l;
        lp.y = t;
        progressbar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }
}
