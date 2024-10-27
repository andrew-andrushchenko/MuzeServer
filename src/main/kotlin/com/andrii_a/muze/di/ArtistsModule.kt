package com.andrii_a.muze.di

import com.andrii_a.muze.data.dao.ArtistsDaoImpl
import com.andrii_a.muze.data.repository.ArtistsRepositoryImpl
import com.andrii_a.muze.domain.dao.ArtistsDao
import com.andrii_a.muze.domain.repository.ArtistsRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val artistsModule = module {
    singleOf(::ArtistsDaoImpl) { bind<ArtistsDao>() }
    singleOf(::ArtistsRepositoryImpl) { bind<ArtistsRepository>() }
}