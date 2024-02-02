package app.duss.kcommerce.core.database.di

import app.duss.kcommerce.core.database.createDatabase
import app.duss.kcommerce.core.database.dao.PokemonDao
import app.duss.kcommerce.core.database.dao.PokemonInfoDao
import app.duss.kcommerce.core.database.sqlDriverFactory
import org.koin.dsl.module

val databaseModule = module {
    factory { sqlDriverFactory() }
    single { createDatabase(driver = get()) }
    single { PokemonDao(pokemonDatabase = get()) }
    single { PokemonInfoDao(pokemonDatabase = get()) }
}