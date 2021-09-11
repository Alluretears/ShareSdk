package com.imzhiqiang.wechat

import android.app.Activity
import android.content.Intent
import com.umeng.socialize.PlatformConfig
import com.umeng.socialize.UMAuthListener
import com.umeng.socialize.UMShareAPI
import com.umeng.socialize.bean.SHARE_MEDIA

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
        PlatformConfig.setWeixin(appId, appSecret)
        PlatformConfig.setWXFileProvider(fileProvider)
    }

    fun isInstall(activity: Activity): Boolean {
        return UMShareAPI.get(activity).isInstall(activity, SHARE_MEDIA.WEIXIN)
    }

    fun getPlatformInfo(activity: Activity, authListener: AuthListener) {
        UMShareAPI.get(activity)
            .getPlatformInfo(activity, SHARE_MEDIA.WEIXIN, object : UMAuthListener {

                /**
                 * @desc 授权开始的回调
                 * @param platform 平台名称
                 */
                override fun onStart(platform: SHARE_MEDIA) {
                    authListener.onStart()
                }

                /**
                 * @desc 授权成功的回调
                 * @param platform 平台名称
                 * @param action 行为序号，开发者用不上
                 * @param data 用户资料返回
                 */
                override fun onComplete(
                    platform: SHARE_MEDIA,
                    action: Int,
                    data: MutableMap<String, String>
                ) {
                    authListener.onComplete(action, data)
                }

                /**
                 * @desc 授权失败的回调
                 * @param platform 平台名称
                 * @param action 行为序号，开发者用不上
                 * @param t 错误原因
                 */
                override fun onError(platform: SHARE_MEDIA, action: Int, t: Throwable) {
                    authListener.onError(action, t)
                }

                /**
                 * @desc 授权取消的回调
                 * @param platform 平台名称
                 * @param action 行为序号，开发者用不上
                 */
                override fun onCancel(platform: SHARE_MEDIA, action: Int) {
                    authListener.onCancel(action)
                }

            })
    }

    fun release(activity: Activity) {
        UMShareAPI.get(activity).release()
    }

    fun onActivityResult(activity: Activity, requestCode: Int, resultCode: Int, data: Intent?) {
        UMShareAPI.get(activity).onActivityResult(requestCode, resultCode, data)
    }

}