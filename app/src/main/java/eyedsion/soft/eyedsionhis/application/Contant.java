package eyedsion.soft.eyedsionhis.application;

/**
 * Created by Administrator on 2017/2/7.
 */

public class Contant {
    /**
     * 测试url
     */
    public static final String BASE_URL = "https://api.douban.com/v2/movie/";


    /**
     * 图片上传url
     */
    public static final String IMG_UPDATA_URL="http://115.29.7.200:10080/api/ufile/";


    public static String GetCurrueTime() {
        return String.valueOf(System.currentTimeMillis());
    }

}
