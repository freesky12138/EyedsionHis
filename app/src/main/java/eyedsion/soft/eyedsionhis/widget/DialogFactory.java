package eyedsion.soft.eyedsionhis.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;

import eyedsion.soft.eyedsionhis.R;
import eyedsion.soft.eyedsionhis.adapter.common.ChooseOnlyAdapter;
import eyedsion.soft.eyedsionhis.adapter.common.TextAdapter;
import eyedsion.soft.eyedsionhis.bean.result.UpdataResult;
import eyedsion.soft.eyedsionhis.tools.ToastUtils;
import eyedsion.soft.eyedsionhis.tools.VisionUpdateTolls;


/**
 * Created by Administrator on 25/01/2016.
 * Describe : Dialog工厂
 */
public class DialogFactory {
    private static Dialog dialog = null;

    /**
     *
     * @param context
     * @param DeleteTitle  标题
     * @param onClickListener 点击确定的监听
     */
    public static void showDeleteDialog(Context context, String DeleteTitle, View.OnClickListener onClickListener) {
        dialog = new Dialog(context, R.style.loading_dialog);

        dialog.setContentView(R.layout.dialog_delete);
        dialog.setCanceledOnTouchOutside(true);
        AutoUtils.auto(dialog.findViewById(R.id.widget_delete_dialog_linear));
        TextView title = (TextView) dialog.findViewById(R.id.dialog_delete_title);
        title.setText(DeleteTitle);

        TextView sure = (TextView) dialog.findViewById(R.id.sure);
        sure.setOnClickListener(onClickListener);
        TextView clear = (TextView) dialog.findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public static void showChoose2(Context context, String DeleteTitle, String left, String Right, View.OnClickListener leftclick, View.OnClickListener rightclick) {
        dialog = new Dialog(context, R.style.loading_dialog);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.dialog_delete);
        dialog.setCanceledOnTouchOutside(true);
        AutoUtils.auto(dialog.findViewById(R.id.widget_delete_dialog_linear));
        TextView title = (TextView) dialog.findViewById(R.id.dialog_delete_title);
        title.setText(DeleteTitle);

        TextView sure = (TextView) dialog.findViewById(R.id.sure);
        sure.setText(left);
        sure.setOnClickListener(leftclick);

        TextView clear = (TextView) dialog.findViewById(R.id.clear);
        clear.setText(Right);
        clear.setOnClickListener(rightclick);
        dialog.show();
    }


    public static void showChooseDistory2(Context context, String DeleteTitle, String left, String distory, String Right, View.OnClickListener leftclick, View.OnClickListener rightclick) {
        dialog = new Dialog(context, R.style.loading_dialog);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.dialog_delete);
        dialog.setCanceledOnTouchOutside(true);
        AutoUtils.auto(dialog.findViewById(R.id.widget_delete_dialog_linear));
        TextView title = (TextView) dialog.findViewById(R.id.dialog_delete_title);
        title.setText(DeleteTitle);

        TextView sure = (TextView) dialog.findViewById(R.id.sure);
        sure.setText(left);
        sure.setOnClickListener(leftclick);

        TextView clear = (TextView) dialog.findViewById(R.id.clear);
        clear.setText(Right);
        clear.setOnClickListener(rightclick);

