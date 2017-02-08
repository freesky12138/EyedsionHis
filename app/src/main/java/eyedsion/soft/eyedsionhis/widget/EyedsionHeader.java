package eyedsion.soft.eyedsionhis.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.autolayout.AutoRelativeLayout;

import java.util.HashMap;

import eyedsion.soft.eyedsionhis.R;


/**
 * Created by huppert on 15/02/2016.
 * Describe : 通用的title
 */
public class EyedsionHeader extends AutoRelativeLayout {
    private View view;
    private Activity activity;

    public ImageView headerLeft;
    private TextView header_center;
    public TextView header_right_tv;
    public ImageView header_right_img;

    public View headerRight;

    private Context context;


    public EyedsionHeader(Context context) {
        super(context);
        this.context = context;
        initLayoutInflater();
    }

    public EyedsionHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initLayoutInflater();
    }


    private void initLayoutInflater() {
        LayoutInflater lInflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = lInflater.inflate(R.layout.header, this);
        initView();
    }

    private void initView() {

        headerLeft = (ImageView) view.findViewById(R.id.header_left);
        header_center = (TextView) view.findViewById(R.id.header_center);
        header_right_tv = (TextView) view.findViewById(R.id.header_right_tv);
        header_right_img = (ImageView) view.findViewById(R.id.header_right_img);
        headerRight=header_right_img;
    }


    public void setHeaderView(final Activity activity, HashMap<String, Object> header) {


        this.activity = activity;
        if (header.get("left") != null) {

            if(header.get("left") instanceof String){
                headerLeft.setImageResource(R.drawable.back);

                headerLeft.setOnTouchListener(new OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                headerLeft.setBackgroundColor(Color.parseColor("#31a54d"));

                                break;
                            case MotionEvent.ACTION_UP:
                                //headerLeft.setAlpha(1f);
                                headerLeft.setBackgroundColor(Color.parseColor("#37c067"));
                                activity.finish();
                                break;
                        }
                        return true;
                    }
                });
            }else if(header.get("left") instanceof Integer){
                headerLeft.setImageResource((Integer) header.get("left"));

                headerLeft.setOnTouchListener(new OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                headerLeft.setBackgroundColor(Color.parseColor("#31a54d"));

                                break;
                            case MotionEvent.ACTION_UP:
                                //headerLeft.setAlpha(1f);
                                headerLeft.setBackgroundColor(Color.parseColor("#37c067"));
                                headerLeft.callOnClick();
                                break;
                        }
                        return true;
                    }
                });
            }


        }

        if (header.get("center") != null) {
            header_center.setText((String)header.get("center"));
        }

        if (header.get("right") != null) {
            if (header.get("right") instanceof Integer) {
                header_right_tv.setVisibility(GONE);
                header_right_img.setImageResource(Integer.valueOf(header.get("right").toString()));
                headerRight=header_right_img;

            } else if (header.get("right") instanceof String) {
                header_right_img.setVisibility(GONE);
                header_right_tv.setText((String) header.get("right"));
                headerRight=header_right_tv;

            }


        }
    }

    public void setTitle(String title){
        header_center.setText(title);
    }


}