package com.parent.domain.realm.entities

import io.realm.RealmList
import io.realm.RealmObject

/**
 * Created by mahmoud on 11/27/17.
 */
open class EventReplySectionRealm(var name: String = "",
                                  var type: String = "",
                                  var replies: RealmList<EventReplyRealm> = RealmList()) : RealmObject()