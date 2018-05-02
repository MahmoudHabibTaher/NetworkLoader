package com.parent.domain.realm.entities

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by mahmoud on 10/25/17.
 */
open class RoleSectionRealm(@PrimaryKey var name: String = "",
                            var roles: RealmList<RoleRealm> = RealmList()) : RealmObject()