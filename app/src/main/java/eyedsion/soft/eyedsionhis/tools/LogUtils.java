package eyedsion.soft.eyedsionhis.tools;

import android.util.Log;

/**
 * Created by Administrator on 2016/9/9.
 */
public class LogUtils {

    private static boolean isShow=true;
    public  static void showLog(String tag,String msg){
        if(isShow)
            Log.e(tag,msg);
    }
}
