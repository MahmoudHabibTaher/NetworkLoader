package eu.parent.android.app.common.di.nationality

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.parent.domain.nationality.NationalityDataSource
import com.parent.domain.nationality.NationalityRepository

/**
 * Created by mahmoud on 11/28/17.
 */
val nationalityDataModule = Kodein.Module {
    import(nationalityRemoteDataModule)
    import(nationalityLocalDataModule)

    bind<NationalityDataSource>("nationalityDataSource") with singleton {
        NationalityRepository(instance("nationalityRemoteDataSource"), instance("nationalityLocalDataSource"))
    }
}