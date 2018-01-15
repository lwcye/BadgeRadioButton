package com.cqmc.badgeradiobutton.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;


/**
 * <p>带标记的 RadioButton，目前仅支持 drawableTop 的按钮 right的没测试</p><br>
 *
 * @author - lwc
 * @date - 2017/6/14
 * @note -
 * 使用时直接 setBadgeNum
 * setBadgeTextSize -- 设置字体颜色
 * setBadgePadding -- 设置内边距
 * setBadgeShowShadow -- 设置是否显示阴影
 * setBadgeColorBackground -- 设置背景颜色
 * setBadgeColorBadgeText -- 设置字体颜色
 * setBadgeExact -- 设置是否截取图标
 * setBadgeOffX -- 设置X的偏移量
 * setBadgeOffY -- 设置Y的偏移量
 * -------------------------------------------------------------------------------------------------
 * @modified - lwc
 * @date - 2017/12/14
 * @note -
 * setBadgeText -- 设置文本
 * setBadgeGravity -- 设置标记的位置
 */
public class BadgeRadioButton extends DrawableCenterRadioButton {
    /** 字体高度 */
    private float mFontHeight;
    /** 字体宽度 */
    private float mFontWidth;
    /** 位图集合 */
    private Drawable[] mDrawables;
    /** 位图 */
    private Drawable mDrawableBackground;
    /** 背景画笔 */
    private Paint mBadgeBackgroundPaint;
    /** 数字画笔 */
    private Paint mBadgeTextPaint;
    /** 数字字体大小 默认8sp */
    private float mBadgeTextSize;
    /** 内边距 默认4dp */
    private int mBadgePadding;
    /** x偏移量 */
    private int mBadgeOffX;
    /** Y偏移量 */
    private int mBadgeOffY;
    /** 数字 */
    private int mBadgeNumber;
    /** 数字文本 */
    private String mBadgeText;
    /** 是否存在阴影 默认存在 */
    private boolean mBadgeShowShadow;
    /** 背景颜色 默认红色 */
    private int mBadgeColorBackground;
    /** 字体颜色 默认白色 */
    private int mBadgeColorBadgeText;
    /** 是否截取数字 */
    private boolean mBadgeExact;
    /** 背景矩形 */
    private RectF mBadgeBackgroundRect;
    /** 位置，默认中心 */
    private int mGravity = Gravity.CENTER;

    /**
     * 构造函数
     *
     * @param context 上下文
     */
    public BadgeRadioButton(Context context) {
        super(context);
        init();
    }

