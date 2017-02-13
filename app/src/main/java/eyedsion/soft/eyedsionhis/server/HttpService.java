package eyedsion.soft.eyedsionhis.server;

import eyedsion.soft.eyedsionhis.bean.result.LoginResult;
import eyedsion.soft.eyedsionhis.bean.result.MessageDefaultV2Result;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import rx.Observable;
/**
 * Created by Hyppert on 2017/2/7.
 */

public interface HttpService {


    @PUT("user/PasswordLogin")
    Observable<LoginResult> login(@Query("timestamp") String timestamp, @Body RequestBody body);

    @POST("MessageV2/AllMessage")
    Observable<MessageDefaultV2Result> getError(@Query("timestamp") String timestamp, @Body RequestBody body,@Header("signtemp") String  sign);
}
