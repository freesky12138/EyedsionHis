package eyedsion.soft.eyedsionhis.bean.request;

/**
 * Created by Administrator on 2016/3/3.
 */
public class LoginPassRequest {
    private String Mobile;
    private String AuthCode;
    private String RegSource;
    private String PhoneType;
    private String DeviceToken;
    private String Password;

    public LoginPassRequest(String mobile, String authCode, String regSource, String phoneType, String driviceToken, String password) {
        Mobile = mobile;
        AuthCode = authCode;
        RegSource = regSource;
        PhoneType = phoneType;
        this.DeviceToken = driviceToken;
        Password = password;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getAuthCode() {
        return AuthCode;
    }

    public void setAuthCode(String authCode) {
        AuthCode = authCode;
    }

    public String getRegSource() {
        return RegSource;
    }

    public void setRegSource(String regSource) {
        RegSource = regSource;
    }

    public String getPhoneType() {
        return PhoneType;
    }

    public void setPhoneType(String phoneType) {
        PhoneType = phoneType;
    }

    public String getDeviceToken() {
        return DeviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        DeviceToken = deviceToken;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
