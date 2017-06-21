package com.cqmc.badgeradiobutton.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * <p>测量Text字的位置</p><br>
 *
 * @author - lwc
 * @date - 2017/6/21
 * @note -
 * -------------------------------------------------------------------------------------------------
 */
public class MeasureTextView extends android.support.v7.widget.AppCompatTextView {
    Paint mPaint;


    public MeasureTextView(Context context) {
        super(context);
        init();
    }

    public MeasureTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MeasureTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.RED);
        mPaint.setTextSize(sp2px(8));

        Paint.FontMetrics fontMetrics = getPaint().getFontMetrics();
        //baseline
        canvas.drawLine(0, getBaseline(), getWidth(), getBaseline(), mPaint);
        canvas.drawText("baseline", 0, getBaseline(), mPaint);
        //top
        canvas.drawLine(0, fontMetrics.top + getBaseline(), getWidth(), fontMetrics.top + getBaseline(), mPaint);
        canvas.drawText("top", getWidth() / 5, fontMetrics.top + getBaseline(), mPaint);
        //ascent
        canvas.drawLine(0, fontMetrics.ascent + getBaseline(), getWidth(), fontMetrics.ascent + getBaseline(), mPaint);
        canvas.drawText("ascent", getWidth() * 2 / 5, fontMetrics.ascent + getBaseline(), mPaint);
        //descent
        canvas.drawLine(0, fontMetrics.descent + getBaseline(), getWidth(), fontMetrics.descent + getBaseline(), mPaint);
        canvas.drawText("descent", getWidth() * 3 / 5, fontMetrics.descent + getBaseline(), mPaint);
        //bottom
        canvas.drawLine(0, fontMetrics.bottom + getBaseline(), getWidth(), fontMetrics.bottom + getBaseline(), mPaint);
        canvas.drawText("bottom", getWidth() * 4 / 5, fontMetrics.bottom + getBaseline(), mPaint);
        //leading
        canvas.drawLine(0, fontMetrics.leading + getBaseline(), getWidth(), fontMetrics.leading + getBaseline(), mPaint);
        canvas.drawText("leading", getWidth(), fontMetrics.leading + getBaseline(), mPaint);
    }

    /**
     * sp转px
     *
     * @param spValue sp值
     * @return px值
     */
    public int sp2px(float spValue) {
        final float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
