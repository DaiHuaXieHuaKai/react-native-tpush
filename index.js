import {
    NativeModules,
    DeviceEventEmitter,
    Platform
} from 'react-native'

const {
    TPush
} = NativeModules

export default class XG {

    /**
     * 是否开启debug模式,正式发布必须设置为false
     * true:开启,false:关闭。
     * @param {boolean} debugMode
     */
    static enableDebug(debugMode) {
        TPush.enableDebug(debugMode)
    }

    /**
     * 配置accessId
     * @param {long} accessId
     */
    static setAccessId(accessId) {
        TPush.setAccessId(accessId)
    }

    /**
     * 配置accessKey
     * @param {string} accessKey
     */
    static setAccessKey(accessKey) {
        TPush.setAccessKey(accessKey)
    }

    /**
     *
     * 获取设备的token，只有注册成功才能获取到正常的结果
     */
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

    /**
     * 设置上报通知栏是否关闭 默认打开
     * @param {boolean} debugMode
     */
    static setReportNotificationStatusEnable(debugMode) {
        TPush.setReportNotificationStatusEnable(debugMode)
    }

    /**
     * 设置上报APP 列表，用于智能推送 默认打开
     * @param {boolean} debugMode
     */
    static setReportApplistEnable(debugMode) {
        TPush.setReportApplistEnable(debugMode)
    }

    /**
     * 设置支持第三方厂商推送
     * @param {boolean} flag
     */
    static enableOtherPush(flag) {
        TPush.enableOtherPush(flag)
    }

    /**
     * 设置小米推送APPID
     * @param {string} appid
     */
    static setMiPushAppId(appid) {
        TPush.setMiPushAppId(appid)
    }

    /**
     * 设置小米推送APPKEY
     * @param {string} appkey
     */
    static setMiPushAppKey(appkey) {
        TPush.setMiPushAppKey(appkey)
    }

    /**
     * 设置魅族推送APPID
     * @param {string} appid
     */
    static setMzPushAppId(appid) {
        TPush.setMzPushAppId(appid)
    }

    /**
     * 设置魅族推送APPKEY
     * @param {string} appkey
     */
    static setMzPushAppKey(appkey) {
        TPush.setMzPushAppKey(appkey)
    }
    /**
     * 	华为手机的写日志定位问题
     * @param {boolean} isHuaweiDebug
     */
    static setHuaweiDebug(isHuaweiDebug) {
        TPush.setHuaweiDebug(isHuaweiDebug)
    }

    /**
     * 启动并注册（无注册回调）
     */
    static registerPush() {
        TPush.registerPush()
    }

    /**
     * 启动并注册（有注册回调）
     */
    static registerPushCallback() {
        TPush.registerPushCallback()
    }

    /**
     * 启动并注册APP，同时绑定账号,推荐有帐号体系的APP使用（3.2.2不包括3.2.2之前的版本使用，有注册回调）
     * @param {string} account
     */
    static registerPushAccountCallback(account) {
        TPush.registerPushAccountCallback(account)
    }

    /**
     * 同上，仅供带登陆态的业务使用
     * @param {string} account
     * @param {string} ticket 登陆态票据，不能为null
     * @param {int} ticketType ticketType：票据类型
     * @param {string} qua QZone专用字段，不需要时可填null
     */
    static registerPushWithLogin(account, ticket, ticketType, qua) {
        TPush.registerPushWithLogin(account, ticket, ticketType, qua)
    }

    /**
     * 启动并注册APP，同时绑定账号,推荐有帐号体系的APP使用（3.2.2以及3.2.2之后的版本使用，此接口会覆盖设备之前绑定过的账号，仅当前注册的账号生效，无注册回调）
     * @param {string} account
     */
    static bindAccount(account) {
        TPush.bindAccount(account)
    }

    /**
     * 启动并注册APP，同时绑定账号,推荐有帐号体系的APP使用（3.2.2以及3.2.2之后的版本使用，此接口会覆盖设备之前绑定过的账号，仅当前注册的账号生效）
     * @param {string} account
     */
    static bindAccountCallback(account) {
        return new Promise((resolve, reject) => {
            try {
                TPush.bindAccountCallback(account)
            } catch (e) {
                reject(e)
            }
            DeviceEventEmitter.addListener("bindAccount", res => {
                resolve(res)
            })
        })
    }

    /**
     * 启动并注册APP，同时绑定账号,推荐有帐号体系的APP使用（3.2.2以及3.2.2之后的版本使用，此接口保留之前的账号，只做增加操作，一个token下最多只能有3个账号超过限制会自动顶掉之前绑定的账号，无注册回调）
     * @param {string} account
     */
    static appendAccount(account) {
        TPush.appendAccount(account)
    }

    /**
     * 启动并注册APP，同时绑定账号,推荐有帐号体系的APP使用（3.2.2以及3.2.2之后的版本使用，此接口保留之前的账号，只做增加操作，一个token下最多只能有3个账号超过限制会自动顶掉之前绑定的账号，有注册回调）
     * @param {string} account
     */
    static appendAccountCallback(account) {
        return new Promise((resolve, reject) => {
            try {
                TPush.appendAccountCallback(account)
            } catch (e) {
                reject(e)
            }
            DeviceEventEmitter.addListener("appendAccount", res => {
                resolve(res)
            })
        })
    }

