package eyedsion.soft.eyedsionhis.adapter.RecycleAdapter;

/**
 * Created by Administrator on 2016/10/17.
 */
public interface ItemViewDelegate<T>
{

    int getItemViewLayoutId();

    boolean isForViewType(T item, int position);

    void convert(MultiItemTypeAdapter.ViewHolder holder, T t, int position);

}