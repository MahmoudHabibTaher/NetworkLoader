package eu.parent.android.app.common.di.countries

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.provider
import com.parent.domain.base.mappers.ModelMapper
import com.parent.entities.City
import com.parent.entities.Country
import com.parent.entities.CityView
import com.parent.entities.CountryView
import com.parent.domain.countries.CityViewModelMapper
import com.parent.domain.countries.CountryViewModelMapper

/**
 * Created by mahmoud on 12/2/17.
 */
val countriesPresentationModule = Kodein.Module {
    bind<ModelMapper<Country, CountryView>>() with provider {
        CountryViewModelMapper()
    }

    bind<ModelMapper<City, CityView>>() with provider {
        CityViewModelMapper()
    }
}