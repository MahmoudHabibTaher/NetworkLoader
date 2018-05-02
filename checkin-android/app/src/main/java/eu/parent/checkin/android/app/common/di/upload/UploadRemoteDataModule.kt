package eu.parent.android.app.common.di.upload

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.common.network.ServiceHelper
import com.parent.domain.upload.*
import com.parent.entities.MediaModel
import com.parent.entities.UploadPhotoResponse
import com.parent.entities.UploadVideoResponse

/**
 * Created by mahmoud on 11/21/17.
 */
val uploadRemoteDataModule = Kodein.Module {
    bind<UploadDataSource>("uploadRemoteDataSource") with provider {
        UploadRemoteDataSource(instance(), instance(), instance())
    }

    bind<UploadApi>() with provider {
        ServiceHelper.createService<UploadApi>(instance())
    }

    bind<ModelMapper<UploadPhotoResponse, MediaModel>>() with provider {
        UploadPhotoResponseModelMapper()
    }

    bind<ModelMapper<UploadVideoResponse, MediaModel>>() with provider {
        UploadVideoResponseModelMapper()
    }
}