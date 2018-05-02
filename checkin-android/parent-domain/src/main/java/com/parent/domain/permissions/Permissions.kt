package com.parent.domain.permissions

/**
 * Created by mahmoud on 11/9/17.
 */
object Permissions {
    object Authorization {
        const val VIEW_COMPANY_ROLE = "view_company_role"
        const val CREATE_COMPANY_ROLE = "create_company_role"
        const val EDIT_COMPANY_ROLE = "edit_company_role"
        const val DELETE_COMPANY_ROLE = "delete_company_role"
        const val VIEW_INSTITUTE_ROLE = "view_institute_role"
        const val CREATE_INSTITUTE_ROLE = "create_institute_role"
        const val EDIT_INSTITUTE_ROLE = "edit_company_role"
        const val DELETE_INSTITUTE_ROLE = "delete_company_role"
        const val CREATE_INSTITUTE_USER = "create_institute_user"
        const val ASSIGN_ROLE_TO_USER = "assign_role_to_user"
        const val DELETE_USER_FROM_ROLE = "delete_user_from_role"
        const val GET_PERMISSIONS = "get_permissions"
    }

    object Calendar {
        const val VIEW_INSTITUTION_CALENDAR = "view_institute_calendar"
        const val VIEW_MY_CALENDAR = "view_my_calendar"


    }

    object Activities {
        const val CREATE_ACTIVITY = "create_activity"
        const val EDIT_ACTIVITY = "edit_activity"
        const val DELETE_ACTIVITY = "delete_activity"
        const val VIEW_ACTIVITY = "view_activity"

    }

    object Events {
        const val CREATE_EVENT = "create_event"
        const val EDIT_EVENT = "edit_event"
        const val DELETE_EVENT = "delete_event"
        const val VIEW_EVENT_ADMIN = "view_event_admin"
    }

    object Holidays {
        const val CREATE_HOLIDAY = "create_holiday"
        const val EDIT_HOLIDAY = "edit_holiday"
        const val DELETE_HOLIDAY = "delete_holiday"
        const val LIST_HOLIDAY = "list_holidays"
        const val VIEW_ADMIN_HOLIDAY = "view_holiday_admin"

    }

    object InstituteOptions {
        const val VIEW_INSTITUTE_DETAILS = "view_institution"
        const val EDIT_INSTITUTE_DETAILS = "edit_institution"
        const val VIEW_ADMIN_DASHBOARD = "view_admin_dashboard"
        const val MANAGE_INSTITUTE_OPTIONS = "manage_institute_options"
        const val MANAGE_INSTITUTE_TOOLS = "manage_institute_tools"
    }

    object Classes {
        const val ADD_CLASS = "add_class"
        const val EDIT_CLASS = "edit_class"
        const val VIEW_ADMIN_DASHBOARD = "view_admin_dashboard"
        const val ADD_CHILD_TO_CLASS = "add_child_to_class"
        const val VIEW_CLASS_DETAILS = "view_class_details"
    }

    object ChildProfile {
        const val VIEW_CHILD_PROFILE = "view_child_profile"
        const val VIEW_CHILD_GALLERY = "view_child_gallery"
        const val VIEW_CHILD_LEAVES = "view_child_leaves_graph"
        const val VIEW_CHILD_DAILY_REPORT = "view_child_daily_report"
        const val VIEW_CHILD_PROFILE_NEWS_FEED = "view_child_profile_news_feed"
        const val VIEW_CHILD_CHILD_CONTACTS = "view_child_contacts"
        const val VIEW_CHILD_CALENDAR = "view_child_calendar"
        const val VIEW_CHILD_HEALTH_INFO = "view_child_health_info"
        const val VIEW_CHILD_PICKUP_INFO = "view_child_pickup_info"
        const val EDIT_CHILD_PROFILE = "edit_child_profile_information"
        const val VIEW_CHILD_PERMISSIONS = "view_child_permissions_list"
        const val EDIT_CHILD_PERMISSION_REPLY = "edit_child_permission_reply"
        const val ADD_CHILD_PERMISSIONS = "add_permission_to_child"
        const val REPORT_EDIT_CHILD_ACTIONS_STATUS = "report_edit_child_actions_status"
        const val VIEW_CHILD_PROFILE_SENSETIVE_INFORMATION = "view_child_profile_sensitive_information"
        const val EDIT_CHILD_PROFILE_SENSETIVE_INFORMATION = "edit_child_profile_sensitive_information"
        const val VIEW_CHILD_PROFILE_PERSONAL_INFORMATION = "view_child_profile_personal_information"
        const val EDIT_CHILD_PROFILE_PERSONAL_INFORMATION = "edit_child_profile_personal_information"
    }

    object ChildContact {
        const val VIEW_CHILD_CONTACT_PROFILE = "view_child_contact_profile"
        const val EDIT_CHILD_CONTACT_PROFILE = "edit_child_contact_information"
        const val VIEW_CHILD_CONTACT_PROFILE_SENSETIVE_INFORMATION = "view_child_contact_sensitive_information"
        const val EDIT_CHILD_CONTACT_PROFILE_BASIC_INFORMATION = "edit_child_contact_profile_basic_information"
        const val EDIT_CHILD_CONTACT_PROFILE_SENSETIVE_INFORMATION = "edit_child_contact_sensitive_information"
    }

    object NewsFeed {
        const val VIEW_MY_NEWSFEED= "view_my_newsfeed"
        const val VIEW_INSTITUTION_NEWSFEED = "view_institute_newsfeed"
        const val POST_NEWS_FEED = "post_news_feed"
        const val DELETE_NEWSFEED = "delete_newsfeed"
        const val EDIT_NEWSFEED = "edit_newsfeed"
    }

}