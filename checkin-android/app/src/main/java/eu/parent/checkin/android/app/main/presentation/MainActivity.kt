package eu.parent.android.app.main.presentation

import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.ActionMenuView
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.github.salomonbrys.kodein.factory
import com.jakewharton.rxbinding2.view.clicks
import com.parent.entities.NavigationEntityTypes
import eu.parent.android.app.R
import eu.parent.android.app.R.id.bottom_navigation_view
import eu.parent.android.app.accountsettings.presentation.AccountSettingsActivity
import eu.parent.android.app.calendar.view.institution.presentation.ViewCalendarFragment
import eu.parent.android.app.common.presentation.actvities.BaseActivity
import eu.parent.android.app.common.presentation.extensions.loadImageIntoDrawable
import eu.parent.android.app.common.presentation.extensions.visible
import eu.parent.android.app.common.presentation.interfaces.addDisposable
import eu.parent.android.app.common.presentation.viewmodels.BaseViewModel
import eu.parent.android.app.main.children.presentation.ChildrenListDialogFragment
import eu.parent.android.app.main.flow.MainFlow
import eu.parent.android.app.main.flow.MainFlowController
import eu.parent.android.app.newsfeed.list.presentation.NewsFeedFragment
import kotlinx.android.synthetic.main.activity_main.*


/**
 * Created by mahmoud on 9/18/17.
 */
class MainActivity : BaseActivity() {

    companion object {
        const val MAIN_ACTIVITY_FRAGMENT_CONTROLLER: Int = R.id.main_fragment_container
        const val NAVIGATION_ITEM_TYPE: String = "navigationItemType"
        const val NAVIGATION_ITEM_ACTION: String = "navigationItemAction"
        const val NAVIGATION_ITEM_ID: String = "navigationItemID"
        const val NAVIGATION_ITEM_SUB_ID: String = "navigationItemSubID"
        const val NAVIGATION_INSTITUTION_ID: String = "navigationInstitutionId"
        const val LOGOUT_MENU_ITEM: Int = 0
        const val ITEM_NEWSFEED = R.id.nav_item_newsfeed
        const val ITEM_CALENDAR = R.id.nav_item_calendar
        const val ITEM_OVERVIEW = R.id.nav_item_overview
        const val ITEM_APP = R.id.nav_item_apps
        const val ITEM_OPTIONS = R.id.nav_item_options

        const val CONTROLS_GROUP = R.id.controls_group
        const val CHILDREN_GROUP = R.id.children_group
        const val INST_GROUP = R.id.institutions_group
    }

    private lateinit var viewModel: MainViewModel

    override val baseViewModel: BaseViewModel?
        get() = viewModel

    private lateinit var mainFlow: MainFlow

    private lateinit var parentView: View
    private lateinit var context: Context

    private var leftMenuActionView: ActionMenuView? = null
    private var subMenu: Menu? = null

    private var notificationsMenuAction: MenuItem? = null
    private var messagesMenuAction: MenuItem? = null
    private var userMenuAction: MenuItem? = null
    private var logOutMenuAction: MenuItem? = null
    private var accountSettingsMenuAction: MenuItem? = null

    private var calendarTag: Int = 0
    private var calendarIndex: String = ""

    private var newsFeedTag: Int = 0
    private var newsFeedIndex: String = ""
    private var newsFeedSubIndex: String = ""

    private var childrenListTag: Int = ChildrenListDialogFragment.TYPE_SHOW_CHILDREN_LIST
    private var childrenListIndex: String = ""
    private var overViewSubIndex: String = ""
    private var childrenMenuItemInitialized = false

    private var lastOpenTab: Int = -1

    private var loggedInInformationInitialized = false
    private lateinit var childDetailsMenuAction: MenuItem

    private val viewModelProvider: (BaseActivity) -> MainViewModel by factory()

    private val institutionMenuItemsList: MutableList<Pair<String, MenuItem>> = mutableListOf()

