package com.andrii_a.muze.domain.repository

import com.andrii_a.muze.domain.model.Artist
import com.andrii_a.muze.domain.model.Image

interface ArtistsRepository {

    suspend fun getAllArtists(page: Int, perPage: Int): List<Artist>

    suspend fun getArtist(id: Int): Artist?

    suspend fun searchArtists(query: String, page: Int, perPage: Int): List<Artist>

    suspend fun addArtist(
        name: String,
        bornDateString: String?,
        diedDateString: String?,
        portraitImage: Image,
        bio: String?
    ): Boolean

    suspend fun updateArtist(
        id: Int,
        name: String,
        bornDateString: String?,
        diedDateString: String?,
        portraitImage: Image,
        bio: String?
    ): Boolean

    suspend fun deleteAll(): Boolean

    suspend fun deleteById(id: Int): Boolean
}