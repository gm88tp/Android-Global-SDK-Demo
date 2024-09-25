# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\developSoftware\Android\SDK/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Proguard Cocos2d-x-lite for release
-keep public class com.cocos.** { *; }
-dontwarn com.cocos.**

# Proguard Apache HTTP for release
-keep class org.apache.http.** { *; }
-dontwarn org.apache.http.**

# Proguard okhttp for release
-keep class okhttp3.** { *; }
-dontwarn okhttp3.**

-keep class okio.** { *; }
-dontwarn okio.**

# Proguard Android Webivew for release. you can comment if you are not using a webview
-keep public class android.net.http.SslError
-keep public class android.webkit.WebViewClient

-keep public class com.google.** { *; }

-dontwarn android.webkit.WebView
-dontwarn android.net.http.SslError
-dontwarn android.webkit.WebViewClient

## 自定义
-keep class com.maoyoufang.** { *; }
-dontwarn com.maoyoufang.**
-keep public class com.cocos.game.CocosCreatorProxyApi {*;}

# 基类
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.preference.Preference
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.support.v4.**
-keep public class * extends android.support.annotation.**
-keep public class * extends android.support.v7.**
-keepclassmembers public class * extends android.app.Activity{ *;}

# 不混淆泛型
-keepattributes Signature

# 不混淆注解类
-keepattributes *Annotation*

# 不混淆本地方法
-keepclasseswithmembernames class * {
    native <methods>;
}

# 不混淆枚举类
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# 不混淆 R 文件中的字段
-keepclassmembers class **.R$* {
    public static <fields>;
}

# 不混淆 Activity 在 XML 布局所设置的 onClick 属性值
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

# 不混淆 WebView 设置的 JS 接口的方法名
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

# Google Play Services
-keep public class com.google.android.gms.** { public protected *; }
-keep class com.google.android.gms.** { *; }
-dontwarn com.google.android.gms.**

# Google Maps Android API v2
-keep class com.google.android.gms.maps.** { *; }
-dontwarn com.google.android.gms.maps.**

# Google Places API for Android
-keep class com.google.android.gms.location.places.** { *; }
-keep class com.google.android.gms.maps.model.** { *; }
-dontwarn com.google.android.gms.location.places.**
-dontwarn com.google.android.gms.maps.model.**

# Google Drive API
-keep class com.google.android.gms.drive.** { *; }
-dontwarn com.google.android.gms.drive.**

# Google App Indexing API
-keep class com.google.android.gms.appindexing.** { *; }
-dontwarn com.google.android.gms.appindexing.**

# Google Analytics
-keep class com.google.android.gms.analytics.** { *; }
-dontwarn com.google.android.gms.analytics.**

# Google Cast SDK
-keep class com.google.android.gms.cast.** { *; }
-dontwarn com.google.android.gms.cast.**

# Google Fit SDK
-keep class com.google.android.gms.fitness.** { *; }
-dontwarn com.google.android.gms.fitness.**

# Google Games Services SDK
-keep class com.google.android.gms.games.** { *; }
-keep class com.google.android.gms.plus.** { *; }
-dontwarn com.google.android.gms.games.**
-dontwarn com.google.android.gms.plus.**

# Google Wallet SDK
-keep class com.google.android.gms.wallet.** { *; }
-dontwarn com.google.android.gms.wallet.**

# Facebook SDK
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes Exceptions

-keep class com.facebook.** {
    *;
}

-dontwarn com.facebook.**

# Bolts library
-keepattributes *Annotation*
-keepattributes Signature
-keep class bolts.** {
    *;
}

-dontwarn bolts.**

-keep class com.appsflyer.** { *; }
-keep class kotlin.jvm.internal.** { *; }

-keep public class com.gm.** { *; }
-dontwarn com.gm.**

-keep public class com.global.sdk.** { *; }
-dontwarn com.global.sdk.**

-keep class androidx.core.app.CoreComponentFactory { *; }