    private var notificationsNumber = 0
    private var notificationUnreadCounter: TextView? = null

    var navigationItemType: Int = 0
    var navigationItemAction: Int = 0
    var navigationItemId: String = ""
    var navigationItemSubId: String = ""
    var navigationInstitutionId: String = ""

    var messageMenuDrawable: Drawable? = null
    var notificationMenuDrawable: Drawable? = null
    var notificationMenuImageView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isLogoEnabled = true

        setContentView(R.layout.activity_main)

        parentView = findViewById(R.id.container)
        context = this@MainActivity

        viewModel = viewModelProvider(this as BaseActivity)
        mainFlow = MainFlowController(this, R.id.main_fragment_container)

        toolbar?.hideOverflowMenu()

        leftMenuActionView = toolbar?.findViewById(R.id.start_menu_view)
        leftMenuActionView?.setOnMenuItemClickListener {
            onOptionsItemSelected(it)
        }

        bottom_navigation_view.setOnNavigationItemSelectedListener {
            onNavigationItemSelected(it.itemId)
            return@setOnNavigationItemSelectedListener true
        }

        bottom_navigation_view.selectedItemId = ITEM_NEWSFEED

        toolbar?.getChildAt(1)?.clicks()?.subscribe {
            bottom_navigation_view.selectedItemId = ITEM_NEWSFEED
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.header_toolbar_menu, menu)

        leftMenuActionView?.apply {
            this.menu.clear()
            menuInflater.inflate(R.menu.header_toolbar_left_menu, this.menu)
        }

        notificationsMenuAction = toolbar?.menu?.findItem(R.id.action_notifications)
//        messagesMenuAction = toolbar?.menu?.findItem(R.id.action_messages)
        userMenuAction = toolbar?.menu?.findItem(R.id.action_user_menu_item)

        subMenu = userMenuAction?.subMenu

        notificationsMenuAction?.setActionView(R.layout.feed_update_count)

        notificationUnreadCounter = notificationsMenuAction?.actionView?.findViewById(R.id.hotlist_hot) as TextView

        notificationMenuImageView = (notificationsMenuAction?.actionView?.findViewById(R.id.hotlist_bell) as ImageView)
        notificationMenuDrawable = notificationMenuImageView?.drawable
        messageMenuDrawable = messagesMenuAction?.icon

        messagesMenuAction?.setOnMenuItemClickListener {
            messagesMenuAction?.icon = activateTopMenuItemIcon(messageMenuDrawable)
            true
        }

        updateNotificationCount(notificationsNumber)
        object : MyMenuItemStuffListener(notificationsMenuAction?.actionView!!, "") {
            override fun onClick(v: View) {
                mainFlow.openNotifications()
                notificationsMenuAction?.isChecked = true
                notificationMenuImageView?.setImageDrawable(activateTopMenuItemIcon(notificationMenuDrawable))
            }
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.getItemId()

        when (id) {

//            R.id.action_search -> {
//                return true
//            }
//            R.id.action_messages -> {
//                return true
//            }
            R.id.action_notifications -> {
                mainFlow.openNotifications()
                return true
            }
            R.id.action_user_menu_item -> {
                return true
            }
            else -> {
                super.onOptionsItemSelected(item)
                return false
            }
        }
    }

    override fun onResume() {
        super.onResume()
        subscribeLoggedInUserInformationSuccess()
        subscribeNotificationsRefreshed(viewModel)
        subscribeNotificationActivityClosed(viewModel)
        subscribeInstitutionSelectedSuccess()
        subscribeNavigationEvents()
        subscribeOnChildrenViewSuccess()
        subscribeMainNavigationObservableSuccess()
        viewModel.start()
    }

    private fun subscribeMainNavigationObservableSuccess() {
        addDisposable(viewModel.notifyMainNavigationObservable.subscribe({
            onMainActivityNavigation(it.type, it.action, it.itemId, it.subId)
        }))
    }

