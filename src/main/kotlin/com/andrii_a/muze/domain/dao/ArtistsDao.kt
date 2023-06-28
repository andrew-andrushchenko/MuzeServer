package com.andrii_a.muze.domain.dao

import com.andrii_a.muze.domain.model.Artist
import com.andrii_a.muze.domain.model.Image
import java.time.LocalDate

interface ArtistsDao {

    suspend fun getArtists(page: Int, perPage: Int): List<Artist>

    suspend fun getArtist(id: Int): Artist?

    suspend fun getArtistsByQuery(query: String, page: Int, perPage: Int): List<Artist>

    suspend fun insert(
        name: String,
        bornDate: LocalDate?,
        diedDate: LocalDate?,
        portraitImage: Image,
        bio: String?
    ): Boolean

    suspend fun update(
        id: Int,
        name: String,
        bornDate: LocalDate?,
        diedDate: LocalDate?,
        portraitImage: Image,
        bio: String?
    ): Boolean

    suspend fun deleteAll(): Boolean

    suspend fun deleteById(id: Int): Boolean

}