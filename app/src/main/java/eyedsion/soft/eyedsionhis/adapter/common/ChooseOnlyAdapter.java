package eyedsion.soft.eyedsionhis.adapter.common;

import android.content.Context;

import eyedsion.soft.eyedsionhis.R;
import eyedsion.soft.eyedsionhis.adapter.CommonAbsAdapter;


/**
 * Created by Administrator on 2016/3/8.
 */
public class ChooseOnlyAdapter extends CommonAbsAdapter<String> {
    public ChooseOnlyAdapter(Context context, int itemLayoutId) {
        super(context, itemLayoutId);
    }

    @Override
    public void convert(int position, ViewHolder helper, String item) {

        helper.setText(R.id.choose_only_one_text , item);

    }
}
