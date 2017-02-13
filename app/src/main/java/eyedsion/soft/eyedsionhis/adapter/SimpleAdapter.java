package eyedsion.soft.eyedsionhis.adapter;

import android.content.Context;

import eyedsion.soft.eyedsionhis.R;
import eyedsion.soft.eyedsionhis.adapter.RecycleAdapter.BaseRecyCleAdapter;
import eyedsion.soft.eyedsionhis.bean.result.MessageDefaultV2Result;

/**
 * Created by Administrator on 2017/2/10.
 */

public class SimpleAdapter extends BaseRecyCleAdapter<MessageDefaultV2Result.DateEntity>{
    public SimpleAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    protected void convert(int position, ViewHolder holder, MessageDefaultV2Result.DateEntity movieEntity) {
        holder.setText(R.id.item_text,movieEntity.getWarnningDate());
    }
}
