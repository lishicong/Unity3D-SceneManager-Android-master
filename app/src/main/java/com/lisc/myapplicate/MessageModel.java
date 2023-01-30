package com.lisc.myapplicate;

import java.io.Serializable;

public class MessageModel {

    public static class LifeCycleModel implements Serializable {
        public String lifeCycleName;
        public String pageName;
        public String sceneName;
        public String data;
        public long time;
    }
}
