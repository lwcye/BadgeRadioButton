package com.cqmc.badgeradiobutton.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;

/**
 * Drawable居中的TextView
 *
 * @author lwc
 * @date 2016.4.27
 * @note 1. 支持Drawable上、下、左、右四个方向。
 */
public class DrawableCenterTextView extends android.support.v7.widget.AppCompatTextView {
    /** 图片列表 */
    private Drawable[] mDrawables;
    /** 字体偏移量 */
    private float mOffSize;

    /**
     * 构造函数
     *
     * @param context 上下文
     */
    public DrawableCenterTextView(Context context) {
        super(context);
    }

    /**
     * 构造函数
     *
     * @param context 上下文
     * @param attrs 属性
     */
    public DrawableCenterTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 构造函数
     *
     * @param context 上下文
     * @param attrs 属性
     * @param defStyle 样式
     */
    public DrawableCenterTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        mDrawables = getCompoundDrawables();
        if (mDrawables[0] != null || mDrawables[2] != null) {
            // 左、右
            setGravity(Gravity.CENTER_VERTICAL | (mDrawables[0] != null ? Gravity.START : Gravity.END));
        } else if (mDrawables[1] != null || mDrawables[3] != null) {
            // 上、下
            setGravity(Gravity.CENTER_HORIZONTAL | (mDrawables[1] != null ? Gravity.TOP : Gravity.BOTTOM));
        }

        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int drawablePadding = getCompoundDrawablePadding();
        if (mDrawables[0] != null) {
            // 左
            int drawableWidth = mDrawables[0].getIntrinsicWidth();
            float bodyWidth;
            if (TextUtils.isEmpty(getText())) {
                bodyWidth = drawableWidth;
            } else {
                float textWidth = getPaint().measureText(getText().toString());
                bodyWidth = textWidth + drawableWidth + drawablePadding;
            }
            mOffSize = getWidth() - bodyWidth;
            canvas.translate(mOffSize / 2, 0);

        } else if (mDrawables[2] != null) {
            // 右
            int drawableWidth = mDrawables[2].getIntrinsicWidth();
            float bodyWidth;
            if (TextUtils.isEmpty(getText())) {
                bodyWidth = drawableWidth;
            } else {
                float textWidth = getPaint().measureText(getText().toString());
                bodyWidth = textWidth + drawableWidth + drawablePadding;
            }
            mOffSize = bodyWidth - getWidth();
            canvas.translate(mOffSize / 2, 0);

        } else if (mDrawables[1] != null) {
            // 上
            int drawableHeight = mDrawables[1].getIntrinsicHeight();
            float bodyHeight;
            if (TextUtils.isEmpty(getText())) {
                bodyHeight = drawableHeight;
            } else {
                Paint.FontMetrics fm = getPaint().getFontMetrics();
                float fontHeight = (float) Math.ceil(fm.descent - fm.ascent);
                bodyHeight = fontHeight + drawableHeight + drawablePadding;
            }
            mOffSize = getHeight() - bodyHeight;
            canvas.translate(0, mOffSize / 2);

        } else if (mDrawables[3] != null) {
            // 下
            int drawableHeight = mDrawables[3].getIntrinsicHeight();
            float bodyHeight;
            if (TextUtils.isEmpty(getText())) {
                bodyHeight = drawableHeight;
            } else {
                Paint.FontMetrics fm = getPaint().getFontMetrics();
                float fontHeight = (float) Math.ceil(fm.descent - fm.ascent);
                bodyHeight = fontHeight + drawableHeight + drawablePadding;
            }
            mOffSize = bodyHeight - getHeight();
            canvas.translate(0, mOffSize / 2);
        }
        super.onDraw(canvas);
    }

    /**
     * 获取偏移量
     *
     * @return mOffSize
     */
    public float getOffSize() {
        return mOffSize;
    }
}
