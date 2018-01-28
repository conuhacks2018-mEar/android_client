package com.dan190.mear.views.main

import android.content.ContentValues
import android.database.Cursor
import com.dan190.mear.data.Alarm
import com.dan190.mear.MyApplication
import com.dan190.mear.data.contentProvider.AlarmColumns
import com.dan190.mear.data.contentProvider.AlarmProviderJava
import com.dan190.mear.mvp.BaseMvpPresenterImpl
import com.dan190.mear.services.MyNotification
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import rx.Observable
import timber.log.Timber
import java.math.BigDecimal
import java.util.concurrent.TimeUnit

/**
 * Created by Dan on 27/01/2018.
 */
class MainPresenter :
        BaseMvpPresenterImpl<MainContract.View>(),
        MainContract.Presenter {


    private val database = FirebaseDatabase.getInstance()

    private val COL_ID = 0
    private val COL_TITLE = 1
    private val COL_MESSAGE = 2
    private val COL_DATE = 3

    override fun testBackgroundUpdate() {
        val testRef = database.getReference("policeNotification")
        testRef.addChildEventListener(object: ChildEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                Timber.e("child event cancelled")
            }

            override fun onChildMoved(p0: DataSnapshot?, p1: String?) {
                Timber.w("onchild moved")
            }

            override fun onChildChanged(dataSnapShot: DataSnapshot?, p1: String?) {
                Timber.w("on child changed")
                val label = dataSnapShot?.child("label")?.getValue(String::class.java)
                Timber.d("The label for this one is $label")
            }

            override fun onChildAdded(dataSnapShot: DataSnapshot?, p1: String?) {
                val label = dataSnapShot?.child("label")?.getValue(String::class.java)
                Timber.d("The label for this one is $label")
                val probability = dataSnapShot?.child("label_prob")?.getValue(String::class.java)
                val id = dataSnapShot?.child("wav_id")?.getValue(String::class.java)
                val roundedProbability = probability?.toDouble()?.cutTo2DecimalPlaces()?.times(100)
                val notification = ContentValues()
                val title = "$label detected!!"
                val message = "A $label has been detected with $roundedProbability % chance"
                notification.put(AlarmColumns.COLUMN_ID, id)
                notification.put(AlarmColumns.COLUMN_TITLE, title)
                notification.put(AlarmColumns.COLUMN_MESSAGE, message)
                notification.put(AlarmColumns.COLUMN_DATE, System.currentTimeMillis())
                MyApplication.getInstance().contentResolver.insert(AlarmProviderJava.AlarmMessages.CONTENT_URI, notification)

                // create notification
                MyNotification.createNotification(view?.getContext(), title, message)

                // Remove notification entity from firebase
                dataSnapShot?.ref?.removeValue()

            }

            override fun onChildRemoved(p0: DataSnapshot?) {
                Timber.w("on child removed")
            }

        })

    }

    override fun loadAllAlarms(cursor: Cursor?) {
        Timber.d("load all alarms")
        view?.showLoading()
        Observable.timer(2L, TimeUnit.SECONDS)
                .subscribe({
                    val alarmList = ArrayList<Alarm>()
                    if (cursor?.count != null)
                        for (i in 0 until cursor.count) {
                            cursor.moveToPosition(i)
                            val id = cursor.getString(COL_ID)
                            val title = cursor.getString(COL_TITLE)
                            val message = cursor.getString(COL_MESSAGE)
                            val date = cursor.getString(COL_DATE)

                            val alarm = Alarm(id, title, message, date)
                            alarmList.add(alarm)
                        }
                    view?.showListOfAlarms(alarmList)
                }, { view?.showError(it.toString()); Timber.e(it.toString()) })

    }

    fun Double.cutTo2DecimalPlaces() = BigDecimal(this).setScale(2, BigDecimal.ROUND_HALF_DOWN).toDouble()
}