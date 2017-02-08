package eyedsion.soft.eyedsionhis.tools.retrofit;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.concurrent.TimeUnit;

import eyedsion.soft.eyedsionhis.server.HttpService;
import eyedsion.soft.eyedsionhis.tools.ProgressSubscriber;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static eyedsion.soft.eyedsionhis.tools.contant.BASE_URL;

/**
 * Created by Administrator on 2017/2/7.
 */

public class RetrofitManage {
    private volatile static RetrofitManage INSTANCE;
    private static final int DEFAULT_TIMEOUT = 6;
    public static HttpService httpService;

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
        /*Interceptor requestInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                Request compressedRequest = request.newBuilder()
                        .header("Cookie", cookie)
                        .header("Accept-Language", Locale.getDefault().toString())
                        .header("Accept-Charset", "utf-8")
                        .header("Connection", "Keep-Alive")
                        .header("User-Agent", getUserAgent())
                        .build();
                Response response = chain.proceed(compressedRequest);
                return response;
            }
        };

        builder.addInterceptor()*/
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        httpService = retrofit.create(HttpService.class);
    }

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
    public void doHttpDeal(Observable observable, RxAppCompatActivity rxAppCompatActivity, HttpOnNextListener listener) {

        progressSubscriber = new ProgressSubscriber(listener, rxAppCompatActivity);

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

    protected ProgressSubscriber progressSubscriber;
}
