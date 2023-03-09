package com.doudou.privacy.internal.biz

import com.doudou.privacy.internal.Constants
import com.doudou.privacy.internal.hook.BaseHooker

/**
 * ClassName: [AndroidIdHooker]
 * Description:
 */
class AndroidIdHooker : BaseHooker(Constants.NAME_ANDROID_ID) {

    override fun internalReplaceResult(
        declaringClass: Any?,
        thisObj: Any?,
        method: String,
        vararg args: Any?
    ): ReplaceResult? {
        return ReplaceResult("hehehe")
    }

}