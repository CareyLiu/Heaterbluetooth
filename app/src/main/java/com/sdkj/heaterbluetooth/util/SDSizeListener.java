package com.sdkj.heaterbluetooth.util;

public interface SDSizeListener<T>
{
    void onWidthChanged(int newWidth, int oldWidth, int differ, T target);

    void onHeightChanged(int newHeight, int oldHeight, int differ, T target);
}
