package com.sdkj.heaterbluetooth.view;

/**
 * Created by Administrator on 2017/6/28.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.sdkj.heaterbluetooth.R;
import com.sdkj.heaterbluetooth.util.ConstantUtil;
import com.sdkj.heaterbluetooth.util.SharedPreferenceutils;

import static android.content.Context.ACCOUNT_SERVICE;

/**
 * Created by lenovo on 2017/7/15.
 */

public class ArcProgressBar extends ProgressBar {
    public static final String TAG = "Chunna=ArcProgressBar";
    private SharedPreferenceutils sharedPreferenceutils;
    //radian to angle
    private static final double RADIAN = 180 / Math.PI;
    //radius
    private int mRadius;
    //the paint to draw arc
    private Paint mArcPaint;
    //the paint to draw background
    private Paint mBackPaint;
    //the dot at the arc end position
    private Paint mPointPaint;
    //the dot coordinate X
    private float mPointX;
    //the dot coordinate Y
    private float mPointY;
    //the dot radius
    private float mPointRadius;
    //the dot color
    private int mPointColor;
    //the width of arc
    private float mArcWidth;
    //the arc color
    private int mArcColor;
    //start angle
    private int mStartAngle;
    //initial angle
    private int mCalculateStartAngle;
    //the end angle
    private int mMaxAngle;
    //current angle
    private float mCurrentAngle = 0;
    //current progress
    private int mCurrentProgerss;
    //the max progress
    private int mMaxProgress;
    //background color
    private int mBackcolor;

    private OnProgressChangeListener mListener;

    private Handler handler;

    private long currentLongClickTime = 0;
    private long lastLongClickTime = 0;
    private Boolean isOpen = false;


    public ArcProgressBar(Context context) {
        this(context, null);
    }

