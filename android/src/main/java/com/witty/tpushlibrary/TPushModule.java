package com.witty.tpushlibrary;

import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;

/**
 * Created by asus on 2018-07-02.
 */

public class TPushModule extends BaseModule {
    ReactApplicationContext context;
    public TPushModule(ReactApplicationContext reactContext) {
        super(reactContext);
        context = reactContext;
    }


    /*
       * XGPushConfig
       * @description 	Push服务配置项接口
       * */
    @ReactMethod
    public void enableDebug(boolean debugMode) {
        XGPushConfig.enableDebug(context, debugMode);
    }

    @ReactMethod
    public void setAccessId(long accessId) {
        XGPushConfig.setAccessId(context, accessId);
    }

    @ReactMethod
    public void setAccessKey(String accessKey) {
        XGPushConfig.setAccessKey(context, accessKey);
    }

    @ReactMethod
    public void getToken() {
        String token = XGPushConfig.getToken(context);
        WritableMap params = Arguments.createMap();
        params.putString("token", token);
        sendEvent("getToken", params);
    }

    @ReactMethod
    public void setReportNotificationStatusEnable(boolean debugMode) {
        XGPushConfig.setReportNotificationStatusEnable(context, debugMode);
    }

    @ReactMethod
    public void setReportApplistEnable(boolean debugMode) {
        XGPushConfig.setReportApplistEnable(context, debugMode);
    }

    @ReactMethod
    public void enableOtherPush(boolean flag) {
        XGPushConfig.enableOtherPush(context, flag);
    }

    @ReactMethod
    public void setMiPushAppId(String appid) {
        XGPushConfig.setMiPushAppId(context, appid);
    }

    @ReactMethod
    public void setMiPushAppKey(String appkey) {
        XGPushConfig.setMiPushAppKey(context, appkey);
    }

    @ReactMethod
    public void setMzPushAppId(String appid) {
        XGPushConfig.setMzPushAppId(context, appid);
    }

    @ReactMethod
    public void setMzPushAppKey(String appkey) {
        XGPushConfig.setMzPushAppKey(context, appkey);
    }

    @ReactMethod
    public void setHuaweiDebug(boolean isHuaweiDebug) {
        XGPushConfig.setHuaweiDebug(isHuaweiDebug);
    }


    @ReactMethod
    public void registerPush() {
        XGPushManager.registerPush(context, new XGIOperateCallback() {
            @Override
            public void onSuccess(Object data, int flag) {
                //token在设备卸载重装的时候有可能会变
                Log.d("TPush", "注册成功，设备token为：" + data);
                WritableMap params = Arguments.createMap();
                params.putString("token", "注册成功，设备token为：" + data);
                sendEvent("registerPush", params);
            }

            @Override
            public void onFail(Object data, int errCode, String msg) {
                Log.d("TPush", "注册失败，错误码：" + errCode + ",错误信息：" + msg);
                WritableMap params = Arguments.createMap();
                params.putString("token", "注册失败，错误码：" + errCode + ",错误信息：" + msg);
                sendEvent("registerPush", params);
            }
        });
    }
}
