package com.andrii_a.muze.domain.repository

import com.andrii_a.muze.domain.model.ArtistResponse
import com.andrii_a.muze.domain.model.ImageResponse

interface ArtistsRepository {

    suspend fun getAllArtists(page: Int, perPage: Int): List<ArtistResponse>

    suspend fun getArtist(id: Int): ArtistResponse?

    suspend fun searchArtists(query: String, page: Int, perPage: Int): List<ArtistResponse>

    suspend fun addArtist(
        name: String,
        bornDateString: String?,
        diedDateString: String?,
        portraitImage: ImageResponse,
        bio: String?
    ): Boolean

    suspend fun updateArtist(
        id: Int,
        name: String,
        bornDateString: String?,
        diedDateString: String?,
        portraitImage: ImageResponse,
        bio: String?
    ): Boolean

    suspend fun deleteAll(): Boolean

    suspend fun deleteById(id: Int): Boolean
}