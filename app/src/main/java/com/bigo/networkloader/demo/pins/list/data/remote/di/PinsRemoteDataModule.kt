package com.bigo.networkloader.demo.pins.list.data.remote.di

import com.bigo.networkloader.demo.core.mapper.ModelMapper
import com.bigo.networkloader.demo.core.network.parsers.JsonParser
import com.bigo.networkloader.demo.pins.list.data.remote.PinsRemoteDataSource
import com.bigo.networkloader.demo.pins.list.data.remote.mapper.PinsRemoteModelMapper
import com.bigo.networkloader.demo.pins.list.data.remote.mapper.UserRemoteModelMapper
import com.bigo.networkloader.demo.pins.list.data.remote.models.PinRemote
import com.bigo.networkloader.demo.pins.list.data.remote.models.UserRemote
import com.bigo.networkloader.demo.pins.list.domain.PinsRepository
import com.bigo.networkloader.demo.pins.list.domain.entities.Pin
import com.bigo.networkloader.demo.pins.list.domain.entities.User
import com.bigo.networkloader.parser.ResponseParser
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

val pinsRemoteDataModule = Kodein.Module("pinsRemoteDataModule") {
    bind<PinsRepository>("pinsRemoteDataSource") with provider {
        PinsRemoteDataSource(instance(), instance(), instance())
    }

    bind<ResponseParser<List<PinRemote>>>() with provider {
        JsonParser.create<List<PinRemote>>(instance())
    }

    bind<Gson>() with provider {
        GsonBuilder().create()
    }

    bind<ModelMapper<PinRemote, Pin>>() with provider {
        PinsRemoteModelMapper(instance())
    }

    bind<ModelMapper<UserRemote, User>>() with provider {
        UserRemoteModelMapper()
    }
}