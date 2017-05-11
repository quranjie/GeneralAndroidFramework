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

参考[GreenDAO](https://github.com/greenrobot/greenDAO)

参考[GreenDaoUpgradeHelper](https://github.com/yuweiguocn/GreenDaoUpgradeHelper)

### 图片加载篇























