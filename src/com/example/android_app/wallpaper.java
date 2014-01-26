package com.example.android_app;

import java.io.*;
import java.net.URL;


import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.apache.cordova.api.PluginResult;
import org.apache.cordova.api.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class Wallpaper extends CordovaPlugin {
    private static final String TAG = "wall";

    private static final int IO_BUFFER_SIZE = 4 * 1024;

    public final String ACTION_SET_WALLPAPER = "setWallPaper";
    /**
     * Loads a bitmap from the specified url. This can take a while, so it should not
     * be called from the UI thread.
     *
     * @param url The location of the bitmap asset
     *
     * @return The bitmap, or null if it could not be loaded
     */
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
                android.util.Log.e(TAG, "Could not close stream", e);
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
}