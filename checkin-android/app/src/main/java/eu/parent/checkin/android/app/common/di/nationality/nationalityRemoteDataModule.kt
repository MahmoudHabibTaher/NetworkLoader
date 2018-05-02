package eu.parent.android.app.common.di.nationality

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.common.network.ServiceHelper
import com.parent.domain.nationality.NationalityDataSource
import com.parent.entities.Nationality
import com.parent.domain.nationality.NationalityApi
import com.parent.domain.nationality.NationalityRemoteDataSource
import com.parent.entities.NationalityRemote
import com.parent.domain.nationality.mappers.NationalityRemoteModelMapper

/**
 * Created by mahmoud on 11/28/17.
 */
val nationalityRemoteDataModule = Kodein.Module {
    bind<NationalityDataSource>("nationalityRemoteDataSource") with provider {
        NationalityRemoteDataSource(instance(), instance())
    }

    bind<NationalityApi>() with provider {
        ServiceHelper.createService<NationalityApi>(instance())
    }

    bind<ModelMapper<NationalityRemote, Nationality>>() with provider {
        NationalityRemoteModelMapper()
    }
}