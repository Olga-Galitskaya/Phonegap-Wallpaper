Phonegap-Wallpaper
==================
Для внедрения плагина проект необходимо:
- скопировать *src/Wallpaper.java* в папку *src* проекта;
- скопировать *www/plugin.js в папку* *assets/www/* проекта и подлючить в index.html;
- добавить в *AndroidManifest.xml* следующую строчку:
```
    <uses-permission android:name="android.permission.SET_WALLPAPER" />
```
- добавить в *res/xml/plugins.xml*:<br/>
```
    <plugin name="Wallpaper" value="com.example.android_app.Wallpaper"/>
```
   ,<br/>где ```com.example.android_app``` нужно заменить на *package name* своего проекта,<br/>
а так же в *Wallpaper.java* необходимо заменить ```package com.example.android_app```
