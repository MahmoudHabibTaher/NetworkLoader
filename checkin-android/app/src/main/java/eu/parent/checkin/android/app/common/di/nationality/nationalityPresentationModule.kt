package eu.parent.android.app.common.di.nationality

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.provider
import com.parent.domain.base.mappers.ModelMapper
import com.parent.entities.Nationality
import com.parent.entities.NationalityView
import com.parent.domain.nationality.mappers.NationalityViewModelMapper

/**
 * Created by mahmoud on 12/2/17.
 */
val nationalityPresentationModule = Kodein.Module {
    bind<ModelMapper<Nationality, NationalityView>>() with provider {
        NationalityViewModelMapper()
    }
}