    private fun subscribeInstitutionSelectedSuccess() {
        addDisposable(viewModel.institutionSelectedSuccessObservable.subscribe({

            if (!viewModel.instituteSelectionInitialized) {
                if (viewModel.canViewAdminDashBoard) {
                    bottom_navigation_view.selectedItemId = ITEM_OVERVIEW
                    viewModel.instituteSelectionInitialized = true
                } else {
                    bottom_navigation_view.selectedItemId = ITEM_NEWSFEED
                }
                bottom_navigation_view.visible(true)
                notificationsMenuAction?.isVisible = true
                messagesMenuAction?.isVisible = true

                for (item in institutionMenuItemsList) {
                    item.second.isChecked = false
                    if (item.first.equals(it)) {
                        item.second.isChecked = true
                    }
                }

            }
        }))
    }

    private fun subscribeOnChildrenViewSuccess() {
        addDisposable(viewModel.onChildrenViewSuccessObservable.subscribe({
            isBackButton = !it

            if (!it) {
                if (lastOpenTab != -1) {
                    bottom_navigation_view.menu.findItem(lastOpenTab)?.isChecked = true
                }
            }
        }))
    }

    private fun subscribeLoggedInUserInformationSuccess() {
        addDisposable(viewModel.loggedInUserInformationObservable.subscribe({
            loggedInUserInformationSuccess()
        }))
    }

    private fun subscribeNavigationEvents() {
        addDisposable(viewModel.navigationEventObservable.subscribe({ event -> onNavigationEvent(event) }))
    }

    private fun subscribeNotificationsRefreshed(viewModel: MainViewModel) {
        addDisposable(viewModel.notificationsRefreshedObservable.subscribe {
            updateNotificationCount(it.toInt())
        })
    }

    private fun subscribeNotificationActivityClosed(viewModel: MainViewModel) {
        addDisposable(viewModel.notificationActivityClosedObservable.subscribe {
            notificationMenuImageView?.setImageDrawable(resetTopMenuItemIcon(notificationMenuDrawable))
        })
    }


    private fun loggedInUserInformationSuccess() {
        bottom_navigation_view.menu.removeItem(ITEM_OPTIONS)
        bottom_navigation_view.menu.removeItem(ITEM_APP)

//        if (!viewModel.canManageInstituteOptions) {
//            bottom_navigation_view.menu.removeItem(ITEM_OPTIONS)
//        }
//        if (!viewModel.canManageInstituteTools) {
//            bottom_navigation_view.menu.removeItem(ITEM_APP)
//        }
        if (!viewModel.canViewAdminDashBoard) {
            bottom_navigation_view.menu.removeItem(ITEM_OVERVIEW)
        }

        initChildrenBottomMenuItems()

        if (subMenu?.size() ?: 0 > 0) {
        } else {
            initSelectableInstitutionsMenuItems()
            initChildrenMenuItems()

            initAccountSettingsMenuItem()
            initLogoutMenuItem()

        }
        loadImageIntoDrawable(viewModel.userModelView.avatar, context, { userMenuAction?.icon = it }, true)

        for (item in institutionMenuItemsList) {
            item.second.isChecked = false
            if (item.first.equals(viewModel.currentInstitutionId)) {
                item.second.isChecked = true
            }
        }
        if (hasExtraNavigationInfo()) {
            initExtraIntentListener()
        } else {
            if (!loggedInInformationInitialized) {
                loggedInInformationInitialized = true

                initInstitutionsList()
            }
        }
    }

    private fun onNavigationEvent(navEvent: Int) {
        when (navEvent) {
            MainViewModel.NAV_NEWS_FEED -> mainFlow.openNewsFeed(newsFeedTag, newsFeedIndex)
            MainViewModel.NAV_CALENDAR -> mainFlow.openCalendar(calendarTag, calendarIndex)
            MainViewModel.NAV_OVERVIEW -> mainFlow.openOverview()
            MainViewModel.NAV_APPS -> mainFlow.openApps()
            MainViewModel.NAV_OPTIONS -> mainFlow.openOptions()

        }
    }

