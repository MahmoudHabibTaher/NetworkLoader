package eu.parent.android.app.common.di.countries

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.countries.CountriesDataSource
import com.parent.entities.City
import com.parent.entities.Country
import com.parent.domain.countries.CountriesDao
import com.parent.domain.countries.CountriesLocalDataSource
import com.parent.domain.countries.CountriesRealmDao
import com.parent.domain.realm.entities.CityRealm
import com.parent.domain.realm.entities.CountryRealm
import com.parent.domain.countries.CityRealmModelMapper
import com.parent.domain.countries.CountryRealmModelMapper

/**
 * Created by mahmoud on 11/28/17.
 */
val countriesLocalDataModule = Kodein.Module {
    bind<CountriesDataSource>("countriesLocalDataSource") with provider {
        CountriesLocalDataSource(instance())
    }

    bind<CountriesDao>() with provider {
        CountriesRealmDao(instance(), instance())
    }

    bind<ModelMapper<CountryRealm, Country>>() with provider {
        CountryRealmModelMapper()
    }

    bind<ModelMapper<CityRealm, City>>() with provider {
        CityRealmModelMapper()
    }
}