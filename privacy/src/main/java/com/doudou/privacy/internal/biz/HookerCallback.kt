package com.doudou.privacy.internal.biz

import com.doudou.log.loge
import com.doudou.log.logi
import de.robv.android.xposed.XC_MethodHook
import java.lang.StringBuilder

/**
 * ClassName: [HookerCallback]
 * Description:
 */
open class HookerCallback : XC_MethodHook() {

    override fun beforeHookedMethod(param: MethodHookParam?) {
        super.beforeHookedMethod(param)
        loge { formatParams("pre call: ", param) }
    }

    fun replaceResult(
        declaringClass: Any?
        , thisObj: Any?
        , method: String
        , vararg args: Any?
    ) : ReplaceResult? {
        logi { "declaringClass = $declaringClass, thisObj = $thisObj, method = $method, args = ${args.joinToString(", ") }" }
        try {
            return internalReplaceResult(declaringClass, thisObj, method, *args)
        } catch (e: Exception) {
            loge(e)
        }
        return null
    }

    protected open fun internalReplaceResult(declaringClass: Any?
                                             , thisObj: Any?
                                             , method: String
                                             , vararg args: Any?) : ReplaceResult? {
        return null
    }

    override fun afterHookedMethod(param: MethodHookParam?) {
        super.afterHookedMethod(param)
        loge { "${formatParams("call after: ", param)} = ${param?.result}"}
    }

    private fun formatParams(tag: String, param: MethodHookParam?) : String = param?.let {
        val sb = StringBuilder(tag)
        sb.append(it.method?.declaringClass?.name ?: "undefine")
            .append("#")
            .append(it.method?.name ?: "undefine")
        if (!it.args.isNullOrEmpty()) {
            sb.append("(")
            val size = it.args.size
            it.args.forEachIndexed { index, arg ->
                sb.append(arg ?: "null")
                if (index < size - 1) {
                    sb.append(", ")
                }
            }
            sb.append(")")
        }
        sb.toString()
    } ?: tag

    data class ReplaceResult(val result: Any? = null, val thr: Throwable? = null)

}