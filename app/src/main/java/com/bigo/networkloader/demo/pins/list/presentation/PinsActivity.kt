package com.bigo.networkloader.demo.pins.list.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.bigo.networkloader.R
import com.bigo.networkloader.demo.BaseActivity
import com.bigo.networkloader.demo.core.log.logDebug
import org.kodein.di.generic.factory

class PinsActivity : BaseActivity() {
    private lateinit var viewModel: PinsListViewModel

    private val viewModelProvider: (AppCompatActivity) -> PinsListViewModel by factory()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pins)

        viewModel = viewModelProvider(this)

        observePins(viewModel, this)
        observeLoadingVisible(viewModel, this)
        observeError(viewModel, this)

        viewModel.loadPins()
    }

    private fun observePins(viewModel: PinsListViewModel, owner: LifecycleOwner) {
        viewModel.pinsLiveData.observe(this, Observer {
            logDebug("$it")
        })
    }

    private fun observeLoadingVisible(viewModel: PinsListViewModel, owner: LifecycleOwner) {
        viewModel.loadingVisibleLiveData.observe(owner, Observer {
            setLoadingVisible(it)
        })
    }

    private fun observeError(viewModel: PinsListViewModel, owner: LifecycleOwner) {
        viewModel.errorLiveData.observe(owner, Observer {
            showError(it)
        })
    }

    private fun setLoadingVisible(visible: Boolean) {

    }

    private fun showError(error: String) {

    }
}
