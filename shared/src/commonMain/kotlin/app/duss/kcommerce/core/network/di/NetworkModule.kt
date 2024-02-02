package app.duss.kcommerce.core.network.di

import app.duss.kcommerce.core.network.client.PokemonClient
import app.duss.kcommerce.core.network.createHttpClient
import org.koin.core.module.Module
import org.koin.dsl.module

val networkModule: (enableLogging: Boolean) -> Module get() = { enableLogging ->
    module {
        single { createHttpClient(enableLogging) }
        single { PokemonClient(httpClient = get()) }
    }
}