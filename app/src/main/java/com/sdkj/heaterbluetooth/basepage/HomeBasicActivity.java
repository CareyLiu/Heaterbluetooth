package com.sdkj.heaterbluetooth.basepage;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import com.blankj.utilcode.util.StringUtils;
import com.google.gson.Gson;
import com.sdkj.heaterbluetooth.R;
import com.sdkj.heaterbluetooth.activity.DiagnosisActivity;
import com.sdkj.heaterbluetooth.app.App;
import com.sdkj.heaterbluetooth.app.AppManager;
import com.sdkj.heaterbluetooth.app.BaseActivity;
import com.sdkj.heaterbluetooth.app.ConstanceValue;
import com.sdkj.heaterbluetooth.app.Notice;
import com.sdkj.heaterbluetooth.app.PreferenceHelper;
import com.sdkj.heaterbluetooth.basicmvp.BasicFragment;
import com.sdkj.heaterbluetooth.dialog.MyCarCaoZuoDialog_Notify;
import com.sdkj.heaterbluetooth.fragment.ShebeiFrament;
import com.sdkj.heaterbluetooth.fragment.ShuoMingFragment;
import com.sdkj.heaterbluetooth.fragment.WoDeFragment;
import com.sdkj.heaterbluetooth.model.AlarmClass;
import com.sdkj.heaterbluetooth.util.DoMqttValue;
import com.sdkj.heaterbluetooth.util.SoundPoolUtils;
import com.sdkj.heaterbluetooth.view.BottomBar;
import com.sdkj.heaterbluetooth.view.BottomBarTab;
import com.sdkj.heaterbluetooth.view.DoubleClickExitHelper;

import static com.sdkj.heaterbluetooth.app.MyApplication.getAppContext;


public class HomeBasicActivity extends BaseActivity {

    private static final String tag = HomeBasicActivity.class.getSimpleName();

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;

    private final int VISIBLESTATE = 1;//可见
    private final int INVISIBLESTATE = 0;//不可见

