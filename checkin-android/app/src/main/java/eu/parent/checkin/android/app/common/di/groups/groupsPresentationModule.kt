package eu.parent.android.app.common.di.groups

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.provider
import com.parent.entities.Group
import com.parent.entities.GroupView
import com.parent.domain.groups.GroupViewModelMapper
import com.parent.domain.base.mappers.ModelMapper

/**
 * Created by mahmoud on 11/13/17.
 */
val groupsPresentationModule = Kodein.Module {
    bind<ModelMapper<Group, GroupView>>() with provider {
        GroupViewModelMapper()
    }
}