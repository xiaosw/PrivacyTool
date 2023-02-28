package com.doudou.privacy.internal

import com.doudou.privacy.internal.bean.SensitiveApi

/**
 * ClassName: [Constants]
 * Description:
 */
internal object Constants {

    const val NAME_ANDROID_ID = "android_id"

    val ALL_SENSITIVE_API by lazy {
        mutableMapOf<String, MutableList<SensitiveApi>>().also {
            it[NAME_ANDROID_ID] = mutableListOf<SensitiveApi>().apply {
                add(SensitiveApi("android.provider.Settings\$Secure", "getString(android.content.ContentResolver,String)String"))
            }
        }
    }

}