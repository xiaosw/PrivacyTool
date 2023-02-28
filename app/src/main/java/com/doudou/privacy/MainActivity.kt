package com.doudou.privacy

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.doudou.log.loge

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @SuppressLint("HardwareIds")
    fun test(view: View) {
//        val hasPrimaryClip = (getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager).hasPrimaryClip()
//        loge { "hasPrimaryClip = $hasPrimaryClip" }
        loge { "androidId = ${Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)}" }
    }
}