package com.eegets.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat


/**
 * @packageName: com.eegets.notification
 * @author: eegets
 * @date: 20-9-14 上午10:55
 * @description: 通知工具类
 */


fun createNotifyChannel(context: Context): String? {

    val sound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + context.packageName + "/raw/" + R.raw.sound)
    // NotificationChannels are required for Notifications on O (API 26) and above.
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

        val audioAttributes = AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
            .build()

        // Initializes NotificationChannel.
        val notificationChannel = NotificationChannel("huawei", "Sample Social", NotificationManager.IMPORTANCE_DEFAULT)
        notificationChannel.enableVibration(true) //是否有震动
        notificationChannel.lockscreenVisibility = notifPublicVisible()

        notificationChannel.setSound(sound, audioAttributes)
        // Adds NotificationChannel to system. Attempting to create an existing notification
        // channel with its original values performs no operation, so it's safe to perform the
        // below sequence.
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)
        "huawei"
    } else {
        // Returns null for pre-O (26) devices.
        null
    }
}



fun notifyBuilder(context: Context, channelId: String): NotificationCompat.Builder {

    return NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.mipmap.ic_launcher)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setCategory(Notification.CATEGORY_REMINDER)
        .setAutoCancel(true) //用户在点击通知后自动移除通知
//        .setGroup("com.android.example.WORK_EMAIL")  //如果您的应用发出 4 条或更多条通知且未指定通知组，那么在 Android 7.0 及更高版本上，系统会自动将这些通知分为一组。
//        .setGroupSummary(true)

}

/**
 * 配置Intent点击通知栏跳转页面
 */
fun configNotifyIntent(context: Context, clazz: Class<*>): PendingIntent {
    val notifyIntent = Intent(context, clazz)

    return PendingIntent.getActivity(
        context,
        0,
        notifyIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )
}
