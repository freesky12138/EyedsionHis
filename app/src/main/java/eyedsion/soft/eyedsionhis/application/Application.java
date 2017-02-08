package eyedsion.soft.eyedsionhis.application;


/**
 * Created by Administrator on 23/01/2016.
 */
public class Application extends android.app.Application{

    public static Application application;
    @Override
    public void onCreate() {
        super.onCreate();
        application=this;
    }

    public static Application getApplication() {
        return application;
    }

    public static void setApplication(Application application) {
        Application.application = application;
    }
}
