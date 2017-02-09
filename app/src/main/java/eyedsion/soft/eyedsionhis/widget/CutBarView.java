package eyedsion.soft.eyedsionhis.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


/**
 * 一个条形自定义控件，可以自定义这个条形每个位置的大小颜色
 * Created by Huppert on 2016/12/14.
 */

public class CutBarView extends View {

    public static enum Orientation {
        VERTICAL,
        HORITITAL
    }

    //显示方式，横向或者竖向
    private Orientation orientation;

    /**
     * 从上至下的值
     */
    private int[] values;


    /**
     * 三种颜色
     */
    private String[] colors;


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


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (paint == null)
            paint = new Paint();

        if (Orientation.VERTICAL == orientation) {
            DrawVertical(paint, canvas);
        } else {
            DrawHortital(paint, canvas);
        }


//        paint.set

    }

    private void DrawHortital(Paint paint, Canvas canvas) {
        int weigh = this.getWidth();
        int heigh = this.getHeight();

        float left = 0;
        float right = 0;
        float top = 0;
        float bottom = heigh;

        float sum = 0;
        for (int i = 0; i < values.length; i++) {
            sum += values[i];
        }

        float kong = 100 - sum;

        this.paint.setColor(Color.TRANSPARENT);
        this.paint.setAntiAlias(true);  //消除锯齿

        canvas.drawRect(left, top, weigh * (kong / 100), bottom, this.paint);

        for (int i = 0; i < colors.length; i++) {
            this.paint.setColor(Color.parseColor(colors[i]));
            this.paint.setAntiAlias(true);  //消除锯齿

            int widthTop = 0;// 这段颜色的上部分的位置
            int widthBottom = 0; //这段颜色下部分的位置
            for (int j = 0; j <= i; j++) {

                widthBottom += values[j];
                if (j < i) {
                    widthTop += values[j];
                }
            }
            canvas.drawRect(weigh * ((kong + widthTop) / 100), top, weigh * (kong + widthBottom) / 100, bottom, this.paint);
        }
    }

    private void DrawVertical(Paint paint, Canvas canvas) {
        int weigh = this.getWidth();
        int heigh = this.getHeight();

        float left = 0;
        float right = weigh;
        float top = 0;
        float bottom = 0;

        float sum = 0;
        for (int i = 0; i < values.length; i++) {
            sum += values[i];
        }

        float kong = 100 - sum;

        paint.setColor(Color.TRANSPARENT);
        paint.setAntiAlias(true);  //消除锯齿

        canvas.drawRect(left, top, right, heigh * (kong / 100), paint);

        for (int i = 0; i < colors.length; i++) {
            paint.setColor(Color.parseColor(colors[i]));
            paint.setAntiAlias(true);  //消除锯齿

            int heighTop = 0;// 这段颜色的上部分的位置
            int heighBottom = 0; //这段颜色下部分的位置
            for (int j = 0; j <= i; j++) {

                heighBottom += values[j];
                if (j < i) {
                    heighTop += values[j];
                }
            }
            canvas.drawRect(left, heigh * ((kong + heighTop) / 100), right, heigh * ((kong + heighBottom) / 100), paint);
        }
    }


    /**
     * 获取进度.需要同步
     *
     * @return
     */
    public synchronized int[] getValue() {
        return values;
    }

    /**
     * 设置值和颜色,方向
     */
    public synchronized void setValue(int[] values, String[] colors, Orientation orientation) {


        this.values = values;
        this.colors = colors;
        this.orientation = orientation;

        postInvalidate();

    }


}
