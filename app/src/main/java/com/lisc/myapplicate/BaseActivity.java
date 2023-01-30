package com.lisc.myapplicate;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class BaseActivity extends AppCompatActivity {

    FrameLayout frameLayout;
    TextView logView;

    static String stack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        frameLayout = new FrameLayout(this);
        setContentView(frameLayout);

        Button button = new Button(this);
        //button.setX(500);
        button.setY(220);
        button.setBackgroundColor(0x44ff0000);
        button.setText("打开 " + this.getOpenPageText());

        TextView tv = new TextView(this);
        tv.setY(400);
        tv.setTextSize(32);
        tv.setTextColor(0xffffffff);
        tv.setText(this.getPageTip());

        frameLayout.addView(button, 650, 120);
        frameLayout.addView(tv, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);

        logView = new TextView(this);
        logView.setMovementMethod(new ScrollingMovementMethod());
        logView.setText(stack);
        int height = getResources().getDisplayMetrics().heightPixels / 2 + 100;
        logView.setY(height);
        frameLayout.addView(logView, FrameLayout.LayoutParams.MATCH_PARENT, (height - 100));

        button.setOnClickListener(v -> {
            this.openPageClickEvent();
        });
    }

    protected JSONObject convertObjectToJSONObject(String str) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    protected abstract String getOpenPageText();

    protected abstract void openPageClickEvent();

    protected String getPageTip() {
        return ""; // 页面提示的信息，子类有需要重写此方法
    }
}
