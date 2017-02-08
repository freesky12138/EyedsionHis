package eyedsion.soft.eyedsionhis.tools;

import android.widget.Toast;

import eyedsion.soft.eyedsionhis.application.Application;


/**
 * Created by Administrator on 2016/11/17.
 */

public class ToastUtils {

    private static Toast toast;
    public static void show(String data){
        if (toast == null) {
            toast = Toast.makeText(Application.getApplication(), data, Toast.LENGTH_LONG);
        } else {
            toast.setText(data);
        }
        toast.show();

    }
}
