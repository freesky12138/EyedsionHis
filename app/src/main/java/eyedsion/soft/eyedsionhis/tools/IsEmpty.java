package eyedsion.soft.eyedsionhis.tools;

import android.widget.EditText;

/**
 * Created by Administrator on 16/02/2016.
 * Describe : 对是否为空进行判断
 */
public class IsEmpty {
    /**
     * 判断EditText是否为空
     */
    public static boolean EditIsEmpty(EditText editText){
        if(editText==null){
            return true;
        }
        if("".equals(editText.getText().toString())){
            return true;
        }
        return false;
    }
}
