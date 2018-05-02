package com.parent.domain.upload

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.parent.domain.base.Params
import com.parent.domain.base.SingleUseCase
import com.parent.domain.executor.test.TestExecutor
import com.parent.domain.executor.test.TestPostExecutor
import com.parent.entities.exceptions.FileExtensionNotSupportedException
import io.reactivex.Completable
import io.reactivex.Single
import org.amshove.kluent.any
import org.amshove.kluent.mock
import org.junit.Before
import org.junit.Test
import java.io.File

/**
 * Created by habib on 2/24/18.
 */
class UploadVideoTest {
    private val uploadDataSource = mock<UploadDataSource>()

    private val videoValidator = mock<VideoValidator> {
        on { validate(any()) } doReturn Completable.complete()
    }

    private val uploadVideoThumbnail = mock<SingleUseCase<String>>(){
        on { getSingle(any()) } doReturn Single.just("thumbnailUrl")
    }

    private lateinit var uploadVideo: UploadVideo

    @Before
    fun setUp() {
        uploadVideo = UploadVideo(TestExecutor(), TestPostExecutor(), uploadDataSource,
                videoValidator, uploadVideoThumbnail)
    }

    @Test
    fun validateVideoExtension() {
        val extensions = arrayOf("AVI", "ASF", "MOV", "QT", "AVCHD", "FLV", "SWF", "MPG", "MPEG",
                "MP4", "MPEG-4", "WMV", "H.264", "DIVX", "invalid")
        val files = extensions.mapIndexed { index, ext ->
            File("file.$ext").apply {
                if (index == extensions.size - 1) {
                    whenever(videoValidator.validate(this)).doReturn(
                            Completable.error(FileExtensionNotSupportedException("Only ${UploadSettings.VIDEO_VALID_EXTENSIONS} are allowed.",
                                    this)))
                }
            }
        }

        files.forEachIndexed { index, file ->
            val observer = uploadVideo.getObservable(Params(UploadConstants.PARAM_FILE to file,
                    UploadConstants.PARAM_TYPE to UploadConstants.Type.NEWSFEED)).test()
            observer.awaitTerminalEvent()
            if (index == extensions.size - 1) {
                observer.assertError(FileExtensionNotSupportedException::class.java)
                observer.assertErrorMessage("Only ${UploadSettings.VIDEO_VALID_EXTENSIONS} are allowed.")
            } else {
                observer.assertNoErrors()
            }
        }
    }
}