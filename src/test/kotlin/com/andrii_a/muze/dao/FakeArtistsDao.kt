package com.andrii_a.muze.dao

import com.andrii_a.muze.domain.dao.ArtistsDao
import com.andrii_a.muze.domain.model.ArtistResponse
import com.andrii_a.muze.domain.model.ImageResponse
import java.time.LocalDate

class FakeArtistsDao : ArtistsDao {

    override suspend fun getArtists(page: Int, perPage: Int): List<ArtistResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getArtist(id: Int): ArtistResponse? {
        TODO("Not yet implemented")
    }

    override suspend fun getArtistsByQuery(query: String, page: Int, perPage: Int): List<ArtistResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun insert(
        name: String,
        bornDate: LocalDate?,
        diedDate: LocalDate?,
        portraitImage: ImageResponse,
        bio: String?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun update(
        id: Int,
        name: String,
        bornDate: LocalDate?,
        diedDate: LocalDate?,
        portraitImage: ImageResponse,
        bio: String?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteById(id: Int): Boolean {
        TODO("Not yet implemented")
    }
}