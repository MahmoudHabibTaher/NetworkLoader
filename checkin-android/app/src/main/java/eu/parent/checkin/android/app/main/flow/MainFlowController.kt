package eu.parent.android.app.main.flow

import android.content.Intent
import android.support.v4.app.DialogFragment
import com.parent.entities.ChildModelView
import eu.parent.android.app.R
import eu.parent.android.app.Tools.list.presentation.ToolsFragment
import eu.parent.android.app.calendar.view.institution.presentation.ViewCalendarFragment
import eu.parent.android.app.children.basic.profile.presentation.BasicChildProfileFragment
import eu.parent.android.app.common.navgiation.NavigationManager
import eu.parent.android.app.common.presentation.actvities.BaseActivity
import eu.parent.android.app.common.presentation.fragments.BaseFragment
import eu.parent.android.app.entities.UserViewModel
import eu.parent.android.app.main.children.presentation.ChildrenListDialogFragment
import eu.parent.android.app.main.landing.presentation.LandingPageFragment
import eu.parent.android.app.newsfeed.list.presentation.NewsFeedFragment
import eu.parent.android.app.notifications.presentation.NotificationsFragment
import eu.parent.android.app.options.presentation.OptionsFragment
import eu.parent.android.app.overview.presentation.OverviewFragment
import eu.parent.android.app.user.auth.login.presentation.LoginActivity
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug

/**
 * Created by mahmoud on 9/18/17.
 */
class MainFlowController(private val activity: BaseActivity, private val mainFragmentContainer: Int) : MainFlow, AnkoLogger {


    override fun openNewsFeed(tag: Int, index: String) {

        var fragment = activity.supportFragmentManager.findFragmentById(mainFragmentContainer)
        if (fragment != null && fragment is NewsFeedFragment) {
            fragment.refresh()
        } else {
            fragment = NewsFeedFragment.newInstance(tag, index)

            activity.navigationManager?.openAsRoot(fragment)
        }
    }


    override fun openCalendar(tag: Int, index: String) {
        debug("open Calendar")
        var fragment = activity.supportFragmentManager.findFragmentById(mainFragmentContainer)
        if (fragment != null && fragment is ViewCalendarFragment) {
            fragment.refresh()
        } else {
            fragment = ViewCalendarFragment.newInstance(tag, index)
            activity.navigationManager?.openAsRoot(fragment)
        }
    }

    override fun openOverview() {
        debug("open Overview")
        var fragment = activity.supportFragmentManager.findFragmentById(mainFragmentContainer)
        if (fragment != null && fragment is OverviewFragment) {
            fragment.refresh()
        } else {
            fragment = OverviewFragment.newInstance()
            activity.navigationManager?.openAsRoot(fragment)
        }
    }


    override fun openApps() {
        var fragment = activity.supportFragmentManager.findFragmentById(mainFragmentContainer)
        if (fragment != null && fragment is ToolsFragment) {
            fragment.refresh()
        } else {
            fragment = ToolsFragment.newInstance()
            activity.navigationManager?.openAsRoot(fragment)
        }
    }

    override fun openOptions() {
        debug("open Options")
        var fragment = activity.supportFragmentManager.findFragmentById(mainFragmentContainer)
        if (fragment != null && fragment is OptionsFragment) {
            fragment.refresh()
        } else {
            fragment = OptionsFragment.newInstance()
            activity.navigationManager?.openAsRoot(fragment)
        }
    }


    override fun openLogin() {
        val i = Intent(activity, LoginActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        activity.startActivity(i)
        activity.finishAffinity()
    }

    override fun openNotifications() {
        debug("open Notifications")
        var fragment = activity.supportFragmentManager.findFragmentById(mainFragmentContainer) as BaseFragment?
        if (fragment != null && fragment is NotificationsFragment) {
            fragment.refresh()
        } else {
            fragment = NotificationsFragment.newInstance()
            activity.navigationManager?.open(fragment, mainFragmentContainer)
        }
    }

    override fun openInstitutionLandingPage(userModelView: UserViewModel) {
        debug("open LandingPageFragment")
        var fragment = LandingPageFragment.newInstance(userModelView)
        activity.navigationManager?.openAsRoot(fragment)
    }

    override fun openChildrenList(childrenList: List<ChildModelView>, childListTag: Int, childListIndex: String) {
        debug("open ChildrenList")
        val fragment: DialogFragment =
                ChildrenListDialogFragment.newInstance(childrenList, childListTag, childListIndex)
        fragment.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.FullScreenDialogStyle)
        fragment.show(activity.supportFragmentManager, "ChildrenListDialogFragment_dialog")
    }

    override fun openChildProfile(childId: String) {
        debug("open ChildrenList")
        var fragment = activity.supportFragmentManager.findFragmentById(mainFragmentContainer) as BaseFragment?
        if (fragment != null && fragment is BasicChildProfileFragment) {
            activity.navigationManager?.navigateBackStack(activity)
        }
        fragment = BasicChildProfileFragment.newInstance(childId, true)
        activity.navigationManager?.open(fragment, openMethod = NavigationManager.OpenMethod.ADD)
    }

}