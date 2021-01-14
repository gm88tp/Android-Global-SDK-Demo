# GM88 Android海外游戏1.3.5版本SDK 对接文档

***请注意：demo内的所有参数均是为了方便展示，接入时请使用运营提供的参数进行接入***

## 1.相关依赖引入

在工程级别的build.gradle 的android 内加入以下代码

```
   defaultConfig{
        minSdkVersion 17
        targetSdkVersion 29
        multiDexEnabled true
    }
    sourceSets {
        main {
            assets.srcDirs = ['../assets', 'src/main/assets', 'src/main/assets/']
            jniLibs.srcDirs = ['libs']
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
```

引入以下依赖：

```
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation(name: 'Globalsdk_1.3.5', ext: 'aar')
    implementation(name: 'cafeSdk-4.4.1', ext: 'aar')
    implementation(name: 'sos_library-1.1.3.4', ext: 'aar')
    implementation 'androidx.appcompat:appcompat:1.0.0'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    // Required -- JUnit 4 framework
    testImplementation 'junit:junit:4.12'
    // Optional -- Mockito framework（可选，用于模拟一些依赖对象，以达到隔离依赖的效果）
    testImplementation 'org.mockito:mockito-core:2.19.0'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'
    androidTestImplementation 'androidx.test:runner:1.2.0'
    androidTestImplementation 'androidx.test:rules:1.2.0'
    api 'com.google.gms:google-services:4.3.3'
    api 'com.google.android.gms:play-services-analytics:17.0.0'
    api 'com.google.android.gms:play-services-auth:17.0.0'
    api 'com.facebook.android:facebook-android-sdk:5.0.0'
    api 'org.apache.httpcomponents:httpcore:4.4.10'
    api 'com.google.android.gms:play-services-ads:19.0.0'
    implementation 'com.google.ads.mediation:applovin:9.11.2.0'
    implementation 'com.google.ads.mediation:facebook:5.8.0.0'
    implementation 'com.google.ads.mediation:unity:3.4.2.0'
    implementation 'com.google.ads.mediation:ironsource:6.14.0.0'
    implementation 'com.google.ads.mediation:vungle:6.7.0.0'
    implementation 'com.google.android.play:core:1.8.0'
    api 'androidx.multidex:multidex:2.0.1'
    //noinspection GradleCompatible
    api 'androidx.recyclerview:recyclerview:1.1.0'
    // Required Dependency by Audience Network SDK
    api 'com.facebook.android:audience-network-sdk:5.6.0'
    api 'com.alibaba:fastjson:1.1.70.android'
    //firebase
    // Add dependency crashlytics
    implementation 'com.google.firebase:firebase-crashlytics:17.2.2'
    implementation 'com.google.firebase:firebase-analytics:17.6.0'
    // Check for v11.4.2 or higher
    implementation 'com.google.firebase:firebase-core:17.2.0'
    // (Recommended) Add Analytics
    implementation platform('com.google.firebase:firebase-bom:25.12.0')
    api 'androidx.appcompat:appcompat:1.1.0'
    api 'androidx.annotation:annotation:1.1.0'
    api 'androidx.vectordrawable:vectordrawable-animated:1.1.0'
    api 'androidx.legacy:legacy-support-v4:1.0.0'
    api 'androidx.core:core:1.2.0'
    api 'com.google.firebase:firebase-core:17.2.3'
    api 'com.google.firebase:firebase-iid:20.1.0'
    api 'android.arch.work:work-runtime:1.0.1'
    api 'com.google.firebase:firebase-messaging:20.1.2'
    api 'com.google.guava:guava:28.0-jre'
    api 'androidx.constraintlayout:constraintlayout:1.1.3'
    // Add the dependency for the Performance Monitoring library
    api 'com.google.firebase:firebase-perf:19.0.5'
    //Dynamic-links
    api 'com.google.firebase:firebase-dynamic-links:19.1.0'
    api 'com.google.firebase:firebase-analytics:17.2.3'
    implementation 'com.braintreepayments.api:braintree:2.+'
    //谷歌支付
    implementation 'com.android.billingclient:billing:3.0.2'

    //推特登陆和分享相关
    implementation 'com.twitter.sdk.android:twitter-core:3.1.1'
    implementation 'com.twitter.sdk.android:tweet-ui:3.1.1'
    implementation 'com.twitter.sdk.android:tweet-composer:3.1.1'
    //line
    api 'com.linecorp:linesdk:5.0.1'
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
        jcenter()

        mavenCentral()
        maven {
            url 'https://maven.google.com/'
            name 'Google'
        }
        maven { url 'https://maven.google.com' }
        maven {
            url "https://dl.bintray.com/ironsource-mobile/android-sdk"
        }

    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.4.2'

        classpath 'com.google.gms:google-services:4.3.3'

        classpath 'com.google.firebase:firebase-crashlytics-gradle:2.3.0'

        // Add the dependency for the Performance Monitoring plugin
        classpath 'com.google.firebase:perf-plugin:1.3.1'  // Performance Monitoring plugin

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}
allprojects {
    repositories {
        jcenter()
        google()
        flatDir {
            dirs 'libs'
        }
        maven {
            url 'https://maven.google.com/'
            name 'Google'
        }
        maven {
            url 'https://dl.bintray.com/ironsource-mobile/android-sdk'
        }
    }
}
```

