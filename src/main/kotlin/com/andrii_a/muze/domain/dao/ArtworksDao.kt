package com.andrii_a.muze.domain.dao

import com.andrii_a.muze.domain.model.Artwork
import com.andrii_a.muze.domain.model.Image

interface ArtworksDao {

    suspend fun getArtworks(page: Int, perPage: Int): List<Artwork>

    suspend fun getArtwork(id: Int): Artwork?

    suspend fun getArtworksByArtist(artistId: Int, page: Int, perPage: Int): List<Artwork>

    suspend fun getArtworksByQuery(query: String, page: Int, perPage: Int): List<Artwork>

    suspend fun insert(
        name: String,
        year: String?,
        location: String,
        image: Image,
        description: String,
        artistId: Int
    ): Boolean

    suspend fun update(
        id: Int,
        name: String,
        year: String?,
        location: String,
        image: Image,
        description: String,
        artistId: Int
    ): Boolean

    suspend fun deleteAll(): Boolean

    suspend fun deleteById(id: Int): Boolean
}