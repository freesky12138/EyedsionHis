package eyedsion.soft.eyedsionhis.tools;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import eyedsion.soft.eyedsionhis.R;

/**
 * Created by Administrator on 2017/2/6.
 */

public class ImageTools {

    private static void GlideUrlImg(ImageView imageView, String url, Context context, int comRes) {
        Glide
                .with(context)
                .load(url)
                .error(comRes)//加载错误显示
                /*.dontAnimate()//不要淡入淡出效果*/
                .into(imageView);
    }

    private static void GlideLocalImg(ImageView imageView, int resourceId, Context context) {
        Glide
                .with(context)
                .load(resourceId)
                .crossFade()//淡入淡出效果
                .into(imageView);
    }

    public static void ClearIma(Context context){
        Glide.get(context).clearMemory();
    }

    public static void setHeaderByUrl(ImageView view, String url, Context context) {
        if (url == null) {
            view.setBackgroundResource(R.drawable.head_bg);
        }
        GlideUrlImg(view, url, context, R.drawable.head_bg);

    }


    public static void setBackByUrl(ImageView view, final String url, Context context) {

        if (url == null) {
            view.setBackgroundResource(R.drawable.collect_bg);
        }

        GlideUrlImg(view, url, context, R.drawable.collect_bg);
    }
}
