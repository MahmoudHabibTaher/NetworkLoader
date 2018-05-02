package eu.parent.android.app.common.di.datetime

import com.github.salomonbrys.kodein.*
import com.parent.domain.datetime.DateTimeConverter
import com.parent.domain.datetime.IDateTimeConverter
import com.parent.domain.datetime.DateFormatter
import com.parent.domain.datetime.DateTimeManager
import com.parent.domain.datetime.IDateTimeManager

/**
 * Created by Raed on 9/22/17.
 */
val dateTimeModule = Kodein.Module {
    bind<IDateTimeConverter>() with provider {
        DateTimeConverter()
    }

    bind<DateFormatter>() with provider {
        DateFormatter(instance())
    }

    bind<IDateTimeManager>() with singleton {
        DateTimeManager(instance())
    }
}