## 2.相关资源引入

### 创建assets文件夹。拷贝资源内的GMConfig.xml

1）请修改gmsdk标签内的appId参数为运营提供的游戏id；appReleaseId为提供的发布记录id。
2）Google标签内的clientId，为运营提供的谷歌ClientID；billing为Google支付秘钥。
3）line标签内的channel，为运营提供的Line登录LineChannelID。
4）googlead和fbad标签内的内容，修改为运营提供的相应的广告变现参数。
5）如果游戏是韩国版本，需要修改café标签内的相关参数为运营提供的参数。
6）出正式包时请修改host标签内的url链接为正式服链接，地址为https://m-xmjen.hkpctimes.com。
7）如果游戏需要启用预注册奖励功能，请修改register标签下的item_id和item_price值，相关参数由运营提供

### 拷贝运营提供的google-services.json文件

拷贝运营提供的google-services.json文件到工程级别的主目录。如果是android studio开发，拷贝到app目录下即可
**请注意，demo中没有添加google-services.json，漏加会影响运行、登录**

### 添加libs下相关aar依赖

请添加并引入libs内的全部aar依赖

### 清单文件内容添加

添加Demo内的清单文件内容到游戏Manifest内，并修改To-do标签内的相关value，具体value值运营会提供

```
    <meta-data
        android:name="com.facebook.sdk.ApplicationId"
        android:value="@string/facebook_app_id" />
    <!--todo  facebook广告参数-->
    <provider
        android:name="com.facebook.FacebookContentProvider"
        android:authorities="com.facebook.app.FacebookContentProvider436121163675645"
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
        android:value="ca-app-pub-7496069579613989~1013034939" />
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
```

**请注意，请将facebook_app_id，替换为运营提供的id，fb_login_protocol_scheme中须保留fb开头**

## 3.SDK方法文档

### 3.1Application内的初始化

首先使项目中的Application继承PSDKApplication，若项目无Application，请自定义创建，并在清单文件中注册。(参考demo)
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

### 3.2MainActivity内的初始化

SDK使用统一的Callback，在MainActivity(游戏主Activity)的onCreate方法内实现以下(监听回调根据所需添加)：

```
MSDK.setCallBack(new GMCallback() {
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
                    case GMActionCode.ACTION_SHARE_SUCCESS://分享成功

                        break;
                    case GMActionCode.ACTION_SHARE_FAILED://分享失败

                        break;
                    case GMActionCode.ACTION_SHARE_CANCEL://分享取消

                        break;
                    case GMActionCode.ACTION_QUERY_NOTBIND://未绑定

                        break;
                    case GMActionCode.ACTION_QUERY_BIND_FAILED://绑定失败

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

### 3.3发起登录

当游戏收到初始化成功后，可发起登录
接口定义：

```
GMSDK.doLogin();
```

登录成功或者失败，都可以在回调中得到结果。返回token，示例:

```
JSONObject loginResult = new JSONObject(String.valueOf(msg.obj));
String token = loginResult.getString("token");
```

请使用token调用后端接口获得用户标识，收到回调的token，建议通过服务端验证token，获取用户id，详情查看服务端文档

### 3.4发起支付

当游戏内需发起支付时，应调用此接口
接口定义：

```
GMSDK.doPay(Map<String, String> payJson)
```

**payJson参数**

| 字段           | 类型     | 说明                                 |
| ------------ | ------ | ---------------------------------- |
| productId    | string | 商品ID                               |
| productName  | string | 商品名称，会显示在相应支付界面上                   |
| productPrice | float  | 商品价格                               |
| extra        | string | 订单透传参数，这些参数会在支付回调时一并回传给CP，请CP自行解析  |
| roleId       | string | 待支付角色ID                            |
| roleName     | string | 待支付角色名                             |
| serverId     | string | 待支付角色区服ID                          |
| serverName   | string | 待支付角色区服名称                          |
| notifyUrl    | string | 支付通知地址，没有的话请不要传递该参数(请求信息内不需要该参数为空) |

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

### 3.5角色变更接口

当游戏内角色状态变化时，应调用此接口
接口定义：

```
GMSDK.doSpot(String spotJson)
```

| 字段       | 类型     | 说明                                                                                                                      |
| -------- | ------ | ----------------------------------------------------------------------------------------------------------------------- |
| spotType | string | 事件类型，取值为：1：创建角色   2：完成新手引导 3：玩家等级变化后上传 4:玩家选择完区服                                                                        |
| extra    | json   | 这是角色具体信息，格式为Json，包括6种信息：roleId: 角色ID, roleName： 角色名，roleServer： 区服ID， serverName ：区服名字，roleLevel： 角色等级，vipLevel：角色Vip等级 |

**请注意，玩家选择完区服上报（spotType为4）必须接入，否则会影响SDK功能，其余上报不接入会影响打点数据准确性**
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
    spotJson.put("extra",extra.toString());
} catch (JSONException e) {
    e.printStackTrace();
}
GMSDK.doSpot(spotJson.toString())
```

