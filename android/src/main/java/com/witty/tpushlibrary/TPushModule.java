package com.witty.tpushlibrary;

import android.app.Notification;
import android.content.Context;
import android.media.RingtoneManager;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.WritableMap;
import com.tencent.android.tpush.XGCustomPushNotificationBuilder;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGLocalMessage;
import com.tencent.android.tpush.XGPushBaseReceiver;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.XGPushRegisterResult;
import com.tencent.android.tpush.XGPushShowedResult;
import com.tencent.android.tpush.XGPushTextMessage;

import org.json.JSONException;

import java.util.HashMap;

/**
 * Created by asus on 2018-07-02.
 */

public class TPushModule extends BaseModule {


    private static ReactApplicationContext context;

    private final static String EVENT_GET_TOKEN = "getToken";
    private final static String EVENT_REGISTER_RESULT = "registerResult";
    private final static String EVENT_UNREGISTER_RESULT = "unRegisterResult";
    private final static String EVENT_BIND_ACCOUNT = "bindAccount";
    private final static String EVENT_APPEND_ACCOUNT = "appendAccount";
    private final static String EVENT_DELETE_ACCOUNT = "deleteAccount";
    private final static String EVENT_SET_TAG_RESULT = "setTagResult";
    private final static String EVENT_DELETE_TAG_RESULT = "deleteTagResult";
    private final static String EVENT_CLICKED_RESULT = "clickedResult";
    private final static String EVENT_ADD_LOCAL_NOTIFICATION = "addLocalNotification";
    private final static String EVENT_IS_NOTIFICATION_OPENED = "isNotificationOpened";
    private final static String EVENT_TEXT_MESSAGE = "textMessage";
    private final static String EVENT_NOTIFICATION_CLICKED_RESULT = "notificationClickedResult";
    private final static String EVENT_NOTIFICATION_SHOWED_RESULT = "notificationShowedResult";

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
        sendEvent(EVENT_GET_TOKEN, params);
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
    public void registerPush() {
        XGPushManager.registerPush(context);
    }

    @ReactMethod
    public void registerPushCallback() {
        XGPushManager.registerPush(context, new XGIOperateCallback() {
            @Override
            public void onSuccess(Object data, int flag) {
                //token在设备卸载重装的时候有可能会变,通过继承XGPushBaseReceiver实现回调
                Log.d("TPush", "注册成功，设备token为：" + data);
            }

            @Override
            public void onFail(Object data, int errCode, String msg) {
                Log.d("TPush", "注册失败，错误码：" + errCode + ",错误信息：" + msg);
            }
        });
    }

    @ReactMethod
    public void registerPushAccountCallback(String account) {
        XGPushManager.registerPush(context, account, new XGIOperateCallback() {
            @Override
            public void onSuccess(Object data, int flag) {
                Log.d("TPush", "注册成功，设备token为：" + data);
            }

            @Override
            public void onFail(Object data, int errCode, String msg) {
                Log.d("TPush", "注册失败，错误码：" + errCode + ",错误信息：" + msg);
            }
        });
    }

    @ReactMethod
    public void registerPushWithLogin(String account, String ticket, int ticketType, String qua) {
        XGPushManager.registerPush(context, account, ticket, ticketType, qua, new XGIOperateCallback() {
            @Override
            public void onSuccess(Object data, int flag) {
                Log.d("TPush", "注册成功，设备token为：" + data);
            }

            @Override
            public void onFail(Object data, int errCode, String msg) {
                Log.d("TPush", "注册失败，错误码：" + errCode + ",错误信息：" + msg);
            }
        });
    }

    @ReactMethod
    public void bindAccount(String account) {
        XGPushManager.bindAccount(context, account);
    }

    @ReactMethod
    public void bindAccountCallback(String account) {
        XGPushManager.bindAccount(context, account, new XGIOperateCallback() {
            @Override
            public void onSuccess(Object data, int flag) {
                Log.d("TPush", "注册绑定成功，设备token为：" + data);
                WritableMap params = Arguments.createMap();
                params.putString("token", String.valueOf(data));
                sendEvent(EVENT_BIND_ACCOUNT, params);
            }

            @Override
            public void onFail(Object data, int errCode, String msg) {
                Log.d("TPush", "注册绑定失败，错误码：" + errCode + ",错误信息：" + msg);
                WritableMap params = Arguments.createMap();
                params.putInt("errCode", errCode);
                params.putString("msg", msg);
                sendEvent(EVENT_BIND_ACCOUNT, params);
            }
        });
    }

    @ReactMethod
    public void appendAccount(String account) {
        XGPushManager.appendAccount(context, account);
    }

