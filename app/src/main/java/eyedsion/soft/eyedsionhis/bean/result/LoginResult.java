package eyedsion.soft.eyedsionhis.bean.result;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

/**
 * Created by Administrator on 2016/2/29.
 */
public class LoginResult {


    /**
     * result : 1
     * mobile : 13699419545
     * errcode : 0
     * errormsg :
     * usertoken : 44112ff7-727b-49d5-babd-b312a3bb70a3
     * Birthday : 1983-12-23T00:00:00
     * Email : ownxy@163.com
     * HeadImageUrl : http://head-10026190.file.myqcloud.com/e6e7db8d-f44e-4b66-9181-bfcbae8d17af.jpg
     * NickName : 徐洋
     * RegDate : 2016-07-13T11:26:00.817
     * RegPhone : 13699419545
     * RegSource : app
     * Sex : true
     * UID : 100303
     * UName : 徐洋
     * IsDoctor : 1
     * StaffName : 徐洋
     * TitleCode : 2
     * voipaccount : 8011395400000173
     * voippwd : BFpU7I41
     */

    private int result;
    private String mobile;
    private int errcode;
    private String errormsg;
    private String usertoken;
    private String Birthday;
    private String Email;
    private String HeadImageUrl;
    private String NickName;
    private String RegDate;
    private String RegPhone;
    private String RegSource;
    private boolean Sex;
    private int UID;
    private String UName;
    private int IsDoctor;
    private String StaffName;
    private String TitleCode;
    private String voipaccount;
    private String voippwd;
    private String TitleCodeName;

    public LoginResult parse(String result) {

        try {
            return JSON.parseObject(result, LoginResult.class);
        }catch (JSONException e){
            return new LoginResult();
        }

    }

    public String getTitleCodeName() {
        return TitleCodeName;
    }

    public void setTitleCodeName(String titleCodeName) {
        TitleCodeName = titleCodeName;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getUsertoken() {
        return usertoken;
    }

    public void setUsertoken(String usertoken) {
        this.usertoken = usertoken;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String Birthday) {
        this.Birthday = Birthday;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getHeadImageUrl() {
        return HeadImageUrl;
    }

    public void setHeadImageUrl(String HeadImageUrl) {
        this.HeadImageUrl = HeadImageUrl;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String NickName) {
        this.NickName = NickName;
    }

    public String getRegDate() {
        return RegDate;
    }

    public void setRegDate(String RegDate) {
        this.RegDate = RegDate;
    }

    public String getRegPhone() {
        return RegPhone;
    }

    public void setRegPhone(String RegPhone) {
        this.RegPhone = RegPhone;
    }

    public String getRegSource() {
        return RegSource;
    }

    public void setRegSource(String RegSource) {
        this.RegSource = RegSource;
    }

    public boolean isSex() {
        return Sex;
    }

    public void setSex(boolean Sex) {
        this.Sex = Sex;
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public String getUName() {
        return UName;
    }

    public void setUName(String UName) {
        this.UName = UName;
    }

    public int getIsDoctor() {
        return IsDoctor;
    }

    public void setIsDoctor(int IsDoctor) {
        this.IsDoctor = IsDoctor;
    }

    public String getStaffName() {
        return StaffName;
    }

    public void setStaffName(String StaffName) {
        this.StaffName = StaffName;
    }

    public String getTitleCode() {
        return TitleCode;
    }

    public void setTitleCode(String TitleCode) {
        this.TitleCode = TitleCode;
    }

    public String getVoipaccount() {
        return voipaccount;
    }

    public void setVoipaccount(String voipaccount) {
        this.voipaccount = voipaccount;
    }

    public String getVoippwd() {
        return voippwd;
    }

    public void setVoippwd(String voippwd) {
        this.voippwd = voippwd;
    }
}
