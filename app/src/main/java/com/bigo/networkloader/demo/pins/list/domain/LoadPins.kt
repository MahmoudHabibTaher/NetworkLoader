package com.bigo.networkloader.demo.pins.list.domain

import com.bigo.networkloader.demo.core.domain.BaseSingleUseCase
import com.bigo.networkloader.demo.core.domain.Params
import com.bigo.networkloader.demo.core.domain.executors.ThreadExecutor
import com.bigo.networkloader.demo.pins.list.domain.entities.Pin
import io.reactivex.Single

class LoadPins(
    threadExecutor: ThreadExecutor,
    postThreadExecutor: ThreadExecutor,
    private val pinsRepository: PinsRepository
) :
    BaseSingleUseCase<List<Pin>, Params>(threadExecutor, postThreadExecutor) {
    override fun buildUseCase(params: Params?): Single<List<Pin>> =
        pinsRepository.loadPins()
}