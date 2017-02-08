package eyedsion.soft.eyedsionhis.tools;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by Administrator on 2016/4/28.
 */
public class ScreenCat {

    public static String getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null)
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        else
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.parseColor("#4cb86a"));
        // draw the view on the canvas
        view.draw(canvas);

        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                , String.valueOf(System.currentTimeMillis()) + ".jpg" );
        OutputStream stream = null;
        try {
            stream = new FileOutputStream(file);
            returnedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream );
            stream.close();
        } catch (Exception e) {
            Log.v("share", "share history error:" + e.getMessage());
        }

        //return the bitmap
        return file.getPath();
    }

}
