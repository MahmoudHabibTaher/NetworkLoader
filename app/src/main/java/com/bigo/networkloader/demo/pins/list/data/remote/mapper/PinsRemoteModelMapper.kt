package com.bigo.networkloader.demo.pins.list.data.remote.mapper

import com.bigo.networkloader.demo.core.mapper.ModelMapper
import com.bigo.networkloader.demo.pins.list.data.remote.models.PinRemote
import com.bigo.networkloader.demo.pins.list.data.remote.models.PinUrlsRemote
import com.bigo.networkloader.demo.pins.list.data.remote.models.UserRemote
import com.bigo.networkloader.demo.pins.list.domain.entities.Pin
import com.bigo.networkloader.demo.pins.list.domain.entities.PinUrls
import com.bigo.networkloader.demo.pins.list.domain.entities.User

class PinsRemoteModelMapper(private val userModelMapper: ModelMapper<UserRemote, User>) :
    ModelMapper<PinRemote, Pin>() {
    override fun mapFrom(first: PinRemote): Pin =
        Pin.Builder().run {
            id(first.id ?: "")
            user(mapUser(first.user))
            urls(mapUrls(first.urls))
            build()
        }

    private fun mapUser(user: UserRemote?) =
        user?.let { userModelMapper.mapFrom(it) } ?: User.Builder().build()

    private fun mapUrls(urls: PinUrlsRemote?) =
        PinUrls.Builder().run {
            raw(urls?.raw ?: "")
            full(urls?.full ?: "")
            regular(urls?.regular ?: "")
            small(urls?.small ?: "")
            thumb(urls?.thumb ?: "")
            build()
        }
}