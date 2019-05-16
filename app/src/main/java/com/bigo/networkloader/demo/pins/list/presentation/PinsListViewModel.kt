package com.bigo.networkloader.demo.pins.list.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bigo.networkloader.demo.core.domain.Params
import com.bigo.networkloader.demo.core.domain.SingleUseCase
import com.bigo.networkloader.demo.core.log.logError
import com.bigo.networkloader.demo.pins.list.domain.entities.Pin
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import java.lang.IllegalArgumentException

class PinsListViewModel(
    application: Application,
    private val loadPins: SingleUseCase<List<Pin>, Params>
) : AndroidViewModel(application) {

    val loadingVisibleLiveData: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val errorLiveData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val pinsLiveData: MutableLiveData<List<Pin>> by lazy {
        MutableLiveData<List<Pin>>()
    }

    private val disposables = CompositeDisposable()

    private var isLoadingVisible: Boolean = false
        set(value) {
            field = value
            loadingVisibleLiveData.postValue(value)
        }

    fun loadPins() {
        isLoadingVisible = true
        val single = loadPins.getSingle()
        disposables.add(single.subscribeBy(onSuccess = {
            pinsLiveData.postValue(it)
            isLoadingVisible = false
        }, onError = {
            isLoadingVisible = false
            logError("Error loading pins", it)
            it.message?.apply {
                errorLiveData.postValue(this)
            }
        }))
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    class Factory(
        private val application: Application,
        private val loadPins: SingleUseCase<List<Pin>, Params>
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PinsListViewModel::class.java)) {
                return PinsListViewModel(application, loadPins) as T
            }

            throw IllegalArgumentException("Unknown view model class")
        }
    }
}