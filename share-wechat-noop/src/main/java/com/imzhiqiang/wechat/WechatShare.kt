package com.imzhiqiang.wechat

import android.app.Activity
import android.content.Intent

object WechatShare {

    interface AuthListener {
        fun onStart()

        fun onComplete(
            action: Int,
            data: MutableMap<String, String>
        )

        fun onError(action: Int, error: Throwable)

        fun onCancel(action: Int)
    }

    fun setConfig(appId: String, appSecret: String, fileProvider: String) {
    }

    fun isInstall(activity: Activity): Boolean {
        return false
    }

    fun getPlatformInfo(activity: Activity, authListener: AuthListener) {
    }

    fun release(activity: Activity) {
    }

    fun onActivityResult(activity: Activity, requestCode: Int, resultCode: Int, data: Intent?) {
    }

}