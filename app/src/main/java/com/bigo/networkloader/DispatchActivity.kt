package com.bigo.networkloader

import android.content.Intent
import android.os.Bundle
import com.bigo.networkloader.demo.BaseActivity
import com.bigo.networkloader.demo.pins.list.presentation.PinsActivity

class DispatchActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showHome()
    }

    private fun showHome() {
        startActivity(Intent(this, PinsActivity::class.java))
        finish()
    }
}