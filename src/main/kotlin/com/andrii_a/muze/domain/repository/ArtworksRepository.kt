package com.andrii_a.muze.domain.repository

import com.andrii_a.muze.domain.model.ArtworkResponse
import com.andrii_a.muze.domain.model.ImageResponse

interface ArtworksRepository {

    suspend fun getArtworks(
        page: Int,
        perPage: Int
    ): List<ArtworkResponse>

    suspend fun getArtwork(id: Int): ArtworkResponse?

    suspend fun getArtworksByArtist(
        artistId: Int,
        page: Int,
        perPage: Int
    ): List<ArtworkResponse>

    suspend fun searchArtworks(
        query: String,
        page: Int,
        perPage: Int
    ): List<ArtworkResponse>

    suspend fun addArtwork(
        name: String,
        year: String?,
        location: String,
        image: ImageResponse,
        description: String,
        artistId: Int
    ): Boolean

    suspend fun updateArtwork(
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