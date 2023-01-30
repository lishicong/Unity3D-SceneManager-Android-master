package com.lisc.myapplicate;

import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UnityCallUtil {

    public static boolean callUnity(String gameObjectName, String functionName, String message) {
        if (gameObjectName == null || gameObjectName.equals("") || functionName == null || functionName.equals("")) {
            return false;
        }

        try {
            Class<?> classType = Class.forName("com.unity3d.player.UnityPlayer");
            Method method = classType.getMethod("UnitySendMessage", String.class, String.class, String.class);
            method.invoke(classType, gameObjectName, functionName, message);
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static String getCallUnityLifeCycleMsg(String lifeCycleName, String currentActivityName, String openSceneName, Object data) {
        MessageModel.LifeCycleModel model = new MessageModel.LifeCycleModel();
        model.lifeCycleName = lifeCycleName;
        model.pageName = currentActivityName;
        model.sceneName = openSceneName;
        model.time = System.currentTimeMillis();

        if (data != null) {
            model.data = JSONObject.toJSONString(data);
        }
        return JSONObject.toJSONString(model);
    }
}