    private fun onNavigationItemSelected(itemId: Int) {
        val navItem = when (itemId) {
            ITEM_NEWSFEED -> MainViewModel.NAV_NEWS_FEED
            ITEM_CALENDAR -> MainViewModel.NAV_CALENDAR
            ITEM_OVERVIEW -> MainViewModel.NAV_OVERVIEW
            ITEM_APP -> MainViewModel.NAV_APPS
            ITEM_OPTIONS -> MainViewModel.NAV_OPTIONS
            else -> -1
        }

        viewModel.onNavItemClick(navItem)
    }

    @Suppress("RedundantOverride")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun updateNotificationCount(newNotificationNumber: Int) {
        notificationsNumber = newNotificationNumber
        if (notificationUnreadCounter == null) return
        runOnUiThread {
            if (newNotificationNumber == 0)
                notificationUnreadCounter?.visibility = View.INVISIBLE
            else {
                notificationUnreadCounter?.visibility = View.VISIBLE
                notificationUnreadCounter?.text = newNotificationNumber.toString()
            }
        }
    }

    internal abstract class MyMenuItemStuffListener(private val view: View, private val hint: String) : View.OnClickListener, View.OnLongClickListener {
        init {
            view.setOnClickListener(this)
            view.setOnLongClickListener(this)
        }

        abstract override fun onClick(v: View)

