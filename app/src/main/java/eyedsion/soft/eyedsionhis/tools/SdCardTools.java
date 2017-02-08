package eyedsion.soft.eyedsionhis.tools;

import android.content.Context;
import android.os.Environment;

/**
*
*
* @ClassName SettingDetailActivity
*/
public class SdCardTools {
	/**
	 * 检查是否存在SDCard
	 * @return
	 */
	public static boolean hasSdcard(){
		String state = Environment.getExternalStorageState();
		if(state.equals(Environment.MEDIA_MOUNTED)){
			return true;
		}else{
			return false;
		}
	}

	public static String getAbsolutePath(Context context) {
		return context.getApplicationContext().getFilesDir().getAbsolutePath();
	}
}
