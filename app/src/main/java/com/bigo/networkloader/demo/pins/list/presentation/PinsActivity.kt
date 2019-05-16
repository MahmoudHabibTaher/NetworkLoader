package com.bigo.networkloader.demo.pins.list.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bigo.networkloader.R
import com.bigo.networkloader.demo.BaseActivity
import com.bigo.networkloader.demo.core.log.logDebug
import com.bigo.networkloader.demo.pins.list.domain.entities.Pin
import com.bigo.networkloader.image.ImageLoader
import kotlinx.android.synthetic.main.activity_pins.*
import org.kodein.di.generic.factory
import org.kodein.di.generic.instance

class PinsActivity : BaseActivity() {
    private lateinit var viewModel: PinsListViewModel

    private val viewModelProvider: (AppCompatActivity) -> PinsListViewModel by factory()

    private val imageLoader: ImageLoader by instance()

    private val pinsAdapter by lazy { PinsAdapter(mutableListOf(), imageLoader) }

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
        loading_view.visibility = if (visible) View.VISIBLE else View.GONE
    }

    private fun showError(error: String) {
        AlertDialog.Builder(this)
            .setMessage(error)
            .setPositiveButton(R.string.ok, null)
            .create()
            .show()
    }

    private fun showPins(pins: List<Pin>) {
        pinsAdapter.setNewData(pins)
    }
}
