package eu.parent.android.app.common.di.countries

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.parent.domain.base.SingleUseCase
import com.parent.entities.City
import com.parent.entities.Country
import com.parent.domain.countries.LoadCountries
import com.parent.domain.countries.LoadCountryCities

/**
 * Created by mahmoud on 11/28/17.
 */
val countriesDomainModule = Kodein.Module {
    bind<SingleUseCase<List<Country>>>("loadCountriesUseCase") with provider {
        LoadCountries(instance(), instance(), instance("countriesDataSource"))
    }

    bind<SingleUseCase<List<City>>>("loadCitiesUseCase") with provider {
        LoadCountryCities(instance(), instance(), instance("countriesDataSource"))
    }
}