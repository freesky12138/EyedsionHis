package eyedsion.soft.eyedsionhis.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

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
    @BindView(R.id.show_delete_dialog)
    Button showDeleteDialog;
    @BindView(R.id.show_choose2_dialog)
    Button showChoose2Dialog;
    @BindView(R.id.show_choose2_desc_dialog)
    Button showChoose2DescDialog;
    @BindView(R.id.show_choose_only_dialog)
    Button showChooseOnlyDialog;
    @BindView(R.id.show_edit_dialog)
    Button showEditDialog;
    @BindView(R.id.show_choose_value_dialog)
    Button showChooseValueDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);


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

        RequestBody body = RetrofitManage.object2Body(new LoginPassRequest("18398609020", null, "app", "Android", Session.GetString("userId") + "," + Session.GetString("channelId"), passMd5));

        RetrofitManage.getInstance()
                .doHttpDeal(RetrofitManage.httpService.login(Contant.GetCurrueTime(), body), this, new HttpOnNextListener<LoginResult>() {
                    @Override
                    public void onNext(LoginResult loginResult) {
                        ToastUtils.show(loginResult.getNickName());
                        Session.SetString("usertoken", loginResult.getUsertoken());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);

                    }
                }, true);
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
        Intent intent = new Intent(this, SimpleListActivity.class);
        startActivity(intent);
    }

    @OnClick({R.id.show_delete_dialog, R.id.show_choose2_dialog, R.id.show_choose2_desc_dialog, R.id.show_choose_only_dialog, R.id.show_edit_dialog, R.id.show_choose_value_dialog})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.show_delete_dialog:
                DialogFactory.showDeleteDialog(this, "删除", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DialogFactory.dismiss();
                        ToastUtils.show("删除");
                    }
                });
                break;
            case R.id.show_choose2_dialog:
                DialogFactory.showChooseNoDesc(this, "choose2", "left", "right", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DialogFactory.dismiss();
                        ToastUtils.show("Left");
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DialogFactory.dismiss();
                        ToastUtils.show("Right");
                    }
                });
                break;
            case R.id.show_choose2_desc_dialog:

                DialogFactory.showChooseDesc(this, "choose2","left", "desc", "right", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DialogFactory.dismiss();
                        ToastUtils.show("Left");
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DialogFactory.dismiss();
                        ToastUtils.show("Right");
                    }
                });
                break;
            case R.id.show_choose_only_dialog:
                ArrayList<String> strings=new ArrayList<>();
                strings.add("111");
                strings.add("222");
                strings.add("333");
                DialogFactory.showChooseOnly(this, "choose_only", strings, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        ToastUtils.show(i+"");
                        DialogFactory.dismiss();
                    }
                });
                break;
            case R.id.show_edit_dialog:
                DialogFactory.showEditDialog(this, "edit", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ToastUtils.show(DialogFactory.EditDialogDetal);
                    }
                });
                break;
            case R.id.show_choose_value_dialog:
                ArrayList<String> string1=new ArrayList<>();
                string1.add("111");
                string1.add("222");
                string1.add("333");

                ArrayList<String> string2=new ArrayList<>();
                string2.add("111");
                string2.add("222");
                string2.add("333");
                DialogFactory.showChooseValue(this, string1, string2, "danwei", "title", new DialogFactory.valueReturn() {
                    @Override
                    public void onResult(int res) {

                    }

                    @Override
                    public void onResult2(int res1, int res2) {
                        ToastUtils.show(res1+"-"+res2);
                    }
                });
                break;
        }
    }
}
