package com.parent.domain.common.prefs

/**
 * Created by mahmoud on 10/5/17.
 */
interface Prefs {
    var currentUserId: String
    var currentInstitutionId: String
    var isCurrentInstituteSelected :Boolean
    var accessToken: String
    val hasAccessToken: Boolean
    var refreshToken: String
    val hasRefreshToken: Boolean
    var lastUserEmail: String
    var language: String
    var languageDisplay: String
    var languageID : Int
    var selectedLanguageId : Int

}