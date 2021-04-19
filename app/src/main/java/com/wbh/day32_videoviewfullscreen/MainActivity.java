package com.wbh.day32_videoviewfullscreen;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private VideoView videoView;
    private Button btn;
    private boolean isFullScreen = false;
        private String path = "http://121.46.19.90/?new=/36/112/sc1OQIuZQmyZDF8uSz263F.mp4&key=CWdSmRODAhAieshPNQWXmMVcZ61rtxmM&prod=ugc&pg=0&cateCode=128113&vid=84310632&ch=my&plat=6&mkey=s2dparmoDY1yTFCXOb-2_Gst9rjORbGq";
//    private String path = Environment.getExternalStorageDirectory() + "/rzdx.mp4";
    private int width;
    private int height;
    /**
     * 1.屏幕旋转时Activity的生命周期如何执行？
     * 1》默认情况，竖屏生命周期执行一次，横屏生命周期重新一次；
     * 2》android:configChanges：只设orientation：竖屏生命周期执行一次，横屏生命周期重新一次；
     * 只设screenSize：竖屏生命周期执行一次，横屏生命周期重新一次；
     * 同时设置orientation和screenSize:横竖屏直执行一次生命周期，横屏生命周期不再重新执行，只执行onConfigurationChanged()
     * <p/>
     * 2.播放视频屏幕旋转依然接着原来位置；
     *
     * 1.重写VideoView中onMeasure()让动态设置宽和高生效setLayoutParams()
     * 2.onConfigurationChanged()此横竖屏操作让视频界面显示正常
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView = (VideoView) findViewById(R.id.videoView);
        btn = (Button) findViewById(R.id.button);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        width = metrics.widthPixels;
        height = metrics.heightPixels;

        videoView.setVideoPath(path);
        videoView.start();
        btn.setOnClickListener(this);
        initScreen();
        Log.e("Bing", "============onCreate:==============");
    }

    private void initScreen() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, height / 3);
        videoView.setLayoutParams(layoutParams);
        isFullScreen = false;
    }

    @Override
    public void onClick(View v) {
        if (isFullScreen == true) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, height / 3);
            videoView.setLayoutParams(layoutParams);
            isFullScreen = false;
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            Log.e("Bing", "onClick: 切换成竖屏");
        } else if(isFullScreen == false){
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(height, width);
            videoView.setLayoutParams(layoutParams);
            isFullScreen = true;
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            Log.e("Bing", "onClick: 切换成横屏");
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //如果是横屏
        if (getResources().getConfiguration().orientation==newConfig.ORIENTATION_LANDSCAPE){
            WindowManager.LayoutParams attrs = getWindow().getAttributes();
            attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            getWindow().setAttributes(attrs);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

            //如果是竖屏
        }else if (getResources().getConfiguration().orientation==newConfig.ORIENTATION_PORTRAIT){
            WindowManager.LayoutParams attrs = getWindow().getAttributes();
            attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setAttributes(attrs);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("Bing", "============onResume:==============");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("Bing", "============onPause:==============");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("Bing", "============onStop:==============");
    }
}
