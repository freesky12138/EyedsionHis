package eyedsion.soft.eyedsionhis.adapter.RecycleAdapter;

import android.content.Context;
import android.view.LayoutInflater;

import java.util.List;

/**
 * 使用 如mListView.setAdapter(mAdapter = new CommonAdapter<String>(
 * getApplicationContext(),, R.doctor_left_layout.item_single_str)
 * {
 *
 * @Override public void convert(ViewHolder helper, String item)
 * {
 * helper.setText(R.id.id_tv_title,item);
 * }
 * <p/>
 * });
 */
public abstract class BaseRecyCleAdapter<T> extends MultiItemTypeAdapter<T> {
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;

    public BaseRecyCleAdapter(final Context context, final int layoutId) {
        super(context);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;

        addItemViewDelegate(new ItemViewDelegate<T>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutId;
            }

            @Override
            public boolean isForViewType(T item, int position) {
                return true;
            }

            @Override
            public void convert(ViewHolder holder, T t, int position) {
                BaseRecyCleAdapter.this.convert(position,holder, t);
            }
        });
    }
    public BaseRecyCleAdapter(final Context context, final int layoutId,boolean isChange) {
        super(context);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;

        addItemViewDelegate(new ItemViewDelegate<T>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutId;
            }

            @Override
            public boolean isForViewType(T item, int position) {
                return true;
            }

            @Override
            public void convert(ViewHolder holder, T t, int position) {
                BaseRecyCleAdapter.this.convert(position,holder, t );
            }
        });
    }


    public void notifyDataSetChanged(List<T> arrayList,boolean isRefer){
        mDatas=arrayList;
        super.notifyDataSetChanged(arrayList);
        if(isRefer){
            notifyDataSetChanged();
        }
    }

    @Override
    public void notifyDataSetChanged(List<T> arrayList) {
        mDatas=arrayList;
        super.notifyDataSetChanged(arrayList);
    }

    public BaseRecyCleAdapter(final Context context, final int layoutId, List<T> datas) {
        super(context, datas);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;

        addItemViewDelegate(new ItemViewDelegate<T>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutId;
            }

            @Override
            public boolean isForViewType(T item, int position) {
                return true;
            }

            @Override
            public void convert(ViewHolder holder, T t, int position) {
                BaseRecyCleAdapter.this.convert(position,holder, t );
            }
        });
    }

    protected abstract void convert(int position,ViewHolder holder, T t );


}