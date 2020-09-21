package com.eegets.notification

import android.annotation.SuppressLint
import android.app.Notification
import android.content.ContentResolver
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var notificationChannlId: String? = null
    private var notificationManagerCompat: NotificationManagerCompat? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notificationChannlId = createNotifyChannel(this)

        notificationManagerCompat = NotificationManagerCompat.from(applicationContext)
//        notificationManagerCompat?.notify(notificationId, notifyPicture())


        notificationText.setOnClickListener {
            notificationId++
            notificationManagerCompat?.notify(notificationId, notification())
        }

        notificationExpandText.setOnClickListener {
            notificationManagerCompat?.notify(notificationId, notifyShowMore())
        }

        notificationSmallImage.setOnClickListener {
            notificationManagerCompat?.notify(notificationId, notifyLargeIcon())
        }

        notificationSmallBigImage.setOnClickListener {
            notificationId++
            notificationManagerCompat?.notify(notificationId, notifyPicture())
        }
    }

    @SuppressLint("WrongConstant")
    private fun notification(): Notification {

        val sound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + packageName + "/raw/" + R.raw.sound)
        return notifyBuilder(this, notificationChannlId!!)
            .setContentTitle(notifTitle)
            .setContentText(notifDescription)
            .setSubText(subText)
            .setSound(sound)
            .setContentIntent(configNotifyIntent(this, NextActivity::class.java))
            .build()
    }

    private fun notifyShowMore(): Notification {
        val bigTextStyle =
            NotificationCompat.BigTextStyle() // Overrides ContentText in the big form of the template.
                .setBigContentTitle(notifTitle) // Summary line after the detail section in the big form of the template.
                .bigText(notifDescription) // Overrides ContentTitle in the big form of the template.
                .setSummaryText(subText)  //设置副标题,等同于'NotificationCompat.Builder.setSubText()'

        return notifyBuilder(this, notificationChannlId!!)
            .setStyle(bigTextStyle)
            .setContentTitle(notifTitle)
            .setContentText(notifDescription)
            .setContentIntent(configNotifyIntent(this, NextActivity::class.java))
            .build()
    }


    private fun notifyLargeIcon(): Notification {
        val bigTextStyle =
            NotificationCompat.BigTextStyle() // Overrides ContentText in the big form of the template.
                .setBigContentTitle(notifTitle) // Summary line after the detail section in the big form of the template.
                .bigText(notifDescription) // Overrides ContentTitle in the big form of the template.
                .setSummaryText(subText)  //设置副标题,等同于'NotificationCompat.Builder.setSubText()'
        return notifyBuilder(this, notificationChannlId!!)
            .setStyle(bigTextStyle)
            .setContentTitle(notifTitle)
            .setContentText(notifDescription)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.timg))
            .setContentIntent(configNotifyIntent(this, NextActivity::class.java))
            .build()
    }


    private fun notifyPicture(): Notification {
        val bigPictureStyle =
            NotificationCompat.BigPictureStyle() // Provides the bitmap for the BigPicture notification.
                .bigPicture(BitmapFactory.decodeResource(resources, R.drawable.big_picture)) // Overrides ContentTitle in the big form of the template.
                .setBigContentTitle(notifTitle) // Summary line after the detail section in the big form of the template.
                .setSummaryText(notifDescription)
                .bigLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.big_picture))

        return notifyBuilder(this, notificationChannlId!!)
            .setStyle(bigPictureStyle)
            .setContentTitle(notifTitle)
            .setContentText(notifDescription)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.timg))
            .setContentIntent(configNotifyIntent(this, NextActivity::class.java))
            .build()
    }

    companion object {
        /**
         * notificationId 通知ID
         */
        var notificationId = 888
        /**
         * 渠道号
         */
        const val CHANNEL_ID = "huawei"

        const val subText = "副标题"

        /**
         * 标题
         */
        const val notifTitle = "华为重磅发布鸿蒙OS 2.0，18:18开源！"

        /**
         * 描述
         */
        const val notifDescription = "鸿蒙OS 2.0将向170多个国家和地区的销售服务组织、50多个国家和地区的生态运营组织、10多个华为自有应用分发体系全面共享，向合作伙伴全面开放华为全球网络和渠道。"

        /**
         * 小图
         */
        const val smallImage = "https://upload.jianshu.io/users/upload_avatars/18406403/8ddda40c-c94e-4e1b-bc38-73f9b9e65528.jpeg?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240"

        /**
         * 静态大图
         */
        const val bigImage = "https://upload-images.jianshu.io/upload_images/10137682-933e5f806a70d0aa?imageMogr2/auto-orient/strip|imageView2/2/w/1080/format/webp"

        /**
         * 动态图片
         */
        const val bigImageGif = "https://upload-images.jianshu.io/upload_images/10137682-91d4ba263f206d7f?imageMogr2/auto-orient/strip|imageView2/2/w/638/format/webp"
    }

}