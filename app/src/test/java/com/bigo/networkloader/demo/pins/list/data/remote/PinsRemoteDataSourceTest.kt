package com.bigo.networkloader.demo.pins.list.data.remote

import com.bigo.networkloader.demo.core.mapper.ModelMapper
import com.bigo.networkloader.demo.pins.list.data.remote.models.PinRemote
import com.bigo.networkloader.demo.pins.list.data.remote.models.PinUrlsRemote
import com.bigo.networkloader.demo.pins.list.data.remote.models.ProfileImageRemote
import com.bigo.networkloader.demo.pins.list.data.remote.models.UserRemote
import com.bigo.networkloader.demo.pins.list.domain.entities.Pin
import com.bigo.networkloader.demo.pins.list.domain.entities.PinUrls
import com.bigo.networkloader.demo.pins.list.domain.entities.ProfileImage
import com.bigo.networkloader.demo.pins.list.domain.entities.User
import com.bigo.networkloader.NetworkLoader
import com.bigo.networkloader.parser.ResponseParser
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class PinsRemoteDataSourceTest {

    private val networkLoader = mock<NetworkLoader>()

    private val parser = mock<ResponseParser<List<PinRemote>>>()

    private val modelMapper = mock<ModelMapper<PinRemote, Pin>>()

    private lateinit var remoteDataSource: PinsRemoteDataSource

    @Before
    fun setUp() {
        remoteDataSource = PinsRemoteDataSource(networkLoader, parser, modelMapper)
    }

    @Test
    fun testLoadPinsFromNetwork() {
        val pins = listOf(
            PinRemote(
                "1", UserRemote(
                    "2", "johnDoe", "John Doe",
                    ProfileImageRemote("small", "medium", "large")
                ),
                PinUrlsRemote("raw", "full", "regular", "small", "thumb")
            )
        )

        val pinsDomain = listOf(
            Pin(
                "1", User(
                    "2", "johnDoe", "John Doe",
                    ProfileImage("small", "medium", "large")
                ),
                PinUrls("raw", "full", "regular", "small", "thumb")
            )
        )

        val url = ApiUrls.LOAD_PINS

        whenever(networkLoader.load<List<PinRemote>>(any(), any())) doReturn Single.just(pins)

        whenever(parser.parse(any())) doReturn pins

        whenever(modelMapper.mapFromAsync(pins)) doReturn Single.just(pinsDomain)

        val testObserver = remoteDataSource.loadPins().test()

        testObserver.awaitTerminalEvent()

        verify(networkLoader).load(url, parser)

        verify(modelMapper).mapFromAsync(pins)

        testObserver.assertValue(pinsDomain)
    }
}