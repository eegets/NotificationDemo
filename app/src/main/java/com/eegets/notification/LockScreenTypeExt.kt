package com.eegets.notification

import androidx.core.app.NotificationCompat

/**
 * @packageName: com.eegets.notification
 * @author: eegets
 * @date: 20-9-14 上午10:48
 * @description: TODO
 */

/**
 * 通知可见性：在任何情况都可显示通知完整内容
 */
fun notifPublicVisible() = NotificationCompat.VISIBILITY_PUBLIC

/**
 * 通知可见性： 显示基本信息，例如通知图标和内容标题，但隐藏通知的完整内容。
 */
fun notifPrivateVisible() = NotificationCompat.VISIBILITY_PRIVATE

/**
 * 通知可见性：不在锁定屏幕上显示该通知的任何部分。
 */
fun notifSecretVisible() = NotificationCompat.VISIBILITY_SECRET
