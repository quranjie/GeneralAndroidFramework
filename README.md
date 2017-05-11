## 使用说明
本项目旨在制作通用的Android开发框架，使各模块（如网络、数据库、图片加载）便于替换，对特殊问题进行详细说明。

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

PercentRelativeLayout
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

PercentLinearLayout
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

PercentFrameLayout
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




















