package com.witty.tpushlibrary;

import android.app.Activity;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;

/**
 * Created by asus on 2018-07-02.
 */

public class TPushModule extends BaseModule {


    /*
    * EventsList
    * getToken
    *
    *
    * */

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

    /*
    * XGPushManager
    * @description Push服务推送
    * */

    @ReactMethod
    public void registerPush(){
        XGPushManager.registerPush(context);
    }

    @ReactMethod
    public void registerPushCallback() {
        XGPushManager.registerPush(context, new XGIOperateCallback() {
            @Override
            public void onSuccess(Object data, int flag) {
                //token在设备卸载重装的时候有可能会变
                Log.d("TPush", "注册成功，设备token为：" + data);
                WritableMap params = Arguments.createMap();
                params.putString("token",  data.toString());
                params.putInt("flag",flag);
                sendEvent("registerPush", params);
            }

            @Override
            public void onFail(Object data, int errCode, String msg) {
                Log.d("TPush", "注册失败，错误码：" + errCode + ",错误信息：" + msg);
                WritableMap params = Arguments.createMap();
                params.putString("data", data.toString());
                params.putInt("errCode", errCode);
                params.putString("msg", msg);
                sendEvent("registerPush", params);
            }
        });
    }

    @ReactMethod
    public void registerPushAccountCallback(String account) {
        XGPushManager.registerPush(context, account ,new XGIOperateCallback() {
            @Override
            public void onSuccess(Object data, int flag) {
                //token在设备卸载重装的时候有可能会变
                Log.d("TPush", "注册成功，设备token为：" + data);
                WritableMap params = Arguments.createMap();
                params.putString("token",  data.toString());
                params.putInt("flag",flag);
                sendEvent("registerPush", params);
            }

            @Override
            public void onFail(Object data, int errCode, String msg) {
                Log.d("TPush", "注册失败，错误码：" + errCode + ",错误信息：" + msg);
                WritableMap params = Arguments.createMap();
                params.putString("data", data.toString());
                params.putInt("errCode", errCode);
                params.putString("msg", msg);
                sendEvent("registerPush", params);
            }
        });
    }

    @ReactMethod
    public void registerPushWithLogin(String account,String ticket, int ticketType, String qua) {
        XGPushManager.registerPush(context, account,ticket, ticketType, qua ,new XGIOperateCallback() {
            @Override
            public void onSuccess(Object data, int flag) {
                //token在设备卸载重装的时候有可能会变
                Log.d("TPush", "注册成功，设备token为：" + data);
                WritableMap params = Arguments.createMap();
                params.putString("token",  data.toString());
                params.putInt("flag",flag);
                sendEvent("registerPush", params);
            }

            @Override
            public void onFail(Object data, int errCode, String msg) {
                Log.d("TPush", "注册失败，错误码：" + errCode + ",错误信息：" + msg);
                WritableMap params = Arguments.createMap();
                params.putString("data", data.toString());
                params.putInt("errCode", errCode);
                params.putString("msg", msg);
                sendEvent("registerPush", params);
            }
        });
    }

    @ReactMethod
    public void bindAccount(String account) {
        XGPushManager.bindAccount(context, account);
    }

    @ReactMethod
    public void bindAccountCallback(String account) {
        XGPushManager.bindAccount(context, account ,new XGIOperateCallback() {
            @Override
            public void onSuccess(Object data, int flag) {
                //token在设备卸载重装的时候有可能会变
                Log.d("TPush", "注册绑定成功，设备token为：" + data);
                WritableMap params = Arguments.createMap();
                params.putString("token",  data.toString());
                params.putInt("flag",flag);
                sendEvent("bindAccount", params);
            }

            @Override
            public void onFail(Object data, int errCode, String msg) {
                Log.d("TPush", "注册绑定失败，错误码：" + errCode + ",错误信息：" + msg);
                WritableMap params = Arguments.createMap();
                params.putString("data", data.toString());
                params.putInt("errCode", errCode);
                params.putString("msg", msg);
                sendEvent("bindAccount", params);
            }
        });
    }

    @ReactMethod
    public void appendAccount(String account) {
        XGPushManager.appendAccount(context, account);
    }

