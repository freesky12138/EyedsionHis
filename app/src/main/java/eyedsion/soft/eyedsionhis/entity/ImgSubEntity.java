package eyedsion.soft.eyedsionhis.entity;

/**
 * Created by Administrator on 2017/2/8.
 */

public class ImgSubEntity {


    /**
     * state : 1
     * access_url : http://head-10026190.file.myqcloud.com/ac4b5495-f367-4204-8c86-484ad2ce7ce5.jpg
     */

    private int state;
    private String access_url;


    public void setState(int state) {
        this.state = state;
    }

    public void setAccess_url(String access_url) {
        this.access_url = access_url;
    }

    public int getState() {
        return state;
    }

    public String getAccess_url() {
        return access_url;
    }
}
