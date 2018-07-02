package com.witty.tpushlibrary;

import android.support.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

/**
 * Created by asus on 2018-07-02.
 */

public class BaseModule extends ReactContextBaseJavaModule {

    ReactApplicationContext context;
    public BaseModule(ReactApplicationContext reactContext) {
        super(reactContext);
        context = reactContext;
    }

    @Override
    public String getName() {
        return "TPush";
    }

    /**
     * 传递消息给JS
     */
    protected void sendEvent(String eventName, @Nullable WritableMap params) {
        context
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }
}
