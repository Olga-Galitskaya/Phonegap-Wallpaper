package com.example.android_app;

import java.io.*;
import java.net.URL;


import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Wallpaper extends CordovaPlugin
{
    public static final String ACTION_SET_WALLPAPER = "setWallPaper";
    private static final int IO_BUFFER_SIZE = 4 * 1024;
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        try {
            String path = args.getString(0);

            WallpaperManager wallpaperManager = WallpaperManager.getInstance(this.cordova.getActivity().getApplicationContext());

            if(path.contains("https://") || path.contains("http://")){
                ConnectivityManager connec = (ConnectivityManager) this.cordova.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo ni = connec.getActiveNetworkInfo();
                if (ni == null) {
                    callbackContext.error("Internet not Available.");
                    return false;
                }
                else{
                    if (ACTION_SET_WALLPAPER.equals(action)) {
                        Bitmap setAsWallpaper = loadRemoteImage(path);
                        wallpaperManager.setBitmap(setAsWallpaper);
                        callbackContext.success();
                        return true;
                    }
                }
            }
            else{
                if (ACTION_SET_WALLPAPER.equals(action)) {
                    InputStream ins = this.cordova.getActivity().getApplicationContext().getAssets().open(path);
                    wallpaperManager.setStream(ins);
                    callbackContext.success();
                    return true;
                }
            }
            callbackContext.error("Invalid action");
            return false;
        } catch(Exception e) {
            System.err.println("Exception: " + e.getMessage());
            callbackContext.error(e.getMessage());
            return false;
        }
    }
    public static Bitmap loadRemoteImage(String path){
        Bitmap bitmap = null;
        InputStream in = null;
        BufferedOutputStream out = null;

        try {
            in = new BufferedInputStream(new URL(path).openStream(), IO_BUFFER_SIZE);

            final ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
            out = new BufferedOutputStream(dataStream, IO_BUFFER_SIZE);
            copy(in, out);
            out.flush();

            final byte[] data = dataStream.toByteArray();
            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        } catch (IOException e) {
            System.err.println("Exception:" + e.getMessage());
        } finally {
            closeStream(in);
            closeStream(out);
        }

        return bitmap;
    }
    /**
     * Closes the specified stream.
     *
     * @param stream The stream to close.
     */
    private static void closeStream(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
                System.err.println("Exception: " + e.getMessage());
            }
        }
    }

    /**
     * Copy the content of the input stream into the output stream, using a
     * temporary byte array buffer whose size is defined by
     * {@link #IO_BUFFER_SIZE}.
     *
     * @param in The input stream to copy from.
     * @param out The output stream to copy to.
     * @throws IOException If any error occurs during the copy.
     */
    private static void copy(InputStream in, OutputStream out) throws IOException {
        byte[] b = new byte[IO_BUFFER_SIZE];
        int read;
        while ((read = in.read(b)) != -1) {
            out.write(b, 0, read);
        }
    }
}
/*
public class Wallpaper extends CordovaPlugin {
    private static final String TAG = "wall";

    private static final int IO_BUFFER_SIZE = 4 * 1024;

    public final String ACTION_SET_WALLPAPER = "setWallPaper";
    */
/**
     * Loads a bitmap from the specified url. This can take a while, so it should not
     * be called from the UI thread.
     *
     * @param url The location of the bitmap asset
     *
     * @return The bitmap, or null if it could not be loaded
     *//*

    public static Bitmap loadBitmap(String url) {
        Bitmap bitmap = null;
        InputStream in = null;
        BufferedOutputStream out = null;

        try {
            in = new BufferedInputStream(new URL(url).openStream(), IO_BUFFER_SIZE);

            final ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
            out = new BufferedOutputStream(dataStream, IO_BUFFER_SIZE);
            copy(in, out);
            out.flush();

            final byte[] data = dataStream.toByteArray();
            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        } catch (IOException e) {
            Log.e(TAG, "Could not load Bitmap from: " + url);
        } finally {
            closeStream(in);
            closeStream(out);
        }

        return bitmap;
    }

    */
/**
     * Closes the specified stream.
     *
     * @param stream The stream to close.
     *//*

    private static void closeStream(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
                android.util.Log.e(TAG, "Could not close stream", e);
            }
        }
    }

    */
/**
     * Copy the content of the input stream into the output stream, using a
     * temporary byte array buffer whose size is defined by
     * {@link #IO_BUFFER_SIZE}.
     *
     * @param in The input stream to copy from.
     * @param out The output stream to copy to.
     * @throws IOException If any error occurs during the copy.
     *//*

    private static void copy(InputStream in, OutputStream out) throws IOException {
        byte[] b = new byte[IO_BUFFER_SIZE];
        int read;
        while ((read = in.read(b)) != -1) {
            out.write(b, 0, read);
        }
    }
    @Override
    public boolean execute(String action, JSONArray arg1, CallbackContext callbackContext) {
        PluginResult result = new PluginResult(Status.INVALID_ACTION);
        if (action.equals(ACTION_SET_WALLPAPER)) {
            Context ctx = this.cordova.getActivity().getApplicationContext();
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(ctx);
            try {
                String imageUrl = "";
                try{
                    imageUrl = arg1.getString(0);
                }catch (JSONException e){
                    e.printStackTrace();
                }
                Bitmap bit = loadBitmap(imageUrl);
                wallpaperManager.setBitmap(bit);
                result = new PluginResult(Status.OK);
            } catch (IOException e) {
                e.printStackTrace();
                result = new PluginResult(Status.ERROR, e.getMessage());
            }
        }
        callbackContext.sendPluginResult(result);
        return true;
    }
}*/