    /**
     * 构造函数
     *
     * @param context 上下文
     * @param attrs 属性
     */
    public BadgeRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * 构造函数
     *
     * @param context 上下文
     * @param attrs 属性
     * @param defStyle 样式
     */
    public BadgeRadioButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (null != mDrawableBackground && null != mBadgeText) {
            showShadowImpl(mBadgeShowShadow, mBadgeBackgroundPaint);
            float maxPadding = getOffSize();
            if (mBadgePadding > maxPadding) {
                if (mBadgePadding - maxPadding > mBadgeOffY) {
                    mBadgeOffY = (int) (mBadgePadding - maxPadding);
                }
            }
            drawWithDrawable(canvas);
        } else if (null == mDrawableBackground && null != mBadgeText) {
            showShadowImpl(mBadgeShowShadow, mBadgeBackgroundPaint);
            float maxPadding = getOffSize();
            if (mBadgePadding > maxPadding) {
                if (mBadgePadding - maxPadding > mBadgeOffY) {
                    mBadgeOffY = (int) (mBadgePadding - maxPadding);
                }
            }
            drawWithNoDrawable(canvas);
        }
    }

    /**
     * 无图标时候，绘制标签
     *
     * @param canvas 画布
     */
    private void drawWithNoDrawable(Canvas canvas) {
        float textWidth = getPaint().measureText(getText().toString());
        if (mBadgeText.length() == 0) {
            //绘制圆
            if (mGravity == Gravity.CENTER) {
                canvas.drawCircle((getWidth() + textWidth) / 2 + mBadgeOffX, mFontHeight / 2 + mBadgeOffY, mFontHeight / 2 + mBadgePadding, mBadgeBackgroundPaint);
            } else if (mGravity == Gravity.RIGHT) {
                canvas.drawCircle(getWidth() - mFontWidth / 2 - mBadgePadding, mFontHeight / 2 + mBadgeOffY, mFontHeight / 2 + mBadgePadding, mBadgeBackgroundPaint);
            } else if (mGravity == Gravity.LEFT) {
                canvas.drawCircle(mFontWidth / 2 + mBadgePadding, mFontHeight / 2 + mBadgeOffY, mFontHeight / 2 + mBadgePadding, mBadgeBackgroundPaint);
            }
        } else {
            //多个字符，绘制椭圆
            mBadgeBackgroundRect.left = (getWidth() + textWidth) / 2 - mFontWidth / 2 - mBadgePadding + mBadgeOffX;
            mBadgeBackgroundRect.right = (getWidth() + textWidth) / 2 + mFontWidth / 2 + mBadgePadding + mBadgeOffX;
            mBadgeBackgroundRect.top = -mBadgePadding + mBadgeOffY;
            mBadgeBackgroundRect.bottom = mFontHeight + mBadgePadding + mBadgeOffY;
            if (mGravity == Gravity.RIGHT) {
                mBadgeBackgroundRect.left = getWidth() - mFontWidth - mBadgePadding * 2;
                mBadgeBackgroundRect.right = getWidth();
            } else if (mGravity == Gravity.LEFT) {
                mBadgeBackgroundRect.left = 0;
                mBadgeBackgroundRect.right = mFontWidth + mBadgePadding * 2;
            }
            canvas.drawRoundRect(mBadgeBackgroundRect, mFontHeight / 2 + mBadgePadding, mFontHeight / 2 + mBadgePadding, mBadgeBackgroundPaint);
        }
        //绘制文本
        if (mGravity == Gravity.RIGHT) {
            canvas.drawText(mBadgeText, getWidth() - mFontWidth - mBadgePadding, mFontHeight + mBadgeOffY, mBadgeTextPaint);
        } else if (mGravity == Gravity.LEFT) {
            canvas.drawText(mBadgeText, mBadgePadding, mFontHeight + mBadgeOffY, mBadgeTextPaint);
        } else {
            canvas.drawText(mBadgeText, (getWidth() + textWidth) / 2 - mFontWidth / 2 + mBadgeOffX, mFontHeight + mBadgeOffY, mBadgeTextPaint);
        }
    }

    /**
     * 有图标时候，绘制标签
     *
     * @param canvas 画布
     */
    private void drawWithDrawable(Canvas canvas) {
        if (mBadgeText.length() == 0) {
            //绘制圆
            if (mGravity == Gravity.CENTER) {
                canvas.drawCircle((getWidth() + mDrawableBackground.getIntrinsicWidth()) / 2 + mBadgeOffX, mFontHeight / 2 + mBadgeOffY, mFontHeight / 2 + mBadgePadding, mBadgeBackgroundPaint);
            } else if (mGravity == Gravity.RIGHT) {
                canvas.drawCircle(getWidth() - mFontWidth / 2 - mBadgePadding, mFontHeight / 2 + mBadgeOffY, mFontHeight / 2 + mBadgePadding, mBadgeBackgroundPaint);
            } else if (mGravity == Gravity.LEFT) {
                canvas.drawCircle(mFontWidth / 2 + mBadgePadding, mFontHeight / 2 + mBadgeOffY, mFontHeight / 2 + mBadgePadding, mBadgeBackgroundPaint);
            }
        } else {
            //多个字符，绘制椭圆
            mBadgeBackgroundRect.left = (getWidth() + mDrawableBackground.getIntrinsicWidth()) / 2 - mFontWidth / 2 - mBadgePadding + mBadgeOffX;
            mBadgeBackgroundRect.right = (getWidth() + mDrawableBackground.getIntrinsicWidth()) / 2 + mFontWidth / 2 + mBadgePadding + mBadgeOffX;
            mBadgeBackgroundRect.top = -mBadgePadding + mBadgeOffY;
            mBadgeBackgroundRect.bottom = mFontHeight + mBadgePadding + mBadgeOffY;
            if (mGravity == Gravity.RIGHT) {
                mBadgeBackgroundRect.left = getWidth() - mFontWidth - mBadgePadding * 2;
                mBadgeBackgroundRect.right = getWidth();
            } else if (mGravity == Gravity.LEFT) {
                mBadgeBackgroundRect.left = 0;
                mBadgeBackgroundRect.right = mFontWidth + mBadgePadding * 2;
            }
            canvas.drawRoundRect(mBadgeBackgroundRect, mFontHeight / 2 + mBadgePadding, mFontHeight / 2 + mBadgePadding, mBadgeBackgroundPaint);
        }
        //绘制文本
        if (mGravity == Gravity.RIGHT) {
            canvas.drawText(mBadgeText, getWidth() - mFontWidth - mBadgePadding, mFontHeight + mBadgeOffY, mBadgeTextPaint);
        } else if (mGravity == Gravity.LEFT) {
            canvas.drawText(mBadgeText, mBadgePadding, mFontHeight + mBadgeOffY, mBadgeTextPaint);
        } else {
            canvas.drawText(mBadgeText, (getWidth() + mDrawableBackground.getIntrinsicWidth()) / 2 - mFontWidth / 2 + mBadgeOffX, mFontHeight + mBadgeOffY, mBadgeTextPaint);
        }
    }

    /**
     * 设置显示数字
     *
     * @param badgeNumber 标记数字
     */
    public BadgeRadioButton setBadgeNumber(int badgeNumber) {
        mBadgeNumber = badgeNumber;
        if (mBadgeNumber < 0) {
            mBadgeText = null;
        } else if (mBadgeNumber > 99) {
            mBadgeText = mBadgeExact ? String.valueOf(mBadgeNumber) : "99+";
        } else if (mBadgeNumber > 0 && mBadgeNumber <= 99) {
            mBadgeText = String.valueOf(mBadgeNumber);
        } else if (mBadgeNumber == 0) {
            mBadgeText = "";
        }
        return invalidateLayout();
    }

    /**
     * 设置显示数字
     *
     * @param badge 标记文本
     */
    public BadgeRadioButton setBadgeText(String badge) {
        mBadgeText = badge;
        return invalidateLayout();
    }

    /**
     * 重新刷新界面
     */
    private BadgeRadioButton invalidateLayout() {
        if (!TextUtils.isEmpty(mBadgeText)) {
            measureText();
        }
        invalidate();
        return this;
    }

    /**
     * 测量文本高度和宽度
     */
    private void measureText() {
        mFontHeight = Math.abs(mBadgeTextPaint.getFontMetrics().descent + mBadgeTextPaint.getFontMetrics().ascent);
        mFontWidth = mBadgeTextPaint.measureText(mBadgeText);
    }

    /**
     * 为画笔设置阴影
     *
     * @param showShadow 是否显示
     * @param badgeBackgroundPaint 画笔
     */
    private void showShadowImpl(boolean showShadow, Paint badgeBackgroundPaint) {
        int x = dp2px(1);
        int y = dp2px(1.5f);
        badgeBackgroundPaint.setShadowLayer(showShadow ? dp2px(2f) : 0, x, y, 0x33000000);
    }

    @Override
    void init() {
        super.init();
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        mBadgeTextSize = dp2px(10);
        mBadgePadding = dp2px(4);
        mBadgeShowShadow = true;
        mBadgeColorBackground = Color.RED;
        mBadgeColorBadgeText = Color.WHITE;
        mBadgeOffX = 0;
        mBadgeOffY = 0;
        mFontWidth = 0;
        mFontHeight = 0;
        mBadgeBackgroundRect = new RectF(0, 0, 0, 0);

        //目前只支持drawableTop的RadioButton
        mDrawables = getCompoundDrawables();
        if (null != mDrawables[1]) {
            mDrawableBackground = mDrawables[1];
        }
        /* 理论上可以支持drawableRight，但是没测试
       else if (null != mDrawables[2]) {
            mDrawableBackground = mDrawables[2];
        }*/

        mBadgeTextPaint = new TextPaint();
        mBadgeTextPaint.setAntiAlias(true);
        mBadgeTextPaint.setSubpixelText(true);
        mBadgeTextPaint.setFakeBoldText(true);
        mBadgeTextPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        mBadgeTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mBadgeTextPaint.setDither(true);
        mBadgeTextPaint.setColor(mBadgeColorBadgeText);
        mBadgeTextPaint.setTextSize(mBadgeTextSize);

        mBadgeBackgroundPaint = new Paint();
        mBadgeBackgroundPaint.setAntiAlias(true);
        mBadgeBackgroundPaint.setStyle(Paint.Style.FILL);
        mBadgeBackgroundPaint.setDither(true);
        mBadgeBackgroundPaint.setColor(mBadgeColorBackground);
        showShadowImpl(mBadgeShowShadow, mBadgeBackgroundPaint);
    }

    /**
     * 设置字体大小，默认8dp
     *
     * @param badgeTextSize 内边距
     */
    public BadgeRadioButton setBadgeTextSize(float badgeTextSize) {
        mBadgeTextSize = badgeTextSize;
        mBadgeTextPaint.setTextSize(mBadgeTextSize);
        return invalidateLayout();
    }

    /**
     * 设置标记的位置
     * 目前仅支持Gravity.LEFT Gravity.RIGHT Gravity.CENTER，默认Gravity.CENTER
     *
     * @param gravity {@link Gravity}
     */
    public BadgeRadioButton setBadgeGravity(int gravity) {
        mGravity = gravity;
        return invalidateLayout();
    }

    /**
     * 设置内边距，默认4dp
     *
     * @param badgePadding 内边距
     */
    public BadgeRadioButton setBadgePadding(int badgePadding) {
        mBadgePadding = badgePadding;
        return invalidateLayout();
    }

    /**
     * 设置是否显示阴影，默认显示
     *
     * @param badgeShowShadow true - 显示
     */
    public BadgeRadioButton setBadgeShowShadow(boolean badgeShowShadow) {
        mBadgeShowShadow = badgeShowShadow;
        return invalidateLayout();
    }

    /**
     * 设置背景颜色，默认红色
     *
     * @param badgeColorBackground 背景颜色
     */
    public BadgeRadioButton setBadgeColorBackground(@ColorInt int badgeColorBackground) {
        mBadgeColorBackground = badgeColorBackground;
        mBadgeBackgroundPaint.setColor(mBadgeColorBackground);
        return invalidateLayout();
    }

    /**
     * 设置标记字体颜色，默认白色
     *
     * @param badgeColorBadgeText 字体颜色
     */
    public BadgeRadioButton setBadgeColorBadgeText(int badgeColorBadgeText) {
        mBadgeColorBadgeText = badgeColorBadgeText;
        mBadgeTextPaint.setColor(mBadgeColorBadgeText);
        return invalidateLayout();
    }

    /**
     * 设置X偏移量
     *
     * @param badgeOffX x偏移量
     */
    public BadgeRadioButton setBadgeOffX(int badgeOffX) {
        mBadgeOffX = badgeOffX;
        return invalidateLayout();
    }

    /**
     * 设置Y偏移量
     *
     * @param badgeOffY Y偏移量
     */
    public BadgeRadioButton setBadgeOffY(int badgeOffY) {
        mBadgeOffY = badgeOffY;
        return invalidateLayout();
    }

    /**
     * 是否截取字体，默认截取，如 100 -> 99+
     *
     * @param badgeExact true - 截取
     */
    public BadgeRadioButton setBadgeExact(boolean badgeExact) {
        mBadgeExact = badgeExact;
        return invalidateLayout();
    }

    /**
     * dp转px
     *
     * @param dpValue dp值
     * @return px值
     */
    public int dp2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
