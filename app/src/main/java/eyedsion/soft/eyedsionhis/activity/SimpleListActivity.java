package eyedsion.soft.eyedsionhis.activity;

import android.os.Bundle;

import eyedsion.soft.eyedsionhis.R;
import eyedsion.soft.eyedsionhis.adapter.SimpleAdapter;
import eyedsion.soft.eyedsionhis.application.Contant;
import eyedsion.soft.eyedsionhis.base.BaseListActivity;
import eyedsion.soft.eyedsionhis.bean.request.CollectionMessageV2Request;
import eyedsion.soft.eyedsionhis.bean.result.MessageDefaultV2Result;
import eyedsion.soft.eyedsionhis.tools.retrofit.HttpOnNextListener;
import eyedsion.soft.eyedsionhis.tools.retrofit.RetrofitManage;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/2/10.
 */

public class SimpleListActivity extends BaseListActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        adapter = new SimpleAdapter(this, R.layout.item_simple);
        setContentView(R.layout.activity_simple_layout);
        super.onCreate(savedInstanceState);

       getReferData();
    }

    @Override
    public void getReferData() {
        RequestBody body=RetrofitManage.object2Body(new CollectionMessageV2Request(3,indexPageNum,8));

        String sign=String.valueOf(3)+ String.valueOf(indexPageNum)+ String.valueOf(8);

        RetrofitManage.getInstance()
                .doHttpDeal(RetrofitManage.httpService.getError(Contant.GetCurrueTime(),body,sign), this, new HttpOnNextListener<MessageDefaultV2Result>() {
                    @Override
                    public void onNext(MessageDefaultV2Result movieEntity) {

                        setPullRefer(movieEntity.getDate());
                        swip_refersh_layout.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        swip_refersh_layout.setRefreshing(false);
                    }
                },false);
    }
}
