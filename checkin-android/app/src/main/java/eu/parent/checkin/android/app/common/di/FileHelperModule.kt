package eu.parent.android.app.common.di

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.parent.domain.common.io.FileHelper
import com.parent.domain.common.io.IFileHelper

/**
 * Created by mahmoud on 11/21/17.
 */
val fileHelperModule = Kodein.Module {
    bind<IFileHelper>() with singleton {
        FileHelper(instance())
    }
}