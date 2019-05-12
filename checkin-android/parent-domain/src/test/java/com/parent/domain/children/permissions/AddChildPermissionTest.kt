package com.parent.domain.children.permissions

import com.nhaarman.mockito_kotlin.*
import com.parent.domain.base.Params
import com.parent.domain.children.ParamsConstants
import com.parent.domain.children.data.ChildrenDataSource
import com.parent.domain.executor.test.TestExecutor
import com.parent.domain.executor.test.TestPostExecutor
import io.reactivex.Single
import org.amshove.kluent.mock
import org.junit.Before
import org.junit.Test

class AddChildPermissionTest {

    private val childrenDataSource = mock<ChildrenDataSource>()
    private val childPermissionStatePublisher = mock<IChildPermissionStatePublisher>()

    private lateinit var addChildPermission: AddChildPermission

    @Before
    fun setUp() {
        clearInvocations(childrenDataSource, childPermissionStatePublisher)

        addChildPermission = AddChildPermission(TestExecutor(), TestPostExecutor(),
                childrenDataSource, childPermissionStatePublisher)
    }

    @Test
    fun addChildPermission() {
        val message = "Permission added"
        val addPermissionResult = AddChildPermissionResult.Builder().message(message).build()


        val childId = "1"
        val instituteId = "2"
        val permissionName = "Can he eat nuts?"
        val permissionDescription = "Is he allowed to eat nuts or will he die ?"
        val request = AddChildPermissionRequest.Builder().childId(childId)
                .permissionName(permissionName)
                .instituteId(instituteId)
                .permissionDescription(permissionDescription)
                .build()

        whenever(childrenDataSource.addChildPermission(childId, instituteId, permissionName,
                permissionDescription)) doReturn Single.just(addPermissionResult)

        val params = Params(ParamsConstants.ADD_PERMISSION_REQUEST to request)

        val observer = addChildPermission.getSingle(params).test()

        observer.awaitTerminalEvent()

        verify(childrenDataSource).addChildPermission(request.childId, instituteId, request.permissionName,
                request.permissionDescription)

        verifyNoMoreInteractions(childrenDataSource)

        observer.assertValue(addPermissionResult)
    }

    @Test
    fun addChildPermissionSuccessNotifyNewChildPermission() {
        val message = "Permission added"
        val addPermissionResult = AddChildPermissionResult.Builder().message(message).build()

        val childId = "1"
        val instituteId = "2"
        val permissionName = "Can he eat nuts?"
        val permissionDescription = "Is he allowed to eat nuts or will he die ?"
        val request = AddChildPermissionRequest.Builder().childId(childId)
                .permissionName(permissionName)
                .instituteId(instituteId)
                .permissionDescription(permissionDescription)
                .build()

        whenever(childrenDataSource.addChildPermission(childId, instituteId, permissionName,
                permissionDescription)) doReturn Single.just(addPermissionResult)

        val params = Params(ParamsConstants.ADD_PERMISSION_REQUEST to request)

        val observer = addChildPermission.getSingle(params).test()

        observer.awaitTerminalEvent()

        verify(childPermissionStatePublisher).notifyNewPermission()
    }
}