package eu.parent.android.app.common.di.staff

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.parent.domain.staff.InstituteStaffDataSource
import com.parent.domain.staff.InstituteStaffRepository

/**
 * Created by mahmoud on 11/28/17.
 */
val staffDataModule = Kodein.Module {
    import(staffRemoteDataModule)
    import(staffLocalDataModule)

    bind<InstituteStaffDataSource>("staffDataSource") with singleton {
        InstituteStaffRepository(instance("staffRemoteDataSource"), instance("staffLocalDataSource"))
    }
}