package eu.parent.android.app.common.di.staff

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.staff.InstituteStaffDataSource
import com.parent.domain.staff.InstituteStaffDao
import com.parent.domain.staff.InstituteStaffLocalDataSource
import com.parent.domain.staff.InstituteStaffRealmDao
import com.parent.domain.realm.entities.InstituteStaffModelRealm
import com.parent.domain.staff.InstituteStaffRealmModelMapper
import com.parent.entities.InstituteStaffModel


/**
 * Created by mahmoud on 11/28/17.
 */
val staffLocalDataModule = Kodein.Module {
    bind<InstituteStaffDataSource>("staffLocalDataSource") with provider {
        InstituteStaffLocalDataSource(instance())
    }

    bind<InstituteStaffDao>() with provider {
        InstituteStaffRealmDao(instance())
    }

    bind<ModelMapper<InstituteStaffModelRealm, InstituteStaffModel>>() with provider {
        InstituteStaffRealmModelMapper()
    }

}