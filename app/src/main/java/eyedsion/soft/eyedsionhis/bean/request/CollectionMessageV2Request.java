package eyedsion.soft.eyedsionhis.bean.request;

/**
 * Created by Administrator on 2016/11/17.
 */

public class CollectionMessageV2Request {
    private int type;
    private int page;
    private int count;

    public CollectionMessageV2Request(int type, int page, int count) {
        this.type = type;
        this.page = page;
        this.count = count;
    }
}
