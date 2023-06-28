package com.andrii_a.muze.domain.dao

import com.andrii_a.muze.domain.model.ArtistResponse
import com.andrii_a.muze.domain.model.ImageResponse
import java.time.LocalDate

interface ArtistsDao {

    suspend fun getArtists(page: Int, perPage: Int): List<ArtistResponse>

    suspend fun getArtist(id: Int): ArtistResponse?

    suspend fun getArtistsByQuery(query: String, page: Int, perPage: Int): List<ArtistResponse>

    suspend fun insert(
        name: String,
        bornDate: LocalDate?,
        diedDate: LocalDate?,
        portraitImage: ImageResponse,
        bio: String?
    ): Boolean

    suspend fun update(
        id: Int,
        name: String,
        bornDate: LocalDate?,
        diedDate: LocalDate?,
        portraitImage: ImageResponse,
        bio: String?
    ): Boolean

    suspend fun deleteAll(): Boolean

    suspend fun deleteById(id: Int): Boolean

}