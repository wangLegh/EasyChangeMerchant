package com.easychange.admin.easychangemerchant.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by   admin on 2018/4/17.
 */

public class SquareViewGroup extends RelativeLayout {
    public SquareViewGroup(Context context) {
        super(context);
    }

    public SquareViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

}
