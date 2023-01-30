package com.lisc.myapplicate;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class Unity_03_Activity extends BaseUnityActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getOpenPageText() {
        return "Native_02_Activity";
    }

    @Override
    protected void openPageClickEvent() {
        Intent intent = new Intent(this, Native_02_Activity.class);
        startActivity(intent);
    }

    @Override
    protected String getPageName() {
        return "Unity_03_Activity";
    }

    @Override
    protected String getSceneName() {
        return "Scene1";
    }
}