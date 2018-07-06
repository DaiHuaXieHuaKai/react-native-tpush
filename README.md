# react-native-tpush
封装腾讯信鸽推送SDK,实现移动推送

### 安装

```
npm install react-native-tpush --save
```

### 配置

```
react-native link react-native-tpush
```
执行完后查看项目名称->android->app->build.gradle中dependencies是否存在以下项，不存在则手动添加
```
compile project(':react-native-tpush')
```
并在项目名称->android->app->src->main->java->项目包名路径->MainApplication.java中的getPackages方法中添加new TPushPackage()并引入包
import com.witty.tpushlibrary.TPushPackage;
```
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
          new MainReactPackage(),
            new TPushPackage()
      );
    }
```


### 修改ACCESS_ID和ACCESS_KEY

替换 项目名称->android->app->build.gradle中的manifestPlacehoders里面的XG_ACCESS_ID和XG_ACCESS_KEY为你自己申请的ID和KEY。

如果不存在该项，则在defaultConfig中添加
```
defaultConfig{
    ...
        manifestPlaceholders = [
                XG_ACCESS_ID : "YOUR ACCESS ID",
                XG_ACCESS_KEY : "YOUR ACCESS KEY"
        ]
    }
```

### 使用方法

```
import XG from 'react-native-tpush'

  componentDidMount(){
    XG.registerPush();
    XG.addRegisterResultListener().then(res=>{
      alert("register:"+JSON.stringify(res))
    })
    XG.addUnregisterResultListener().then(res=>{
      alert("unregister:"+JSON.stringify(res))
    })
    XG.addSetTagResultListener().then(res=>{
      alert("setTag:"+JSON.stringify(res))
    })
    XG.addDeleteTagResultListener().then(res=>{
      alert("deleteTag:"+JSON.stringify(res))
    })
    XG.addTextMessageListener().then(res=>{
      alert("textMessage:"+JSON.stringify(res))
    })
    XG.addNotifactionClickedResultListener().then(res=>{
      alert("clicked:"+JSON.stringify(res))
    })
    XG.addNotifactionShowedResult().then(res=>{
      alert("showed:"+JSON.stringify(res))
    })
  }


    testTPush(){    
        let msg = {
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
  }
```

### 完整API

[Android API](https://github.com/DaiHuaXieHuaKai/react-native-tpush/blob/master/TPush_Android_API.md)

### 常见问题

```
Error:Execution failed for task ':app:processDebugManifest'.
> Manifest merger failed : Attribute application@allowBackup value=(false) from AndroidManifest.xml:11:7-34
	is also present at [pushdemo:tpushlibrary:unspecified] AndroidManifest.xml:12:9-35 value=(true).
	Suggestion: add 'tools:replace="android:allowBackup"' to <application> element at AndroidManifest.xml:7:5-24:19 to override.
```
##### 解决方式一：
修改   项目名->android->app->src->main->AndroidManifest.xml中的android:allowBackup="false"为android:allowBackup="true"
##### 解决方式二：
修改   项目名->android->app->src->main->AndroidManifest.xml中

在manifest节点加上

```
xmlns:tools="http://schemas.android.com/tools"
```
在application节点增加
tools:replace="android:allowBackup"
例如：

```
<application tools:replace="android:allowBackup"
```