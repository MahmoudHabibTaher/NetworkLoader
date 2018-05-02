package eu.parent.android.app.common.di.nationality

import com.github.salomonbrys.kodein.Kodein

/**
 * Created by mahmoud on 11/28/17.
 */
val nationalityModule = Kodein.Module {
    import(nationalityDataModule)
    import(nationalityDomainModule)
    import(nationalityPresentationModule)


}