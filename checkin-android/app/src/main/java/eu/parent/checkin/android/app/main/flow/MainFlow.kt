package eu.parent.android.app.main.flow

import com.parent.entities.ChildModelView
import com.parent.entities.InstitutionModel
import eu.parent.android.app.entities.UserModelView
import eu.parent.android.app.entities.UserViewModel

/**
 * Created by mahmoud on 9/18/17.
 */
interface MainFlow {
    fun openNewsFeed(tag:Int,index:String)

    fun openCalendar(tag:Int,index:String)

    fun openOverview()

    fun openApps()

    fun openOptions()

    fun openLogin()

    fun openNotifications()

    fun openChildrenList(childrenList:List<ChildModelView>,childListTag:Int=0,childListIndex:String="")

    fun openInstitutionLandingPage(userModelView: UserViewModel)

    fun openChildProfile(childId: String)

}