    /**
     * 解绑指定账号（3.2.2以及3.2.2之后的版本使用，无注册回调）
     * @param {string} account
     */
    static delAccount(account) {
        TPush.delAccount(account)
    }

    /**
     * 解绑指定账号（3.2.2以及3.2.2之后的版本使用，有注册回调）
     * @param {string} account
     */
    static delAccountCallback(account) {
        return new Promise((resolve, reject) => {
            try {
                TPush.delAccountCallback(account)
            } catch (e) {
                reject(e)
            }
            DeviceEventEmitter.addListener("deleteAccount", res => {
                resolve(res)
            })
        })
    }

    /**
     * 反注册，建议在不需要接收推送的时候调用
     */
    static unregisterPush() {
        TPush.unregisterPush()
    }

    /**
     * 设置标签
     * @param {string} tagName
     */
    static setTag(tagName) {
        TPush.setTag(tagName)
    }

    /**
     * 删除标签
     * @param {string} tagName
     */
    static deleteTag(tagName) {
        TPush.deleteTag(tagName)
    }

    /**
     * Activity被打开的效果统计；获取下发的自定义key-value
     */
    static onActivityStarted() {
        return new Promise((resolve, reject) => {
            try {
                TPush.onActivityStarted()
            } catch (e) {
                reject(e)
            }
            DeviceEventEmitter.addListener("clickedResult", res => {
                resolve(res)
            })
        })
    }

    /**
     * Activity被关闭的效果统计
     */
    static onActivityStoped() {
        TPush.onActivityStoped()
    }

    /**
     * 自定义本地通知样式
     * @param {int} notificationBulderId
     * @param {Object} options
     */

     static setPushNotificationBuilder(notificationBulderId,options){
         TPush.setPushNotificationBuilder(notificationBulderId,options)
     }

    /**
     * 本地通知
     * @param {Object} msg
     *
     * @property {int} type  //设置本地消息类型，1:通知，2:消息
     * @property {string} title //设置消息标题
     * @property {string} content //设置消息内容
     * @property {string} date //设置消息日期，格式为：20140502
     * @property {string} hour //设置消息触发的小时(24小时制)，例如：22代表晚上10点
     * @property {string} min //获取消息触发的分钟，例如：05代表05分
     * @property {int} builderId //设置消息样式，默认为0或不设置
     * @property {int} actionType //设置动作类型：1打开activity或app本身，2打开浏览器，3打开Intent ，4通过包名打开应用
     * @property {string} activity //设置拉起应用页面,主页面：MainActivity
     * @property {string} url // 设置URL
     * @property {string} intent // 设置Intent
     * @property {int} isCover // 是否覆盖原先build_id的保存设置。1覆盖，0不覆盖
     * @property {string} ring // 设置音频资源
     * @property {Object} extra //自定义字段，{"key1":"value1","key2":"value2"}
     * @property {string} downloadUrl //设置下载应用URL
     *
     */
    static addLocalNotification(msg) {
        return new Promise((resolve, reject) => {
            try {
                TPush.addLocalNotification(msg)
            } catch (e) {
                reject(e)
            }
            DeviceEventEmitter.addListener("addLocalNotification", res => {
                resolve(res)
            })
        })
    }

    /**
     * 检测通知栏是否关闭
     */
    static isNotificationOpened() {
        return new Promise((resolve, reject) => {
            try {
                TPush.isNotificationOpened()
            } catch (e) {
                reject(e)
            }
            DeviceEventEmitter.addListener("isNotificationOpened", res => {
                resolve(res)
            })
        })
    }

    /**
     * 注册回调
     */
    static addRegisterResultListener() {
        return new Promise((resolve, reject) => {
            DeviceEventEmitter.addListener("registerResult", res => {
                resolve(res);
            })
        })
    }

    /**
     * 反注册回调
     */
    static addUnregisterResultListener() {
        return new Promise((resolve, reject) => {
            DeviceEventEmitter.addListener("unRegisterResult", res => {
                resolve(res);
            })
        })
    }

    /**
     * 设置标签回调
     */
    static addSetTagResultListener() {
        return new Promise((resolve, reject) => {
            DeviceEventEmitter.addListener("setTagResult", res => {
                resolve(res);
            })
        })
    }

    /**
     * 删除标签回调
     */
    static addDeleteTagResultListener() {
        return new Promise((resolve, reject) => {
            DeviceEventEmitter.addListener("deleteTagResult", res => {
                resolve(res);
            })
        })
    }

    /**
     * 应用内消息的回调
     */
    static addTextMessageListener() {
        return new Promise((resolve, reject) => {
            DeviceEventEmitter.addListener("textMessage", res => {
                resolve(res);
            })
        })
    }

    /**
     * 通知被点击触发的回调
     */
    static addNotifactionClickedResultListener() {
        return new Promise((resolve, reject) => {
            DeviceEventEmitter.addListener("notificationClickedResult", res => {
                resolve(res);
            })
        })
    }

    /**
     * 通知被展示触发的回调，可以在此保存APP收到的通知
     */
    static addNotifactionShowedResult() {
        return new Promise((resolve, reject) => {
            DeviceEventEmitter.addListener("notificationShowedResult", res => {
                resolve(res);
            })
        })
    }

}