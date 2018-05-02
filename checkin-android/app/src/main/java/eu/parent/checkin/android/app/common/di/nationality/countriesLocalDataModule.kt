package eu.parent.android.app.common.di.nationality

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.nationality.NationalityDataSource
import com.parent.entities.Nationality
import com.parent.domain.nationality.NationalityDao
import com.parent.domain.nationality.NationalityLocalDataSource
import com.parent.domain.nationality.NationalityRealmDao
import com.parent.domain.realm.entities.NationalityRealm
import com.parent.domain.nationality.mappers.NationalityRealmModelMapper

/**
 * Created by mahmoud on 11/28/17.
 */
val nationalityLocalDataModule = Kodein.Module {
    bind<NationalityDataSource>("nationalityLocalDataSource") with provider {
        NationalityLocalDataSource(instance())
    }

    bind<NationalityDao>() with provider {
        NationalityRealmDao(instance())
    }

    bind<ModelMapper<NationalityRealm, Nationality>>() with provider {
        NationalityRealmModelMapper()
    }

}