package eu.parent.android.app.common.di

import com.github.salomonbrys.kodein.*
import com.parent.domain.common.network.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by mahmoud on 9/22/17.
 */
val networkModule = Kodein.Module {
    bind<NetworkManager>() with singleton {
        NetworkManager(instance())
    }

    bind<Retrofit>() with singleton {
        Retrofit.Builder()
                .baseUrl(instance<String>("baseUrl"))
                .client(instance())
                .addCallAdapterFactory(instance())
                .addConverterFactory(instance())
                .build()
    }

    bind<CallAdapter.Factory>() with provider {
        RxErrorHandlingCallAdapterFactory.create(instance())
    }

    bind<Converter.Factory>() with provider {
        GsonConverterFactory.create()
    }

    bind<OkHttpClient>() with provider {
        OkHttpClientHelper.buildClient(instance("connectionTimeOut"),
                instance("readTimeOut"),
                instance("writeTimeOut"),
                instance(),
                instance())
    }

    bind<HeadersProvider>() with provider {
        GenericHeadersProvider(instance())
    }

    bind<NetworkErrorHandler>() with provider {
        GenericNetworkErrorHandler(instance())
    }

    constant("baseUrl") with ApiConstants.BASE_URL
    constant("connectionTimeOut") with NetworkConstants.CONNECT_TIME_OUT
    constant("readTimeOut") with NetworkConstants.READ_TIME_OUT
    constant("writeTimeOut") with NetworkConstants.WRITE_TIME_OUT

    bind<Interceptor>() with provider {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}