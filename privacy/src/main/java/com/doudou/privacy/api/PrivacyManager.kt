package com.doudou.privacy.api

import android.app.Application
import com.doudou.log.LogConfig
import com.doudou.log.LogFormat
import com.doudou.log.Logger
import com.doudou.log.Logger.init
import com.doudou.privacy.internal.PrivacyContext
import com.doudou.privacy.internal.delegate.PrivacyDelegate
import com.doudou.privacy.internal.delegate.impl.PrivacyDelegateImpl

/**
 * ClassName: [PrivacyManager]
 * Description:
 */
object PrivacyManager {

    private var mDelegate: PrivacyDelegate? = null

    fun start(app: Application, config: PrivacyConfig = PrivacyConfig()) {
        PrivacyContext.init(app)
        initLog(config)
        mDelegate = PrivacyDelegateImpl().also {
            it.inject()
        }
    }

    fun stop() = mDelegate?.uninject()

    private fun initLog(config: PrivacyConfig) {
        init(
            PrivacyContext.sApp, LogConfig(
                if (config.debug) Logger.Behavior.V_ALL else Logger.Behavior.NONE,
                "Privacy>>> ",
                LogConfig.MAX_LEN_TAG,
                LogConfig.MAX_LEN_MESSAGE,
                LogFormat(),
                mutableListOf()
            )
        )
    }

}