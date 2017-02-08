package eyedsion.soft.eyedsionhis.adapter.common;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import eyedsion.soft.eyedsionhis.R;
import eyedsion.soft.eyedsionhis.adapter.RecycleAdapter.BaseRecyCleAdapter;


/**
 * Created by Administrator on 2016/11/21.
 */

public class TextAdapter extends BaseRecyCleAdapter<String> {
    private int chooseIndex=2;

    public int getChooseIndex() {
        return chooseIndex;
    }

    public void setChooseIndex(int chooseIndex) {
        this.chooseIndex = chooseIndex;
    }

    public TextAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    protected void convert(int position, ViewHolder holder, String s) {
        holder.setText(R.id.item_text,s);

        TextView textView=holder.getView(R.id.item_text);

        if(position==chooseIndex){
            textView.setTextColor(Color.parseColor("#37c067"));
            textView.setTextSize(32);
        }else {
            textView.setTextColor(Color.parseColor("#666666"));
            textView.setTextSize(28);
        }
    }
}
