package eyedsion.soft.eyedsionhis.tools.retrofit;

import com.google.gson.Gson;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import eyedsion.soft.eyedsionhis.application.Session;
import eyedsion.soft.eyedsionhis.entity.ImgSubEntity;
import eyedsion.soft.eyedsionhis.server.FileService;
import eyedsion.soft.eyedsionhis.server.HttpService;
import eyedsion.soft.eyedsionhis.tools.ProgressSubscriber;
import eyedsion.soft.eyedsionhis.tools.ToastUtils;
import eyedsion.soft.eyedsionhis.tools.Utils;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static eyedsion.soft.eyedsionhis.application.Contant.BASE_URL;
import static eyedsion.soft.eyedsionhis.application.Contant.IMG_UPDATA_URL;

/**
 * Created by Administrator on 2017/2/7.
 */

public class RetrofitManage {
    private volatile static RetrofitManage INSTANCE;
    private static final int DEFAULT_TIMEOUT = 6;
    public static HttpService httpService;
    public static FileService fileService;

    public static RequestBody object2Body(Object object){

        String  bodyString=new Gson().toJson(object);
        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),bodyString);
        return body;
    }

    private RetrofitManage() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        /*builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response=chain.request().newBuilder().addHeader();

                return null;
            }
        });*/
        Interceptor requestInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();


                String sign=chain.request().headers().get("signtemp");
                if(sign==null){
                    Request compressedRequest = request.newBuilder()
                            .addHeader("sign", Utils.GetSign(chain.request().url().toString()))
                            .addHeader("Content-Type", "application/json")
                            .addHeader("usertoken", Session.GetString("usertoken"))
                            .build();
                    Response response = chain.proceed(compressedRequest);
                    return response;
                }else {
                    Request compressedRequest = request.newBuilder()
                            .addHeader("sign", Utils.GetSign(chain.request().url().toString(),sign))
                            .addHeader("Content-Type", "application/json")
                            .addHeader("usertoken", Session.GetString("usertoken"))
                            .build();
                    Response response = chain.proceed(compressedRequest);
                    return response;
                }

            }
        };

        builder.addInterceptor(requestInterceptor);
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        httpService = retrofit.create(HttpService.class);
    }

    /**
     *  加固定参数
     **/

    public static RetrofitManage getInstance() {
        if (INSTANCE == null) {
            synchronized (RetrofitManage.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RetrofitManage();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 处理http请求
     */
    public void doHttpDeal(Observable observable, RxAppCompatActivity rxAppCompatActivity, HttpOnNextListener listener,boolean IsShowDialog) {

        progressSubscriber = new ProgressSubscriber(listener, rxAppCompatActivity,IsShowDialog,false);


                /*失败后的retry配置*/
        observable
                .retryWhen(new RetryWhenNetworkException())
                /*生命周期管理*/
                .compose(rxAppCompatActivity.bindToLifecycle())
                /*http请求线程*/
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                /*回调线程*/
                .observeOn(AndroidSchedulers.mainThread())
                /*结果判断*/
                .subscribe(progressSubscriber);
        /*数据回调*/
        //observable.subscribe(progressSubscriber);
    }

    public void doFileDeal(String imgPath,Callback callback){
        File file=new File(imgPath);
        if(!file.exists()){
            ToastUtils.show("图片上传失败");
            return;
        }

        RequestBody requestBody=RequestBody.create(MediaType.parse("multipart/form-data"),file);

        MultipartBody.Part body=MultipartBody.Part.createFormData("head",file.getName(),requestBody);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(IMG_UPDATA_URL)
                .build();

        fileService = retrofit.create(FileService.class);


        Call<ImgSubEntity> call= fileService.upload(body);
        call.enqueue(callback);
    }

    protected ProgressSubscriber progressSubscriber;
}
