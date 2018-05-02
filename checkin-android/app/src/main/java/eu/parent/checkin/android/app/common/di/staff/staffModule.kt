package eu.parent.android.app.common.di.staff

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.parent.domain.base.BaseSingleUseCase
import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.staff.LoadInstituteStaffList
import com.parent.entities.InstituteStaffModel
import com.parent.entities.InstituteStaffModelView
import com.parent.domain.staff.InstituteStaffViewModelMapper

/**
 * Created by mahmoud on 9/25/17.
 */
val staffModule = Kodein.Module {

    import(staffDataModule)

    bind<BaseSingleUseCase<List<InstituteStaffModel>>>("loadInstituteStaffUseCase") with provider {
        LoadInstituteStaffList(instance(), instance(), instance("staffDataSource"))
    }

    bind<ModelMapper<InstituteStaffModelView, InstituteStaffModel>>() with provider {
        InstituteStaffViewModelMapper()
    }


}