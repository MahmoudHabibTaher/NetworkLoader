package com.bigo.networkloader.demo.pins.list.domain

import com.bigo.networkloader.demo.pins.list.domain.entities.Pin
import io.reactivex.Single

interface PinsRepository {
    fun loadPins(): Single<List<Pin>>
}