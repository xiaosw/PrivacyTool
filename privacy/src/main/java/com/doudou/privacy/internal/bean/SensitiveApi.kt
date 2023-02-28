package com.doudou.privacy.internal.bean

import com.doudou.privacy.internal.biz.HookerCallback
import com.doudou.privacy.internal.hook.SystemClassMapping
import java.io.Serializable
import java.lang.IllegalArgumentException
import java.lang.RuntimeException

/**
 * ClassName: [SensitiveApi]
 * Description:
 */
data class SensitiveApi(
    val className: String,
    val signature: String,
    val isConstructor: Boolean = false
) : Serializable {

    fun parseClass() : Class<*> {
        var clazz = SystemClassMapping.get(className)
        if (null == clazz) {
            clazz = Class.forName(className, false, javaClass.classLoader)?.also {
                SystemClassMapping.add(className, clazz)
            }
        }
        if (null == clazz) {
            throw RuntimeException("not find class: $className")
        }
        return clazz
    }

    fun parseSignature(classLoader: ClassLoader = javaClass.classLoader) = arrayOfNulls<Any>(2).also {
        val start = signature.indexOf("(")
        val end = signature.indexOf(")")
        val methodName = signature.substring(0, start)
        val paramList = signature.substring(start + 1, end)
        val parameters = if (paramList.isNullOrEmpty()) {
            arrayOfNulls(1)
        } else {
            val segments = paramList.split(",")
            arrayOfNulls<Any>(segments.size + 1).also { p ->
                segments.forEachIndexed { index, className ->
                    var clazz = SystemClassMapping.get(className)
                    if (clazz == null) {
                        clazz = Class.forName(className.trim(), false, classLoader)
                    }
                    if (null == clazz) {
                        throw IllegalArgumentException("invalid signature ($signature)")
                    }
                    p[index] = clazz
                }
            }
        }
        parameters[parameters.size - 1] = HookerCallback()
        it[0] = methodName
        it[1] = parameters
    }

}