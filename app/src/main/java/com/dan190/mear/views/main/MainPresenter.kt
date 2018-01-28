package com.dan190.mear.views.main

import com.dan190.mear.R
import com.dan190.mear.data.MyAlarm
import com.dan190.mear.mvp.BaseMvpPresenterImpl
import com.dan190.mear.services.MyNotification
import com.google.firebase.database.*
import io.realm.Realm
import rx.Observable
import rx.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by Dan on 27/01/2018.
 */
class MainPresenter : BaseMvpPresenterImpl<MainContract.View>(), MainContract.Presenter {
    val database = FirebaseDatabase.getInstance()

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
                Timber.d("Child added")
                val label = dataSnapShot?.child("label")?.getValue(String::class.java)
                Timber.d("The label for this one is $label")

            }

            override fun onChildRemoved(p0: DataSnapshot?) {
                Timber.w("on child removed")
            }

        })
        /*testRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                for (child in dataSnapshot?.children!!) {
                    val label = child?.child("label")?.getValue(String::class.java)
                    Timber.d(label)

                    MyNotification.createNotification(view?.getContext(),
                            "Alarm",
                            label)

                    val realm = Realm.getDefaultInstance()

                    realm.beginTransaction()

                    val alarmRealm = realm.createObject(MyAlarm::class.java).apply {
                        title = "Alarm"
                        message = label
                        time = System.currentTimeMillis()
                        icon = R.drawable.announcement
                    }

                    realm.commitTransaction()

                }

            }

            override fun onCancelled(p0: DatabaseError?) {
                Timber.e("testRefCancelled")
            }

        })*/
    }

    override fun loadAllAlarms() {
        Observable.just(Realm.getDefaultInstance())
                .subscribeOn(Schedulers.io())
                .subscribe ({
                    val results = it.where(MyAlarm::class.java)
                            .findAll()
                    for (result in results) {
                        Timber.d(result.toString())
                    }
                }, { view?.showError(it.toString()); Timber.e(it.toString()) })

    }
}