package app.duss.kcommerce.core.di

import app.duss.kcommerce.core.database.di.databaseModule
import app.duss.kcommerce.core.network.di.networkModule
import app.duss.kcommerce.data.di.dataModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(enableNetworkLogs: Boolean = false, appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            databaseModule,
            networkModule(enableNetworkLogs),
            dataModule
        )
    }