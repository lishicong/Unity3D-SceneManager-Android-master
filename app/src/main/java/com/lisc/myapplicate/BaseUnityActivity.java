package com.lisc.myapplicate;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.unity3d.player.UnityPlayer;

import org.json.JSONObject;

public abstract class BaseUnityActivity extends BaseActivity {
    private UnityPlayer mUnityPlayer;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUnityPlayer = PlayerHolder.getInstance().getPlayer(this);

        ViewUtil.addView(frameLayout, mUnityPlayer);
        setUnityToBottom();

        String message = UnityCallUtil.getCallUnityLifeCycleMsg("onCreate", getPageName(), getSceneName(), null);
        UnityCallUtil.callUnity("TestRun", "OnNativeLifeCycle", message);
    }

    @Override
    public void onBackPressed() {
        boolean call = UnityCallUtil.callUnity("TestRun", "OnNativeBackEvent", "");
        if (call) {
            return;
        }
        super.onBackPressed();
    }

    // on unity
    public void OnUnityBackEvent(final String message) {
        JSONObject jsonObject = convertObjectToJSONObject(message);
        boolean isIntercept = jsonObject.optBoolean("isIntercept");
        if (!isIntercept) {
            Toast.makeText(this, "回退到上一个页面", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "u3d 回退到上一个场景", Toast.LENGTH_SHORT).show();
        }
    }

    public void SendPageStackToNative(final String stack) {
        this.stack = stack;
        logView.setText(stack);
    }

    @Override
    public void finish() {
        PlayerHolder.getInstance().adjustContext(MyApplication.getInstance());
        String message = UnityCallUtil.getCallUnityLifeCycleMsg("finish", getPageName(), "", null);
        UnityCallUtil.callUnity("TestRun", "OnNativeLifeCycle", message);
        super.finish();
    }

    @Override
    protected void onResume() {
        PlayerHolder.getInstance().adjustContext(this);
        super.onResume();
        //ViewUtil.addView(frameLayout, mUnityPlayer);
        String message = UnityCallUtil.getCallUnityLifeCycleMsg("onResume", getPageName(), "", null);
        UnityCallUtil.callUnity("TestRun", "OnNativeLifeCycle", message);

        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ViewUtil.addView(frameLayout, mUnityPlayer);

                mUnityPlayer.resume();
                mUnityPlayer.requestFocus();
                mUnityPlayer.windowFocusChanged(true);
            }
        }, 0);
    }

    @Override
    protected void onPause() {
        PlayerHolder.getInstance().adjustContext(MyApplication.getInstance());
        super.onPause();
        mUnityPlayer.pause();
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        this.mUnityPlayer.windowFocusChanged(hasFocus);
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.mUnityPlayer.configurationChanged(newConfig);
    }

    protected void setUnityToBottom() {
        View view = mUnityPlayer.getChildAt(0);
        if (view instanceof SurfaceView) {
            ((SurfaceView) view).getHolder().setFormat(-1);
            ((SurfaceView) view).setZOrderMediaOverlay(false);
        }
    }

    protected abstract String getPageName();

    protected abstract String getSceneName();
}
