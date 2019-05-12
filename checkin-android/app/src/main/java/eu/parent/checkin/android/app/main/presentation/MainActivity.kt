package eu.parent.checkin.android.app.main.presentation

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
    override val baseViewModel: BaseViewModel?
        get() = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}