    @ReactMethod
    public void appendAccountCallback(String account) {
        XGPushManager.appendAccount(context, account, new XGIOperateCallback() {
            @Override
            public void onSuccess(Object data, int flag) {
                Log.d("TPush", "注册增加成功，设备token为：" + data);
                WritableMap params = Arguments.createMap();
                params.putString("token", String.valueOf(data));
                sendEvent(EVENT_APPEND_ACCOUNT, params);
            }

            @Override
            public void onFail(Object data, int errCode, String msg) {
                Log.d("TPush", "注册增加失败，错误码：" + errCode + ",错误信息：" + msg);
                WritableMap params = Arguments.createMap();
                params.putInt("errCode", errCode);
                params.putString("msg", msg);
                sendEvent(EVENT_APPEND_ACCOUNT, params);
            }
        });
    }

    @ReactMethod
    public void delAccount(String account) {
        XGPushManager.delAccount(context, account);
    }

    @ReactMethod
    public void delAccountCallback(String account) {
        XGPushManager.delAccount(context, account, new XGIOperateCallback() {
            @Override
            public void onSuccess(Object data, int flag) {
                Log.d("TPush", "解绑账号成功，设备token为：" + data);
                WritableMap params = Arguments.createMap();
                params.putString("token", String.valueOf(data));
                sendEvent(EVENT_DELETE_ACCOUNT, params);
            }

            @Override
            public void onFail(Object data, int errCode, String msg) {
                Log.d("TPush", "解绑账号失败，错误码：" + errCode + ",错误信息：" + msg);
                WritableMap params = Arguments.createMap();
                params.putInt("errCode", errCode);
                params.putString("msg", msg);
                sendEvent(EVENT_DELETE_ACCOUNT, params);
            }
        });
    }

    @ReactMethod
    public void unregisterPush() {
        XGPushManager.unregisterPush(context);
    }

    @ReactMethod
    public void setTag(String tagName) {
        XGPushManager.setTag(context, tagName);
    }

    @ReactMethod
    public void deleteTag(String tagName) {
        XGPushManager.deleteTag(context, tagName);
    }

    @ReactMethod
    public void onActivityStarted() {
        XGPushClickedResult clickedResult = XGPushManager.onActivityStarted(getCurrentActivity());
        if (clickedResult != null) {
            Log.d("TPush", "点击通知：" + clickedResult);
            WritableMap params = Arguments.createMap();
            params.putInt("msgId", (int) clickedResult.getMsgId());
            params.putString("title", clickedResult.getTitle());
            params.putString("content", clickedResult.getContent());
            params.putString("activityName", clickedResult.getActivityName());
            params.putString("customContent", clickedResult.getCustomContent());
            sendEvent(EVENT_CLICKED_RESULT, params);
        }
    }

    @ReactMethod
    public void onActivityStoped() {
        XGPushManager.onActivityStoped(getCurrentActivity());
    }

    @ReactMethod
    public void setPushNotificationBuilder(int notificationBulderId, ReadableMap readableMap) {

        XGCustomPushNotificationBuilder builder = new XGCustomPushNotificationBuilder();
        //设置声音
        builder.setSound(RingtoneManager.getActualDefaultRingtoneUri(context, RingtoneManager.TYPE_ALARM));
        //设置类型soundType:1--声音，2--震动，3--呼吸灯,默认系统通知类型
        builder.setDefaults(TPushUtils.isEmpty(String.valueOf(readableMap.getInt("soundType"))) ? ~0 : readableMap.getInt("soundType")); // 振动
        //是否可以清除flag:auto--自动清除，no--不清除,默认不可清除
        if(TPushUtils.isEmpty(readableMap.getString("flag"))){
                if(readableMap.getString("flag").equals("auto")){
                    builder.setFlags(Notification.FLAG_AUTO_CANCEL);
                }else{
                    builder.setFlags(Notification.FLAG_NO_CLEAR);
                }
        }else{
            builder.setFlags(Notification.FLAG_NO_CLEAR); // 是否可清除
        }

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
        XGPushManager.setPushNotificationBuilder(context, notificationBulderId, builder);
    }

