package com.doudou.privacy.internal.hook;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Message;
import android.os.Process;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebSettings;
import android.webkit.WebView;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileReader;
import java.io.InputStream;
import java.lang.reflect.Modifier;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import javax.microedition.khronos.opengles.GL10;
import org.json.JSONObject;

/**
 * ClassName: {@link SystemClassMapping}
 * Description:
 * <p>
 * Create by admin.
 */
public class SystemClassMapping {

    private static Map<String, Class<?>> MAPPING = new HashMap() {
        {
            put("Context", Context.class);
            put("Context", Context.class);
            put("String", String.class);
            put("Map", Map.class);
            put("I", Integer.TYPE);
            put("Z", Boolean.TYPE);
            put("Throwable", Throwable.class);
            put("J", Long.TYPE);
            put("D", Double.TYPE);
            put("GL10", GL10.class);
            put("BluetoothAdapter", BluetoothAdapter.class);
            put("Resources", Resources.class);
            put("DisplayMetrics", DisplayMetrics.class);
            put("Configuration", Configuration.class);
            put("LocationManager", LocationManager.class);
            put("Location", Location.class);
            put("WifiInfo", WifiInfo.class);
            put("Build", Build.class);
            put("Build$VERSION", VERSION.class);
            put("ContentResolver", ContentResolver.class);
            put("Settings$Secure", Secure.class);
            put("CdmaCellLocation", CdmaCellLocation.class);
            put("GsmCellLocation", GsmCellLocation.class);
            put("TelephonyManager", TelephonyManager.class);
            put("Display", Display.class);
            put("WebSettings", WebSettings.class);
            put("[String", String[].class);
            put("ProcessBuilder", ProcessBuilder.class);
            put("File", File.class);
            put("Runtime", Runtime.class);
            put("NetworkInterface", NetworkInterface.class);
            put("B", Byte.TYPE);
            put("URL", URL.class);
            put("Properties", Properties.class);
            put("List", List.class);
            put("FileReader", FileReader.class);
            put("InetAddress", InetAddress.class);
            put("ClassLoader", ClassLoader.class);
            put("Modifier", Modifier.class);
            put("Thread", Thread.class);
            put("Throwable", Throwable.class);
            put("CharSequence", CharSequence.class);
            put("PackageManager", PackageManager.class);
            put("System", System.class);
            put("JSONObject", JSONObject.class);
            put("WebView", WebView.class);
            put("WebSettings", WebSettings.class);
            put("DexClassLoader", DexClassLoader.class);
            put("[B", byte[].class);
            put("ActivityManager", ActivityManager.class);
            put("CountDownLatch", CountDownLatch.class);
            put("[Class", Class[].class);
            put("WifiManager", WifiManager.class);
            put("Process", Process.class);
            put("Integer", Integer.class);
            put("Bundle", Bundle.class);
            put("Object", Object.class);
            put("F", Float.TYPE);
            put("PackageInfo", PackageInfo.class);
            put("View", View.class);
            put("ViewGroup$LayoutParams", LayoutParams.class);
            put("[Object", Object[].class);
            put("FileDescriptor", FileDescriptor.class);
            put("Activity", Activity.class);
            put("Class", Class.class);
            put("Message", Message.class);
            put("Uri", Uri.class);
            put("Intent", Intent.class);
            put("ContextWrapper", ContextWrapper.class);
            put("Runnable", Runnable.class);
            put("EditorInfo", EditorInfo.class);
            put("ArrayList", ArrayList.class);
            put("Application", Application.class);
            put("MotionEvent", MotionEvent.class);
            put("InputStream", InputStream.class);
        }
    };

    public static void add(String className, Class<?> clazz) {
        MAPPING.put(className, clazz);
    }

    public static Class<?> get(String className) {
        return (Class) MAPPING.get(className);
    }

}
