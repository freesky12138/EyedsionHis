package eyedsion.soft.eyedsionhis.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import eyedsion.soft.eyedsionhis.R;
import eyedsion.soft.eyedsionhis.base.BaseListActivity;
import eyedsion.soft.eyedsionhis.tools.IsNetConnect;

/**
 * Created by Administrator on 2017/2/13.
 */

public class EmptyRecycleView extends RecyclerView{

    final private AdapterDataObserver observer = new AdapterDataObserver() {
        @Override
        public void onChanged() {
          checkEmpty();

          //  checkIfEmpty();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            checkEmpty();
            //checkIfEmpty();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            checkEmpty();
            //checkIfEmpty();
        }
    };

    private View EmptyView=null;
    private View  NoNetView=null;
    private TextView try_again;

    public View getEmptyView() {
        return EmptyView;
    }

    public void setEmptyView(View emptyView) {
        EmptyView = emptyView;
    }

    public View getNoNetView() {
        return NoNetView;
    }

    public void setNoNetView(View noNetView) {
        NoNetView = noNetView;
    }

    private void checkEmpty() {
        if(this.getAdapter()!=null && this.getAdapter().getItemCount()==1){

            if(IsNetConnect.isConnect(getContext())){
                EmptyView.setVisibility(VISIBLE);
            }else {
                NoNetView.setVisibility(VISIBLE);
                try_again= (TextView) NoNetView.findViewById(R.id.try_again);
                try_again.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((BaseListActivity)EmptyView.getContext()).getReferData();
                        checkEmpty();
                    }
                });
            }
            this.setVisibility(GONE);

        }else {
            if(EmptyView!=null){
                EmptyView.setVisibility(GONE);

            }
            if(NoNetView!=null){
                NoNetView.setVisibility(GONE);
            }

            this.setVisibility(VISIBLE);

        }
    }

    public EmptyRecycleView(Context context) {
        super(context);
    }

    public EmptyRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EmptyRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        final Adapter oldAdapter=getAdapter();
        if(oldAdapter!=null){
            oldAdapter.unregisterAdapterDataObserver(observer);
        }
        super.setAdapter(adapter);
        if(adapter!=null){
            adapter.registerAdapterDataObserver(observer);
        }
    }
}
