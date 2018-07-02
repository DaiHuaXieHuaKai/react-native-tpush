import { NativeModules,DeviceEventEmitter,Platform } from 'react-native'

const { TPush } = NativeModules

export default class XG {

    enableDebug(debugMode) {
        TPush.enableDebug(debugMode)
    }

   setAccessId(accessId) {
        TPush.setAccessId(accessId)
    }

   setAccessKey(accessKey) {
        TPush.setAccessId(accessKey)
    }

   getToken() {
        return new Promise((resolve, reject) => {
            try {
                TPush.getToken();
            } catch (e) {
                reject(e)
            }
            DeviceEventEmitter.once("getToken", res => {
                resolve(res);
            })
        })
    }

   setReportNotificationStatusEnable(debugMode) {
        TPush.setAccessId(debugMode)
    }

   setReportApplistEnable(debugMode) {
        TPush.setAccessId(debugMode)
    }

   enableOtherPush(flag) {
        TPush.setAccessId(flag)
    }

   setMiPushAppId(appid) {
        TPush.setAccessId(appid)
    }

   setMiPushAppKey(appkey) {
        TPush.setAccessId(appkey)
    }

    setMzPushAppId(appid) {
        TPush.setAccessId(appid)
    }

    setMzPushAppKey(appkey) {
        TPush.setAccessId(appkey)
    }

    setHuaweiDebug(isHuaweiDebug) {
        TPush.setAccessId(isHuaweiDebug)
    }

}