    public ArcProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArcProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        sharedPreferenceutils = new SharedPreferenceutils(context, ACCOUNT_SERVICE);
        initView(attrs, defStyleAttr);
    }

    private void initView(AttributeSet attrs, int defStyleAttr) {
        initAttrs(attrs, defStyleAttr);
        initPaint();
    }
    public int getCurrentProgerss(){
        return mCurrentProgerss;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    private void initAttrs(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ArcProgressBar, defStyleAttr, 0);
        mArcWidth = a.getDimension(R.styleable.ArcProgressBar_arc_width, 20);
        mArcColor = a.getColor(R.styleable.ArcProgressBar_arc_color, Color.BLUE);
        mBackcolor = a.getColor(R.styleable.ArcProgressBar_back_color, Color.WHITE);
        mPointRadius = a.getDimension(R.styleable.ArcProgressBar_point_radius, mArcWidth / 2);
        mPointColor = a.getColor(R.styleable.ArcProgressBar_point_color, Color.GREEN);
        mStartAngle = a.getInt(R.styleable.ArcProgressBar_start_angle, 135);
        mMaxAngle = a.getInt(R.styleable.ArcProgressBar_max_angle, 270);
        mCurrentProgerss = a.getInt(R.styleable.ArcProgressBar_current_progress, 0);
        mMaxProgress = a.getInt(R.styleable.ArcProgressBar_max_progress, 100);
        a.recycle();
        //handle angle offset
        mCalculateStartAngle = mStartAngle % 90;
    }

    private void initPaint() {
        //init background paint
        mBackPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackPaint.setColor(mBackcolor);
        mBackPaint.setStrokeWidth(mArcWidth);
        mBackPaint.setStyle(Paint.Style.STROKE);
        //mBackPaint.setStrokeCap(Paint.Cap.ROUND);
        //init arc paint
        mArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mArcPaint.setColor(mArcColor);
        mArcPaint.setStrokeWidth(mArcWidth);
        mArcPaint.setStyle(Paint.Style.STROKE);
        //mArcPaint.setStrokeCap(Paint.Cap.ROUND);
        //init dot paint
        mPointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPointPaint.setColor(mPointColor);
        mPointPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);
        //get the min value of width and height
        //int diameter=Math.min(width,height);
        setMeasuredDimension(width, height);

    }

    private int measureWidth(int widthMeasureSpec) {
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int result;
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 30;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    private int measureHeight(int heightMeasureSpec) {
        return measureWidth(heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRadius = Math.min(w - getPaddingLeft() - getPaddingRight(), h - getPaddingTop() - getPaddingBottom()) / 2;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF reactF = new RectF(getPaddingLeft() + mArcWidth, getPaddingTop() + mArcWidth, getPaddingLeft() + 2 * mRadius - mArcWidth, getPaddingTop() + 2 * mRadius - mArcWidth);
        canvas.drawArc(reactF, mStartAngle, mMaxAngle, false, mBackPaint);
        canvas.drawArc(reactF, mStartAngle, mCurrentAngle, false, mArcPaint);
        if (mCurrentAngle >= 0) {
            if (mPointX == 0) {
                mPointX = calculatePointX(true, Math.sin((90 - (float) (90 - Math.asin((double) (321 - mRadius) / (Math.sqrt(Math.pow(94 - mRadius, 2) + Math.pow(321 - mRadius, 2)))) * RADIAN)) / RADIAN));
                mPointY = calculatePointY((double) (321 - mRadius) / Math.sqrt(Math.pow(94 - mRadius, 2) + Math.pow(321 - mRadius, 2)));

            }
            //canvas.drawCircle(mPointX, mPointY, mPointRadius, mPointPaint);
        }
    }

    /**
     * dip change to px
     *
     * @param dip
     * @return
     */
    private int dipToPx(float dip) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f * (dip >= 0 ? 1 : -1));
    }

    /**
     * get the screen width
     *
     * @return
     */
    private int getScreenWidth() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public void setOpen(Boolean isOpen){
        this.isOpen = isOpen;
    }
    public Boolean getIsOpen(){
        return isOpen;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isOpen) {
            return true;
        }

        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!isValid(x, y)) {
                    return false;
                }
                calculateAngle(x, y);
                calculateProgress();
                break;
            case MotionEvent.ACTION_MOVE:
                calculateAngle(x, y);
                calculateProgress();
                break;
            case MotionEvent.ACTION_UP:
                calculateAngle(x, y);
                //add Viscous effect
                if (mCurrentAngle <= 5) {
                    mCurrentAngle = 0;
                }
                currentLongClickTime = System.currentTimeMillis();
                long intervalTime = (currentLongClickTime - lastLongClickTime);
                Log.d(TAG, "===intervalTime = " + intervalTime);
                if (intervalTime < 1000) {
                    Toast.makeText(getContext(), R.string.text_heater_click_ofen, Toast.LENGTH_SHORT).show();
                    return true;
                }
                calculateProgress();
                Message msg = Message.obtain();
                msg.what = ConstantUtil.MSG_HEATER_ROGRESS_VALUE_CHANGE;
                Bundle b = new Bundle();
                b.putInt("progress_value", mCurrentProgerss);
                msg.setData(b);
                Log.d(TAG, "send progress chage message");
                handler.sendMessage(msg);

                lastLongClickTime = currentLongClickTime;
                break;
        }
        invalidate();
        return true;
    }

    private void calculateAngle(float x, float y) {
        float angle;
        //hypotenuse
        double hypotenuse;
        hypotenuse = Math.sqrt(Math.pow(x - mRadius, 2) + Math.pow(y - mRadius, 2));
        double sin = (double) (y - mRadius) / hypotenuse;
        float pointX;
        float pointY;
        boolean isLeft = x - mRadius < 0;
        if (isLeft) {
            angle = (float) (90 - Math.asin(sin) * RADIAN);
            //Calculate the dot coordinates
            pointX = calculatePointX(isLeft, sin);
            pointY = calculatePointY(sin);
            Log.d("c_angle", "left:" + angle);
        } else {
            angle = (float) (180 + 90 + Math.asin(sin) * RADIAN);
            //Calculate the dot coordinates
            pointX = calculatePointX(isLeft, sin);
            pointY = calculatePointY(sin);
            Log.d("c_angle", "right:" + angle);
        }
        if (angle >= mCalculateStartAngle && angle <= mMaxAngle + mCalculateStartAngle) {
            mCurrentAngle = Math.round(angle - mCalculateStartAngle);
            Log.d("cur_angle", "mCurrentAngle:" + mCurrentAngle);
            mPointX = pointX;
            mPointY = pointY;
        }
    }

    /**
     * Calculate the dot coordinates X
     *
     * @param isLeft Determine whether the point is located in the left half of the arc
     * @param sin    #calculateAngle Calculate the sin value
     * @return the coordinates X of dot
     */
    private float calculatePointX(boolean isLeft, double sin) {
        return isLeft ? (float) (mRadius - (mRadius - mArcWidth) * Math.sqrt(1 - sin * sin)) + getPaddingLeft() :
                (float) (mRadius + (mRadius - mArcWidth) * Math.sqrt(1 - sin * sin)) + getPaddingLeft();
    }

    private float calculatePointY(double sin) {
        return (float) (mRadius + (mRadius - mArcWidth) * sin) + getPaddingTop();
    }

    private void calculateProgress() {  //角度赋值
        Log.d(TAG, "Chunna==aaaaaaaaa");
        mCurrentProgerss = Math.round(mCurrentAngle / mMaxAngle * mMaxProgress);
        int showdata_bit = Integer.parseInt(sharedPreferenceutils.getHostStatus());
        if ((showdata_bit <= 5) && (showdata_bit >= 1)) {
            if (mCurrentProgerss <= 18) {
                mCurrentProgerss = 18;
            } else if (mCurrentProgerss > 18 && mCurrentProgerss <= 36) {
                mCurrentProgerss = 36;
            } else if (mCurrentProgerss > 36 && mCurrentProgerss <= 54) {
                mCurrentProgerss = 54;
            } else if (mCurrentProgerss > 54 && mCurrentProgerss <= 72) {
                mCurrentProgerss = 72;
            } else if (mCurrentProgerss > 72 && mCurrentProgerss <= 90) {
                mCurrentProgerss = 90;
            }
        } else if ((showdata_bit <= 32) && (showdata_bit >= 15)) {
            if (mCurrentProgerss <= 5) {
                mCurrentProgerss = 5;
            } else if (mCurrentProgerss > 5 && mCurrentProgerss <= 10) {
                mCurrentProgerss = 10;
            } else if (mCurrentProgerss > 10 && mCurrentProgerss <= 15) {
                mCurrentProgerss = 15;
            } else if (mCurrentProgerss > 15 && mCurrentProgerss <= 20) {
                mCurrentProgerss = 20;
            } else if (mCurrentProgerss > 20 && mCurrentProgerss <= 25) {
                mCurrentProgerss = 25;
            } else if (mCurrentProgerss > 25 && mCurrentProgerss <= 30) {
                mCurrentProgerss = 30;
            } else if (mCurrentProgerss > 30 && mCurrentProgerss <= 35) {
                mCurrentProgerss = 35;
            } else if (mCurrentProgerss > 35 && mCurrentProgerss <= 40) {
                mCurrentProgerss = 40;
            } else if (mCurrentProgerss > 40 && mCurrentProgerss <= 45) {
                mCurrentProgerss = 45;
            } else if (mCurrentProgerss > 45 && mCurrentProgerss <= 50) {
                mCurrentProgerss = 50;
            } else if (mCurrentProgerss > 50 && mCurrentProgerss <= 55) {
                mCurrentProgerss = 55;
            } else if (mCurrentProgerss > 55 && mCurrentProgerss <= 60) {
                mCurrentProgerss = 60;
            } else if (mCurrentProgerss > 60 && mCurrentProgerss <= 65) {
                mCurrentProgerss = 65;
            } else if (mCurrentProgerss > 65 && mCurrentProgerss <= 70) {
                mCurrentProgerss = 70;
            } else if (mCurrentProgerss > 70 && mCurrentProgerss <= 75) {
                mCurrentProgerss = 75;
            } else if (mCurrentProgerss > 75 && mCurrentProgerss <= 80) {
                mCurrentProgerss = 80;
            } else if (mCurrentProgerss > 80 && mCurrentProgerss <= 85) {
                mCurrentProgerss = 85;
            } else if (mCurrentProgerss > 85 && mCurrentProgerss <= 90) {
                mCurrentProgerss = 90;
            }
        } else {
        }
        mCurrentAngle = (float) mCurrentProgerss / mMaxProgress * mMaxAngle;
        float angle = mCurrentAngle + mCalculateStartAngle;
        //with calculateAngle method ,get the value of sin opposition
        boolean isLeft = angle <= 180;
        if (isLeft) {
            mPointX = calculatePointX(isLeft, Math.sin((90 - angle) / RADIAN));
            mPointY = calculatePointY(Math.sin((90 - angle) / RADIAN));
        } else {
            mPointX = calculatePointX(isLeft, Math.sin((angle - 180 - 90) / RADIAN));
            mPointY = calculatePointY(Math.sin((angle - 180 - 90) / RADIAN));
        }
        invalidate();
        if (mListener != null) {
            mListener.onProgress(mCurrentProgerss);
        }
    }


    /**
     * Determine whether the point is within the radius of the arc
     *
     * @param x
     * @param y
     * @return true is in radius ,false not in radius
     */
    private boolean isValid(float x, float y) {
        return Math.pow(x - mRadius - getPaddingLeft(), 2) + Math.pow(y - mRadius - getPaddingTop(), 2) <= mRadius * mRadius;
    }

    public void setCurrentProgress(int progress) {
        if (progress > mMaxProgress) {
            throw new IllegalArgumentException("progress must < mMaxProgress");
        }
        mCurrentProgerss = progress;
        mCurrentAngle = (float) mCurrentProgerss / mMaxProgress * mMaxAngle;
        float angle = mCurrentAngle + mCalculateStartAngle;
        //with calculateAngle method ,get the value of sin opposition
        boolean isLeft = angle <= 180;
        if (isLeft) {
            mPointX = calculatePointX(isLeft, Math.sin((90 - angle) / RADIAN));
            mPointY = calculatePointY(Math.sin((90 - angle) / RADIAN));
        } else {
            mPointX = calculatePointX(isLeft, Math.sin((angle - 180 - 90) / RADIAN));
            mPointY = calculatePointY(Math.sin((angle - 180 - 90) / RADIAN));
        }
        invalidate();
    }


    public void setOnProgressChangeListener(OnProgressChangeListener listener) {
        this.mListener = listener;
    }

    public interface OnProgressChangeListener {
        void onProgress(int progress);
    }
}