        override fun onLongClick(v: View): Boolean {
            if (hint.isNotBlank()) {
                val screenPos = IntArray(2)
                val displayFrame = Rect()
                view.getLocationOnScreen(screenPos)
                view.getWindowVisibleDisplayFrame(displayFrame)
                val context = view.context
                val width = view.width
                val height = view.height
                val midy = screenPos[1] + height / 2
                val screenWidth = context.resources.displayMetrics.widthPixels
                val cheatSheet = Toast.makeText(context, hint, Toast.LENGTH_SHORT)
                if (midy < displayFrame.height()) {
                    cheatSheet.setGravity(Gravity.TOP or Gravity.RIGHT,
                            screenWidth - screenPos[0] - width / 2, height)
                } else {
                    cheatSheet.setGravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, height)
                }
                cheatSheet.show()
            }
            return true
        }
    }

    private fun initLogoutMenuItem() {
        var nextItemIndex = subMenu?.size()
        logOutMenuAction = subMenu?.add(CONTROLS_GROUP, Menu.NONE, nextItemIndex
                ?: 0, R.string.log_out)
        logOutMenuAction?.icon = ContextCompat.getDrawable(context, R.drawable.ic_logout_rounded)
        logOutMenuAction?.setShowAsAction(MenuItem.SHOW_AS_ACTION_WITH_TEXT)
        logOutMenuAction?.setOnMenuItemClickListener {
            viewModel.logOutUser()
            true
        }
    }


    private fun initAccountSettingsMenuItem() {
        var nextItemIndex = subMenu?.size()
        accountSettingsMenuAction = subMenu?.add(CONTROLS_GROUP, Menu.NONE, nextItemIndex
                ?: 0, R.string.account_settings)
        accountSettingsMenuAction?.icon = ContextCompat.getDrawable(context, R.drawable.ic_edit_user)
        accountSettingsMenuAction?.setShowAsAction(MenuItem.SHOW_AS_ACTION_WITH_TEXT)
        accountSettingsMenuAction?.setOnMenuItemClickListener {

            startActivity(AccountSettingsActivity.startIntent(this))
            true
        }
    }

    private fun initChildrenMenuItems() {
        if (viewModel.userModelView.children.isNotEmpty()) {
            for (child in viewModel.userModelView.children) {
                var nextItemIndex = subMenu?.size()
                var childDetailsMenuAction = subMenu?.add(CHILDREN_GROUP, Menu.NONE, nextItemIndex
                        ?: 0, child.fullName)
                loadImageIntoDrawable(child.photo, context, { childDetailsMenuAction?.icon = it }, true)
                childDetailsMenuAction?.setShowAsAction(MenuItem.SHOW_AS_ACTION_WITH_TEXT)
                childDetailsMenuAction?.setOnMenuItemClickListener {
                    childrenListTag = ChildrenListDialogFragment.TYPE_SHOW_CHILDREN_DETAILS
                    childrenListIndex = child.id
                    bottom_navigation_view.selectedItemId = childDetailsMenuAction.itemId
                    true
                }
            }
        }
    }

    private fun initChildrenBottomMenuItems() {
        if (viewModel.userModelView.children.isNotEmpty()) {
            if (!childrenMenuItemInitialized) {
                val nextItemIndex = bottom_navigation_view.menu.size()
                childDetailsMenuAction = bottom_navigation_view.menu.add(Menu.NONE, Menu.NONE, nextItemIndex, "")
                childrenMenuItemInitialized = true
            }
            if (viewModel.userModelView.children.size == 1) {
                val child = viewModel.userModelView.children.first()
                childDetailsMenuAction.title = child.fullName
                loadImageIntoDrawable(child.photo, context, { childDetailsMenuAction.icon = it }, true)
                childDetailsMenuAction.setOnMenuItemClickListener {
                    lastOpenTab = bottom_navigation_view.selectedItemId
                    childrenListTag = ChildrenListDialogFragment.TYPE_SHOW_CHILDREN_DETAILS
                    childrenListIndex = child.id
                    childDetailsMenuAction.isChecked = true
                    mainFlow.openChildProfile(childrenListIndex)
                    viewModel.instituteSelectionInitialized = false
                    true
                }
            } else {
                childDetailsMenuAction.title = context.resources.getString(R.string.children)
                childDetailsMenuAction.icon = context.resources.getDrawable(R.drawable.ic_children_faces)
                childDetailsMenuAction.setOnMenuItemClickListener {
                    lastOpenTab = bottom_navigation_view.selectedItemId
                    childDetailsMenuAction.isChecked = true
                    if (childrenListTag == ChildrenListDialogFragment.TYPE_SHOW_CHILDREN_DETAILS) {
                        mainFlow.openChildProfile(childrenListIndex)
                    } else {
                        mainFlow.openChildrenList(viewModel.userModelView.children, childrenListTag, childrenListIndex)
                    }
                    viewModel.instituteSelectionInitialized = false
                    childrenListTag = ChildrenListDialogFragment.TYPE_SHOW_CHILDREN_LIST
                    childrenListIndex = ""
                    true
                }
            }
        }
    }

    private fun initSelectableInstitutionsMenuItems() {
        institutionMenuItemsList.clear()


        if ((viewModel.userType == MainViewModel.UserTypes.ADMIN ||
                        viewModel.userType == MainViewModel.UserTypes.STAFF)) {
            viewModel.userModelView.institutions.take(3).forEach { institute ->
                val nextItemIndex = subMenu?.size()
                val instituteMenuAction = subMenu?.add(INST_GROUP, Menu.NONE, nextItemIndex
                        ?: 0, institute.name)
                loadImageIntoDrawable(institute.avatar, context, { instituteMenuAction?.icon = it }, true)
                instituteMenuAction?.setShowAsAction(MenuItem.SHOW_AS_ACTION_WITH_TEXT)
                institutionMenuItemsList.add(Pair(institute.id, instituteMenuAction!!))
                instituteMenuAction.isCheckable = true
                instituteMenuAction.setOnMenuItemClickListener {
                    viewModel.instituteSelectionInitialized = false
                    viewModel.setCurrentInstitution(institute.id)
                    true
                }
            }
            if (viewModel.userModelView.institutions.size > 3) {
                val nextItemIndex = subMenu?.size()
                val midSizeSpan = RelativeSizeSpan(0.85f)
                val menuItemTitle = SpannableString(resources.getString(R.string.show_more))


                menuItemTitle.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, R.color.blueRegular)), 0,
                        menuItemTitle.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
                menuItemTitle.setSpan(midSizeSpan, 0, menuItemTitle.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

                val viewAllInstitutesMenuAction = subMenu?.add(INST_GROUP, Menu.NONE, nextItemIndex
                        ?: 0,
                        menuItemTitle)
                viewAllInstitutesMenuAction?.setShowAsAction(MenuItem.SHOW_AS_ACTION_WITH_TEXT)
                viewAllInstitutesMenuAction?.setOnMenuItemClickListener {
                    viewModel.instituteSelectionInitialized = false
                    mainFlow.openInstitutionLandingPage(viewModel.userModelView)
                    bottom_navigation_view.visible(false)
                    notificationsMenuAction?.isVisible = false
                    messagesMenuAction?.isVisible = false
                    true
                }
            }
        }
    }

    private fun hasExtraNavigationInfo() =
            intent.hasExtra(NAVIGATION_ITEM_TYPE) && intent.hasExtra(NAVIGATION_ITEM_ID)

    private fun initExtraIntentListener() {
        navigationItemType = intent.getIntExtra(NAVIGATION_ITEM_TYPE, 0)
        navigationItemAction = intent.getIntExtra(NAVIGATION_ITEM_ACTION, 0)
        navigationItemId = intent.getStringExtra(NAVIGATION_ITEM_ID) ?: ""
        navigationItemSubId = intent.getStringExtra(NAVIGATION_ITEM_SUB_ID) ?: ""
        navigationInstitutionId = intent.getStringExtra(NAVIGATION_INSTITUTION_ID) ?: ""


        if (viewModel.currentInstituteSelected.equals(navigationInstitutionId)) {
        } else {
            viewModel.setCurrentInstitution(navigationInstitutionId, true)
        }
        navigateMainActivitySubs()
    }

    fun onMainActivityNavigation(type: Int, action: Int, itemId: String, subId: String) {

        navigationItemType = type
        navigationItemAction = action
        navigationItemId = itemId
        navigationItemSubId = subId

        navigateMainActivitySubs()
    }

    private fun navigateMainActivitySubs() {
        when (navigationItemType) {
            NavigationEntityTypes.TYPE_ACTIVITY -> {
                if (navigationItemAction.equals(NavigationEntityTypes.ACTION_DELETE) ||
                        navigationItemAction.equals(NavigationEntityTypes.ACTION_DEFAULT)) {
                    calendarTag = ViewCalendarFragment.TYPE_MY_CALENDAR
                    calendarIndex = ""
                    bottom_navigation_view.selectedItemId = ITEM_CALENDAR
                } else {
                    calendarTag = ViewCalendarFragment.TYPE_ACTIVITY
                    calendarIndex = navigationItemId
                    bottom_navigation_view.selectedItemId = ITEM_CALENDAR
                    resetCalendarTags()
                }
            }
            NavigationEntityTypes.TYPE_EVENT -> {
                if (navigationItemAction.equals(NavigationEntityTypes.ACTION_DELETE) ||
                        navigationItemAction.equals(NavigationEntityTypes.ACTION_DEFAULT)) {
                    calendarTag = ViewCalendarFragment.TYPE_MY_CALENDAR
                    calendarIndex = ""
                    bottom_navigation_view.selectedItemId = ITEM_CALENDAR
                } else {
                    calendarTag = ViewCalendarFragment.TYPE_EVENT
                    calendarIndex = navigationItemId
                    bottom_navigation_view.selectedItemId = ITEM_CALENDAR
                    resetCalendarTags()
                }
            }
            NavigationEntityTypes.TYPE_HOLIDAY -> {
                if (navigationItemAction.equals(NavigationEntityTypes.ACTION_DELETE) ||
                        navigationItemAction.equals(NavigationEntityTypes.ACTION_DEFAULT)) {
                    calendarTag = ViewCalendarFragment.TYPE_MY_CALENDAR
                    calendarIndex = ""
                    bottom_navigation_view.selectedItemId = ITEM_CALENDAR
                } else {
                    calendarTag = ViewCalendarFragment.TYPE_HOLIDAY
                    calendarIndex = navigationItemId
                    bottom_navigation_view.selectedItemId = ITEM_CALENDAR
                    resetCalendarTags()
                }
            }
            NavigationEntityTypes.TYPE_NEWSFEED -> {
                if (navigationItemAction.equals(NavigationEntityTypes.ACTION_DELETE) ||
                        navigationItemAction.equals(NavigationEntityTypes.ACTION_DEFAULT)) {
                    newsFeedTag = NewsFeedFragment.TYPE_MY_NEWSFEED
                    newsFeedIndex = ""
                    newsFeedSubIndex = ""
                    bottom_navigation_view.selectedItemId = ITEM_NEWSFEED
                } else {
                    newsFeedTag = NewsFeedFragment.TYPE_POST_DETAILS
                    newsFeedIndex = navigationItemId
                    newsFeedSubIndex = navigationItemSubId
                    bottom_navigation_view.selectedItemId = ITEM_NEWSFEED
                    resetNewsFeedTags()
                }
            }
            NavigationEntityTypes.TYPE_NOT_IMPLEMENTED -> {
            }
        }
    }


    private fun initInstitutionsList() {
        when (viewModel.userType) {
            MainViewModel.UserTypes.ADMIN,
            MainViewModel.UserTypes.STAFF -> {
                if (viewModel.userModelView.institutions.isEmpty()) {

                } else if (viewModel.userModelView.institutions.size == 1 || viewModel.currentInstituteSelected) {
                    if (viewModel.canViewAdminDashBoard) {
                        bottom_navigation_view.selectedItemId = ITEM_OVERVIEW
                    } else {
                        bottom_navigation_view.selectedItemId = ITEM_NEWSFEED
                    }
                } else {
                    mainFlow.openInstitutionLandingPage(viewModel.userModelView)
                    bottom_navigation_view.visible(false)
                    notificationsMenuAction?.isVisible = false
                    messagesMenuAction?.isVisible = false
                }
            }
            MainViewModel.UserTypes.CONTACT -> {
                Log.e("UserTypes", "UserTypes: CONTACT")
                bottom_navigation_view.selectedItemId = ITEM_NEWSFEED

            }
        }
    }

    private fun resetCalendarTags() {
        calendarTag = ViewCalendarFragment.TYPE_MY_CALENDAR
        calendarIndex = ""
    }

    private fun resetNewsFeedTags() {
        newsFeedTag = NewsFeedFragment.TYPE_MY_NEWSFEED
        newsFeedIndex = ""
        newsFeedSubIndex = ""
    }

    private fun activateTopMenuItemIcon(drawable: Drawable?): Drawable? {
        resetAllTopMenuItems()
        drawable?.mutate()
        drawable?.setColorFilter(resources.getColor(R.color.white), PorterDuff.Mode.SRC_ATOP)
        return drawable
    }

    private fun resetTopMenuItemIcon(drawable: Drawable?): Drawable? {
        drawable?.mutate();
        drawable?.setColorFilter(null);
        return drawable
    }

    private fun resetAllTopMenuItems() {
        notificationMenuImageView?.setImageDrawable(resetTopMenuItemIcon(notificationMenuDrawable))
        messagesMenuAction?.setIcon(resetTopMenuItemIcon(messageMenuDrawable))
    }
}