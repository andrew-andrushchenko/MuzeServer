package com.andrii_a.muze.domain.dao

import com.andrii_a.muze.domain.model.ArtworkResponse
import com.andrii_a.muze.domain.model.ImageResponse

interface ArtworksDao {

    suspend fun getArtworks(page: Int, perPage: Int): List<ArtworkResponse>

    suspend fun getArtwork(id: Int): ArtworkResponse?

    suspend fun getArtworksByArtist(artistId: Int, page: Int, perPage: Int): List<ArtworkResponse>

    suspend fun getArtworksByQuery(query: String, page: Int, perPage: Int): List<ArtworkResponse>

    suspend fun insert(
        name: String,
        year: String?,
        location: String,
        image: ImageResponse,
        description: String,
        artistId: Int
    ): Boolean

    suspend fun update(
        id: Int,
        name: String,
        year: String?,
        location: String,
        image: ImageResponse,
        description: String,
        artistId: Int
    ): Boolean

    suspend fun deleteAll(): Boolean

    suspend fun deleteById(id: Int): Boolean
}