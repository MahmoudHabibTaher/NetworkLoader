package com.parent.domain.realm.base

import io.realm.annotations.RealmModule

/**
 * a class that enables integrating REALM into the app through the domain / use-cases module
 *
 * Created by Ahmed Adel Ismail on 2/17/2018.
 */
@RealmModule(library = true, allClasses = true)
class UseCasesModule