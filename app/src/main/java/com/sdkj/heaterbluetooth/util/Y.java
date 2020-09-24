package com.sdkj.heaterbluetooth.util;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.lzy.okgo.model.Response;
import com.sdkj.heaterbluetooth.app.MyApplication;
import com.sdkj.heaterbluetooth.app.MyApplication;

import java.text.DecimalFormat;

/**
 * 开发工具类
 */
public class Y {


    private static final boolean open = true;

    public static void i(String str) {
        if (open)
            Log.i("安卓开发", "----------------------------     " + str + "      -------------------------------");
    }

    public static void e(String bodyMsg) {
        if (open) {
            if (bodyMsg.length() > 4000) {
                for (int i = 0; i < bodyMsg.length(); i += 4000) {
                    if (i + 4000 < bodyMsg.length()) {
                        Log.e("安卓长开发", bodyMsg.substring(i, i + 4000));
                    } else {
                        //当前截取的长度已经超过了总长度，则打印出剩下的全部信息
                        Log.e("安卓长开发", bodyMsg.substring(i, bodyMsg.length()));
                    }
                }
            } else {
                //直接打印
                Log.e("安卓开发:", "----------------------------     " + bodyMsg + "      -------------------------------");
            }
        }
    }

    public static void t(String str) {
        Toast.makeText(MyApplication.getInstance().getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }

    public static void tLong(String str) {
        Toast.makeText(MyApplication.getInstance().getApplicationContext(), str, Toast.LENGTH_LONG).show();
    }

    public static void tError(Response response) {
        String msg = response.getException().getMessage();
        String[] msgToast = msg.split("：");
        if (msgToast.length == 3) {
            Y.t(msgToast[2]);
        } else {
            Y.t("网络异常");
        }
    }

    public static Resources getResources() {
        return MyApplication.getInstance().getApplicationContext().getResources();
    }

    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    public static int getDimen(int resId) {
        return (int) getResources().getDimension(resId);
    }


    public static int getInt(String content) {
        if (!TextUtils.isEmpty(content)) {
            try {
                return (int) getDouble(content);
            } catch (Exception e) {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public static double getDouble(String content) {
        if (!TextUtils.isEmpty(content)) {
            try {
                return Double.parseDouble(content);
            } catch (Exception e) {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public static float getFloat(String content) {
        if (!TextUtils.isEmpty(content)) {
            try {
                return Float.parseFloat(content);
            } catch (Exception e) {
                return 0;
            }
        } else {
            return 0;
        }
    }


    public static String formatNum(float num) {
        String format = new DecimalFormat("#.#").format(num);
        return format;
    }

    /**
     * 获取Money
     */
    public static String getMoney(double money) {
        String format = new DecimalFormat("#.##").format(money);
        return format;
    }

    /**
     * 获取Money
     */
    public static String getMoney(float money) {
        String format = new DecimalFormat("#.##").format(money);
        return format;
    }


    /**
     * 获得屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获得屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * 获得控件高度
     *
     * @param context
     * @return
     */
    public static int getViewHeght(View view, Context context) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        int height = view.getMeasuredHeight();
        return height;
    }

    /**
     * 获得控件宽度
     *
     * @param context
     * @return
     */
    public static int getViewWidth(View view, Context context) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        int width = view.getMeasuredWidth();
        return width;
    }

    public static void setViewMarginTop(View view, int top) {
        ViewGroup.MarginLayoutParams p = getViewMarginLayoutParams(view);
        if (p != null) {
            p.topMargin = top;
            view.setLayoutParams(p);
        }
    }

    public static void setViewMarginLeft(View view, int left) {
        ViewGroup.MarginLayoutParams p = getViewMarginLayoutParams(view);
        if (p != null) {
            p.leftMargin = left;
            view.setLayoutParams(p);
        }
    }

    public static void setViewMarginBottom(View view, int bottom) {
        ViewGroup.MarginLayoutParams p = getViewMarginLayoutParams(view);
        if (p != null) {
            p.bottomMargin = bottom;
            view.setLayoutParams(p);
        }
    }

    public static void setViewMarginRight(View view, int right) {
        ViewGroup.MarginLayoutParams p = getViewMarginLayoutParams(view);
        if (p != null) {
            p.rightMargin = right;
            view.setLayoutParams(p);
        }
    }

    public static boolean setViewHeight(View view, int height) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params != null) {
            if (params.height != height) {
                params.height = height;
                view.setLayoutParams(params);
            }
            return true;
        }
        return false;
    }

    public static boolean setViewWidth(View view, int width) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params != null) {
            if (params.width != width) {
                params.width = width;
                view.setLayoutParams(params);
            }
            return true;
        }
        return false;
    }


    public static ViewGroup.MarginLayoutParams getViewMarginLayoutParams(View view) {
        ViewGroup.MarginLayoutParams result = null;
        if (view != null) {
            ViewGroup.LayoutParams params = view.getLayoutParams();
            if (params != null && params instanceof ViewGroup.MarginLayoutParams) {
                result = (ViewGroup.MarginLayoutParams) params;
            }
        }
        return result;
    }
}
