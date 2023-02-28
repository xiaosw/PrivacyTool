package com.doudou.privacy.internal.factory

import com.doudou.privacy.internal.Constants
import com.doudou.privacy.internal.biz.AndroidIdHooker
import com.doudou.privacy.internal.hook.HookerDelegate
import java.lang.IllegalArgumentException

/**
 * ClassName: [DynamicHookerFactory]
 * Description:
 */
internal object DynamicHookerFactory {

    fun create(name: String) : HookerDelegate = when(name) {
        Constants.NAME_ANDROID_ID -> AndroidIdHooker()

        else -> throw IllegalArgumentException("undefine hooker: $name")
    }
}