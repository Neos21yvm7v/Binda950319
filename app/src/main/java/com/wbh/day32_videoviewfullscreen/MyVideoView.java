package com.wbh.day32_videoviewfullscreen;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * Created by Administrator on 2016/10/9.
 */
public class MyVideoView extends VideoView {
    public MyVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //getDefaultSize()根据widthMeasureSpec获得测量的大小, 0为默认值, 返回值是真正的结果
        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);
        //自己设置宽和高
        setMeasuredDimension(width, height);
    }
}
