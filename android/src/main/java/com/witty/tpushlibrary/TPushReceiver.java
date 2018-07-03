package com.witty.tpushlibrary;

import android.content.Context;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.tencent.android.tpush.XGPushBaseReceiver;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushRegisterResult;
import com.tencent.android.tpush.XGPushShowedResult;
import com.tencent.android.tpush.XGPushTextMessage;

/**
 * Created by asus on 2018-07-03.
 */

public class TPushReceiver extends XGPushBaseReceiver{

    ReactApplicationContext reactApplicationContext;
    /*
    *注册回调
    * */
    @Override
    public void onRegisterResult(Context context, int i, XGPushRegisterResult xgPushRegisterResult) {
        TPushModule mo = new TPushModule(reactApplicationContext);
        WritableMap params = Arguments.createMap();
        params.putInt("accessId",(int)xgPushRegisterResult.getAccessId());
        params.putString("account",xgPushRegisterResult.getAccount());
        params.putString("deviceId",xgPushRegisterResult.getDeviceId());
        params.putString("ticket",xgPushRegisterResult.getTicket());
        params.putString("token",xgPushRegisterResult.getToken());
        params.putInt("ticketType",(int)xgPushRegisterResult.getTicketType());
        mo.sendEvent("registerResult",params);
    }
    /*
    *反注册回调
    * */
    @Override
    public void onUnregisterResult(Context context, int i) {

    }
    /*
    * 设置标签回调
    * */
    @Override
    public void onSetTagResult(Context context, int i, String s) {

    }
    /*
    * 删除标签回调
    * */
    @Override
    public void onDeleteTagResult(Context context, int i, String s) {

    }

    /*
    * 应用内消息的回调
    * */
    @Override
    public void onTextMessage(Context context, XGPushTextMessage xgPushTextMessage) {

    }
    /*
    * 通知被点击触发的回调
    * */
    @Override
    public void onNotifactionClickedResult(Context context, XGPushClickedResult xgPushClickedResult) {

    }

    /*
    * 通知被展示触发的回调，可以在此保存APP收到的通知
    * */
    @Override
    public void onNotifactionShowedResult(Context context, XGPushShowedResult xgPushShowedResult) {

    }
}
