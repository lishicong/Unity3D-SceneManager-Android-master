package com.lisc.myapplicate;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class Native_01_Activity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getOpenPageText() {
        return "Unity_02_Activity";
    }

    @Override
    protected void openPageClickEvent() {
        Intent intent = new Intent(this, Unity_02_Activity.class);
        startActivity(intent);
    }

    protected String getPageTip() {
        return "这是 native 的 Native_01_Activity 页面，什么内容也没有，只是用于测试页面的变化";
    }
}
