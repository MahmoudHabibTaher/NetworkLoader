package eu.parent.android.app.common.di.countries

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.parent.domain.countries.CountriesDataSource
import com.parent.domain.countries.CountriesRepository

/**
 * Created by mahmoud on 11/28/17.
 */
val countriesDataModule = Kodein.Module {
    import(countriesRemoteDataModule)
    import(countriesLocalDataModule)

    bind<CountriesDataSource>("countriesDataSource") with singleton {
        CountriesRepository(instance("countriesRemoteDataSource"), instance("countriesLocalDataSource"))
    }
}