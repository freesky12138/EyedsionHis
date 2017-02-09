package eyedsion.soft.eyedsionhis.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eyedsion.soft.eyedsionhis.R;
import eyedsion.soft.eyedsionhis.entity.MovieEntity;
import eyedsion.soft.eyedsionhis.tools.CameraUtils;
import eyedsion.soft.eyedsionhis.tools.retrofit.HttpOnNextListener;
import eyedsion.soft.eyedsionhis.tools.retrofit.RetrofitManage;
import eyedsion.soft.eyedsionhis.widget.DialogFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static eyedsion.soft.eyedsionhis.tools.SdCardTools.hasSdcard;

public class MainActivity extends RxAppCompatActivity {

    @BindView(R.id.hellow)
    TextView hellow;
    @BindView(R.id.get_data)
    Button getData;
    @BindView(R.id.ima_sub)
    Button imaSub;
    @BindView(R.id.take)
    Button take;
    @BindView(R.id.image)
    ImageView image;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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
    public void Move(View v){
        CameraUtils.getCameraUtils().chooseImg(this);
    }

    @OnClick(R.id.get_data)
    public void onClick() {

        RetrofitManage.getInstance()
                .doHttpDeal(RetrofitManage.httpService.getTopMovie(0, 10), this, new HttpOnNextListener<MovieEntity>() {
                    @Override
                    public void onNext(MovieEntity movieEntity) {
                        hellow.setText(movieEntity.getSubjects().get(0).getTitle());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);

                    }
                });
    }


    @OnClick(R.id.ima_sub)
    public void subImg() {
        CameraUtils.openCamera(this);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(CameraUtils.getCameraUtils().OnResult(this,requestCode,resultCode,data)){

        }else {

        }

    }


}
