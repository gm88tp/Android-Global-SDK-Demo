# GM88 Android海外游戏2.6.0版本SDK 对接文档 2024/9/25

***请注意：demo内的所有参数均是为了方便展示，接入时请使用运营提供的参数进行接入，在SDK1.4.0版本后横屏、竖屏的界面会有所不同，请接入出包时锁定横竖屏***

v2.6.1 更新:
1.更新Globalsdk_2.6.1.aar SDK文件
解决Google上架新报的“设备和网络滥用”违规政策问题

v2.6.0 更新:
从2.5.2版本迁移时请注意下列更新操作
1.更新Globalsdk_2.6.0.aar SDK文件
2.更新targetSdkVersion为34, 请注意适配其他代码
2.1更新AndroidManifest.xml文件内修改namespace代码
2.2更新App级别的gradle文件，对ToDo 内容中的 update2.6.0代码进行修改替换
2.3更新项目级别的gradle文件和gradle-wrapper.properties文件，符合更新targetSdkVersion为34所对应的编辑器和gradle插件版本

v2.5.2 更新:
从2.5.1版本迁移时请注意下列更新操作
1.更新Globalsdk_2.5.2.aar SDK文件

v2.5.1 更新:
从2.5版本迁移时请注意下列更新操作
1.更新targetSdkVersion为33, 请注意适配其他代码
2.更新Google IAPsdk版本为com.android.billingclient:billing:6.0.1
3.升级其他远程依赖

v2.5 更新:
从老版本迁移时请注意下列更新操作
1.更新targetSdkVersion为31
2.升级依赖
3.新增aihelp客服功能, 请注意修改GMConfig文件
4.fb新版SDK登录需要添加ClientToken参数, 如果没有此参数请联系怪猫运营提供并添加到包内, 添加方法见文档内2.相关资源引入部分

## 1.相关依赖引入

