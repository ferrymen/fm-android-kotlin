package com.ferrymen.goods.ui.activity

import android.os.Bundle
import com.ferrymen.core.ui.activity.BaseActivity
import com.ferrymen.goods.R
import com.ferrymen.goods.ui.fragment.CartFragment

class CartActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_cart)
        (fragment as CartFragment).setBackVisible(true)
    }
}