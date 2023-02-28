package com.doudou.privacy.internal.helper;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.doudou.log.Logger;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

/**
 * ClassName: {@link GlobalActivityLifecycleMonitor}
 * Description:
 * <p>
 */
public class GlobalActivityLifecycleMonitor  implements Application.ActivityLifecycleCallbacks {
    private static final List<SimpleRef> appLifeCallbackList = new LinkedList();
    private static boolean lastIsAppBackground;
    private static boolean lastIsAppForeground;
    private static int mForegroundActivityCount;
    private static GlobalActivityLifecycleMonitor sInstance;

    public interface AppLifeCallback {
        void onAppBackground(Activity activity);

        void onAppForeground(Activity activity);
    }

    static class SimpleRef {
        WeakReference<AppLifeCallback> ref;

        SimpleRef(AppLifeCallback callback) {
            this.ref = new WeakReference(callback);
        }

        boolean isOccupied() {
            return (this.ref == null || this.ref.get() == null) ? false : true;
        }

        void update(AppLifeCallback callback) {
            this.ref = new WeakReference(callback);
        }

        void clear() {
            this.ref = null;
        }

        AppLifeCallback get() {
            return this.ref == null ? null : (AppLifeCallback) this.ref.get();
        }
    }

    public static void register(Application app) {
        if (sInstance == null) {
            synchronized (GlobalActivityLifecycleMonitor.class) {
                if (sInstance == null) {
                    sInstance = new GlobalActivityLifecycleMonitor();
                    app.registerActivityLifecycleCallbacks(sInstance);
                }
            }
        }
    }

    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    public void onActivityStarted(Activity activity) {
        mForegroundActivityCount++;
        Logger.e("onActivityStarted() activity=" + activity + "  mForegroundActivityCount=" + mForegroundActivityCount + "  lastForeground=" + lastIsAppForeground + "  lastBackground=" + lastIsAppBackground);
        tryNotifyAppStateChange(activity);
    }

    public void onActivityResumed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
        mForegroundActivityCount--;
        Logger.e("onActivityStopped() activity=" + activity + "  mForegroundActivityCount=" + mForegroundActivityCount + "  lastForeground=" + lastIsAppForeground + "  lastBackground=" + lastIsAppBackground);
        tryNotifyAppStateChange(activity);
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public static boolean isAppOnForeground() {
        return mForegroundActivityCount > 0;
    }

    private static void tryNotifyAppStateChange(Activity activity) {
        boolean currentIsAppForeground = isAppOnForeground();
        boolean currentIsAppBackground = !currentIsAppForeground;
        Logger.d("tryNotifyAppStateChange  lastIsAppForeground=" + lastIsAppForeground + "   currentIsAppForeground=" + currentIsAppForeground);
        if (!lastIsAppForeground && currentIsAppForeground) {
            onAppForeground(activity);
        }
        Logger.d("tryNotifyAppStateChange  lastIsAppBackground=" + lastIsAppBackground + "   currentIsAppBackground=" + currentIsAppBackground);
        if (!lastIsAppBackground && currentIsAppBackground) {
            onAppBackground(activity);
        }
        lastIsAppForeground = currentIsAppForeground;
        lastIsAppBackground = currentIsAppBackground;
    }

    private static synchronized void onAppForeground(Activity activity) {
        synchronized (GlobalActivityLifecycleMonitor.class) {
            for (SimpleRef ref : appLifeCallbackList) {
                AppLifeCallback callback = ref.get();
                if (callback != null) {
                    callback.onAppForeground(activity);
                }
            }
        }
    }

    private static synchronized void onAppBackground(Activity activity) {
        synchronized (GlobalActivityLifecycleMonitor.class) {
            for (SimpleRef ref : appLifeCallbackList) {
                AppLifeCallback callback = ref.get();
                if (callback != null) {
                    callback.onAppBackground(activity);
                }
            }
        }
    }

    public static synchronized void addAppLifeCallback(AppLifeCallback callback) {
        synchronized (GlobalActivityLifecycleMonitor.class) {
            if (callback != null) {
                int size = appLifeCallbackList.size();
                for (int i = 0; i < size; i++) {
                    SimpleRef ref = (SimpleRef) appLifeCallbackList.get(i);
                    if (!ref.isOccupied()) {
                        ref.update(callback);
                        break;
                    }
                }
                appLifeCallbackList.add(new SimpleRef(callback));
            }
        }
    }

    public static synchronized void removeAppLifeCallback(AppLifeCallback callback) {
        synchronized (GlobalActivityLifecycleMonitor.class) {
            if (callback != null) {
                int size = appLifeCallbackList.size();
                for (int i = 0; i < size; i++) {
                    SimpleRef ref = (SimpleRef) appLifeCallbackList.get(i);
                    if (ref.get() == callback) {
                        ref.clear();
                    }
                }
            }
        }
    }
}
