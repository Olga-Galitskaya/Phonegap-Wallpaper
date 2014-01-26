Phonegap-Wallpaper
==================
Для внедрения плагина проект необходимо:
1) скопировать src/Wallpaper.java в папку src Вашего проекта;
2) скопировать www/plugin.js в папку assets/www/ Вашего проекта и подлючить в index.html;
3) добавить в AndroidManifest.xml следующую строчку:
    <uses-permission android:name="android.permission.SET_WALLPAPER" />
4) добавить в res/xml/plugins.xml:
    <plugin name="Wallpaper" value="com.example.android_app.Wallpaper"/> ,
   где com.example.android_app нужно заменить на название своего проекта, а так же в Wallpaper.java необходимо заменить package com.example.android_app
