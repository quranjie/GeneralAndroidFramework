## 使用说明
本项目旨在制作通用的Android开发框架，作为开发项目前的参考工具。

我的格言：**对于新技术，永远不应该心生畏惧**。

我一直试图在架构层面对项目做技术分析，不会事无巨细的介绍每一个技术点。

目录
---
   * [布局适配篇](#布局适配篇)
   	    * [PercentRelativeLayout](#PercentRelativeLayout)
   	    * [PercentLinearLayout](#PercentLinearLayout)
   	    * [PercentFrameLayout](#PercentFrameLayout)
   * [字体型图标篇](#字体型图标篇)
   * [数据库篇](#数据库篇)
   * [图片加载篇](#图片加载篇)
   * [网络请求篇](#网络请求篇)
   * [View注入框架](#view注入框架)
   * [公用工具包](#公用工具包)
   * [Android 编程规范](#android-编程规范)
   * [自定义View整理](#自定义view整理)
   * [Tinker](#tinker)
   * [Android动画](#android动画)

### 布局适配篇
Google目前已经支持百分比布局了，下面详细介绍一下这个库（android-percent-support-lib-sample）：
这个库提供两种布局PercentRelativeLayout、PercentFrameLayout，分别继承自RelativeLayout和FrameLayout。
支持的属性有：
* layout_widthPercent
* layout_heightPercent
* layout_marginPercent
* layout_marginLeftPercent
* layout_marginTopPercent
* layout_marginRightPercent
* layout_marginBottomPercent
* layout_marginStartPercent
* layout_marginEndPercent

在开发过程中只要替换相应的Layout即可，但官方没有LinearLayout对应的PercentLinearLayout，见widget->PercentLinearLayout。
使用方法如下：

#### PercentRelativeLayout
```
<android.support.percent.PercentRelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <View
        android:id="@+id/top_left"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:layout_alignParentTop="true"
        android:background="#ff44aacc"
        app:layout_heightPercent="20%"
        app:layout_widthPercent="70%" />

    <View
        android:id="@+id/top_right"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:layout_alignParentTop="true"
        android:layout_toRightOf="@+id/top_left"
        android:background="#ffe40000"
        app:layout_heightPercent="20%"
        app:layout_widthPercent="30%" />


    <View
        android:id="@+id/bottom"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_below="@+id/top_left"
        android:background="#ff00ff22"
        app:layout_heightPercent="80%" />
</android.support.percent.PercentRelativeLayout>
```

#### PercentLinearLayout
```
<com.jingdong.sdk.generalandroidframe.widget.PercentLinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">
     <View
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:background="#ff44aacc"
        app:layout_heightPercent="10%"
        app:layout_widthPercent="60%"/>

    <View
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:background="#ff4400cc"
        app:layout_heightPercent="10%"
        app:layout_widthPercent="70%"/>
</com.juliengenoud.percentsamples.PercentLinearLayout>
```

#### PercentFrameLayout
```
<android.support.percent.PercentFrameLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
        <!-- ... XML CODE -->
</android.support.percent.PercentFrameLayout>
```

参考[Github](https://github.com/JulienGenoud/android-percent-support-lib-sample)

### 字体型图标篇
设计对于开发者来说通常都是头疼的事，如何让产品在实现功能的基础上更加漂亮，减少开发者在界面开发上的时间。使用字体型图标的好处还有不用考虑适配的问题。
目前网上已经有了一些字体型图标（icon font）的开源库，如：
* [Android-Bootstrap](https://github.com/Bearded-Hen/Android-Bootstrap)
如果你熟悉Bootstrap并且你的产品设计就采用Bootstrap风格，可以使用它
* [Iconify](https://github.com/JoanZapata/android-iconify)
Iconify提供了大量的矢量图标供你选择，包括fontawesome、material、ionicons等等，找不到想要的图标也可以自定义扩展，扩展方法见github上的说明。
* [Iconic](https://github.com/mikepenz/Android-Iconics)
我用的是这个，它同样集成了大量图标，同时自定义扩展也非常方便，但需要UI设计师提供SVG格式的文件。虽然字体型图标很方便，但是它不支持xml中selector的方式改变按下等效果。
如果非要实现按下等效果，估计只能实现它的onTouch事件了。在我看来，它适用于没有专业设计的情况下，能够保证页面展现的美感。在此不过多介绍。

参考[useiconic](https://useiconic.com/)

### 数据库篇
数据库的第三方框架有很多，如：
* OrmLite JDBC和Android的轻量级ORM java包
* Sugar   用超级简单的方法处理Android数据库
* GreenDAO    一种轻快地将对象映射到SQLite数据库的ORM解决方案
* ActiveAndroid   以活动记录方式为Android SQLite提供持久化
* SQLBrite    SQLiteOpenHelper 和ContentResolver的轻量级包装
* Realm   移动数据库：一个SQLite和ORM的替换品

经过对比及我的项目经验，选择GreenDAO最好，不仅存取速度快，支持数据库加密，轻量级，支持缓存，等等。

GreenDAO目前已经更新到3.2.0，相比于2.0版本有了很多的改进，不用新建Module等一系列操作，直接在build.gradle里配置并新建实体用添加注解的方式生成。

具体步骤如下：

1、在项目的build.gradle中添加`classpath 'org.greenrobot:greendao-gradle-plugin:3.2.2'`到dependencies

2、在app的build.gradle中添加
```
apply plugin: 'org.greenrobot.greendao'

greendao {
    schemaVersion 1//数据库版本号
    daoPackage 'com.jingdong.sdk.generalandroidframework.greendao'//设置DaoMaster、DaoSession、Dao包名
    targetGenDir 'src/main/java'//设置DaoMaster、DaoSession、Dao目录
}

compile 'org.greenrobot:greendao-generator:3.2.2'
compile 'org.greenrobot:greendao:3.2.2'
```

3、最后就是用注解写实体类，然后Make Project。在此不多叙述。具体怎么用请看GreenDaoActivity或参考官方文档。

如果有将来可能替换DB层的需求，可以进一步封装

参考[GreenDAO](https://github.com/greenrobot/greenDAO)

参考[GreenDaoUpgradeHelper](https://github.com/yuweiguocn/GreenDaoUpgradeHelper)

### 图片加载篇
图片加载的第三方框架可以说很成熟也很多，有：

* UniversalImageLoader
ImageLoader图片加载器采用单例模式，用于图片的加载和显示。MemoryCache默认使用LRU算法，当接近内存缓存的阈值时，put一个bitmap对象时，将近期最少使用的bitmap对象移除。
UIL可以算是老牌最火的图片加载库，再也不用担心出现OOM和ListView图片错乱了，可惜的是该作者明确说明已经停止对该项目的维护，因此不推荐使用了。
* Picasso
Picasso是Square公司开源的，简单易用，一句话搞定图片加载。如：
`Picasso.with(this).load("url").placeholder(R.mipmap.ic_default).into(imageView);`
同样是单例模式，DownLoader是下载用的工具类，可以配合OKHttp使用。使用ExecutorService线程池。
* Glide
Glide是Google一位员工的大作，完全基于Picasso，但做了大量优化与改进。值得注意的是Glide支持加载Gif动态图，Picasso不支持。Glide是Google官方推荐的图片加载库。
* Fresco
Fresco是Facebook出品的新一代图片加载库，可说是功能最强大的。为避免OOM，Facebook另辟蹊径，将图片放到一个特别的内存区域Ashmem区，属于Native堆，图片不再占用App的内存。
但Fresco有个很大的缺点就是包特别大，使用它APK包会增加2-3M。如果不在乎这个缺点，使用它是最合适的。

当然除了这些还有其他的第三方库（如Volley），都是不错的选择。我的倾向是使用Glide。

参考[UniversalImageLoader](https://github.com/nostra13/Android-Universal-Image-Loader)

参考[Picasso](https://github.com/square/picasso)

参考[Glide](https://github.com/bumptech/glide)

参考[Fresco](https://github.com/facebook/fresco)

### 网络请求篇
目前流行的网络请求框架，如下：
* Android Async HTTP
Android异步HTTP库，清晰的网络请求回调，使用ThreadPool限制并发资源使用情况。GET/POST基于参数构建（RequestParams），这一点和xutils很像。
支持Multipart文件上传，大数据上传下载，内置响应解析成JSON（这部分后续会详细讲解）。持久化cookie存储，支持二进制文件的下载。
但是它封装的是HttpClient，Android平台已经不推荐使用HttpClient了，所以已经不适用了。
* OkHttp
OkHttp非常高效，基于HttpURLConnection，HttpClient（Android6.0移除了HttpClient）。支持SPDY、连接池、GZIP和HTTP缓存。
OkHttp会自动处理常见的网络问题，像二次连接、SSL的握手问题。目前OkHttp已经更细到3.0了，与之前有很大区别。
* Retrofit
和Volley框架的请求方式很相似，底层网络请求采用OkHttp（效率高，android4.4底层采用OkHttp），采用注解方式来指定请求方式和url地址，减少了代码量。
如果接口标准是RESTful API，并且是GSON格式的数据，那么Retrofit的契合度非常高。
* Volley
Volley是一个异步HTTP库，不支持同步。早期使用HttpClient，后来使用HttpURLConnection，是Google推出的网络请求框架，非常适合去进行数据量不大，但通信频繁的网络操作，
而对于大数据量的网络操作，比如说下载文件等，Volley的表现就会**非常**糟糕。
* xutils
缓存已经网络请求数据，集成了网络、数据库、ViewInject等，但不维护了。

可以说每个框架都有优缺点，用哪个都凭个人意愿。

我推荐的框架是Retrofit + RxJava，它们的组合是近一两年来最流行的。

参考[Retrofit的详细用法](http://blog.csdn.net/duanyy1990/article/details/52139294)

参考[Retrofit](https://github.com/square/retrofit)

参考[Rxjava](https://github.com/ReactiveX/RxJava)

参考[简书](http://gank.io/post/560e15be2dca930e00da1083)

### View注入框架
[ButterKnife](https://github.com/JakeWharton/butterknife)是一个专注于Android系统的View注入框架，
可以减少大量的findViewById以及setOnClickListener代码。

### 公用工具包
整理了一些Android开发的工具包，详情请查看util包。

参考[android-utils](https://github.com/jingle1267/android-utils)

### Android 编程规范

自己整理了一些Android编程规范，前往<a href="./Specification.md">Specification.md</a>

### 自定义View整理

自定义View是开发人员必须会的，自己整理了一些自定义View，前往<a href="./CustomView.md">CustomView.md</a>

### Tinker

[Tinker](https://github.com/Tencent/tinker)是微信官方的Android热补丁解决方案，
它支持动态下发代码、.so库以及资源库，让应用能够在不需要安装的情况下实现更新。

>Tinker的已知不足：
1. Tinker不支持修改AndroidManifest.xml，Tinker不支持新增四大组件。
2. 由于Google Pay的开发者条款限制，不建议在GP渠道动态更新代码。
3. 在Android N上，补丁对应用启动时有轻微的影响。
4. 不支持部分三星android-21机型，加载补丁时会主动抛出"TinkerRuntimeException:checkDexInstall failed"异常。
5. 由于各个厂商加固实现并不一致，在1.7.6以后的版本，Tinker不在支持加固的动态更新。
6. 对于资源替换，不支持修改remoteView，例如transition动画，notification icon及桌面图标。

上述的缺点主要体现在不支持部分机型，其他的缺点影响不太大或者可以绕过去。

当下热更新、热修复是个很时髦的词，在手机端Tinker是目前最合适的解决方案。

---------------------
参考[Tinker实现原理](https://zhuanlan.zhihu.com/p/23270052)

参考[wiki](https://github.com/Tencent/tinker/wiki)

参考[简书](http://www.jianshu.com/p/d50817b6d622)

### Android动画

参考[AndroidCustomView](https://github.com/lygttpod/AndroidCustomView)

参考[nineoldandroids](http://nineoldandroids.com/)

属性动画参考
[公共技术点之 Android 动画基础](http://a.codekk.com/detail/Android/lightSky/公共技术点之%20Android%20动画基础)

数据库加密用[SQLCypher](https://github.com/sqlcipher/sqlcipher-android-tests)就可以了

换皮肤/夜间模式用[MultipleTheme](https://github.com/dersoncheng/MultipleTheme)

丰富一点的换肤功能可以参考这个
[Android-Skin-Loader](https://github.com/fengjundev/Android-Skin-Loader)









