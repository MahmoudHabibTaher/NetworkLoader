package com.bigo.networkloader.demo.core.network.di

import com.bigo.networkloader.NetworkLoaderImpl
import com.bigo.networkloader.cache.Cache
import com.bigo.networkloader.cache.CacheImpl
import com.bigo.networkloader.http.HttpClient
import com.bigo.networkloader.http.HttpClientImpl
import com.bigo.networkloader.http.HttpResponse
import com.bigo.networkloader.http.builder.ConnectionBuilder
import com.bigo.networkloader.http.builder.HttpConnectionBuilder
import com.bigo.networkloader.image.BitmapParser
import com.bigo.networkloader.image.ImageLoader
import com.bigo.networkloader.NetworkLoader
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

val networkModule = Kodein.Module("networkModule") {
    bind<ImageLoader>() with singleton {
        ImageLoader(instance(), BitmapParser())
    }

    bind<NetworkLoader>() with singleton {
        NetworkLoaderImpl(instance(), instance())
    }

    bind<HttpClient>() with provider {
        HttpClientImpl(instance())
    }

    bind<ConnectionBuilder>() with provider {
        HttpConnectionBuilder()
    }

    bind<Cache<String, HttpResponse<*>>>() with singleton {
        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
        val cacheSize = maxMemory / 8
        CacheImpl(cacheSize)
    }
}