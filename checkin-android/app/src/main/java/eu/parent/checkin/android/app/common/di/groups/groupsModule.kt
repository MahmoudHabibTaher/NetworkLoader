package eu.parent.android.app.common.di.groups

import com.github.salomonbrys.kodein.Kodein

/**
 * Created by mahmoud on 11/13/17.
 */
val groupsModule = Kodein.Module {

    import(groupsRealmDataModule)
    import(groupsRemoteDataModule)
    import(groupsPresentationModule)
}