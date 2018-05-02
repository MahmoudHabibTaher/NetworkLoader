package com.parent.domain.realm.base

import io.realm.DynamicRealm
import io.realm.RealmMigration

class Migration : RealmMigration {
    /*This method is used to migrate realm database by modifyng the schema based on the schema
               version
       e.g:
       var version = oldVersion
       val schema = realm?.schema
       if (version == 1L) {
           schema?.apply {
               create("Person")
                       .addField("id", String::class.java)
                       .addField("name", String::class.java)
           }

           version += 1
       }

       if (version == 2L) {
           schema?.apply {
               get("Person")
                       ?.removeField("name")
                       ?.addField("firstName", String::class.java)
                       ?.addField("lastName", String::class.java)
                       ?.addRealmObjectField("relative", get("Person")!!)
           }

           version += 1
       }
       */
    override fun migrate(realm: DynamicRealm?, oldVersion: Long, newVersion: Long) {

    }
}