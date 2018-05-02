package com.parent.domain.classes

import com.nhaarman.mockito_kotlin.*
import com.parent.domain.base.Params
import com.parent.domain.datetime.DateFormatter
import com.parent.domain.datetime.TestDateTimeManager
import com.parent.domain.executor.test.TestExecutor
import com.parent.domain.executor.test.TestPostExecutor
import com.parent.entities.ClassModel
import com.parent.entities.ClassChild
import com.parent.entities.ClassDetails
import io.reactivex.Single
import org.amshove.kluent.mock
import org.junit.Before
import org.junit.Test

class LoadClassDetailsTest {

    private val classesDataSource = mock<ClassesDataSource>()

    private val dateFormatter = DateFormatter(TestDateTimeManager())

    private lateinit var loadClassDetails: LoadClassDetails

    @Before
    fun setUp() {
        clearInvocations(classesDataSource)

        loadClassDetails = LoadClassDetails(TestExecutor(), TestPostExecutor(), classesDataSource,
                dateFormatter)
    }

    @Test
    fun loadClassDetails() {
        val details = ClassDetails.Builder().clazz(ClassModel.TestBuilder.buildNormalClass())
                .currentChildren(ClassChild.TestBuilder.buildList())
                .futureChildren(ClassChild.TestBuilder.buildList())
                .build()

        val classId = "1"

        whenever(classesDataSource.loadClass(classId)) doReturn Single.just(details)

        val params = Params(ParamsConstants.CLASS_ID to classId)

        val observer = loadClassDetails.getSingle(params).test()

        observer.awaitTerminalEvent()

        verify(classesDataSource).loadClass(classId)

        verifyNoMoreInteractions(classesDataSource)

        observer.assertValue(details)
    }

}