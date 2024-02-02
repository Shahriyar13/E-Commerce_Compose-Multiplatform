package app.duss.kcommerce.data.di

import app.duss.kcommerce.data.repository.PokemonRepository
import app.duss.kcommerce.data.repository.PokemonRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single<PokemonRepository> { PokemonRepositoryImpl() }
}