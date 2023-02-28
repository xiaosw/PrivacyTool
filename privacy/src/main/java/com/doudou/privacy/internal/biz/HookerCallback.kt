package com.doudou.privacy.internal.biz

import com.doudou.log.loge
import de.robv.android.xposed.XC_MethodHook
import java.lang.StringBuilder

/**
 * ClassName: [HookerCallback]
 * Description:
 */
class HookerCallback : XC_MethodHook() {

    override fun beforeHookedMethod(param: MethodHookParam?) {
        super.beforeHookedMethod(param)
        loge { formatParams("pre call: ", param) }
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

}