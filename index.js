import { NativeModules,DeviceEventEmitter,Platform } from 'react-native'

const { TPush } = NativeModules

export default class XG {

    static enableDebug(debugMode) {
        TPush.enableDebug(debugMode)
    }
    static setAccessId(accessId) {
        TPush.setAccessId(accessId)
    }
    static setAccessKey(accessKey) {
        TPush.setAccessId(accessKey)
    }
    static getToken() {
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
    static setReportNotificationStatusEnable(debugMode) {
        TPush.setAccessId(debugMode)
    }
    static setReportApplistEnable(debugMode) {
        TPush.setAccessId(debugMode)
    }
    static enableOtherPush(flag) {
        TPush.setAccessId(flag)
    }
    static setMiPushAppId(appid) {
        TPush.setAccessId(appid)
    }
    static setMiPushAppKey(appkey) {
        TPush.setAccessId(appkey)
    }
    static setMzPushAppId(appid) {
        TPush.setAccessId(appid)
    }
    static setMzPushAppKey(appkey) {
        TPush.setAccessId(appkey)
    }
    static setHuaweiDebug(isHuaweiDebug) {
        TPush.setAccessId(isHuaweiDebug)
    }
    static registerPush(){
        TPush.registerPush()
    }

    static registerPushCallback(){
        return new Promise((resolve, reject) => {
            try {
                TPush.registerPushCallback()
            } catch (e) {
                reject(e)
            }
            DeviceEventEmitter.once("registerResult", res => {
                resolve(res);
            })
        })
    }

}
