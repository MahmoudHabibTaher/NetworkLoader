package eu.parent.android.app.common.di.countries

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.common.network.ServiceHelper
import com.parent.domain.countries.CountriesDataSource
import com.parent.entities.City
import com.parent.entities.Country
import com.parent.domain.countries.CountriesApi
import com.parent.domain.countries.CountriesRemoteDataSource
import com.parent.entities.CityRemote
import com.parent.entities.CountryRemote
import com.parent.domain.countries.CityRemoteModelMapper
import com.parent.domain.countries.CountryRemoteModelMapper

/**
 * Created by mahmoud on 11/28/17.
 */
val countriesRemoteDataModule = Kodein.Module {
    bind<CountriesDataSource>("countriesRemoteDataSource") with provider {
        CountriesRemoteDataSource(instance(), instance(), instance())
    }

    bind<CountriesApi>() with provider {
        ServiceHelper.createService<CountriesApi>(instance())
    }

    bind<ModelMapper<CountryRemote, Country>>() with provider {
        CountryRemoteModelMapper()
    }

    bind<ModelMapper<CityRemote, City>>() with provider {
        CityRemoteModelMapper()
    }
}