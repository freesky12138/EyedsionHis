package eyedsion.soft.eyedsionhis.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
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
public abstract class CommonAbsAdapter<T> extends BaseAdapter {
    protected LayoutInflater mInflater;
    protected Context mContext;
    public List<T> mDatas = new ArrayList<T>();
    protected final int mItemLayoutId;
    public static boolean isChange;

    public CommonAbsAdapter(Context context, int itemLayoutId, boolean isChange) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mItemLayoutId = itemLayoutId;
        this.isChange=isChange;
    }
    public CommonAbsAdapter(Context context, int itemLayoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mItemLayoutId = itemLayoutId;
        this.isChange=false;
    }


    public void notifyDataSetChanged(List<T> datas, boolean needClear) {
        if (needClear)
            mDatas.clear();
        if (null == datas)
            return;
        mDatas.addAll(datas);
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder = getViewHolder(position, convertView,
                parent);

            convert(position, viewHolder, getItem(position));

        return viewHolder.getConvertView();
    }


    public abstract void convert(int position, ViewHolder helper, T item);

    private ViewHolder getViewHolder(int position, View convertView,
                                     ViewGroup parent) {
        return ViewHolder.get(mContext, convertView, parent, mItemLayoutId,
                position);
    }

    public static class ViewHolder {
        private final SparseArray<View> mViews;
        private int mPosition;
        private View mConvertView;
        private Context mContext;

        private ViewHolder(Context context, ViewGroup parent, int layoutId,
                           int position) {
            this.mPosition = position;
            this.mViews = new SparseArray<View>();
            mContext=context;
            mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
                    false);
            // setTag
            mConvertView.setTag(this);
            AutoUtils.autoSize(mConvertView);
        }

        /**
         * 拿到一个ViewHolder对象
         *
         * @param context
         * @param convertView
         * @param parent
         * @param layoutId
         * @param position
         * @return
         */
        public static ViewHolder get(Context context, View convertView,
                                     ViewGroup parent, int layoutId, int position) {
            if(isChange)
                return new ViewHolder(context, parent, layoutId, position);

            if (convertView == null) {
                return new ViewHolder(context, parent, layoutId, position);
            }
            return (ViewHolder) convertView.getTag();
        }

        public View getConvertView() {
            return mConvertView;
        }

        /**
         * 通过控件的Id获取对于的控件，如果没有则加入views
         *
         * @param viewId
         * @return
         */
        public <T extends View> T getView(int viewId) {
            View view = mViews.get(viewId);
            if (view == null) {
                view = mConvertView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }

        /**
         * 为TextView设置字符串
         *
         * @param viewId
         * @param text
         * @return
         */
        public ViewHolder setText(int viewId, String text) {
            TextView view = getView(viewId);
            view.setText(text);
            return this;
        }


        /**
         * 为ImageView设置图片
         *
         * @param viewId
         * @param drawableId
         * @return
         */
        public ViewHolder setImageResource(int viewId, int drawableId) {
            ImageView view = getView(viewId);
            view.setImageResource(drawableId);

            return this;
        }

        /**
         * 为ImageView设置图片
         *
         * @param viewId
         * @return
         */
        public ViewHolder setImageBitmap(int viewId, Bitmap bm) {
            ImageView view = getView(viewId);
            view.setImageBitmap(bm);
            return this;
        }



        public int getPosition() {
            return mPosition;
        }

    }

}  
