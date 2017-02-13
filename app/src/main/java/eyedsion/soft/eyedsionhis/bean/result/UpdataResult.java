package eyedsion.soft.eyedsionhis.bean.result;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/28.
 */
public class UpdataResult {


    /**
     * result : 1
     * errcode : 0
     * errormsg :
     * date : [{"Vid":1,"VersionId":3,"VersionNo":"1.0","FileName":"ajax-loader.apk","FileSize":"11.3MB","UpdateUrl":"http://r.eyedsion.com:10086/VersionUp/GetFile","UpdateInfo":"更新了版本资料","LastForce":1,"SendTime":"2016-06-28T00:00:00"}]
     */

    private int result;
    private int errcode;
    private String errormsg;
    /**
     * Vid : 1
     * VersionId : 3
     * VersionNo : 1.0
     * FileName : ajax-loader.apk
     * FileSize : 11.3MB
     * UpdateUrl : http://r.eyedsion.com:10086/VersionUp/GetFile
     * UpdateInfo : 更新了版本资料
     * LastForce : 1
     * SendTime : 2016-06-28T00:00:00
     */

    private List<DateEntity> date;

    public static Object parse(String result){
        try {
            JSONObject jsonObject= JSON.parseObject(result);
            JSONArray jsonData=jsonObject.getJSONArray("date");
            if(jsonData!=null){
                return JSON.parseArray(jsonData.toJSONString(), DateEntity.class);
            }
            else {
                return new ArrayList<>();
            }
        }catch (JSONException e){
            return new ArrayList<>();
        }

    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrormsg() {
        return errormsg;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }

    public List<DateEntity> getDate() {
        return date;
    }

    public void setDate(List<DateEntity> date) {
        this.date = date;
    }


    public static class DateEntity {
        private int Vid;
        private int VersionId;
        private String VersionNo;
        private String FileName;
        private String FileSize;
        private String UpdateUrl;
        private String UpdateInfo;
        private int LastForce;
        private String SendTime;

        public int getVid() {
            return Vid;
        }

        public void setVid(int Vid) {
            this.Vid = Vid;
        }

        public int getVersionId() {
            return VersionId;
        }

        public void setVersionId(int VersionId) {
            this.VersionId = VersionId;
        }

        public String getVersionNo() {
            return VersionNo;
        }

        public void setVersionNo(String VersionNo) {
            this.VersionNo = VersionNo;
        }

        public String getFileName() {
            return FileName;
        }

        public void setFileName(String FileName) {
            this.FileName = FileName;
        }

        public String getFileSize() {
            return FileSize;
        }

        public void setFileSize(String FileSize) {
            this.FileSize = FileSize;
        }

        public String getUpdateUrl() {
            return UpdateUrl;
        }

        public void setUpdateUrl(String UpdateUrl) {
            this.UpdateUrl = UpdateUrl;
        }

        public String getUpdateInfo() {
            return UpdateInfo;
        }

        public void setUpdateInfo(String UpdateInfo) {
            this.UpdateInfo = UpdateInfo;
        }

        public int getLastForce() {
            return LastForce;
        }

        public void setLastForce(int LastForce) {
            this.LastForce = LastForce;
        }

        public String getSendTime() {
            return SendTime;
        }

        public void setSendTime(String SendTime) {
            this.SendTime = SendTime;
        }
    }
}
