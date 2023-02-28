package com.doudou.privacy.internal

import android.app.Application
import android.content.Context
import com.doudou.privacy.internal.helper.GlobalActivityLifecycleMonitor

/**
 * ClassName: [PrivacyContext]
 * Description:
 */
internal object PrivacyContext {

    lateinit var sApp: Application
        private set

    @JvmStatic
    fun init(context: Context) {
        sApp = (context as? Application) ?: (context.applicationContext as Application)
        GlobalActivityLifecycleMonitor.register(sApp)
    }

}