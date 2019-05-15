package com.bigo.networkloader.demo.pins.list.data.remote.mapper

import com.bigo.networkloader.demo.core.mapper.ModelMapper
import com.bigo.networkloader.demo.pins.list.data.remote.models.ProfileImageRemote
import com.bigo.networkloader.demo.pins.list.data.remote.models.UserRemote
import com.bigo.networkloader.demo.pins.list.domain.entities.ProfileImage
import com.bigo.networkloader.demo.pins.list.domain.entities.User

class UserRemoteModelMapper : ModelMapper<UserRemote, User>() {
    override fun mapFrom(first: UserRemote): User =
        User.Builder().run {
            id(first.id ?: "")
            userName(first.userName ?: "")
            name(first.name ?: "")
            image(mapImage(first.image))
            build()
        }

    private fun mapImage(image: ProfileImageRemote?) =
        ProfileImage.Builder().run {
            small(image?.small ?: "")
            medium(image?.medium ?: "")
            large(image?.large ?: "")
            build()
        }
}