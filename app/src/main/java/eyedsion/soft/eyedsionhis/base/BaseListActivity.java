package eyedsion.soft.eyedsionhis.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import eyedsion.soft.eyedsionhis.R;
import eyedsion.soft.eyedsionhis.adapter.RecycleAdapter.DividerItemDecoration;
import eyedsion.soft.eyedsionhis.adapter.RecycleAdapter.LoadMoreWrapper;
import eyedsion.soft.eyedsionhis.adapter.RecycleAdapter.MultiItemTypeAdapter;
import eyedsion.soft.eyedsionhis.widget.EmptyRecycleView;

/**
 * Created by Administrator on 2017/2/10.
 */

public class BaseListActivity extends BaseActivity{
    public EmptyRecycleView pullToRefreshView;

    protected View no_data_view;
    protected View no_net_view;

    //通用的adapter
    public MultiItemTypeAdapter adapter;

    //页码num
    public int indexPageNum = 1;

    //下拉刷新控件
    public SwipeRefreshLayout swip_refersh_layout;

    //是否需要分割线
    public boolean isDev = true;

    //如果没有data之后就不需要
    private boolean isNoData = false;

    public LoadMoreWrapper mLoadMoreWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        settingRefershView();
    }


    //设置下拉刷新
    private void settingRefershView() {
        swip_refersh_layout = (SwipeRefreshLayout) findViewById(R.id.swip_refersh_layout);

        pullToRefreshView = (EmptyRecycleView) findViewById(R.id.pull_to_refresh_list_view);

        pullToRefreshView.setEmptyView(findViewById(R.id.recycle_no_data));
        pullToRefreshView.setNoNetView(findViewById(R.id.recycle_no_net));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        pullToRefreshView.setLayoutManager(linearLayoutManager);

        swip_refersh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                indexPageNum = 1;
                getReferData();
            }
        });
        swip_refersh_layout.setColorSchemeColors(Color.parseColor("#37c067"));

        //initPullRefer();

        pullToRefreshView.setAdapter(adapter);

        if (isDev){
            pullToRefreshView.addItemDecoration(new DividerItemDecoration(this,
                    DividerItemDecoration.VERTICAL_LIST));
        }
    }
    //上拉下拉刷新必须继承这个类实现
    public void getReferData() {
    }


    public void setPullRefer(List list) {
        if(mLoadMoreWrapper==null){
            initPullRefer();
        }

        // 针对一开始就没有数据
        if(list.size()==0){
            mLoadMoreWrapper.setLoadMoreView(R.layout.recycle_bottom_no_more);
        }

        if (list.size() != 0) {
            if (indexPageNum == 1) {
                if (adapter != null && adapter.getDatas() != null) {
                    adapter.getDatas().clear();
                }
                adapter.notifyDataSetChanged(list);
                mLoadMoreWrapper.notifyDataSetChanged();
            } else {
                adapter.getDatas().addAll(list);
                mLoadMoreWrapper.notifyDataSetChanged();
            }
            indexPageNum++;
        } else {
            if (isNoData == false) {
                Toast.makeText(BaseListActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
                isNoData = true;

                mLoadMoreWrapper.notifyDataSetChanged();

                // 针对最终没有数据
                if (mLoadMoreWrapper != null && mLoadMoreWrapper.getViewHolder()!=null){
                    TextView textView= mLoadMoreWrapper.getViewHolder().getView(R.id.recycle_bottom_load_more);
                    textView.setText("没有更多数据了");
                }

                mLoadMoreWrapper.notifyDataSetChanged();
                adapter.notifyDataSetChanged();

            }
        }
        swip_refersh_layout.setRefreshing(false);
    }
    public void initPullRefer() {
        mLoadMoreWrapper = new LoadMoreWrapper(adapter);
        mLoadMoreWrapper.setLoadMoreView(R.layout.recycle_bottom_load_more);

        mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getReferData();
            }
        });

        pullToRefreshView.setAdapter(mLoadMoreWrapper);

    }

}
