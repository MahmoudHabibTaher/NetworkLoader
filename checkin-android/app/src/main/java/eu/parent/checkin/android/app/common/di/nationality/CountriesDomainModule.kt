package eu.parent.android.app.common.di.nationality

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.parent.domain.base.SingleUseCase
import com.parent.entities.Nationality
import com.parent.domain.nationality.LoadNationality

/**
 * Created by mahmoud on 11/28/17.
 */
val nationalityDomainModule = Kodein.Module {
    bind<SingleUseCase<List<Nationality>>>("loadNationalityUseCase") with provider {
        LoadNationality(instance(), instance(), instance("nationalityDataSource"))
    }

}