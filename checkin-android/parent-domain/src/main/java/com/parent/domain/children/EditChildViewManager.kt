package com.parent.domain.children

import android.util.Log
import com.jakewharton.rxrelay2.BehaviorRelay
import com.parent.entities.ChildModelView
import io.reactivex.disposables.Disposable

/**
 * Created by mahmoud on 1/4/18.
 */
object EditChildViewManager : IEditChildViewPublisher, IEditeChildViewObservable {

    private val childViewModeToggleObservable: BehaviorRelay<Boolean> = BehaviorRelay.create()

    private val baseChildViewModeToggleObservable: BehaviorRelay<Boolean> = BehaviorRelay.create()

    private val childProfileEditedStateObservable: BehaviorRelay<ChildModelView> = BehaviorRelay.create()

    private val photoSelectedObservable: BehaviorRelay<Boolean> = BehaviorRelay.create()

    private val childAvatarUploadSuccessObservable: BehaviorRelay<String> = BehaviorRelay.create()

    private val regLastDateValidationErrorObservable: BehaviorRelay<String> = BehaviorRelay.create()

    private val regLastDateViewModeChangedObservable: BehaviorRelay<Boolean> = BehaviorRelay.create()

    private val editChildSuccessObservable: BehaviorRelay<String> = BehaviorRelay.create()

    private val cancelEditButtonStateObservable: BehaviorRelay<Boolean> = BehaviorRelay.create()

    override fun notifyCancelEditButtonState(state: Boolean){
        cancelEditButtonStateObservable.accept(state)
    }

    override fun notifyViewModeToggleState(state: Boolean) {
        childViewModeToggleObservable.accept(state)
    }

    override fun notifyEditChildSuccess(message: String){
        editChildSuccessObservable.accept(message)
    }

    override fun notifyViewModeTogglFromBaseeState(state: Boolean) {
        baseChildViewModeToggleObservable.accept(state)
    }

    override fun notifyChildProfileEditedState(child: ChildModelView) {
        Log.e("ChildProfileEditedState", "notifyChildProfileEditedState")
        childProfileEditedStateObservable.accept(child)
    }

    override fun notifyPhotoSelectedState(state: Boolean) {
        photoSelectedObservable.accept(state)
    }

    override fun notifyChildAvatarUploadSuccessState(state: String) {
        childAvatarUploadSuccessObservable.accept(state)
    }

    override fun notifyRegLastDateViewModeChanged(state: Boolean) {
        regLastDateViewModeChangedObservable.accept(state)
    }

    override fun notifyRegLastDateValidation(message: String) {
        regLastDateValidationErrorObservable.accept(message)
    }

    override fun subscribeChildToggleViewModeState(onChildViewModeUpdated: (Boolean) -> Unit): Disposable =
            childViewModeToggleObservable.subscribe {
                onChildViewModeUpdated(it)
            }

    override fun subscribeBaseChildViewModeTogglState(onChildViewModeUpdated: (Boolean) -> Unit): Disposable =
            baseChildViewModeToggleObservable.subscribe {
                onChildViewModeUpdated(it)
            }

    override fun subscribeChildProfileEditedState(onChildProfileUpdated: (ChildModelView) -> Unit): Disposable {
        Log.e("ChildProfileEditedState", "childProfileEditedStateObservable")
        return childProfileEditedStateObservable.subscribe {
            onChildProfileUpdated(it)
        }
    }

    override fun subscribePhotoSelectedState(onPhotoSelected: (Boolean) -> Unit): Disposable =
            photoSelectedObservable.subscribe {
                onPhotoSelected(it)
            }


    override fun subscribeChildAvatarUploadSuccessState(onChildAvatarUploaded: (String) -> Unit): Disposable =
            childAvatarUploadSuccessObservable.subscribe {
                onChildAvatarUploaded(it)
            }

    override fun subscribeRegLastDateValidationError(onRegLastDateValidationError: (String) -> Unit): Disposable =
            regLastDateValidationErrorObservable.subscribe {
                onRegLastDateValidationError(it)
            }

    override fun subscribeRegLastDateViewModeChanged(onRegLastDateViewModeChanged: (Boolean) -> Unit): Disposable =
            regLastDateViewModeChangedObservable.subscribe {
                onRegLastDateViewModeChanged(it)
            }

    override fun subscribeEditChildSuccessSuccess(onSuccess: (String) -> Unit): Disposable =
            editChildSuccessObservable.subscribe {
                onSuccess(it)
            }

    override fun subscribeCancelEditButtonStateObservable(onCancel: (Boolean) -> Unit): Disposable =
            cancelEditButtonStateObservable.subscribe { onCancel(it) }

}