    public static BottomBar mBottomBar;
    private BasicFragment[] mFragments = new BasicFragment[5];
    DoubleClickExitHelper doubleClick;
    private App app;
    public static HomeBasicActivity ac;
    AlarmClass alarmClass;

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, HomeBasicActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_home_basic;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        overridePendingTransition(R.anim.anim_bottom_in, R.anim.anim_bottom_out);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.anim_bottom_in, R.anim.anim_bottom_out);
        ac = this;
        BasicFragment firstFragment = findFragment(ShebeiFrament.class);
        if (firstFragment == null) {
            mFragments[FIRST] = ShebeiFrament.newInstance();
            mFragments[SECOND] = ShuoMingFragment.newInstance();
            mFragments[THIRD] = WoDeFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_container1, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD]);
        }

        mBottomBar = (BottomBar) findViewById(R.id.bottomBar);
        mBottomBar.show();
        mBottomBar
                .addItem(new BottomBarTab(this, R.mipmap.tab_icon_shebei_nor, R.mipmap.tab_icon_shebei_sel, "设备"))
                .addItem(new BottomBarTab(this, R.mipmap.tab_icon_shuomingshu_nor, R.mipmap.tab_icon_shuomingshu_sel, "说明"))
                .addItem(new BottomBarTab(this, R.mipmap.tab_icon_wode_nor, R.mipmap.tab_icon_wode_sel, "我的"));

        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
                switch (position) {

                }
                // 在FirstPagerFragment中接收, 因为是嵌套的孙子Fragment 所以用EventBus比较方便
                // 主要为了交互: 重选tab 如果列表不在顶部则移动到顶部,如果已经在顶部,则刷新
                // EventBusActivityScope.getDefault(MainActivity.this).post(new TabSelectedEvent(position));
            }
        });
        app = App.getInstance();
        doubleClick = new DoubleClickExitHelper(this);

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice notice) {
                if (notice.type == ConstanceValue.MSG_GUZHANG_SHOUYE) {

                    String xiangmu = PreferenceHelper.getInstance(mContext).getString(App.CHOOSE_KONGZHI_XIANGMU, "");

                    if (!StringUtils.isEmpty(xiangmu)){
                        return;
                    }

                    String message = (String) notice.content;
                    Gson gson = new Gson();
                    alarmClass = gson.fromJson(message.toString(), AlarmClass.class);
                    Log.i("alarmClass", alarmClass.changjia_name + alarmClass.sell_phone);
//
//                    if (player != null) {
//                        player.stop();
//                        player.release();
//                        audioFocusManage.releaseTheAudioFocus();
//                        player = null;
//                    }


                    switch (alarmClass.sound) {

                        case "chSound1.mp3":
                            // SoundPoolUtils.soundPool(mContext,R.raw.ch_sound1);
                            playMusic(R.raw.ch_sound1);
                            break;
                        case "chSound2.mp3":
                            playMusic(R.raw.ch_sound2);
                            break;
                        case "chSound3.mp3":
                            playMusic(R.raw.ch_sound3);
                            break;
                        case "chSound4.mp3":
                            playMusic(R.raw.ch_sound4);
                            break;
                        case "chSound5.mp3":
                            playMusic(R.raw.ch_sound5);
                            break;
                        case "chSound6.mp3":
                            playMusic(R.raw.ch_sound6);
                            break;
                        case "chSound8.mp3":
                            playMusic(R.raw.ch_sound8);
                            break;
                        case "chSound9.mp3":
                            playMusic(R.raw.ch_sound9);
                            break;
                        case "chSound10.mp3":
                            playMusic(R.raw.ch_sound10);
                            break;
                        case "chSound11.mp3":
                            playMusic(R.raw.ch_sound11);
                            break;
                        case "chSound18.mp3":
                            playMusic(R.raw.ch_sound18);
                            break;
                    }
                } else if (notice.type == ConstanceValue.MSG_LOGIN) {
                    mBottomBar.setCurrentItem(2);
                }
            }
        }));


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
        app = App.getInstance();
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            /*
             * 注意在if判断中要加一个event.getAction() == KeyEvent.ACTION_DOWN判断，
             * 因为按键有两个事件ACTION_DOWN和ACTION_UP，也就是按下和松开，
             * 如果不加这个判断，代码会执行两遍
             */
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                //exitApp();
                doubleClick.onKeyDown(event.getKeyCode(), event);
            }
            //实现只在冷启动时显示启动页，即点击返回键与点击HOME键退出效果一致
//            Intent intent = new Intent(Intent.ACTION_MAIN);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.addCategory(Intent.CATEGORY_HOME);
//            startActivity(intent);
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    public void playMusic(int res) {
        boolean flag = false;

        Activity currentActivity = AppManager.getAppManager().currentActivity();
        if (currentActivity != null) {
            if (!currentActivity.getClass().getSimpleName().equals(DiagnosisActivity.class.getSimpleName())) {
                MyCarCaoZuoDialog_Notify myCarCaoZuoDialog_notify = new MyCarCaoZuoDialog_Notify(getAppContext(), new MyCarCaoZuoDialog_Notify.OnDialogItemClickListener() {
                    @Override
                    public void clickLeft() {
                        // player.stop();
                        if (SoundPoolUtils.soundPool != null) {
                            SoundPoolUtils.soundPool.release();
                        }

                    }

                    @Override
                    public void clickRight() {
                        DiagnosisActivity.actionStart(HomeBasicActivity.this, alarmClass);
                        //SoundPoolUtils.soundPool.release();
                        if (SoundPoolUtils.soundPool != null) {
                            SoundPoolUtils.soundPool.release();
                        }

                    }
                }
                );

                myCarCaoZuoDialog_notify.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG);
                myCarCaoZuoDialog_notify.show();
                myCarCaoZuoDialog_notify.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        if (SoundPoolUtils.soundPool != null) {
                            SoundPoolUtils.soundPool.release();
                        }
                    }
                });

            } else {
                flag = true;
            }
        }

        if (flag) {
            return;
        }

        SoundPoolUtils.soundPool(mContext, res);

    }
}
