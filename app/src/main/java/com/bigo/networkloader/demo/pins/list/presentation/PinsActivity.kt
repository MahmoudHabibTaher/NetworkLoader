package com.bigo.networkloader.demo.pins.list.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bigo.networkloader.R
import com.bigo.networkloader.demo.BaseActivity
import com.bigo.networkloader.demo.core.log.logDebug
import com.bigo.networkloader.demo.pins.list.domain.entities.Pin
import kotlinx.android.synthetic.main.activity_pins.*
import org.kodein.di.generic.factory

class PinsActivity : BaseActivity() {
    private lateinit var viewModel: PinsListViewModel

    private val viewModelProvider: (AppCompatActivity) -> PinsListViewModel by factory()

    private val pinsAdapter = PinsAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pins)

        pins_recycler_view.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = pinsAdapter
        }

        viewModel = viewModelProvider(this)

        observePins(viewModel, this)
        observeLoadingVisible(viewModel, this)
        observeError(viewModel, this)

        viewModel.loadPins()
    }

    private fun observePins(viewModel: PinsListViewModel, owner: LifecycleOwner) {
        viewModel.pinsLiveData.observe(this, Observer {
            showPins(it)
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

    private fun showPins(pins: List<Pin>) {
        pinsAdapter.setNewData(pins)
    }
}
