package eu.parent.android.app.common.di.groups

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.provider
import com.parent.domain.base.mappers.ModelMapper
import com.parent.entities.Group
import com.parent.domain.realm.entities.GroupRealm
import com.parent.domain.groups.GroupRealmModelMapper

/**
 * Created by mahmoud on 11/13/17.
 */
val groupsRealmDataModule = Kodein.Module {

    bind<ModelMapper<GroupRealm, Group>>() with provider {
        GroupRealmModelMapper()
    }
}