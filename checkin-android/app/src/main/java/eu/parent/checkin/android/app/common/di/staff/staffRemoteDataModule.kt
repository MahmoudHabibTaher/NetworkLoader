package eu.parent.android.app.common.di.staff

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.common.network.ServiceHelper
import com.parent.domain.staff.InstituteStaffDataSource
import com.parent.domain.staff.InstituteStaffApi
import com.parent.domain.staff.InstituteStaffRemoteDataSource
import com.parent.entities.InstituteStaffModelRemote
import com.parent.domain.staff.InstituteStaffRemoteModelMapper
import com.parent.entities.InstituteStaffModel


/**
 * Created by mahmoud on 11/28/17.
 */
val staffRemoteDataModule = Kodein.Module {
    bind<InstituteStaffDataSource>("staffRemoteDataSource") with provider {
        InstituteStaffRemoteDataSource(instance(), instance())
    }

    bind<InstituteStaffApi>() with provider {
        ServiceHelper.createService<InstituteStaffApi>(instance())
    }

    bind<ModelMapper<InstituteStaffModelRemote, InstituteStaffModel>>() with provider {
        InstituteStaffRemoteModelMapper()
    }
}