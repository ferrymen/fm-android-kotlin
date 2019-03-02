package com.ferrymen.fmshop.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ferrymen.fmshop.R
import com.ferrymen.fmshop.ui.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import java.util.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBottomNavBar.checkMsgBadge(false)
        mBottomNavBar.checkCartBadge(20)
//        Observable.timer(2, TimeUnit.SECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({mBottomNavBar.checkMsgBadge(true)})
//        Observable.timer(5, TimeUnit.SECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({mBottomNavBar.checkCartBadge(0)})

        initView()

    }

    private fun initView() {
        var manager = supportFragmentManager.beginTransaction()
        manager.replace(R.id.mContainer, HomeFragment())
        manager.commit()
    }
}