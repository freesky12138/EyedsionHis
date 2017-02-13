package eyedsion.soft.eyedsionhis.bean.result;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

import eyedsion.soft.eyedsionhis.bean.BaseResult;

/**
 * Created by Administrator on 2016/11/17.
 */

public class MessageDefaultV2Result implements BaseResult{


    /**
     * result : 1
     * errcode : 0
     * errormsg :
     * date : [{"Mtype":"4","Mcontent":"你娃该吃屎啦","IsRead":false,"Uid":100242,"SendDate":"2016-11-16T00:00:00","MSort":1,"MStatus":true,"ApplyId":0,"Remark":"","IsExpired":false,"UName":"","NickName":"","HeadImageUrl":"","ShareId":0,"ApplyStateCode":"","PersonId":0,"SharePhone":"","ShareDate":"2016-11-17T20:47:08.2526314","ExpiredDate":"2016-11-17T20:47:08.2526314","ShareResult":"","PersonImage":"","PersionName":"","RegPhone":"","WId":0,"RecStatus":"","WarningValue":"","WarnningDate":"2016-11-17T20:47:08.2526314","WarnningId":"","WarnningType":"","HeaderImgUrl":"","PersonName":""}]
     */

    private int result;
    private int errcode;
    private String errormsg;
    /**
     * Mtype : 4
     * Mcontent : 你娃该吃屎啦
     * IsRead : false
     * Uid : 100242
     * SendDate : 2016-11-16T00:00:00
     * MSort : 1
     * MStatus : true
     * ApplyId : 0
     * Remark :
     * IsExpired : false
     * UName :
     * NickName :
     * HeadImageUrl :
     * ShareId : 0
     * ApplyStateCode :
     * PersonId : 0
     * SharePhone :
     * ShareDate : 2016-11-17T20:47:08.2526314
     * ExpiredDate : 2016-11-17T20:47:08.2526314
     * ShareResult :
     * PersonImage :
     * PersionName :
     * RegPhone :
     * WId : 0
     * RecStatus :
     * WarningValue :
     * WarnningDate : 2016-11-17T20:47:08.2526314
     * WarnningId :
     * WarnningType :
     * HeaderImgUrl :
     * PersonName :
     */

    private List<DateEntity> date;

