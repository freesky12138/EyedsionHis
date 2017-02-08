package com.zhy.autolayout.attr;

import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Administrator on 16/02/2016.
 * Describe :
 */
public class DrawablePaddingAttr extends AutoAttr
{
    public DrawablePaddingAttr(int pxVal, int baseWidth, int baseHeight)
    {
        super(pxVal, baseWidth, baseHeight);
    }

    @Override
    protected int attrVal()
    {
        return Attrs.DRAWABLE_PADDING;
    }

    @Override
    protected boolean defaultBaseWidth()
    {
        return true;
    }

    @Override
    protected void execute(View view, int val)
    {
        if (!(view instanceof EditText))
            return;
        ((EditText) view).setCompoundDrawablePadding(val);
    }
}
