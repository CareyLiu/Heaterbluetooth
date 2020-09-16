package com.sdkj.heaterbluetooth.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.viewpager.widget.ViewPager;
import com.bumptech.glide.Glide;
import com.sdkj.heaterbluetooth.R;
import com.sdkj.heaterbluetooth.adapter.WelcomeAdapter;
import com.sdkj.heaterbluetooth.app.BaseActivity;
import com.sdkj.heaterbluetooth.app.PreferenceHelper;
import com.sdkj.heaterbluetooth.basepage.HomeBasicActivity;


import java.util.ArrayList;
import java.util.List;

/**
 * 实现首次启动的引导页面
 */
public class WelcomeActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private int[] imageIdArray;//图片资源的数组

    //参考自http://stackoverflow.com/questions/32061934/permission-from-manifest-doesnt-work-in-android-6
    public static int OVERLAY_PERMISSION_REQ_CODE = 1234;

    Context context;
    //最后一页的按钮
    private ImageButton ib_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ib_start = (ImageButton) findViewById(R.id.guide_ib_start);
        ib_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PreferenceHelper.getInstance(WelcomeActivity.this).getBoolean("ISLOGIN", false)) {
                    startActivity(new Intent(WelcomeActivity.this, HomeBasicActivity.class));
                    finish();
                } else {
                    //startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                    //UIHelper.ToastMessage(mContext, "登陆页面");
                    LoginActivity.actionStart(mContext);
                    finish();
                }
            }
        });

        //加载ViewPager
        initViewPager();
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_welcome;
    }

    /**
     * 加载图片ViewPager
     */
    private void initViewPager() {
        ViewPager vp = (ViewPager) findViewById(R.id.guide_vp);
        //实例化图片资源
        imageIdArray = new int[]{R.drawable.splash_one, R.drawable.splash_two, R.drawable.splash_three};
        List<View> viewList = new ArrayList<>();
        //获取一个Layout参数，设置为全屏
        ViewGroup.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


        for (int anImageIdArray : imageIdArray) {
            //new ImageView并设置全屏和图片资源
            ImageView imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setLayoutParams(params);
            //使用Glide加载高清图片以减少Viewpage的卡顿现象
            Glide.with(mContext).load(anImageIdArray).into(imageView);
            //将ImageView加入到集合中
            viewList.add(imageView);
        }

        //View集合初始化好后，设置Adapter
        vp.setAdapter(new WelcomeAdapter(viewList));
        //设置滑动监听
        vp.addOnPageChangeListener(this);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * 滑动后的监听
     */
    @Override
    public void onPageSelected(int position) {
        //判断是否是最后一页，若是则显示按钮
        if (position == imageIdArray.length - 1) {
            ib_start.setVisibility(View.VISIBLE);
        } else {
            ib_start.setVisibility(View.GONE);
        }
    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}