### 3.6发起分享接口

当游戏需要拉起分享的时候，应调用此接口
接口定义：

```
GMSDK.share(String shareInfo)
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

### 3.7调起广告接口

当游戏需要拉起广告的时候，应调用此接口
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

### 3.8游戏绑定账号接口

游戏内需提供显示绑定账号页面的入口，点击入口时调用此接口
接口定义：

```
GMSDK.showBind();
```

调用示例：

```
GMSDK.showBind();
```

调用后会给游戏对应的回调

### 3.9查询绑定账号接口

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

### 3.10查询当前游戏需要展示给用户查看的货币

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
            }
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

### 3.11生命周期接入

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

### 4.3获取当前手机系统语言和地区

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

```java
GMSDK.doOpenURLbyWebView(String url);
```

| 字段  | 类型     | 说明               |
| --- | ------ | ---------------- |
| url | string | 需要打开的webview网页地址 |

### 4.7打开SDK客服中心界面

当游戏内需要显示打开客服中心页面的入口，点击入口时调用此接口
调用示例：

```java
GMSDK.showServiceCenter();
```

### 4.8打开SDK常见问题界面

当游戏内需要显示打开常见问题界面的入口，点击入口时调用此接口
调用示例：

```java
GMSDK.showQuestions();
```

### 4.9查询预注册状态，申请发放预注册奖励

预注册奖励为游戏在Google Play申请的提前开放游戏预约下载功能，申请预注册的用户在游戏正式开放下载后，需要发放一份预注册奖励。
该方法仅适用于游戏在谷歌启用了预注册功能。

请注意，每个用户应该只能获得一份预注册奖励，且这里的用户指的是google用户，而非gm88用户或游戏角色，建议cp只为一个角色发放奖励，并做防止同一角色领取多份预注册奖励的判断
按照google play的政策，如果一个已经预约游戏的google用户，没有获得应得的预注册奖励，可以向google投诉，google有权利在核实时下架该游戏，请谨慎处理

#### 4.9.1 查询预注册状态

建议在游戏启动或用户创角后，调用此接口，用于查询当前google用户是否应发放预注册奖励

cp可以自行决定将奖励发放在哪个角色上，或者让用户选择

```java
GMSDK.checkRegistrationType();
```

游戏首先需要调用该方法来查询手机上登录的Google Play账号的预注册状态。

GMActionCode.ACTION_REGISTERATION_CHECK_SUCCESS ：表示当前登录的Google Play账号已申请了预注册奖励，且该奖励暂未领取。

GMActionCode.ACTION_REGISTERATION_CHECK_FAILED ： 表示当前登录的Google Play账号未申请预注册奖励，或者该账号的预注册奖励已被领取。

#### 4.9.2 申请发放预注册奖励

建议在cp决定向哪个用户角色发放奖励后，再调用此接口

```java
GMSDK.sendRegistrationGift(String servierId,String roleId)
```

申请发放预注册奖励只能在收到预注册查询成功后调用(即：GMActionCode.ACTION_REGISTERATION_CHECK_SUCCESS)，否则会抛出异常。调用该方法时，请携带当前登录用户所在的区服ID和角色ID。该方法仅供客户端调用来启动预注册奖励发放流程，具体的预注册奖励发放，请后续对接服务端接口。
