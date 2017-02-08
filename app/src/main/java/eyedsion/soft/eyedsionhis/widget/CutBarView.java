package eyedsion.soft.eyedsionhis.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import eyedsion.soft.eyedsionhis.R;


/**
 *   待修改
 * Created by Huppert on 2016/12/14.
 */

public class CutBarView extends View {
    /**
     * 从上至下的三个值
     */
    private int value1 = 0;
    private int value2 = 0;
    private int value3 = 0;
    private int value4 = 0;

    /**
     * 三种颜色
     */
    private String color1 = "#ffaf46";
    private String color2 = "#51dd82";
    private String color3 = "#738bef";
    private String color4 = "#ff7777";


    /**
     * 整体的宽高
     */
    private int heigh = 500;
    private int weigh = 200;

    private int TextSize;
    private int TextColor;

    private Paint paint;

    public CutBarView(Context context) {
        super(context);
    }

    public CutBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CutBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CutBarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        paint = new Paint();

        TypedArray mTypedArray = context.obtainStyledAttributes(attrs,
                R.styleable.CutBarView);
        /*heigh=mTypedArray.getDimension(R.style.w)
                weigh*/

        TextSize = (int) mTypedArray.getDimension(R.styleable.CutBarView_TextSize, 15);
        TextColor = mTypedArray.getColor(R.styleable.CutBarView_TextColor, Color.GRAY);
        heigh = (int) mTypedArray.getDimension(R.styleable.CutBarView_Heigh, 400);
        weigh = (int) mTypedArray.getDimension(R.styleable.CutBarView_Width, 100);

        mTypedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (paint == null)
            paint = new Paint();


        float left = 0;
        float right = weigh;
        float top = 0;
        float bottom = 0;

        float sum = value1 + value2 + value3 + value4;
        float kong = 100 - sum;

        paint.setColor(Color.TRANSPARENT);
        paint.setAntiAlias(true);  //消除锯齿
        canvas.drawRect(left, top, right, heigh * (kong / 100), paint);

        paint.setColor(Color.parseColor(color1));
        paint.setAntiAlias(true);  //消除锯齿
        canvas.drawRect(left, heigh * (kong / 100), right, heigh * ((kong + value1) / 100), paint);

        paint.setColor(Color.parseColor(color2));
        paint.setAntiAlias(true);  //消除锯齿
        canvas.drawRect(left, heigh * ((kong + value1) / 100), right, heigh * ((kong + value1 + value2) / 100), paint);

        paint.setColor(Color.parseColor(color3));
        paint.setAntiAlias(true);  //消除锯齿
        canvas.drawRect(left, heigh * ((kong + value1 + value2) / 100), right, heigh * ((kong + value1 + value2 + value3) / 100), paint);

        paint.setColor(Color.parseColor(color4));
        paint.setAntiAlias(true);  //消除锯齿
        canvas.drawRect(left, heigh * ((kong + value1 + value2 + value3) / 100), right, heigh * ((kong + value1 + value2 + value3 + value4) / 100), paint);


//        paint.set

    }


    /**
     * 获取进度.需要同步
     *
     * @return
     */
    public synchronized int[] getValue() {
        return new int[]{value1, value2, value3, value4};
    }

    /**
     * 设置三个值
     *
     * @param v1
     * @param v2
     * @param v3
     */
    public synchronized void setValue(int v1, int v2, int v3, int v4, int screenHeigh, int screenWidth, String... color) {
        if (v1 < 0 || v2 < 0 || v3 < 0 || v4 < 0) {
            v1 = 0;
            v2 = 0;
            v3 = 0;
            v4 = 0;
            //throw new IllegalArgumentException("progress not less than 0");
        }
        if (v1 + v2 + v3 + v4 > 100) {
            v1 = 0;
            v2 = 0;
            v3 = 0;
            v4 = 0;
        }

        this.value1 = v1;
        this.value2 = v2;
        this.value3 = v3;
        this.value4 = v4;
        heigh = screenHeigh * 360 / 1136;
        weigh = screenWidth * 30 / 640;


        if (color.length == 2) {
            color1 = color[0];
            color2 = color[1];
        }

        postInvalidate();

    }


}
