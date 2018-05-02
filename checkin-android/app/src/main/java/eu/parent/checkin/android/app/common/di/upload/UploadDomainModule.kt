package eu.parent.android.app.common.di.upload

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.parent.domain.base.ObservableUseCase
import com.parent.domain.base.SingleUseCase
import com.parent.domain.upload.*

/**
 * Created by mahmoud on 11/21/17.
 */
val uploadDomainModule = Kodein.Module {
    bind<ObservableUseCase<UploadInfo>>("uploadPhotoUseCase") with provider {
        UploadPhoto(instance(), instance(), instance("uploadDataSource"), instance())
    }

    bind<ObservableUseCase<UploadInfo>>("uploadVideoUseCase") with provider {
        UploadVideo(instance(), instance(), instance("uploadDataSource"), instance(),
                instance("uploadVideoThumbnailUseCase"))
    }

    bind<SingleUseCase<String>>("uploadVideoThumbnailUseCase") with provider {
        UploadVideoThumbnail(instance(), instance(), instance("uploadDataSource"), instance())
    }

    bind<ObservableUseCase<UploadInfo>>("uploadDocumentUseCase") with provider {
        UploadDocument(instance(), instance(), instance("uploadDataSource"), instance())
    }

    bind<IValidatePhoto>() with provider {
        ValidatePhoto(instance(), instance())
    }

    bind<VideoValidator>() with provider {
        VideoValidatorImpl(instance(), instance())
    }

    bind<FileValidator>() with provider {
        FileValidatorImpl(instance(), instance())
    }
}