    public static Object parse(String result){
        try {
            JSONObject jsonObject= JSON.parseObject(result);
            JSONArray jsonData=jsonObject.getJSONArray("date");
            if(jsonData==null){
                return new ArrayList<>();
            }
            else {
                return JSON.parseArray(jsonData.toJSONString(), DateEntity.class);
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
        private String Mtype;
        private String Mcontent;
        private boolean IsRead;
        private int Uid;
        private String SendDate;
        private int MSort;
        private boolean MStatus;
        private int ApplyId;
        private String Remark;
        private boolean IsExpired;
        private String UName;
        private String NickName;
        private String HeadImageUrl;
        private int ShareId;
        private String ApplyStateCode;
        private int PersonId;
        private String SharePhone;
        private String ShareDate;
        private String ExpiredDate;
        private String ShareResult;
        private String PersonImage;
        private String PersionName;
        private String RegPhone;
        private int WId;
        private String RecStatus;
        private String WarningValue;
        private String WarnningDate;
        private String WarnningId;
        private String WarnningType;
        private String HeaderImgUrl;
        private String PersonName;
        private String IsOperation;

        public boolean isRead() {
            return IsRead;
        }

        public void setRead(boolean read) {
            IsRead = read;
        }

        public boolean isExpired() {
            return IsExpired;
        }

        public void setExpired(boolean expired) {
            IsExpired = expired;
        }

        public String getIsOperation() {
            return IsOperation;
        }

        public void setIsOperation(String isOperation) {
            IsOperation = isOperation;
        }

        public String getMtype() {
            return Mtype;
        }

        public void setMtype(String Mtype) {
            this.Mtype = Mtype;
        }

        public String getMcontent() {
            return Mcontent;
        }

        public void setMcontent(String Mcontent) {
            this.Mcontent = Mcontent;
        }

        public boolean isIsRead() {
            return IsRead;
        }

        public void setIsRead(boolean IsRead) {
            this.IsRead = IsRead;
        }

        public int getUid() {
            return Uid;
        }

        public void setUid(int Uid) {
            this.Uid = Uid;
        }

        public String getSendDate() {
            return SendDate;
        }

        public void setSendDate(String SendDate) {
            this.SendDate = SendDate;
        }

        public int getMSort() {
            return MSort;
        }

        public void setMSort(int MSort) {
            this.MSort = MSort;
        }

        public boolean isMStatus() {
            return MStatus;
        }

        public void setMStatus(boolean MStatus) {
            this.MStatus = MStatus;
        }

        public int getApplyId() {
            return ApplyId;
        }

        public void setApplyId(int ApplyId) {
            this.ApplyId = ApplyId;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }

        public boolean isIsExpired() {
            return IsExpired;
        }

        public void setIsExpired(boolean IsExpired) {
            this.IsExpired = IsExpired;
        }

        public String getUName() {
            return UName;
        }

        public void setUName(String UName) {
            this.UName = UName;
        }

        public String getNickName() {
            return NickName;
        }

        public void setNickName(String NickName) {
            this.NickName = NickName;
        }

        public String getHeadImageUrl() {
            return HeadImageUrl;
        }

        public void setHeadImageUrl(String HeadImageUrl) {
            this.HeadImageUrl = HeadImageUrl;
        }

        public int getShareId() {
            return ShareId;
        }

        public void setShareId(int ShareId) {
            this.ShareId = ShareId;
        }

        public String getApplyStateCode() {
            return ApplyStateCode;
        }

        public void setApplyStateCode(String ApplyStateCode) {
            this.ApplyStateCode = ApplyStateCode;
        }

        public int getPersonId() {
            return PersonId;
        }

        public void setPersonId(int PersonId) {
            this.PersonId = PersonId;
        }

        public String getSharePhone() {
            return SharePhone;
        }

        public void setSharePhone(String SharePhone) {
            this.SharePhone = SharePhone;
        }

        public String getShareDate() {
            return ShareDate;
        }

        public void setShareDate(String ShareDate) {
            this.ShareDate = ShareDate;
        }

        public String getExpiredDate() {
            return ExpiredDate;
        }

        public void setExpiredDate(String ExpiredDate) {
            this.ExpiredDate = ExpiredDate;
        }

        public String getShareResult() {
            return ShareResult;
        }

        public void setShareResult(String ShareResult) {
            this.ShareResult = ShareResult;
        }

        public String getPersonImage() {
            return PersonImage;
        }

        public void setPersonImage(String PersonImage) {
            this.PersonImage = PersonImage;
        }

        public String getPersionName() {
            return PersionName;
        }

        public void setPersionName(String PersionName) {
            this.PersionName = PersionName;
        }

        public String getRegPhone() {
            return RegPhone;
        }

        public void setRegPhone(String RegPhone) {
            this.RegPhone = RegPhone;
        }

        public int getWId() {
            return WId;
        }

        public void setWId(int WId) {
            this.WId = WId;
        }

        public String getRecStatus() {
            return RecStatus;
        }

        public void setRecStatus(String RecStatus) {
            this.RecStatus = RecStatus;
        }

        public String getWarningValue() {
            return WarningValue;
        }

        public void setWarningValue(String WarningValue) {
            this.WarningValue = WarningValue;
        }

        public String getWarnningDate() {
            return WarnningDate;
        }

        public void setWarnningDate(String WarnningDate) {
            this.WarnningDate = WarnningDate;
        }

        public String getWarnningId() {
            return WarnningId;
        }

        public void setWarnningId(String WarnningId) {
            this.WarnningId = WarnningId;
        }

        public String getWarnningType() {
            return WarnningType;
        }

        public void setWarnningType(String WarnningType) {
            this.WarnningType = WarnningType;
        }

        public String getHeaderImgUrl() {
            return HeaderImgUrl;
        }

        public void setHeaderImgUrl(String HeaderImgUrl) {
            this.HeaderImgUrl = HeaderImgUrl;
        }

        public String getPersonName() {
            return PersonName;
        }

        public void setPersonName(String PersonName) {
            this.PersonName = PersonName;
        }
    }
}
