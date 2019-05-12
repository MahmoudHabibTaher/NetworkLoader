package com.bigo.todolist.conductor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var router: Router? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        router = Conductor.attachRouter(this, controller_container, savedInstanceState)
        router?.apply {
            if (!hasRootController()) {
                setRoot(RouterTransaction.with(HomeController()))
            }
        }
    }

    override fun onBackPressed() {
        if (router?.handleBack() == false) {
            super.onBackPressed()
        }
    }
}