        AutoLinearLayout delete_distory = (AutoLinearLayout) dialog.findViewById(R.id.delete_distory);
        delete_distory.setVisibility(View.VISIBLE);
        TextView dialog_delete_distory = (TextView) dialog.findViewById(R.id.dialog_delete_distory);
        dialog_delete_distory.setText(distory);
        dialog.show();
    }

    public static void showChooseOnly(Context context, String DeleteTitle, ArrayList<String> names, AdapterView.OnItemClickListener onItemClickListen) {

        dialog = new Dialog(context, R.style.loading_dialog);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.dialog_choose_only);
        dialog.setCanceledOnTouchOutside(true);
        AutoUtils.auto(dialog.findViewById(R.id.widget_choose_only_linear));
        TextView title = (TextView) dialog.findViewById(R.id.choose_only_text);
        title.setText(DeleteTitle);

        ListView choose_only_list = (ListView) dialog.findViewById(R.id.choose_only_list);
        ChooseOnlyAdapter adapter = new ChooseOnlyAdapter(context, R.layout.dialog_item_choose_only);
        choose_only_list.setAdapter(adapter);
        adapter.notifyDataSetChanged(names, true);
        choose_only_list.setOnItemClickListener(onItemClickListen);
        dialog.show();
    }


    public static void dismiss() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public static void showUpdataDialog(final Context context, final UpdataResult.DateEntity dateEntity) {
        dialog = new Dialog(context, R.style.loading_dialog);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.dialog_updata);
        dialog.setCanceledOnTouchOutside(true);
        AutoUtils.auto(dialog.findViewById(R.id.widget_delete_dialog_linear));


        ImageView update_delete = (ImageView) dialog.findViewById(R.id.update_delete);
        TextView update_version_no = (TextView) dialog.findViewById(R.id.update_version_no);

        update_version_no.setText("最新版本：" + dateEntity.getVersionNo());
        TextView update_version_size = (TextView) dialog.findViewById(R.id.update_version_size);
        update_version_size.setText("新版本大小：" + dateEntity.getFileSize());

        TextView update_value = (TextView) dialog.findViewById(R.id.update_value);
        update_value.setText("更新内容：\n" + dateEntity.getUpdateInfo());

        update_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        TextView update_now = (TextView) dialog.findViewById(R.id.update_now);
        update_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(dateEntity.getUpdateUrl());
                intent.setData(content_url);
                context.startActivity(intent);*/
                //Log.e("url",dateEntity.getUpdateUrl());
                dialog.dismiss();
                VisionUpdateTolls.downloadAPK(dateEntity.getUpdateUrl(), context);
            }
        });


        dialog.show();
    }

    public static String EditDialogDetal = "";

    public static void showEditDialog(final Context context, String title, final View.OnClickListener onClickListener) {
        dialog = new Dialog(context, R.style.loading_dialog);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.dialog_edit);
        dialog.setCanceledOnTouchOutside(true);
        AutoUtils.auto(dialog.findViewById(R.id.widget_choose_only_linear));
        final EditText edit_dialog = (EditText) dialog.findViewById(R.id.edit_dialog);
        TextView textView = (TextView) dialog.findViewById(R.id.choose_only_text);
        TextView sure = (TextView) dialog.findViewById(R.id.sure);
        textView.setText(title);
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditDialogDetal = edit_dialog.getText().toString();
                dismiss();
                onClickListener.onClick(v);
            }
        });
        dialog.show();

    }



    public static int choose1 = -1;
    public static int choose2 = 2;
    public static String SavaTitle = "";
    private static ArrayList<String> v1 = null;
    private static ArrayList<String> v2 = null;

    public static void showChooseValue(final Activity context, ArrayList<String> l1, ArrayList<String> l2, String danwei, String title, final valueReturn valueReturn, int... choose) {
        v1 = l1;
        v2 = l2;

        v1.add(0, "");
        v1.add(0, "");
        v1.add("");
        v1.add("");

        if (v2 != null) {
            v2.add(0, "");
            v2.add(0, "");
            v2.add("");
            v2.add("");
        }


        if (!SavaTitle.equals(title)) {
            if (v1.size() > 4)
                choose1 = (v1.size() - 4) / 2 + 2;
            else
                choose1 = 2;

            if (v2 != null && v2.size() > 4)
                choose2 = (v2.size() - 4) / 2 + 2;
            else
                choose2 = 2;
        }

        if (choose.length == 1) {
            choose1 = choose[0] + 2;
        } else if (choose.length == 2) {
            choose1 = choose[0] + 2;
            choose2 = choose[1] + 2;
        }

        SavaTitle = title;

        dialog = new Dialog(context, R.style.loading_dialog);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.dialog_choose_value);
        dialog.setCanceledOnTouchOutside(true);
        AutoUtils.auto(dialog.findViewById(R.id.dialog_choose_value_layout));

        TextView info_set_danwei = (TextView) dialog.findViewById(R.id.info_set_danwei);
        TextView choose_value_title = (TextView) dialog.findViewById(R.id.choose_value_title);

        AutoRelativeLayout sure_layout = (AutoRelativeLayout) dialog.findViewById(R.id.sure_layout);
        TextView sure = (TextView) dialog.findViewById(R.id.sure);
        info_set_danwei.setText(danwei);
        choose_value_title.setText(title);
        final RecyclerView list1 = (RecyclerView) dialog.findViewById(R.id.list1);
        final TextAdapter textAdapter1 = new TextAdapter(context, R.layout.item_text);
        textAdapter1.setChooseIndex(choose1);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        list1.setLayoutManager(linearLayoutManager);
        list1.setAdapter(textAdapter1);


        textAdapter1.notifyDataSetChanged(v1);

        list1.scrollToPosition(choose1 - 2);
        list1.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                RecyclerView.LayoutManager layoutManager = list1.getLayoutManager();
                //判断是当前layoutManager是否为LinearLayoutManager
                // 只有LinearLayoutManager才有查找第一个和最后一个可见view位置的方法
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
                    //获取最后一个可见view的位置
                    int lastItemPosition = linearManager.findLastVisibleItemPosition();
                    //获取第一个可见view的位置
                    int firstItemPosition = linearManager.findFirstVisibleItemPosition();

                    //    Log.e("Text",lastItemPosition + "   " + firstItemPosition);


                    int position = linearManager.findFirstVisibleItemPosition();
                    View firstVisiableChildView = layoutManager.findViewByPosition(position);
                    int itemHeight = firstVisiableChildView.getHeight();
                    int heigh = (position) * itemHeight - firstVisiableChildView.getTop();
                    Log.e("top", (position) * itemHeight - firstVisiableChildView.getTop() + "");
                    if (heigh % itemHeight > itemHeight / 2) {

                        textAdapter1.setChooseIndex(firstItemPosition + 3);

                        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                            choose1 = firstItemPosition + 3;

                            moveToPosition(firstItemPosition + 1, linearManager, list1);

                        }
                        textAdapter1.notifyDataSetChanged();

                    } else {
                        textAdapter1.setChooseIndex(firstItemPosition + 2);

                        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                            choose1 = firstItemPosition + 2;
                            list1.scrollToPosition(firstItemPosition);

                        }
                        textAdapter1.notifyDataSetChanged();
                    }


                }


            }

        });


        final RecyclerView list2 = (RecyclerView) dialog.findViewById(R.id.list2);
        if (v2 == null) {
            list2.setVisibility(View.GONE);
        } else {
            list2.setVisibility(View.VISIBLE);
            final LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(context);
            linearLayoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
            list2.setLayoutManager(linearLayoutManager2);
            final TextAdapter textAdapter2 = new TextAdapter(context, R.layout.item_text);
            list2.setAdapter(textAdapter2);
            textAdapter2.setChooseIndex(choose2);
            textAdapter2.notifyDataSetChanged(v2);


            list2.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);

                    RecyclerView.LayoutManager layoutManager = list2.getLayoutManager();
                    //判断是当前layoutManager是否为LinearLayoutManager
                    // 只有LinearLayoutManager才有查找第一个和最后一个可见view位置的方法
                    if (layoutManager instanceof LinearLayoutManager) {
                        LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
                        //获取最后一个可见view的位置
                        int lastItemPosition = linearManager.findLastVisibleItemPosition();
                        //获取第一个可见view的位置
                        int firstItemPosition = linearManager.findFirstVisibleItemPosition();

                        //    Log.e("Text",lastItemPosition + "   " + firstItemPosition);


                        int position = linearManager.findFirstVisibleItemPosition();
                        View firstVisiableChildView = layoutManager.findViewByPosition(position);
                        int itemHeight = firstVisiableChildView.getHeight();
                        int heigh = (position) * itemHeight - firstVisiableChildView.getTop();
                        //       Log.e("top", (position) * itemHeight - firstVisiableChildView.getTop() + "");
                        if (heigh % itemHeight > itemHeight / 2) {

                            textAdapter2.setChooseIndex(firstItemPosition + 3);

                            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                                choose2 = firstItemPosition + 3;

                                moveToPosition(firstItemPosition + 1, linearLayoutManager2, list2);

                            }
                            textAdapter2.notifyDataSetChanged();

                        } else {
                            textAdapter2.setChooseIndex(firstItemPosition + 2);

                            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                                choose2 = firstItemPosition + 2;
                                list2.scrollToPosition(firstItemPosition);

                            }
                            textAdapter2.notifyDataSetChanged();
                        }

                    }


                }

            });

            list2.scrollToPosition(choose2 - 2);
        }


        sure_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v2 == null) {
                    v1.remove(0);
                    v1.remove(0);
                    v1.remove(v1.size() - 1);
                    v1.remove(v1.size() - 1);
                    valueReturn.onResult(choose1 - 2);
                } else {
                    v1.remove(0);
                    v1.remove(0);
                    v1.remove(v1.size() - 1);
                    v1.remove(v1.size() - 1);

                    v2.remove(0);
                    v2.remove(0);
                    v2.remove(v2.size() - 1);
                    v2.remove(v2.size() - 1);

                    valueReturn.onResult2(choose1 - 2, choose2 - 2);

                }
                isOnclick = true;
                dismiss();
            }
        });
        isOnclick = false;

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (isOnclick == false) {
                    if (v2 == null) {
                        v1.remove(0);
                        v1.remove(0);
                        v1.remove(v1.size() - 1);
                        v1.remove(v1.size() - 1);

                    } else {
                        v1.remove(0);
                        v1.remove(0);
                        v1.remove(v1.size() - 1);
                        v1.remove(v1.size() - 1);

                        v2.remove(0);
                        v2.remove(0);
                        v2.remove(v2.size() - 1);
                        v2.remove(v2.size() - 1);
                    }
                }
            }
        });

        dialog.show();

    }

    private static boolean isOnclick = false;

    private static void moveToPosition(int n, LinearLayoutManager mLinearLayoutManager, RecyclerView mRecyclerView) {
        //先从RecyclerView的LayoutManager中获取第一项和最后一项的Position
        int firstItem = mLinearLayoutManager.findFirstVisibleItemPosition();
        int lastItem = mLinearLayoutManager.findLastVisibleItemPosition();
        //然后区分情况
        if (n <= firstItem) {
            //当要置顶的项在当前显示的第一个项的前面时
            mRecyclerView.scrollToPosition(n);
        } else if (n <= lastItem) {
            //当要置顶的项已经在屏幕上显示时
            int top = mRecyclerView.getChildAt(n - firstItem).getTop();
            mRecyclerView.scrollBy(0, top);
        } else {
            //当要置顶的项在当前显示的最后一项的后面时
            mRecyclerView.scrollToPosition(n);
            //这里这个变量是用在RecyclerView滚动监听里面的
            //     move = true;
        }

    }

    public interface valueReturn {
        void onResult(int res);

        void onResult2(int res1, int res2);
    }


    private static PopupWindow mPopupWindow;

    private static boolean[] status = new boolean[4];

    public static void showManyChoose(final Activity context, int layout, final OnChooseManyReturn onChooseManyReturn, final boolean[] statusTemp) {

        for (int i = 0; i < statusTemp.length; i++) {
            status[i] = !statusTemp[i];
        }

        WindowManager.LayoutParams lp = context.getWindow().getAttributes();

        lp.alpha = 0.7f;
        context.getWindow().setAttributes(lp);

        View item_choose_many = LayoutInflater.from(context).inflate(R.layout.dialog_choose_many, null, true);

        final TextView choose_many_sugar = (TextView) item_choose_many.findViewById(R.id.choose_many_sugar);
        if (status[0] == true) {
            choose_many_sugar.setBackgroundResource(R.drawable.info_edit_select);
        }
        TextView choose_many_press = (TextView) item_choose_many.findViewById(R.id.choose_many_press);
        if (status[1] == true) {
            choose_many_press.setBackgroundResource(R.drawable.info_edit_select);
        }
        TextView choose_many_weight = (TextView) item_choose_many.findViewById(R.id.choose_many_weight);
        if (status[2] == true) {
            choose_many_weight.setBackgroundResource(R.drawable.info_edit_select);
        }
        TextView choose_many_sport = (TextView) item_choose_many.findViewById(R.id.choose_many_sport);
        if (status[3] == true) {
            choose_many_sport.setBackgroundResource(R.drawable.info_edit_select);
        }
        final TextView sure = (TextView) item_choose_many.findViewById(R.id.sure);
        sure.setAlpha(0.5f);

        choose_many_sugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status[0] == true) {
                    v.setBackgroundResource(R.drawable.info_edit_no_select);
                    status[0] = false;
                } else {
                    v.setBackgroundResource(R.drawable.info_edit_select);
                    status[0] = true;
                }
                setCanChoose(sure);
            }
        });
        choose_many_press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status[1] == true) {
                    v.setBackgroundResource(R.drawable.info_edit_no_select);
                    status[1] = false;
                } else {
                    v.setBackgroundResource(R.drawable.info_edit_select);
                    status[1] = true;
                }
                setCanChoose(sure);
            }
        });
        choose_many_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status[2] == true) {
                    v.setBackgroundResource(R.drawable.info_edit_no_select);
                    status[2] = false;
                } else {
                    v.setBackgroundResource(R.drawable.info_edit_select);
                    status[2] = true;
                }
                setCanChoose(sure);
            }
        });
        choose_many_sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status[3] == true) {
                    v.setBackgroundResource(R.drawable.info_edit_no_select);
                    status[3] = false;
                } else {
                    v.setBackgroundResource(R.drawable.info_edit_select);
                    status[3] = true;
                }
                setCanChoose(sure);
            }
        });

        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (setCanChoose(v)) {
                    onChooseManyReturn.onResult(status);
                    mPopupWindow.dismiss();
                } else {
                    ToastUtils.show("至少选择两项");
                }
            }
        });

        mPopupWindow = new PopupWindow(context);
        mPopupWindow.setContentView(item_choose_many);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setWidth(ViewPager.LayoutParams.MATCH_PARENT);
        mPopupWindow.setHeight(ViewPager.LayoutParams.WRAP_CONTENT);

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = context.getWindow().getAttributes();

                lp.alpha = 1f;
                context.getWindow().setAttributes(lp);
            }
        });
        mPopupWindow.update();
        mPopupWindow.showAtLocation(context.findViewById(layout), Gravity.BOTTOM, 0, 0);
    }

    private static boolean setCanChoose(View sure) {
        int count = 0;
        for (int i = 0; i < status.length; i++) {
            if (status[i] == true) {
                count++;
            }
        }
        if (count > 1) {
            sure.setAlpha(1f);
            return true;
        } else {
            sure.setAlpha(0.5f);
            return false;
        }
    }

    public interface OnChooseManyReturn {
        void onResult(boolean[] res);
    }
}



