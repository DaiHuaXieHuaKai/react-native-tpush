
# Android API

#### 该插件是封装腾讯信鸽推送Android SDK，必要时可结合[腾讯信鸽推送Android SDK API](http://docs.developer.qq.com/xg/android_access/android_api.html)查看使用方法。

---

- [配置相关](#配置相关) 
  - [enableDebug](#enabledebug)
  - [setAccessId](#setaccessid)
  - [setAccessKey](#setaccesskey)
  - [getToken](#gettoken)
  - [setReportNotificationStatusEnable](#setreportnotificationstatusnnable)
  - [setReportApplistEnable](#setreportapplistenable)
  - [enableOtherPush](#enableotherpush)
  - [setMiPushAppId](#setmipushappid)
  - [setMiPushAppKey](#setmipushappkey)
  - [setMzPushAppId](#setmzpushappid)
  - [setMzPushAppKey](#setmzpushappkey)
  - [setHuaweiDebug](#sethuaweidebug)


- [推送功能相关](#推送功能相关)
  - [registerPush](#registerpush)
  - [registerPushCallback](#registerpushcallback)
  - [registerPushAccountCallback](#registerpushaccountcallback)
  - [registerPushWithLogin](#registerpushwithlogin)
  - [bindAccount](#bindaccount)
  - [bindAccountCallback](#bindaccountcallback)
  - [appendAccount](#appendaccount)
  - [appendAccountCallback](#appendaccountcallback)
  - [delAccount](#delaccount)
  - [delAccountCallback](#delaccountcallback)
  - [unregisterPush](#unregisterpush)
  - [setTag](#settag)
  - [deleteTag](#deletetag)
  - [onActivityStarted](#onactivitystarted)
  - [onActivityStoped](#onactivitystoped)
  - [setPushNotificationBuilder](#setpushnotificationbuilder)
  - [addLocalNotification](#addlocalnotification)
  - [isNotificationOpened](#isnotificationopened)


- [事件监听相关](#事件监听相关)
  - [addRegisterResultListener](#addregisterresultlistener)
  - [addUnregisterResultListener](#addunregisterresultlistener)
  - [addSetTagResultListener](#addsettagresultlistener)
  - [addDeleteTagResultListener](#adddeletetagresultlistener)
  - [addTextMessageListener](#addtextmessagelistener)
  - [addNotifactionClickedResultListener](#addnotifactionclickedresultlistener)
  - [addNotifactionShowedResult](#addnotifactionshowedresult)

### 配置相关

#### enableDebug

是否开启debug模式，即输出logcat日志重要：为保证数据的安全性，发布前必须设置为false

- enableDebug(debugMode)
  - debugMode:boolean

```
XG.enableDebug(true)
```

#### setAccessId

配置accessId,如果已在AndroidManifest.xml配置过，不需要再次调用；如果2者都存在，则以本接口为准。

- setAccessId(accessId)
  - accessId:long


```
XG.setAccessId("your accessId")
```

#### setAccessKey

配置setAccessKey,如果已在AndroidManifest.xml配置过，不需要再次调用；如果2者都存在，则以本接口为准。

- setAccessKey(accessKey)
  - accessKey:string


```
XG.setAccessKey("your accesskey")
```

#### getToken

获取设备的token，只有注册成功才能获取到正常的结果

- getToken()
  - 返回Promise


```
XG.getToken().then(res=>{
    alert(JSON.stringify(res))
})
```


#### setReportNotificationStatusEnable

设置上报通知栏是否关闭 默认打开

- setReportNotificationStatusEnable(debugMode)
  - debugMode:boolean


```
XG.setReportNotificationStatusEnable(true)
```


#### setReportApplistEnable

设置上报APP 列表，用于智能推送 默认打开

- setReportApplistEnable(debugMode)
  - debugMode:boolean


```
XG.setReportApplistEnable(true)
```

#### enableOtherPush

设置支持第三方厂商推送

- enableOtherPush(flag)
  - flag:boolean


```
XG.enableOtherPush(true)
```

#### setMiPushAppId

设置小米推送APPID

- setMiPushAppId(appid)
  - appid:string


```
XG.setMiPushAppId("your mi appid")
```

#### setMiPushAppKey

设置小米推送APPKEY

- setMiPushAppKey(appkey)
  - appkey:string


```
XG.setMiPushAppKey("your mi appkey")
```

#### setMzPushAppId

设置魅族推送APPID

- setMzPushAppId(appid)
  - appid:string


```
XG.setMzPushAppId("your mz appid")
```

#### setMzPushAppKey

设置魅族推送APPKEY

- setMzPushAppKey(appkey)
  - appkey:string


```
XG.setMzPushAppKey("your mz appkey")
```

#### setHuaweiDebug

华为手机的写日志定位问题

- setHuaweiDebug(isHuaweiDebug)
  - isHuaweiDebug:boolean

```
XG.setHuaweiDebug(true)
```

### 推送功能相关

#### registerPush

注册设备，通过addRegisterResultListener回调

- registerPush()


```
XG.registerPush()
```

#### registerPushCallback

注册设备，通过addRegisterResultListener回调。
因为腾讯信鸽提供了两种回调方式，因为本处采取addRegisterResultListener进行回调，故registerPush和registerPushCallback效果一样。

- registerPushCallback()


```
XG.registerPushCallback()
```

#### registerPushAccountCallback（弃用，使用bindAccount）


注册设备并绑定账号，通过addRegisterResultListener回调。
绑定账号注册指的是，在绑定设备注册的基础上，使用指定的账号（一个账号不能在多个设备登陆）注册APP，这样可以通过后台向指定的账号发送推送消息，有2个版本的API接口。

注意：这里的帐号可以是邮箱、QQ号、手机号、用户名等任意类别的业务帐号。

- registerPushAccountCallback(account)
  - account:string
  - 绑定的账号，绑定后可以针对账号发送推送消息，account不能为单个字符如“2”，“a”。

  - 如果要按别名推送，那就需要开发者在调用注册接口时把别名设置在注册请求里面的account字段，一台设备只允许有一个帐号别名。


```
XG.registerPushAccountCallback("UserAccount")
```

#### registerPushWithLogin

带登录态的注册绑定账号,通过addRegisterResultListener回调

考虑到用户的登陆态问题，比如手机QQ或QZone业务场景，我们提供了带登陆态的注册接口，方便适用该类业务的使用。

- registerPushWithLogin(account, ticket, ticketType, qua)
  - account：绑定的账号，绑定后可以针对账号发送推送消息。

  - 如果要按别名推送，那就需要开发者在调用注册接口时把别名设置在注册请求里面的account字段，一台设备只允许有一个别名，但一个别名下可以有15台设备，不能为null

  - ticket：登陆态票据，不能为null

  - ticketType：票据类型

  - qua：QZone专用字段，不需要时可填null


```
XG.registerPushWithLogin("UserAccount", "ticket", 1, null)
```

#### bindAccount

无回调

启动并注册APP，同时绑定账号,推荐有帐号体系的APP使用.

- bindAccount(account)
  - account:string


```
XG.bindAccount("UserAccount")
```

#### bindAccountCallback

有回调

启动并注册APP，同时绑定账号,推荐有帐号体系的APP使用.

- bindAccountCallback(account)
  - account:string
  - 返回Promise

```
XG.bindAccountCallback("UserAccount").then(res=>{
    alert(JSON.stringify(res))
})
```

#### appendAccount

无回调

此接口保留之前的账号，只做增加操作，
一个token下最多只能有3个账号超过限制会自动顶掉之前绑定的账号

- bindAccountCallback(account)
  - account:string

```
XG.appendAccount("UserAccount")
```

#### appendAccountCallback

有回调

此接口保留之前的账号，只做增加操作，
一个token下最多只能有3个账号超过限制会自动顶掉之前绑定的账号

- appendAccountCallback(account)
  - account:string
  - 返回Promise

```
XG.appendAccountCallback("UserAccount").then(res=>{
    alert(JSON.stringify(res))
})
```

#### delAccount

无回调

解绑指定账号

- delAccount(account)
  - account:string

```
XG.delAccount("UserAccount")
```
#### delAccountCallback

有回调

解绑指定账号

- delAccountCallback(account)
  - account:string
  - 返回Promise

```
XG.delAccountCallback("UserAccount").then(res=>{
    alert(JSON.stringify(res))
})
```
#### unregisterPush

反注册，建议在不需要接收推送的时候调用,通过addUnregisterResultListener回调

- unregisterPush()


```
XG.unregisterPush()
```

#### setTag

设置标签，通过addSetTagResultListener回调

一个应用最多有10000个tag，每个token在一个应用下最多100个tag， tag中不准包含空格。

- setTag(tagName)
  - tagName:string

```
XG.setTag("vip")
```

#### deleteTag

删除标签，通过addDeleteTagResultListener回调

- deleteTag(tagName)
  - tagName:string

```
XG.deleteTag("vip")
```

#### onActivityStarted

Activity被打开的效果统计；获取下发的自定义key-value

- onActivityStarted()
  - 返回Promise


```
XG.onActivityStarted().then(res=>{
    alert(JSON.stringify(res))
})
```

#### onActivityStoped

Activity被打开的效果统计

- onActivityStoped()


```
XG.onActivityStoped()
```

#### setPushNotificationBuilder

自定义推送样式

- setPushNotificationBuilder(builderId,options)
  - builderId:int
  - options:Object


```
options = {
    soundType : 1, //设置类型soundType:1--声音，2--震动，3--呼吸灯,默认系统通知类型
    flag : "auto" //否可以清除通知:auto--自动清除，no--不清除,默认不可清除
}

XG.setPushNotificationBuilder(10,options)
```

#### addLocalNotification

添加本地消息

- addLocalNotification(msg)
  - msg:Object
  - 返回值msgId

***ps:具体参数详情可对照信鸽后台推送高级设置***
```
msg = {
      type : 1, //设置本地消息类型，1:通知，2:消息
      title : "Test消息", //设置消息标题
      content : "我是Test消息的内容", //设置消息内容
      date : "20140502", //设置消息日期，格式为：20140502
      hour : "22", //设置消息触发的小时(24小时制)，例如：22代表晚上10点
      min : "05", //获取消息触发的分钟，例如：05代表05分
      builderId : 0, //设置消息样式，默认为0或不设置
      actionType : 1, //设置动作类型：1打开activity或app本身，2打开浏览器，3打开Intent ，4通过包名打开应用
      activity : "MainActivity", //设置拉起应用页面
      url : "http://www.baidu.com", //设置URL
      intent : "Test消息", //设置Intent
      isCover : 0, //是否覆盖原先build_id的保存设置。1覆盖，0不覆盖
      ring : "Test消息", //设置音频资源
      extra : {
          "customer1":"customer1_content"
      }, //设置自定义消息
      downloadUrl : "Test消息" //设置下载应用URL
  }

XG.addLocalNotification(msg).then(res=>{
    alert(JSON.stringify(res))
})
```


#### isNotificationOpened

检测通知栏是否关闭

- isNotificationOpened()
  - 返回Promise


```
XG.isNotificationOpened().then(res=>{
    alert(JSON.stringify(res))
})
```

### 事件监听相关

#### addRegisterResultListener

注册回调监听,在注册之后调用监听

- addRegisterResultListener()
  - 返回Promise


返回值 | 描述
---|---
accessId | 获取注册的accessId
account | 获取注册绑定的账号
deviceId | row 2 col 2
ticket | 设备ID
token | 设备的token，即设备唯一识别ID
ticketType | 票据类型

```
XG.addRegisterResultListener(res=>{
    alert(JSON.stringify(res))
})
```

#### addUnregisterResultListener

反注册回调,返回errCode

- addUnregisterResultListener()
  - 返回Promise
  

```
XG.addUnregisterResultListener(res=>{
    alert(JSON.stringify(res))
})
```

#### addSetTagResultListener

设置标签回调

- addSetTagResultListener()
  - 返回Promise


返回值 | 描述
---|---
errCode |错误码
tagName |标签名称

```
XG.addSetTagResultListener(res=>{
    alert(JSON.stringify(res))
})
```

#### addDeleteTagResultListener

删除标签回调

- addDeleteTagResultListener()
  - 返回Promise


返回值 | 描述
---|---
errCode |错误码
tagName |标签名称

```
XG.addDeleteTagResultListener(res=>{
    alert(JSON.stringify(res))
})
```

#### addTextMessageListener

应用内消息回调

- addTextMessageListener()
  - 返回Promise
  
返回值 | 描述
---|---
title |消息标题
content |消息内容
customContent |自定义消息内容


```
XG.addTextMessageListener(res=>{
    alert(JSON.stringify(res))
})
```

#### addNotifactionClickedResultListener

通知被点击触发的回调

- addNotifactionClickedResultListener()
  - 返回Promise

返回值 | 描述
---|---
msgId |消息ID
title |消息标题
content |消息内容
activityName |activity名称
customContent |自定义消息内容


```
XG.addNotifactionClickedResultListener(res=>{
    alert(JSON.stringify(res))
})
```


#### addNotifactionShowedResult

通知展示触发的回调

- addNotifactionShowedResult()
  - 返回Promise

返回值 | 描述
---|---
msgId |消息ID
title |消息标题
content |消息内容
activityName |activity名称
customContent |自定义消息内容


```
XG.addNotifactionShowedResult(res=>{
    alert(JSON.stringify(res))
})
```