在工程级别的build.gradle 的android内加入以下代码
注：从2024年8月31日起, 在Google市场上架应用时必须targetSdkVersion>=34, 2024年8月31日起, 在Google市场更新应用时必须targetSdkVersion>=34, 详细[适配文档](https://developer.android.com/about/versions/13/behavior-changes-13?hl=zh-cn)

```
   defaultConfig{
        minSdkVersion 21 //minSdkVersion根据游戏需求制定, 最好大于等于21
        targetSdkVersion 34
        multiDexEnabled true
    }
    sourceSets {
        main {
            assets.srcDirs = ['../assets', 'src/main/assets', 'src/main/assets/']
            jniLibs.srcDirs = ['libs']
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_17
        targetCompatibility JavaVersion.VERSION_17
    }
```

引入以下依赖：

```
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation(name: 'cafeSdk-4.4.1', ext: 'aar')
    //ToDo update2.6.0 更新SDK版本主要需要更新此aar包
    implementation(name: 'Globalsdk_2.6.0', ext: 'aar')
    implementation(name: 'sos_library-1.1.3.4', ext: 'aar')
    api 'com.google.gms:google-services:4.3.15'
    api 'com.google.android.gms:play-services-analytics:18.0.3'
    api 'com.google.android.gms:play-services-auth:20.7.0'
    api 'com.facebook.android:facebook-android-sdk:latest.release'
    api 'org.apache.httpcomponents:httpcore:4.4.10'
    //ToDo update2.6.0 谷歌广告相关SDK版本更新
    api 'com.google.android.gms:play-services-ads:23.3.0'
    implementation 'com.google.ads.mediation:applovin:12.6.0.0'
    implementation 'com.google.ads.mediation:vungle:7.0.0.0'
    implementation 'com.google.ads.mediation:facebook:6.16.0.0'
    //ToDo update2.6.0 谷歌核心服务替换更新
    implementation 'com.google.android.play:review:2.0.1'
    api 'androidx.multidex:multidex:2.0.1'
    //noinspection GradleCompatible
    api 'androidx.recyclerview:recyclerview:1.1.0'
    // Required Dependency by Audience Network SDK
    api 'com.facebook.android:audience-network-sdk:6.16.0'
    api 'com.alibaba:fastjson:1.1.70.android'
    // Import the BoM for the Firebase platform
    //ToDo update2.6.0 firebase相关SDK版本更新
    implementation platform('com.google.firebase:firebase-bom:33.1.2')
// Add the dependencies for the Crashlytics and Analytics libraries
// When using the BoM, you don't specify versions in Firebase library dependencies
    implementation 'com.google.firebase:firebase-crashlytics:19.0.3'
    implementation 'com.google.firebase:firebase-analytics:22.0.2'
    implementation 'com.google.firebase:firebase-messaging:24.0.0'
    implementation 'com.google.firebase:firebase-perf:21.0.1'
    implementation 'com.google.firebase:firebase-dynamic-links:22.1.0'

    api 'androidx.appcompat:appcompat:1.1.0'
    api 'androidx.annotation:annotation:1.1.0'
    api 'androidx.vectordrawable:vectordrawable-animated:1.1.0'
    api 'androidx.legacy:legacy-support-v4:1.0.0'
    api 'androidx.core:core:1.2.0'
    api 'android.arch.work:work-runtime:1.0.1'
    api 'com.google.guava:guava:28.0-jre'
    api 'androidx.constraintlayout:constraintlayout:1.1.3'
    implementation 'com.braintreepayments.api:braintree:2.22.0'
    implementation 'com.squareup.okhttp3:okhttp:4.8.0'
    //谷歌支付6.0.1版本需要搭配SDK2.5.1或以上版本
    implementation 'com.android.billingclient:billing:6.0.1'


    //推特登陆和分享相关
    implementation 'com.twitter.sdk.android:twitter-core:3.1.1'
    implementation 'com.twitter.sdk.android:tweet-ui:3.1.1'
    implementation 'com.twitter.sdk.android:tweet-composer:3.1.1'
    //line
    api 'com.linecorp:linesdk:5.0.1'

    implementation "org.java-websocket:Java-WebSocket:1.4.0"
    implementation 'cn.jzvd:jiaozivideoplayer:7.6.0'

    //AndroidX 版本
    implementation 'com.github.jenly1314:zxing-lite:2.2.1'
    //aihelp
    implementation 'net.aihelp:android-aihelp-aar:4.2.+'
```

在工程级别的build.gradle 文件内增加以下插件

```
    apply plugin: 'com.google.gms.google-services'
    apply plugin: 'com.google.firebase.crashlytics'
    apply plugin: 'com.google.firebase.firebase-perf'
```

在项目级别的build.gradle 文件内增加以下内容

```
buildscript {
    repositories {
        google()
        mavenCentral()
        maven {
            url 'https://maven.aliyun.com/repository/public/'
        }
        maven {
            url 'https://maven.aliyun.com/repository/jcenter/'
        }
        maven {
            url 'https://maven.google.com/'
            name 'Google'
        }
        maven { url 'https://maven.google.com' }
        maven {
            url "https://android-sdk.is.com"
        }

    }
    dependencies {
        //ToDo update2.6.0 请根据开发IDE的版本选择符合API34的gradle插件版本
        classpath 'com.android.tools.build:gradle:8.1.0'
        classpath 'com.google.gms:google-services:4.4.2'
        classpath 'com.google.firebase:firebase-crashlytics-gradle:3.0.2'
        // Add the dependency for the Performance Monitoring plugin
        classpath 'com.google.firebase:perf-plugin:1.4.2'  // Performance Monitoring plugin
    }
}
allprojects {
    repositories {
        google()
        mavenCentral()
        maven {
            url 'https://maven.aliyun.com/repository/public/'
        }
        maven {
            url 'https://maven.aliyun.com/repository/jcenter/'
        }
        flatDir {
            dirs 'libs'
        }
        maven {
            url 'https://maven.google.com/'
            name 'Google'
        }
        maven {
            url 'https://android-sdk.is.com/'
        }
    }
}
```

## 2.相关资源引入

### 创建assets文件夹。拷贝资源内的GMConfig.xml

1）请修改gmsdk标签内的appId参数为运营提供的游戏id，appReleaseId为提供的发布记录id(必须)。
2）Google标签内的clientId，为运营提供的谷歌ClientID；billing为Google支付秘钥(必须)。
3）line标签内的channel，为运营提供的Line登录LineChannelID。
4）twitter标签内的参数，修改为运营提供的相应的twitter参数。
5）aihelp标签内的参数，修改为运营提供的相应的aihelp参数(必须)。
6）如果游戏是韩国版本，需要修改café标签内的相关参数为运营提供的参数。
7）出正式包时请修改host标签内的url链接为正式服链接，GMSDK采用每个项目独立的域名的形式，具体项目使用域名会由运营提供(必须)
8）如果游戏需要启用预注册奖励功能，请修改register标签下的item_id和item_price值，相关参数由运营提供

### 拷贝运营提供的google-services.json文件

拷贝运营提供的google-services.json文件到工程级别的主目录。如果是android studio开发，拷贝到app目录下即可
**请注意，demo中没有添加google-services.json，漏加会影响项目编译**

### 添加libs下相关aar依赖

请添加并引入libs内的全部aar依赖，如果不是QOO渠道包，则无需引入qooapp-opensdk-2.0.6.aar，QOO渠道包对接方法见文档末尾

### 清单文件内容添加

添加Demo内的清单文件内容到游戏Manifest内，并修改To-do标签内的相关value，具体value值运营会提供，FacebookContentProvider下{facebook_app_id}替换为facebook_app_id参数，并去掉括号；AdMob应用ID下的{AdMob_cpkey}替换为Google Admob cpkey并去掉括号

```
    <!--todo  facebook参数-->
    <meta-data
        android:name="com.facebook.sdk.ApplicationId"
        android:value="@string/facebook_app_id" />
    <meta-data
        android:name="com.facebook.sdk.ClientToken"
        android:value="@string/facebook_client_token"/>
    <provider
        android:name="com.facebook.FacebookContentProvider"
        android:authorities="com.facebook.app.FacebookContentProvider{facebook_app_id}"
        android:exported="true" />
    <activity
        android:name="com.facebook.CustomTabActivity"
        android:exported="true">
    </activity>

    <receiver
        android:name="com.appsflyer.SingleInstallBroadcastReceiver"
        android:exported="true">
        <intent-filter>
            <action android:name="com.android.vending.INSTALL_REFERRER" />
        </intent-filter>
    </receiver>

    <!--todo  广告参数 AdMob应用ID-->
    <meta-data
        android:name="com.google.android.gms.ads.APPLICATION_ID"
        android:value="{AdMob_cpkey}" />
```

在application节点内添加：

```
android:networkSecurityConfig="@xml/network_security_config"
```

并在xml下创建network_securitdefalutConfigy_config.xml文件，xml内添加

```
<network-security-config>
    <domain-config cleartextTrafficPermitted="true">
        <domain includeSubdomains="true">127.0.0.1</domain>
    </domain-config>
</network-security-config>
```

在启动activity下添加：

```
    <intent-filter>
        <action android:name="android.intent.action.VIEW" />
        <category android:name="android.intent.category.DEFAULT" />
        <category android:name="android.intent.category.BROWSABLE" />
        <data android:scheme="@string/fb_login_protocol_scheme" />
    </intent-filter>
```

### 资源文件添加

在项目的values文件夹内，添加如下的strings：

```
<string name="facebook_app_id">facebook_app_id</string>
<string name="fb_login_protocol_scheme">fbfacebook_app_id</string>
<string name="facebook_client_token">fb_client_token</string>
```

**请注意，请将facebook_app_id，替换为运营提供的id，fb_login_protocol_scheme中须保留fb开头, facebook_client_token替换为运营提供的token**

## 3.SDK方法文档

### 3.1Application内的初始化(必接)


在Application的onCreate方法中实现以下：

```
GMSDK.initApplication(this);
FirebaseApp.initializeApp(this);
AudienceNetworkAds.initialize(this);
```

在Application的attachBaseContext方法中实现以下：

```
MultiDex.install(this);
```

### 3.2MainActivity内的初始化(必接)

SDK使用统一的Callback，在MainActivity(游戏主Activity)的onCreate方法内实现以下(监听回调根据所需添加)：

```
GMSDK.setCallBack(new GMCallback() {
    @Override
    public void onCallBack(final Message msg) {
        switch (msg.what) {
            case GMActionCode.ACTION_INIT_SUCC://初始化成功

                break;
            case GMActionCode.ACTION_INIT_FAILED://初始化失败

                break;
            case GMActionCode.ACTION_LOGIN_SUCC://登录成功，返回User

                break;
            case GMActionCode.ACTION_LOGIN_CANCEL://退出登录

                break;
            case GMActionCode.ACTION_LOGIN_FAILED://登录失败

                break;
            case GMActionCode.ACTION_LOGOUT_SUCC://登出成功

                break;
            case GMActionCode.ACTION_GAME_EXIT://退出游戏
         
                break;
            case GMActionCode.ACTION_LOGOUT_FAILED://登出失败，一般不会出现，出现代表有问题

                break;
            case GMActionCode.ACTION_PAY_SUCC://支付成功

                break;
            case GMActionCode.ACTION_PAY_CANCEL://用户退出支付

                break;
            case GMActionCode.ACTION_PAY_FAILED://支付失败

                break;
            case GMActionCode.ACTION_ADSHOW_SUCCESS://广告播放完成

                break;
            case GMActionCode.ACTION_ADSHOW_FAILED:// 广告播放失败

                break;
            case GMActionCode.ACTION_ADSHOW_BANED:// 用户所在地区不支持播放广告

                break;
            case GMActionCode.ACTION_SHARE_SUCCESS://分享成功

                break;
            case GMActionCode.ACTION_SHARE_FAILED://分享失败

                break;
            case GMActionCode.ACTION_SHARE_CANCEL://分享取消

                break;
            case GMActionCode.ACTION_QUERY_NOTBIND://未绑定

                break;
            case GMActionCode.ACTION_QUERY_BIND_FAILED://查询绑定失败

                break;
            case GMActionCode.ACTION_QUERY_ISBIND://已绑定

                break;
            case GMActionCode.ACTION_BIND_SUCCESS://绑定成功

                break;
            case GMActionCode.ACTION_BIND_FAILED://绑定失败

                break;
            case GMActionCode.ACTION_TRANSLATION_SUCCESS://翻译成功
            
                break;
            case GMActionCode.ACTION_TRANSLATION_FAILED://翻译失败
            
                break;
            case GMActionCode.ACTION_REGISTERATION_CHECK_SUCCESS://预注册查询成功
            
                break;
            case GMActionCode.ACTION_REGISTERATION_CHECK_FAILED://预注册查询失败
            
                break;       
            default:
                break;
        }
    }
});
GMSDK.initMainActivity(OverSeaGameActivity.this);
```

### 3.3发起登录(必接)

当游戏收到初始化成功后，可发起登录
接口定义：

```
GMSDK.doLogin();
```

登录成功或者失败，都可以在回调中得到结果。返回token和怪猫唯一uid，示例:

```
JSONObject loginResult = new JSONObject(String.valueOf(msg.obj));
String token = loginResult.getString("token");
String uid =loginResult.getString("uid");
```

请使用token调用后端接口获得用户标识，收到回调的token，建议通过服务端验证token，获取用户id，详情查看服务端文档

### 3.4发起支付(必接)

当游戏内需发起支付时，应调用此接口
接口定义：

```
GMSDK.doPay(Map<String, String> payJson)
```

**payJson参数**

| 字段           | 类型     | 说明                                                                     |
| ------------ | ------ |------------------------------------------------------------------------|
| productId    | string | 商品ID                                                                   |
| productName  | string | 商品名称，会显示在相应支付界面上                                                       |
| productPrice | string | 商品价格, 价格必须传美金单位                                                        |
| extra        | string | 订单透传参数，这些参数会在支付回调时一并回传给CP，请CP自行解析（透传参数每笔订单需要变化，否则无法重复拉起支付）             |
| roleId       | string | 待支付角色ID                                                                |
| roleName     | string | 待支付角色名                                                                 |
| serverId     | string | 待支付角色区服ID                                                              |
| serverName   | string | 待支付角色区服名称                                                              |
| notifyUrl    | string | 支付通知地址，对接支付时请将通知地址告知我方运营进行配置，我方后台配置后通知地址后将以后台配置为准这里无需传入，如果这里传入则以传入地址为准 |

**请注意，productName、productId需按照计费表内数据传入，否则不会创建订单**

调用示例：

```
Map<String, String> payinfo = new HashMap<>();
payinfo.put("productName", "1001-60元寶");
payinfo.put("productPrice", "0.99");
payinfo.put("productId", "1001");
payinfo.put("roleId", "1");
payinfo.put("roleName", "1");
payinfo.put("serverId", "1");
payinfo.put("serverName", "1");
payinfo.put("notifyUrl", "");
payinfo.put("extra", System.currentTimeMillis() / 1000 + "");
GMSDK.doPay(payJson);
```

### 3.5角色变更接口(必接)

当游戏内角色状态变化时，应调用此接口
接口定义：

```
GMSDK.doSpot(String spotJson)
```

| 字段       | 类型     | 说明                                                                                                                                                                                                                                                             |
| -------- | ------ |----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| spotType | string | 事件类型,取值为:1:创建角色 2:完成新手引导 3:玩家等级变化后上传（升级） 4:玩家选择完区服进入游戏                                                                                                                                                                                                         |
| extra    | json   | 这是角色具体信息,格式为Json,包括信息:roleId:角色ID(必传),roleName:角色名(必传),roleServer:区服ID(必传),serverName:区服名字(必传),roleLevel:角色等级(必传),vipLevel:角色Vip等级(无vip系统可传0),zone:大区ID(游戏有大区分区例如东西区,传入大区id,游戏没有此设定可不传),zoneName:大区名(同大区id),globalRoleId:全局唯一的角色ID(游戏唯一角色id,如果角色id是唯一的可传如角色id) |

**请注意，玩家选择完区服上报进入游戏 （spotType为4）必须接入，否则会影响SDK功能，其余上报不接入会影响打点数据准确性**
调用示例：

```
JSONObject spotJson = new JSONObject();
try {
    spotJson.put("spotType","3");
    JSONObject extra = new JSONObject();
    extra.put("roleName","等级回传");
    extra.put("roleLevel","11");
    extra.put("roleId","111");
    extra.put("roleServer","22");
    extra.put("serverName","1服");
    extra.put("vipLevel","1");
    extra.put("zone","1");
    extra.put("zoneName","东区");
    extra.put("globalRoleId","gm88_70_10691603");
    spotJson.put("extra",extra);
} catch (JSONException e) {
    e.printStackTrace();
}
GMSDK.doSpot(spotJson.toString())
```

### 3.6生命周期接入(必接)

需要在游戏主Activity内重写以下生命周期方法并接入

```
//重写的方法为
onResume();
onPause();
onStop();
onActivityResult(int requestCode, int resultCode, Intent data);
onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults);
onDestroy();
```

```
//接入的方法为
GMSDK.onResume();
GMSDK.onPause();
GMSDK.onStop();
GMSDK.onActivityResult(int requestCode, int resultCode, Intent data);
GMSDK.onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults);
GMSDK.onDestroy();
```

如果游戏没有对返回键进行处理，请重写以下方法

```
onBackPressed();
```

接入的方法为

```
GMSDK.onBackPressed();
```


### 3.7 相关游戏内接口
3.7.1 游戏进入状态更变接口
当开始进入游戏（加载前）时，调用此接口

调用示例：
```
GMSDK.doSpotStartLoading();
```

当游戏完成加载时，可调用此接口。参数传入实际加载时间（注意参数spendSecond单位为秒）
调用示例：
```
GMSDK.doGameEndLoading(int spendSecond)
```

调用示例：
当游戏进入选服页时，可调用此接口。
```
GMSDK.doGameEnterSelectServer()
```

3.7.2 游戏内游戏币消耗事件接口
当游戏内发生获得游戏币或者消耗游戏币的事件时，调用此接口

调用示例：
```
GMSDK.doGameCoin(int type,String coinName,int coin)
```

| 字段      | 类型    | 说明            |
| --------- | ------ | --------------- |
| type | int | 事件类型:0为获得游戏币，1为消耗游戏币|
| coinName | String | 游戏币名称       |
| coin | int | 游戏币数量         |


### 3.8发起分享接口

SDK分享接口有三种选择，可从GM后台分享，或直接传入链接或图片进行分享。

#### 3.8.1 GM后台分享

当游戏需要拉起分享的时候，想从GM后台读取内容分享时，应调用此接口
接口定义：

```
GMSDK.share(String shareInfo);
```

**shareInfo 示例**

| 字段        | 类型     | 说明              |
| --------- | ------ | --------------- |
| shareID   | int    | 分享内容Id(运营提供)    |
| shareName | string | 分享内容Name(运营提供)  |
| uName     | string | 分享者游戏名          |
| server    | string | 分享者所在区服         |
| code      | string | 邀请码(可供接受分享者使用等) |

调用示例：

```
JSONObject shareinfo = new JSONObject();
try {
    shareinfo.put("shareID", "1");
    shareinfo.put("shareName", "分享");
    shareinfo.put("uName", "11");
    shareinfo.put("server", "2");
    shareinfo.put("code", "3");
} catch (JSONException e) {
    e.printStackTrace();
}
GMSDK.doShare(shareinfo.toString());
```

#### 3.8.2 直接分享（链接形式）

当游戏需要拉起分享的时候，想直接分享链接时，应调用此接口，现阶段此接口支持分享到Facebook和Twitter
接口定义：

```
GMSDK.doCPShareLink(String title, String content, String link);
```

** 传入参数示例**

| 字段        | 类型     | 说明              |
| --------- | ------ | --------------- |
| title | string | 分享标题  |
| content     | string | 分享内容          |
| link    | string | 分享链接         |


#### 3.8.3 直接分享（网络图片形式）

当游戏需要拉起分享的时候，想直接分享图片时，应调用此接口，现阶段此接口支持分享到Facebook
接口定义：

```
GMSDK.doCPShareImage(String title, String content, String imageUrl);
```

** 传入参数示例**

| 字段        | 类型     | 说明              |
| --------- | ------ | --------------- |
| title | string | 分享标题  |
| content     | string | 分享内容          |
| imageUrl    | string | 分享图片url         |

#### 3.8.4 直接分享（本地图片形式）

当游戏需要拉起分享的时候，想直接分享手机本地图片时，应调用此接口，请注意本地分享只能分享包名路径下的地址，例如/data/data/包名/files/Screenshot/Share.png
接口定义：

```
GMSDK.doCPShareLocalImage(String title, String content, String imageUrl);
```

** 传入参数示例**

| 字段        | 类型     | 说明              |
| --------- | ------ | --------------- |
| title | string | 分享标题  |
| content     | string | 分享内容          |
| photoUrl    | string | 分享图片本地地址        |

#### 3.8.5 单独渠道直接分享（本地图片形式）

当游戏需要直接拉起某一分享渠道进行分享的时候，应调用此接口，请注意本地分享只能分享包名路径下的地址，例如/data/data/包名/files/Screenshot/Share.png
接口定义：

```
GMSDK.doShareImage(int type, String imageUrl, String link, String text, String tag);
```

** 传入参数示例**

| 字段        | 类型     | 说明              |
| --------- | ------ | --------------- |
| type | int | 分享渠道  | type = 1为facebook 2为ins 3为twitter 4为line  |
| imageUrl    | string | 分享图片本地地址        |
| link     | string | 分享携带的链接          |
| text    | string | 分享携带的文本       |
| tag    | string | 分享携带的话题标签       |



### 3.9调起广告接口

当游戏需要拉起广告的时候，应调用此接口，播放广告时若播放失败，SDK会回调ACTION_ADSHOW_BANED或ACTION_ADSHOW_FAILED；播放完成时会回调ACTION_ADSHOW_SUCCESS
接口定义：

```
ADSDK.getInstance().doShowAD(String extra);
```

| 字段名称   | 类型     | 属性                         |
| ------ | ------ | -------------------------- |
| adType | int    | 广告形式(目前仅支持激励视频，请直接传int型13) |
| extra  | String | 广告透传参数，在成功的回调内，会原样返回       |

调用示例：

```
String extra = "{"adType":"13","info":"infos"}";
ADSDK.getInstance().doShowAD(extra);
```

### 3.10游戏绑定账号接口

游戏内需提供显示绑定账号页面的入口，点击入口时调用此接口
接口定义：

```
GMSDK.showBind();
```

调用示例：

```
GMSDK.showBind();
```

### 3.10.1游戏单独绑定某一渠道接口

游戏内想要自制绑定界面，可调用此接口用来单独拉起某一绑定渠道
接口定义：

```
GMSDK.doBind(int type);
```
** 传入参数示例**

| 字段        | 类型     | 说明              |
| --------- | ------ | --------------- |
| type | int | 绑定渠道  | type = 1为google 2为fb 3为twitter 4为line  5为邮箱绑定   |


调用后会给游戏对应的回调

### 3.11查询绑定账号接口

游戏内需查询当前账号绑定状态时时调用此接口
接口定义：

```
GMSDK.doQueryBind();
```

调用示例：

```
GMSDK.doQueryBind();
```

调用后会给游戏对应的回调

### 3.12查询当前游戏需要展示给用户查看的货币

多语言环境下，当游戏需要查询当前游戏需要展示给用户查看的货币时调用次接口查询
接口定义：

```
GMSDK.getPurchaseList(GlobalCallback callback);
```

调用示例：

```
GMSDK.getPurchaseList(new GlobalCallback() {
    @Override
    public void onSuccess(String o) {
        SDKLog.d(TAG, "doPurchaseListDone=" + o);
    }   

    @Override
    public void onFailed(String msg) {
        SDKLog.d(TAG, "doPurchaseListDone=" + msg);
    }
});
```

| 字段           | 类型      | 说明                                                                         |
| ------------ | ------- | -------------------------------------------------------------------------- |
| status       | boolean | true : 查询成功    false:  查询失败                                                |
| purchaselist | string  | 商品信息实体beanlist                                                             |
| errorno      | string  | 仅在status 为false 的时候返回，    0：查询失败，游戏可以再次拉起查询，请不要一直尝试。  -1: 因为某些特殊原因，请显示默认货币 |

purchaselist 示例:

```
String purchaselist = "{"1001":"$0.99","1002":"$4.99"}";
```

| 字段        | 类型     | 说明                      |
| --------- | ------ | ----------------------- |
| productId | string | 商品id                    |
| price     | string | 商品当前价格 带货币符号   ￥6/$0.99 |

## 4.SDK方法选接文档

以下接口为选接接口，按需求接入

### 4.1游戏内行为打点

当游戏内发生相关行为后，应调用此接口，如需接入运营会提供打点表进行接入
接口定义：

```
GMSDK.doEventInfo(String eventInfo);
```

| 字段        | 类型     | 说明             |
| --------- | ------ | -------------- |
| eventInfo | string | 行为事件名(该值由运营提供) |

调用示例：

```
GMSDK.doEventInfo(eventInfo);
```

### 4.2将文字存入剪贴板

当游戏需要将文字存入剪贴板时，可以调用此方法
接口定义：

```
GMSDK.doSetPasteboard(String extra);
```

| 字段    | 类型     | 说明         |
| ----- | ------ | ---------- |
| extra | string | 需要存入剪贴板的文字 |

调用示例：

```
GMSDK.doEventInfo(extra);
```

### 4.3获取当前手机系统语言和地区、获取当前手机时区

当游戏需要区分当前手机系统语言时，可以调用此方法来获取
接口定义：

```
GMSDK.doLanguage();
```

调用示例：

```
String language = GMSDK.doLanguage();
```

响应：
返回示例: "zh-CN"。

其中，前半部分表示语言，zh代表中文，后半部分代码地区，CN代表中国。如果只要按语言判断，请只判断前半部分，如果只需按地区判断，请只判断后半部分。因为一种语言会在多个地区出现，一个地区也会有多种语言。

语言码的ISO标准：[ISO 639-1](https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes)

地区码的ISO标准：[ISO 3166-2](https://en.wikipedia.org/wiki/ISO_3166-2)

当游戏需要获取当前用户手机时间所在时区时，可以调用此方法来获取

```
GMSDK.doTimeZone();
```

调用示例：

```
String timeZone = GMSDK.doTimeZone();
```

响应：
返回示例: "+8"。(表示东八区)

### 4.4翻译文本

当游戏需要对任何语言进行翻译时，调用以下方法，该方法会返回源语言对应当前手机语言的翻译
接口定义：

```
GMSDK.translation2Text(String extra,String sourceText);
```

| 字段         | 类型     | 说明                  |
| ---------- | ------ | ------------------- |
| sourceText | string | 源语言，待翻译的语句          |
| extra      | string | 当前语句的唯一标识，在返回的时候会透传 |

在翻译结束后，会给游戏相应的回调

调用示例：

```
GMSDK.translation2Text(extra, Hello);
```

当翻译成功时，会返回一个Json字符串，形式如下：

```json
{"targetText":"hello","extra":"test1"}
```

当翻译失败时，会返回一个Json字符串，形式如下：

```json
{"errorno":"error","extra":"test1"}
```

### 4.5打开外部网页接口（外部浏览器打开）

当游戏需要通过外部浏览器打开一个网页时，可以使用此方法，包括但不限定打开Facebook粉丝页，Lobi，Twitter，巴哈姆特等
调用示例：

```
GMSDK.doOpenURLbyWeb(String url);
```

| 字段  | 类型     | 说明          |
| --- | ------ | ----------- |
| url | string | 需要打开的外部网页地址 |

### 4.6打开webview网页接口（webview打开）

当游戏需要通过webview打开一个网页时，可以使用此方法，请注意打开的url需要为https协议
调用示例：

```
GMSDK.doOpenURLbyWebView(String url);
GMSDK.doOpenURLbyWebView(String url, ScreenOrientation orientation);
```

| 字段  | 类型     | 说明                                        |
| --- | ------ |-------------------------------------------|
| url | string | 需要打开的webview网页地址                          |
| orientation | ScreenOrientation | 可选参数,如果不指定打开webview方向则默认打开和游戏方向一致的webview |



### 4.7打开账号选择界面接口

当游戏内需要显示打开账号选择界面页面的入口，点击入口时调用此接口
调用示例：

```
GMSDK.showLogin();
```

### 4.8打开帮助中心（原客服中心）

sdk2.0版本之后，将原faq、普通客服、vip客服整合在帮助中心页面。
帮助中心集成了faq、用户意见反馈、与后台客服即时聊天功能。
原vip客服showVipServiceCenter()、showQuestions();方法也已合并至帮助中心(方法目前仍保留，提供过渡)。请全部统一改为以下方法。


调用示例：
```
GMSDK.showServiceCenter();
```




### 4.9打开SDK常见问题界面（已合并至帮助中心）
原faq界面接口也已合并至帮助中心，方法保留提供过渡使用。

调用示例：
```
GMSDK.showQuestions();
```

### 4.10查询预注册状态，申请发放预注册奖励

预注册奖励为游戏在Google Play申请的提前开放游戏预约下载功能，申请预注册的用户在游戏正式开放下载后，需要发放一份预注册奖励。
该方法仅适用于游戏在谷歌启用了预注册功能。

请注意，每个用户应该只能获得一份预注册奖励，且这里的用户指的是google用户，而非gm88用户或游戏角色，建议cp只为一个角色发放奖励，并做防止同一角色领取多份预注册奖励的判断
按照google play的政策，如果一个已经预约游戏的google用户，没有获得应得的预注册奖励，可以向google投诉，google有权利在核实时下架该游戏，请谨慎处理

#### 4.10.1 查询预注册状态

建议在游戏启动或用户创角后，调用此接口，用于查询当前google用户是否应发放预注册奖励

cp可以自行决定将奖励发放在哪个角色上，或者让用户选择

```
GMSDK.checkRegistrationType();
```

游戏首先需要调用该方法来查询手机上登录的Google Play账号的预注册状态。

GMActionCode.ACTION_REGISTERATION_CHECK_SUCCESS ：表示当前登录的Google Play账号已申请了预注册奖励，且该奖励暂未领取。

GMActionCode.ACTION_REGISTERATION_CHECK_FAILED ： 表示当前登录的Google Play账号未申请预注册奖励，或者该账号的预注册奖励已被领取。

#### 4.10.2 申请发放预注册奖励

建议在cp决定向哪个用户角色发放奖励后，再调用此接口

```
GMSDK.sendRegistrationGift(String servierId,String roleId);
```

申请发放预注册奖励只能在收到预注册查询成功后调用(即：GMActionCode.ACTION_REGISTERATION_CHECK_SUCCESS)，否则会抛出异常。调用该方法时，请携带当前登录用户所在的区服ID和角色ID。该方法仅供客户端调用来启动预注册奖励发放流程，具体的预注册奖励发放，请后续对接服务端接口。



### 4.11 播放视频

当游戏内需要播放视频，可调用此接口。视频播放支持网络播放和本地播放，支持基本常见的视频格式，此接口会优先播放本地路径下的视频，若不存在会播放网络地址视频，如不需要播放本地视频，参数传空即可。

```
GMSDK.playVideo(String videoUrl, String videoPath, int oritation);
```

| 字段     | 类型   | 说明                                                         |
| -------- | ------ | ------------------------------------------------------------ |
| videoUrl | String | 网络视频播放地址格式："http://com.test.mp4"|
| videoUrl | String | 本地视频播放地址格式：assets/video/test.mp4" (对应存储位置的路径, 现在此接口暂时只支持/data/user/0/com.xm.paoyou.zmg.test/files/games/路径下)|
| oritation|  int	| 0横屏播放,1竖屏播放|
GMActionCode.ACTION_VIDEO_PLAY_CLOSE ：表示视频播放被用户关闭。

GMActionCode.ACTION_VIDEO_PLAY_COMPLETE ：表示视频播放完成。

GMActionCode.ACTION_VIDEO_PLAY_ERROR ：表示视频播放出现异常，一般需要检查视频地址参数是否正确。





### 4.12 获取设备系统信息

当需要获取用户设备的系统信息时，可调用此接口。
```
GMSDK.getDeviceInfo();
```

返回字段示例：

{"deviceId":"9ebd931d-a62d-4d72-8ddd-8eb9a8cdbeb2", //设备gaid

"system":"7.1.2", // 系统版本

"model":"HTC 2Q4R100" //设备BRAND+MODEL
}

### 4.13 打开支付订单修复界面

在用户使用Google完成订单支付后，因网络不稳定等特殊情况，会有极低概率导致无法通知到后台造成丢单。

调用此接口，会提供所有未成功通知到后台的订单集合界面，让用户进行相应的订单修复。

2.0版本之后，页面同时集成了已完成的订单信息供查看
```
GMSDK.showOrderRepair();
```


### 4.14 打开个人中心界面

用户中心界面包含了展示用户账号绑定信息、绑定账号、修改密码、切换账号、查看最新订单、反馈的功能。
注：个人中心应该在已登录状态下调用。

调用示例
```
GMSDK.showUserCenter();
```


### 4.15 打开扫码登录

2.4版本后，集成了扫码登录接口

调用示例
```
GMSDK.scanLogin(activity);
```

### 5.集成SDK除谷歌商店外的渠道

**SDK1.4.4版本后支持上线QOO渠道，后续可能开放更多商店渠道**

此条只适用于上线QOO渠道的游戏，接入时可咨询我方运营，若游戏无上线QOO商店的打算，无需阅读以下内容

以下是谷歌商店包转QOO商店包的接入流程

1.引入QOO商店qooapp-opensdk-2.0.6.aar包
2.在GMConfig.xml下添加标签qoo confirm="1"来标识此包是QOO商店包
3.在Manifest下做出如下添加APP_ID和PUBLIC_KEY填入运营提供的相关参数

```
    <meta-data
        android:name="com.qooapp.APP_ID" android:value="{APP_ID}" />
    <meta-data
        android:name="com.qooapp.PUBLIC_KEY" android:value="{PUBLIC_KEY}" />
    <!-- Only If your  targetSdkVersion >= 30 -->
    <queries>
        <package android:name="com.qooapp.qoohelper" />
    </queries>

```
4.由于QOO商店和Google商店相对独立，主SDK有部分接口在QOO商店渠道变得不可用，CP需要在QOO渠道下禁用部分接口，并关闭游戏内相关接口入口
我们提供两种方式供CP判断：
1）CP自行对接口进行判断，当出QOO商店包时，禁用下列接口

| 接口名称     | 代码名称  | 禁用原因                                                       |
| -------- | ------ | ------------------------------------------------------------ |
| 游戏绑定账号接口 | GMSDK.showBind();| 该接口用于对于游戏账号进行绑定邮箱的操作，这种操作只对怪猫账号有效，在Qoo渠道中并没有怪猫账号的概念 |
| 查询绑定账号接口 | GMSDK.doQueryBind();| 同上                            |
| 查询当前游戏需求展示给用户查看的货币接口 | GMSDK.getPurchaseList(GlobalCallback callback);| 该接口用于在用户显示不同的货币，Qoo渠道固定显示美金  |
| 查询预注册状态，申请发放预注册奖励 | GMSDK.checkRegistrationType(); GMSDK.sendRegistrationGift(); | 这2个接口都是Google Play专用接口      |

2)通过接口的方式供CP查询，在初始化成功后，可调用查询禁用接口列表接口进行查询，此接口会返回禁用接口的列表


```
GMSDK.getDisableInterfaces();
```

同时，CP可监听GMActionCode.ACTION_CALL_DISABLED_INTERFACE回调，当禁用接口被调用时会无效并收到此回调









