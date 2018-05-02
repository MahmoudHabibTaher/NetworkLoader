package eu.parent.android.app.common.di.upload

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.parent.domain.upload.UploadDataSource
import com.parent.domain.upload.UploadRepository

/**
 * Created by mahmoud on 11/21/17.
 */
val uploadDataModule = Kodein.Module {
    import(uploadRemoteDataModule)

    bind<UploadDataSource>("uploadDataSource") with provider {
        UploadRepository(instance("uploadRemoteDataSource"))
    }
}