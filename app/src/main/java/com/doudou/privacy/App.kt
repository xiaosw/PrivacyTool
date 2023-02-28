package com.doudou.privacy

import android.app.Application
import com.doudou.privacy.api.PrivacyConfig
import com.doudou.privacy.api.PrivacyManager

/**
 * ClassName: [App]
 * Description:
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        PrivacyManager.start(this, PrivacyConfig(true))
    }

}