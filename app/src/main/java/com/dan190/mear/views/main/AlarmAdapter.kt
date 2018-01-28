package com.dan190.mear.views.main

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.dan190.mear.R
import com.dan190.mear.data.Alarm
import com.mindorks.placeholderview.Animation
import com.mindorks.placeholderview.PlaceHolderView
import com.mindorks.placeholderview.annotations.*
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Dan on 28/01/2018.
 */
@Animate(Animation.ENTER_TOP_ASC)
@NonReusable
@Layout(R.layout.card_alert_message)
class AlarmAdapter (val context: Context, val placeHolderView: PlaceHolderView, val alarm: Alarm){

    @View(R.id.alertImage)
    var imageView: ImageView? = null

    @View(R.id.title)
    var title: TextView? = null

    @View(R.id.date)
    var date: TextView? = null

    @View(R.id.message)
    var message: TextView? = null

    @Resolve
    private fun onResolved() {
//        Picasso.with(context)
//                .load(R.drawable.announcement)
//                .into(imageView)

        title?.text = alarm.title
        message?.text = alarm.message

        val dateLong = alarm.time?.toLong()
        if (dateLong != null) {
            val dateString = SimpleDateFormat("MM/dd/yyyy  HH:mm:ss").format(Date(dateLong))
            date?.text = dateString
        }
    }
}