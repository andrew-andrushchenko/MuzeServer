package com.andrii_a.muze.di

import com.andrii_a.muze.data.dao.ArtworksDaoImpl
import com.andrii_a.muze.data.repository.ArtworksRepositoryImpl
import com.andrii_a.muze.domain.dao.ArtworksDao
import com.andrii_a.muze.domain.repository.ArtworksRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val artworksModule = module {
    singleOf(::ArtworksDaoImpl) { bind<ArtworksDao>() }
    singleOf(::ArtworksRepositoryImpl) { bind<ArtworksRepository>() }
}