package com.andrii_a.muze.di

import com.andrii_a.muze.dao.FakeArtistsDao
import com.andrii_a.muze.dao.FakeArtworksDao
import com.andrii_a.muze.data.dao.ArtistsDaoImpl
import com.andrii_a.muze.data.dao.ArtworksDaoImpl
import com.andrii_a.muze.data.repository.ArtistsRepositoryImpl
import com.andrii_a.muze.data.repository.ArtworksRepositoryImpl
import com.andrii_a.muze.domain.dao.ArtistsDao
import com.andrii_a.muze.domain.dao.ArtworksDao
import com.andrii_a.muze.domain.repository.ArtistsRepository
import com.andrii_a.muze.domain.repository.ArtworksRepository
import com.andrii_a.muze.repository.FakeArtistsRepository
import com.andrii_a.muze.repository.FakeArtworksRepository
import com.andrii_a.muze.util.DateValidator
import com.andrii_a.muze.util.LocalDateValidator
import org.koin.dsl.module
import java.time.format.DateTimeFormatter

val testAppModule = module {
    single<DateTimeFormatter> { DateTimeFormatter.ofPattern("d/MM/yyyy") }
    single<DateValidator> { LocalDateValidator(get()) }

    single<ArtistsDao> { FakeArtistsDao() }
    single<ArtworksDao> { FakeArtworksDao() }
    single<ArtistsRepository> { FakeArtistsRepository(get(), get()) }
    single<ArtworksRepository> { FakeArtworksRepository(get()) }
}

