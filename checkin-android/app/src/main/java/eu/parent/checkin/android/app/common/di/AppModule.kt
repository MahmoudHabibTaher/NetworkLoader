package eu.parent.android.app.common.di

import com.github.salomonbrys.kodein.Kodein
import eu.parent.android.app.Tools.list.di.appsDataModule
import eu.parent.android.app.Tools.list.di.appsDomainModule
import eu.parent.android.app.Tools.list.di.appsListModule
import eu.parent.android.app.accountsettings.di.accountSettingModule
import eu.parent.android.app.activity.di.activityDataModule
import eu.parent.android.app.activity.di.createActivityModule
import eu.parent.android.app.activity.di.editActivityModule
import eu.parent.android.app.activity.di.loadActivityDetailsModule
import eu.parent.android.app.calendar.di.calendarDataModule
import eu.parent.android.app.calendar.di.viewCalendarModule
import eu.parent.android.app.children.di.childrenModule
import eu.parent.android.app.classes.di.classesDataModule
import eu.parent.android.app.classes.di.classesDomainModule
import eu.parent.android.app.classes.di.classesPresentationModule
import eu.parent.android.app.classes.di.viewInstituteClassesModule
import eu.parent.android.app.common.di.countries.countriesDataModule
import eu.parent.android.app.common.di.countries.countriesDomainModule
import eu.parent.android.app.common.di.countries.countriesPresentationModule
import eu.parent.android.app.common.di.datetime.dateTimeModule
import eu.parent.android.app.common.di.groups.groupsModule
import eu.parent.android.app.common.di.nationality.nationalityModule
import eu.parent.android.app.common.di.staff.staffModule
import eu.parent.android.app.common.di.upload.uploadDataModule
import eu.parent.android.app.common.di.upload.uploadDomainModule
import eu.parent.android.app.events.details.user.replyevent.di.replyToEventsModule
import eu.parent.android.app.events.di.*
import eu.parent.android.app.gallery.di.galleryModule
import eu.parent.android.app.holiday.di.*
import eu.parent.android.app.institutions.di.*
import eu.parent.android.app.location.di.locationModule
import eu.parent.android.app.main.di.mainModule
import eu.parent.android.app.newsfeed.di.newsFeedDataModule
import eu.parent.android.app.newsfeed.di.newsFeedModule
import eu.parent.android.app.notifications.di.notificationModule
import eu.parent.android.app.notifications.di.notificationsDataModule
import eu.parent.android.app.options.di.optionsDataModule
import eu.parent.android.app.options.di.optionsDomainModule
import eu.parent.android.app.options.di.optionsModule
import eu.parent.android.app.overview.di.classesModule
import eu.parent.android.app.overview.di.dashboardClassesDomainModule
import eu.parent.android.app.overview.di.overviewModule
import eu.parent.android.app.overview.di.statusDetailsModule
import eu.parent.android.app.permissions.di.permissionsDataModule
import eu.parent.android.app.permissions.di.permissionsDomainModule
import eu.parent.android.app.permissions.di.permissionsManagerModule
import eu.parent.android.app.permissions.di.permissionsModule
import eu.parent.android.app.recipients.di.recipientsDataModule
import eu.parent.android.app.recipients.di.recipientsSearchDomainModule
import eu.parent.android.app.recipients.di.recipientsSearchModule
import eu.parent.android.app.roles.di.*
import eu.parent.android.app.splash.di.splashModule
import eu.parent.android.app.deeplink.di.deepLinkModule
import eu.parent.android.app.language.di.languageModule
import eu.parent.android.app.status.di.statusDataModule
import eu.parent.android.app.user.di.checkActivationLinkModule
import eu.parent.android.app.user.di.createPasswordModule
import eu.parent.android.app.user.di.userDataModule
import eu.parent.android.app.user.di.userDomainModule
import eu.parent.android.app.user.di.forgetPasswordModule
import eu.parent.android.app.user.di.loginModule
import eu.parent.android.app.user.di.checkResetLinkModule
import eu.parent.android.app.user.di.requestResetPasswordLinkModule
import eu.parent.android.app.user.di.resetPasswordModule
import eu.parent.android.app.user.di.requestActivationLinkModule
import eu.parent.android.app.user.di.userSessionModule
import eu.parent.android.app.status.di.statusModule
import eu.parent.android.app.status.meal.di.reportMealModule

/**
 * Created by mahmoud on 9/22/17.
 */
val appModule = Kodein.Module {
    import(domainModule)
    import(networkModule)
    import(realmModule)

    import(dashboardClassesDomainModule)
    import(overviewModule)
    import(statusDetailsModule)
    import(locationModule)

    import(countriesDataModule)
    import(countriesDomainModule)
    import(countriesPresentationModule)

    import(timezonesDataModule)
    import(timezonesDomainModule)
    import(timezonesPresentationModule)

    import(classesDataModule)
    import(classesDomainModule)
    import(classesPresentationModule)
    import(viewInstituteClassesModule)

    import(statusModule)
    import(statusDataModule)

    import(childrenModule)

    import(groupsModule)

    import(fileHelperModule)

    import(uploadDataModule)
    import(uploadDomainModule)

    import(recipientsDataModule)
    import(userDataModule)
    import(permissionsDataModule)
    import(rolesDataModule)
    import(optionsDataModule)
    import(appsDataModule)
    import(eventsDataModule)

    import(userSessionModule)

    import(dateTimeModule)

    import(recipientsSearchDomainModule)
    import(userDomainModule)
    import(permissionsDomainModule)
    import(rolesDomainModule)
    import(optionsDomainModule)
    import(appsDomainModule)
    import(createEditEventDomainModule)
    import(deleteEventDomainModule)
    import(eventAdminDetailsDomainModule)

    import(permissionsManagerModule)

    import(splashModule)
    import(mainModule)

    import(loginModule)
    import(checkActivationLinkModule)
    import(checkResetLinkModule)
    import(forgetPasswordModule)
    import(createPasswordModule)
    import(requestActivationLinkModule)
    import(requestResetPasswordLinkModule)
    import(resetPasswordModule)

    import(optionsModule)
    import(appsListModule)

    import(permissionsModule)

    import(recipientsSearchModule)

    import(rolesListModule)
    import(cloneRoleModule)
    import(createRoleModule)
    import(editRoleModule)
    import(viewRoleUsersModule)

    import(newsFeedDataModule)
    import(newsFeedModule)

    import(viewCalendarModule)
    import(calendarDataModule)

    import(createActivityModule)
    import(activityDataModule)
    import(loadActivityDetailsModule)
    import(editActivityModule)

    import(closingDaysDataModule)

    import(holidayDataModule)
    import(holidayDetailsModule)
    import(replyToHolidayModule)
    import(addHolidayDomainModule)
    import(addHolidayPresentationModule)
    import(holidaysListDomainModule)
    import(holidaysListModule)

    import(notificationModule)

    import(notificationsDataModule)

    import(institutionsDataModule)
    import(institutionsPresentationModule)
    import(instituteOptionsDataModule)
    import(instituteOptionsDomainModule)
    import(instituteOptionsPresentationModule)

    import(createEditEventModule)
    import(deleteEventModule)
    import(eventDetailsModule)
    import(eventsModule)
    import(replyToEventsModule)
    import(eventAdminDetailsModule)

    import(nationalityModule)
    import(staffModule)

    import(classesModule)

    import(galleryModule)

    import(deepLinkModule)

    import(languageModule)

    import(accountSettingModule)
}