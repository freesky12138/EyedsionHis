package eyedsion.soft.eyedsionhis.tools;

import android.content.Context;
import android.widget.Toast;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * 配置文件
 * @author zengtao 2015年7月17日 上午11:47:44
 *
 */
public class Utils {
    /**
     * 格式化
     */
    private static DecimalFormat dfs = null;

    public static DecimalFormat format(String pattern) {
        if (dfs == null) {
            dfs = new DecimalFormat();
        }
        dfs.setRoundingMode(RoundingMode.FLOOR);
        dfs.applyPattern(pattern);
        return dfs;
    }


    public static String NoPointUtile(double d){
        int temp= (int) d;
        return String.valueOf(temp);
    }

    public static double ToWDouble(double d){
        DecimalFormat   df   =new   DecimalFormat("#.00");
        String temp=df.format(d);
        return Double.valueOf(temp);
    }
    public static double ToOneDouble(double d){
        DecimalFormat   df   =new   DecimalFormat("#.00");
        String temp=df.format(d);
        return Double.valueOf(temp);
    }

    public static String NoPointUtile(String d){
        if(d.contains(".")){
            float temp=Float.valueOf(d);
            int t= (int) temp;
            return String.valueOf(t);
        }
        return String.valueOf(d);
    }
    public static String NoPointUtile(float d){
        int temp= (int) d;
        return String.valueOf(temp);
    }



    public static void showLog(Context context, String info, int time){
        Toast.makeText(context,info,time);
    }

}