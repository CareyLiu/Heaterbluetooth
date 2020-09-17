package com.sdkj.heaterbluetooth.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sdkj.heaterbluetooth.R;


/**
 * Created by Chunna.zheng on 2017/11/21.
 */
public class ItemTitleInfo extends RelativeLayout {
    private TextView mTitle;
    private TextView mInfo;
    private ImageView mImage;

    private String title;
    private float titleSize;
    private int titleColor;
    private String info;
    private float infoSize;
    private int infoColor;
    private boolean isImageHide;

    private float DEFAULT_TITLE_SIZE = 15;
    private int DEFAULT_TITLE_COLOR = getResources().getColor(R.color.gray);
    private float DEFAULT_INFO_SIZE = 15;
    private int DEFAULT_INFO_COLOR = getResources().getColor(R.color.gray);
    private boolean DEFAULT_IMAGE_HIDE = false;

    public ItemTitleInfo(Context context) {
        super(context);
    }

    public ItemTitleInfo(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);//加载属性值
        initView(context);//加载布局
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ItemTitleInfo);
        title = typedArray.getString(R.styleable.ItemTitleInfo_tinfo_title);
        titleSize = typedArray.getDimension(R.styleable.ItemTitleInfo_tinfo_title_size, DEFAULT_TITLE_SIZE);
        titleColor = typedArray.getColor(R.styleable.ItemTitleInfo_tinfo_title_color, DEFAULT_TITLE_COLOR);
        info = typedArray.getString(R.styleable.ItemTitleInfo_tinfo_info);
        infoSize = typedArray.getDimension(R.styleable.ItemTitleInfo_tinfo_info_size, DEFAULT_INFO_SIZE);
        infoColor = typedArray.getColor(R.styleable.ItemTitleInfo_tinfo_info_color, DEFAULT_INFO_COLOR);
        isImageHide = typedArray.getBoolean(R.styleable.ItemTitleInfo_tinfo_image_hide, DEFAULT_IMAGE_HIDE);
        typedArray.recycle();
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_userinfo_item, this);
        mTitle = (TextView) findViewById(R.id.title);
        mInfo = (TextView) findViewById(R.id.info);
        mImage = (ImageView) findViewById(R.id.imgview);

        mTitle.setText(title);
        mTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, titleSize);
        //mTitle.getPaint().setTextSize(titleSize);//如果不设置这个文字会变得非常大
        //mTitle.setTextColor(titleColor);

        mInfo.setText(info);
        mInfo.setTextSize(TypedValue.COMPLEX_UNIT_SP, infoSize);
        //mInfo.getPaint().setTextSize(infoSize);//如果不设置这个文字会变得非常大
        //mInfo.setTextColor(infoColor);

        if (isImageHide) {
            mImage.setVisibility(INVISIBLE);
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        mTitle.setText(title);
    }

    public float getTitleSize() {
        return titleSize;
    }

    public void setTitleSize(float titleSize) {
        this.titleSize = titleSize;
        mTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, titleSize);
        //mTitle.getPaint().setTextSize(titleSize);//如果不设置这个文字会变得非常大
    }

    public int getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
        mTitle.setTextColor(titleColor);
    }

    public String getInfo() {
        if (null == info) {
            return "";
        }
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
        mInfo.setText(info);
    }

    public void setHide(boolean hide) {
        isImageHide = hide;
    }

    public float getInfoSize() {
        return infoSize;
    }

    public void setInfoSize(float infoSize) {
        this.infoSize = infoSize;
        mInfo.setTextSize(TypedValue.COMPLEX_UNIT_SP, infoSize);
        //mInfo.getPaint().setTextSize(infoSize);//如果不设置这个文字会变得非常大
    }

    public int getInfoColor() {
        return infoColor;
    }

    public void setInfoColor(int infoColor) {
        this.infoColor = infoColor;
        mInfo.setTextColor(infoColor);
    }
}
