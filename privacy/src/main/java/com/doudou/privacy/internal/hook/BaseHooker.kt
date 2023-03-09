package com.doudou.privacy.internal.hook

import com.doudou.log.loge
import com.doudou.privacy.internal.Constants
import com.doudou.privacy.internal.biz.HookerCallback
import de.robv.android.xposed.DexposedBridge
import de.robv.android.xposed.XC_MethodHook

/**
 * ClassName: [BaseHooker]
 * Description:
 */
abstract class BaseHooker(private var category: String) : HookerDelegate, HookerCallback() {
    private val mUnhook = mutableListOf<XC_MethodHook.Unhook>()

    override fun startHook() {
        Constants.ALL_SENSITIVE_API[category]?.forEach {
            val targetClazz = it.parseClass()
            val signature = it.parseSignature(this)
            val methodName = signature[0] as String
            if (it.isConstructor) {
//                DexposedBridge.hookMethod(targetClazz.getConstructor(*parameterTypes), HookerCallback()).also { unhook ->
//                    mUnhook.add(unhook)
//                }
            } else {
                DexposedBridge.findAndHookMethod(targetClazz, methodName, *(signature[1] as Array<Any>)).also { unhook ->
                    mUnhook.add(unhook)
                }
            }
        } ?: loge { "hook $category undefine!" }
    }

    override fun stopHook() {
        mUnhook.forEach {
            it.unhook()
        }
        mUnhook.clear()
    }
}