package eyedsion.soft.eyedsionhis.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TimePicker;

import eyedsion.soft.eyedsionhis.R;


/**
 * @Description 时间选择器
 * @Created by huppert on 2015/11/27.
 */
public class TimePickerPopuWindow extends PopupWindow {


    private Context context;

    private Button btn_cancel;

    private Button btn_submit;

    private TimePicker datePicker;

    private View.OnClickListener mListener;
    private int hours;
    private int  min;

    public TimePickerPopuWindow(Context context, View.OnClickListener mListener) {
        super(context);
        this.context = context;
        this.mListener = mListener;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.popuwindow_time, null);

        setContentView(view);

        setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        datePicker = (TimePicker) view.findViewById(R.id.datepicker);
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
                hours=datePicker.getCurrentHour();
                min=datePicker.getCurrentMinute();
                if (mListener != null)
                    mListener.onClick(v);

            }
        });

    }

    public void show(View view) {
        showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
}
