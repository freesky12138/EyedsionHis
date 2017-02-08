package eyedsion.soft.eyedsionhis.widget;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import eyedsion.soft.eyedsionhis.R;


/**
 * @Description 日期选择器
 * @Created by Huppert on 2015/11/27.
 */
public class DataPickerPopuWindow extends PopupWindow {


    private Context context;

    private Button btn_cancel;

    private Button btn_submit;

    private DatePicker datePicker;

    private View.OnClickListener mListener;

    public DataPickerPopuWindow(Context context, View.OnClickListener mListener) {
        super(context);
        this.context = context;
        this.mListener = mListener;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.popuwindow_date, null);

        setContentView(view);

        setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        datePicker = (DatePicker) view.findViewById(R.id.datepicker);
        btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
        btn_submit = (Button) view.findViewById(R.id.btn_submit);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null)
                    dismiss();
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                //选择的日期
                String time = datePicker.getYear() + ""
                        + ((datePicker.getMonth() + 1) < 10 ? "0" + (datePicker.getMonth() + 1) : (datePicker.getMonth() + 1)) + ""
                        + (datePicker.getDayOfMonth() < 10 ? "0" + datePicker.getDayOfMonth() : datePicker.getDayOfMonth());
                if (Long.valueOf(time) > Long.valueOf(getCurrentDate())) {
                    Log.e("time",time+getCurrentDate());
                    Toast.makeText(context, "选择时间不能超过当前日期", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mListener != null)
                    mListener.onClick(v);

            }
        });

    }

    public void show(View view) {
        showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    //选择的日期
    public String selectDate() {
        StringBuilder sb = new StringBuilder();
        sb.append(datePicker.getYear()).append("-")
                .append((datePicker.getMonth() + 1) < 10 ? "0" + (datePicker.getMonth() + 1) : (datePicker.getMonth() + 1) ).append("-")
                .append((datePicker.getDayOfMonth() < 10 ? "0" + datePicker.getDayOfMonth() : datePicker.getDayOfMonth()));
        return sb.toString();
    }

    //当前日期
    public String getCurrentDate() {
        StringBuilder sb = new StringBuilder();
        sb.append(new SimpleDateFormat("yyyyMMdd").format(new Date()));
        return sb.toString();
    }

}
