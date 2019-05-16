package com.bigo.networkloader.demo.pins.list.data.remote

import com.bigo.networkloader.demo.core.mapper.ModelMapper
import com.bigo.networkloader.demo.pins.list.data.remote.models.PinRemote
import com.bigo.networkloader.demo.pins.list.domain.PinsRepository
import com.bigo.networkloader.demo.pins.list.domain.entities.Pin
import com.bigo.networkloader.NetworkLoader
import com.bigo.networkloader.parser.ResponseParser
import io.reactivex.Single

class PinsRemoteDataSource(
    private val networkLoader: NetworkLoader,
    private val pinsRemoteParser: ResponseParser<List<PinRemote>>,
    private val modelMapper: ModelMapper<PinRemote, Pin>
) : PinsRepository {
    override fun loadPins(): Single<List<Pin>> =
        networkLoader.load(ApiUrls.LOAD_PINS, pinsRemoteParser)
            .flatMap(::mapPins)

    private fun mapPins(pins: List<PinRemote>): Single<List<Pin>> =
        modelMapper.mapFromAsync(pins)

}
