package eyedsion.soft.eyedsionhis.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eyedsion.soft.eyedsionhis.R;
import eyedsion.soft.eyedsionhis.application.Contant;
import eyedsion.soft.eyedsionhis.application.Session;
import eyedsion.soft.eyedsionhis.base.BaseActivity;
import eyedsion.soft.eyedsionhis.bean.request.LoginPassRequest;
import eyedsion.soft.eyedsionhis.bean.result.LoginResult;
import eyedsion.soft.eyedsionhis.tools.CameraUtils;
import eyedsion.soft.eyedsionhis.tools.Md5Tolls;
import eyedsion.soft.eyedsionhis.tools.ToastUtils;
import eyedsion.soft.eyedsionhis.tools.retrofit.HttpOnNextListener;
import eyedsion.soft.eyedsionhis.tools.retrofit.RetrofitManage;
import eyedsion.soft.eyedsionhis.widget.DialogFactory;
import okhttp3.RequestBody;


public class MainActivity extends BaseActivity {

    @BindView(R.id.hellow)
    TextView hellow;
    @BindView(R.id.get_data)
    Button getData;
    @BindView(R.id.ima_sub)
    Button imaSub;
    @BindView(R.id.take)
    Button take;
    @BindView(R.id.to_list)
    Button toList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);

        DialogFactory.showDeleteDialog(this, "是否删除", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @OnClick(R.id.take)
    public void Take() {
        CameraUtils.getCameraUtils().openCamera(this);

    }

    @OnClick(R.id.move)
    public void Move(View v) {
        CameraUtils.getCameraUtils().chooseImg(this);
    }

    @OnClick(R.id.get_data)
    public void onClick() {

        String passMd5 = Md5Tolls.encrypt("123456");

        RequestBody body=RetrofitManage.object2Body(new LoginPassRequest("18398609020", null, "app", "Android", Session.GetString("userId") + "," + Session.GetString("channelId"), passMd5));

        RetrofitManage.getInstance()
                .doHttpDeal(RetrofitManage.httpService.login(Contant.GetCurrueTime(),body), this, new HttpOnNextListener<LoginResult>() {
                    @Override
                    public void onNext(LoginResult loginResult) {
                        ToastUtils.show(loginResult.getNickName());
                        Session.SetString("usertoken",loginResult.getUsertoken());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);

                    }
                },true);
    }


    @OnClick(R.id.ima_sub)
    public void subImg() {
        CameraUtils.openCamera(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (CameraUtils.getCameraUtils().OnResult(this, requestCode, resultCode, data)) {

        } else {

        }

    }


    @OnClick(R.id.to_list)
    public void toList() {
        Intent intent=new Intent(this,SimpleListActivity.class);
        startActivity(intent);
    }
}
