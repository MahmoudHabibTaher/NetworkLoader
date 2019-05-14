package com.bigo.networkloader.demo.pins.list.domain

import com.bigo.networkloader.demo.core.domain.executors.TestThreadExecutor
import com.bigo.networkloader.demo.pins.list.domain.entities.Pin
import com.bigo.networkloader.demo.pins.list.domain.entities.PinUrls
import com.bigo.networkloader.demo.pins.list.domain.entities.User
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.junit.Before

import org.junit.Test

class LoadPinsTest {

    private val testThreadExecutor = TestThreadExecutor()

    private val pinsRepository = mock<PinsRepository>()

    private lateinit var loadPinsUseCase: LoadPins

    @Before
    fun setUp() {
        loadPinsUseCase = LoadPins(
            testThreadExecutor, testThreadExecutor, pinsRepository
        )
    }

    @Test
    fun testLoadPins() {
        val pins = listOf<Pin>()

        whenever(pinsRepository.loadPins()) doReturn Single.just(pins)

        var testObserver = loadPinsUseCase.getSingle().test()

        testObserver.awaitTerminalEvent()

        verify(pinsRepository).loadPins()

        testObserver.assertValue(pins)

        clearInvocations(pinsRepository)

        val pins2 = listOf(
            Pin("1", User.Builder().build(), PinUrls.Builder().build()),
            Pin("2", User.Builder().build(), PinUrls.Builder().build())
        )

        whenever(pinsRepository.loadPins()) doReturn Single.just(pins2)

        testObserver = loadPinsUseCase.getSingle().test()

        testObserver.awaitTerminalEvent()

        verify(pinsRepository).loadPins()

        testObserver.assertValue(pins2)
    }
}