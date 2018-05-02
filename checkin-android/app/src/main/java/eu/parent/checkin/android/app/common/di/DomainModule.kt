package eu.parent.android.app.common.di

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.provider
import com.parent.domain.executor.BackgroundThreadExecutor
import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import com.parent.domain.executor.UiThreadExecutor

/**
 * Created by mahmoud on 9/11/17.
 */
val domainModule = Kodein.Module {
    bind<ThreadExecutor>() with provider { BackgroundThreadExecutor() }
    bind<PostThreadExecutor>() with provider { UiThreadExecutor() }
}