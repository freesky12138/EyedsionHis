package eyedsion.soft.eyedsionhis.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eyedsion.soft.eyedsionhis.R;
import eyedsion.soft.eyedsionhis.entity.MovieEntity;
import eyedsion.soft.eyedsionhis.tools.retrofit.HttpOnNextListener;
import eyedsion.soft.eyedsionhis.tools.retrofit.RetrofitManage;

public class MainActivity extends RxAppCompatActivity {

    @BindView(R.id.hellow)
    TextView hellow;
    @BindView(R.id.get_data)
    Button getData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        hellow.setText("啦啦");
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


}
