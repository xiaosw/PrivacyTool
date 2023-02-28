package com.doudou.privacy.internal.delegate.impl

import com.doudou.privacy.internal.Constants
import com.doudou.privacy.internal.delegate.PrivacyDelegate
import com.doudou.privacy.internal.factory.DynamicHookerFactory
import de.robv.android.xposed.DexposedBridge

/**
 * ClassName: [PrivacyDelegateImpl]
 * Description:
 */
internal class PrivacyDelegateImpl : PrivacyDelegate {

    override fun inject() {
        PRIVACY_APIS.forEach {
            DynamicHookerFactory.create(it).startHook()
        }
    }

    override fun uninject() {
        DexposedBridge.unhookAllMethods()
    }

    companion object {
        private val PRIVACY_APIS = arrayOf(Constants.NAME_ANDROID_ID)
    }

}