    @ReactMethod
    public void addLocalNotification(ReadableMap readableMap) throws JSONException {
        XGLocalMessage local_msg = new XGLocalMessage();
        //设置本地消息类型，1:通知，2:消息
        local_msg.setType(readableMap.getInt("type"));
        // 设置消息标题
        local_msg.setTitle(readableMap.getString("title"));
        //设置消息内容
        local_msg.setContent(readableMap.getString("content"));
        //设置消息日期，格式为：20140502
        local_msg.setDate(readableMap.getString("date"));
        //设置消息触发的小时(24小时制)，例如：22代表晚上10点
        local_msg.setHour(readableMap.getString("hour"));
        //获取消息触发的分钟，例如：05代表05分
        local_msg.setMin(readableMap.getString("min"));
        //设置消息样式，默认为0或不设置
        local_msg.setBuilderId(readableMap.getInt("builderId"));
        //设置动作类型：1打开activity或app本身，2打开浏览器，3打开Intent ，4通过包名打开应用
        local_msg.setAction_type(readableMap.getInt("actionType"));
        //设置拉起应用页面
        local_msg.setActivity(context.getCurrentActivity().getPackageName() + "." + readableMap.getString("activity"));
        // 设置URL
        local_msg.setUrl(readableMap.getString("url"));
        // 设置Intent
        local_msg.setIntent(readableMap.getString("intent"));
        // 是否覆盖原先build_id的保存设置。1覆盖，0不覆盖
        local_msg.setStyle_id(readableMap.getInt("isCover"));
        // 设置音频资源
        local_msg.setRing_raw(readableMap.getString("ring"));
        ReadableMap extra = readableMap.getMap("extra");
        HashMap<String, Object> map = new HashMap<String, Object>();
        ReadableMapKeySetIterator iterator = extra.keySetIterator();
        while (iterator.hasNextKey()) {
            String key = iterator.nextKey();
            map.put(key, extra.getString(key));
        }
        local_msg.setCustomContent(map);
        // 设置下载应用URL
        local_msg.setPackageDownloadUrl(readableMap.getString("downloadUrl"));

        long code = XGPushManager.addLocalNotification(context, local_msg);
        WritableMap params = Arguments.createMap();
        params.putInt("msgId", (int) code);
        sendEvent(EVENT_ADD_LOCAL_NOTIFICATION, params);
    }

    @ReactMethod
    public void isNotificationOpened() {
        boolean isOpened = XGPushManager.isNotificationOpened(context);
        Log.d("TPush", "是否打开通知栏：" + isOpened);
        WritableMap params = Arguments.createMap();
        params.putBoolean("isOpened", isOpened);
        sendEvent(EVENT_IS_NOTIFICATION_OPENED, params);
    }

    /*
    * 广播类
    * */
    public static class TPushReceiver extends XGPushBaseReceiver {

        /*注册回调*/
        @Override
        public void onRegisterResult(Context context, int i, XGPushRegisterResult xgPushRegisterResult) {
            WritableMap params = Arguments.createMap();
            params.putInt("accessId", (int) xgPushRegisterResult.getAccessId());
            params.putString("account", xgPushRegisterResult.getAccount());
            params.putString("deviceId", xgPushRegisterResult.getDeviceId());
            params.putString("ticket", xgPushRegisterResult.getTicket());
            params.putString("token", xgPushRegisterResult.getToken());
            params.putInt("ticketType", (int) xgPushRegisterResult.getTicketType());
            sendEvent(EVENT_REGISTER_RESULT, params);
        }

        /*反注册回调*/
        @Override
        public void onUnregisterResult(Context context, int i) {
            WritableMap params = Arguments.createMap();
            params.putInt("errCode", i);
            sendEvent(EVENT_UNREGISTER_RESULT, params);
        }

        /*设置标签*/
        @Override
        public void onSetTagResult(Context context, int i, String s) {
            WritableMap params = Arguments.createMap();
            params.putInt("errCode", i);
            params.putString("tagName", s);
            sendEvent(EVENT_SET_TAG_RESULT, params);
        }

        /*删除标签*/
        @Override
        public void onDeleteTagResult(Context context, int i, String s) {
            WritableMap params = Arguments.createMap();
            params.putInt("errCode", i);
            params.putString("tagName", s);
            sendEvent(EVENT_DELETE_TAG_RESULT, params);
        }

        /*应用内消息的回调*/
        @Override
        public void onTextMessage(Context context, XGPushTextMessage xgPushTextMessage) {
            WritableMap params = Arguments.createMap();
            params.putString("title", xgPushTextMessage.getTitle());
            params.putString("content", xgPushTextMessage.getContent());
            params.putString("customContent", xgPushTextMessage.getCustomContent());
            sendEvent(EVENT_TEXT_MESSAGE, params);
        }

        /*通知被点击触发的回调*/
        @Override
        public void onNotifactionClickedResult(Context context, XGPushClickedResult xgPushClickedResult) {
            if (xgPushClickedResult != null) {
                WritableMap params = Arguments.createMap();
                params.putInt("msgId", (int) xgPushClickedResult.getMsgId());
                params.putString("title", xgPushClickedResult.getTitle());
                params.putString("content", xgPushClickedResult.getContent());
                params.putString("activityName", xgPushClickedResult.getActivityName());
                params.putString("customContent", xgPushClickedResult.getCustomContent());
                sendEvent(EVENT_NOTIFICATION_CLICKED_RESULT, params);
            }
        }

        /*通知被展示触发的回调*/
        @Override
        public void onNotifactionShowedResult(Context context, XGPushShowedResult xgPushShowedResult) {
            WritableMap params = Arguments.createMap();
            params.putInt("msgId", (int) xgPushShowedResult.getMsgId());
            params.putString("title", xgPushShowedResult.getTitle());
            params.putString("content", xgPushShowedResult.getContent());
            params.putString("activityName", xgPushShowedResult.getActivity());
            params.putString("customContent", xgPushShowedResult.getCustomContent());
            sendEvent(EVENT_NOTIFICATION_SHOWED_RESULT, params);
        }
    }

}
