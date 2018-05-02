package com.parent.domain.children

import com.parent.entities.ChildModelView
import io.reactivex.disposables.Disposable

/**
 * Created by mahmoud on 1/4/18.
 */
interface IEditeChildViewObservable {
    fun subscribeChildToggleViewModeState(onChildViewModeUpdated: (Boolean) -> Unit): Disposable

    fun subscribeBaseChildViewModeTogglState(onChildViewModeUpdated: (Boolean) -> Unit): Disposable

    fun subscribeChildProfileEditedState(onChildProfileUpdated: (ChildModelView) -> Unit): Disposable

    fun subscribePhotoSelectedState(onPhotoSelected: (Boolean) -> Unit): Disposable

    fun subscribeChildAvatarUploadSuccessState(onChildAvatarUploaded: (String) -> Unit): Disposable

    fun subscribeRegLastDateValidationError(onRegLastDateValidationError: (String) -> Unit): Disposable

    fun subscribeRegLastDateViewModeChanged(onRegLastDateViewModeChanged: (Boolean) -> Unit): Disposable

    fun subscribeEditChildSuccessSuccess(onSuccess: (String) -> Unit):Disposable

    fun subscribeCancelEditButtonStateObservable(onCancel: (Boolean) -> Unit): Disposable

}