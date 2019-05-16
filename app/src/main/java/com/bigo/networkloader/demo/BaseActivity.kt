package com.bigo.networkloader.demo

import androidx.appcompat.app.AppCompatActivity
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

abstract class BaseActivity : AppCompatActivity(), KodeinAware {
    override val kodein: Kodein by lazy { (applicationContext as KodeinAware).kodein }
}