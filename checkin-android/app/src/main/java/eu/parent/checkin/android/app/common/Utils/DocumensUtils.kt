package eu.parent.android.app.common.Utils

import com.parent.entities.MediaMimeTypes
import eu.parent.android.app.R

/**
 * Created by zMabrook on 03/04/18.
 */
class DocumentsUtils{
    companion object {
        fun getDocIcon(mimeType: String): Int =
                when (mimeType) {
                    MediaMimeTypes.Documents.DOC.mime,
                    MediaMimeTypes.Documents.DOCX.mime -> R.drawable.ic_doc_word
                    MediaMimeTypes.Documents.XLS.mime,
                    MediaMimeTypes.Documents.XLSX.mime -> R.drawable.ic_doc_xls
                    MediaMimeTypes.Documents.CSV.mime -> R.drawable.ic_doc_csv
                    MediaMimeTypes.Documents.PPT.mime,
                    MediaMimeTypes.Documents.PPTX.mime -> R.drawable.ic_doc_powerpoint
                    MediaMimeTypes.Documents.PDF.mime -> R.drawable.ic_doc_pdf
                    MediaMimeTypes.Documents.TXT.mime -> R.drawable.ic_doc_txt
                    MediaMimeTypes.Documents.EPUP.mime -> R.drawable.ic_doc_epub
                    else -> R.drawable.ic_doc_word
                }
    }
}