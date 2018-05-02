package com.parent.domain.children

import com.parent.entities.ChildModelView

/**
 * Created by mahmoud on 1/4/18.
 */
interface IEditChildViewPublisher {

    fun notifyViewModeToggleState(state: Boolean)

    fun notifyViewModeTogglFromBaseeState(state: Boolean)

    fun notifyChildProfileEditedState(child: ChildModelView)

    fun notifyPhotoSelectedState(state: Boolean)

    fun notifyCancelEditButtonState(state: Boolean)

    fun notifyChildAvatarUploadSuccessState(state: String)

    fun notifyRegLastDateViewModeChanged(state: Boolean)

    fun notifyRegLastDateValidation(message: String)

    fun notifyEditChildSuccess(message: String)

}