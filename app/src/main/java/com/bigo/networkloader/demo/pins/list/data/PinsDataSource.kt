package com.bigo.networkloader.demo.pins.list.data

import com.bigo.networkloader.demo.pins.list.domain.PinsRepository
import com.bigo.networkloader.demo.pins.list.domain.entities.Pin
import io.reactivex.Single

class PinsDataSource(private val remoteDataSource: PinsRepository) : PinsRepository {
    override fun loadPins(): Single<List<Pin>> =
        remoteDataSource.loadPins()
}