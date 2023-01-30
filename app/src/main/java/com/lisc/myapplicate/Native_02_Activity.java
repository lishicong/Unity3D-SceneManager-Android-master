package com.lisc.myapplicate;

import android.os.Bundle;

import androidx.annotation.Nullable;

public class Native_02_Activity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getOpenPageText() {
        return "没有页面了！！！";
    }

    @Override
    protected void openPageClickEvent() {

    }

    protected String getPageTip() {
        return "这是 native 的 Native_02_Activity 页面，请测试返回逻辑吧";
    }
}