    @ReactMethod
    public void appendAccountCallback(String account) {
        XGPushManager.appendAccount(context, account ,new XGIOperateCallback() {
            @Override
            public void onSuccess(Object data, int flag) {
                //token在设备卸载重装的时候有可能会变
                Log.d("TPush", "注册增加成功，设备token为：" + data);
                WritableMap params = Arguments.createMap();
                params.putString("token",  data.toString());
                params.putInt("flag",flag);
                sendEvent("appendAccount", params);
            }

            @Override
            public void onFail(Object data, int errCode, String msg) {
                Log.d("TPush", "注册增加失败，错误码：" + errCode + ",错误信息：" + msg);
                WritableMap params = Arguments.createMap();
                params.putString("data", data.toString());
                params.putInt("errCode", errCode);
                params.putString("msg", msg);
                sendEvent("appendAccount", params);
            }
        });
    }

    @ReactMethod
    public void delAccount(String account) {
        XGPushManager.delAccount(context, account);
    }

    @ReactMethod
    public void delAccountCallback(String account) {
        XGPushManager.delAccount(context, account ,new XGIOperateCallback() {
            @Override
            public void onSuccess(Object data, int flag) {
                //token在设备卸载重装的时候有可能会变
                Log.d("TPush", "解绑账号成功，设备token为：" + data);
                WritableMap params = Arguments.createMap();
                params.putString("token",  data.toString());
                params.putInt("flag",flag);
                sendEvent("delAccount", params);
            }

            @Override
            public void onFail(Object data, int errCode, String msg) {
                Log.d("TPush", "解绑账号失败，错误码：" + errCode + ",错误信息：" + msg);
                WritableMap params = Arguments.createMap();
                params.putString("data", data.toString());
                params.putInt("errCode", errCode);
                params.putString("msg", msg);
                sendEvent("delAccount", params);
            }
        });
    }

    @ReactMethod
    public void unregisterPush() {
        XGPushManager.unregisterPush(context);
    }

    @ReactMethod
    public void setTag(String tagName) {
        XGPushManager.setTag(context,tagName);
    }

    @ReactMethod
    public void deleteTag(String tagName) {
        XGPushManager.deleteTag(context,tagName);
    }

    @ReactMethod
    public void onActivityStarted(){
        Activity activity = getCurrentActivity();
        XGPushClickedResult clickedResult =  XGPushManager.onActivityStarted(activity);
        if(clickedResult!=null){
            Log.d("TPush", "点击通知：" + clickedResult);
            WritableMap params = Arguments.createMap();
            params.putInt("msgId", (int)clickedResult.getMsgId());
            params.putString("title",clickedResult.getTitle());
            params.putString("content",clickedResult.getContent());
            params.putString("activityName",clickedResult.getActivityName());
            params.putString("customContent",clickedResult.getCustomContent());
            sendEvent("clickedResult", params);
        }
    }

    @ReactMethod
    public void onActivityStoped(){
        Activity activity = getCurrentActivity();
        XGPushManager.onActivityStoped(activity);
    }

//    @ReactMethod
//    public void setPushNotificationBuilder(int notificationBulderId,Object options){
//        XGCustomPushNotificationBuilder builder = new XGCustomPushNotificationBuilder();
//        builder.setSound(
//                RingtoneManager.getActualDefaultRingtoneUri(context, RingtoneManager.TYPE_ALARM))
//                .setDefaults(Notification.DEFAULT_VIBRATE) // 振动
//                .setFlags(Notification.FLAG_NO_CLEAR); // 是否可清除
//
//        // 设置自定义通知layout,通知背景等可以在layout里设置
//        builder.setLayoutId(R.layout.notification);
//        // 设置自定义通知内容id
//        builder.setLayoutTextId(R.id.content);
//        // 设置自定义通知标题id
//        builder.setLayoutTitleId(R.id.title);
//        // 设置自定义通知图片资源
//        builder.setLayoutIconDrawableId(R.drawable.logo);
//        // 设置状态栏的通知小图标
//        builder.setIcon(R.drawable.right);
//        // 设置时间id
//        builder.setLayoutTimeId(R.id.time);
//        // 若不设定以上自定义layout，又想简单指定通知栏图片资源
//        builder.setNotificationLargeIcon(R.drawable.ic_action_search);
//        XGPushManager.setPushNotificationBuilder(context, notificationBulderId, builder);
//    }

    @ReactMethod
    public void isNotificationOpened() {
        boolean isOpened = XGPushManager.isNotificationOpened(context);
        Log.d("TPush", "是否打开通知栏：" + isOpened);
        WritableMap params = Arguments.createMap();
        params.putBoolean("isOpened", isOpened);
        sendEvent("isNotificationOpened", params);
    }

}
