package com.andrii_a.muze.repository

import com.andrii_a.muze.domain.dao.ArtistsDao
import com.andrii_a.muze.domain.model.Artist
import com.andrii_a.muze.domain.model.Image
import com.andrii_a.muze.domain.repository.ArtistsRepository
import com.andrii_a.muze.util.DateValidator

class FakeArtistsRepository(
    private val dao: ArtistsDao,
    private val dateValidator: DateValidator
) : ArtistsRepository {

    override suspend fun getAllArtists(page: Int, perPage: Int): List<Artist> {
        TODO("Not yet implemented")
    }

    override suspend fun getArtist(id: Int): Artist? {
        TODO("Not yet implemented")
    }

    override suspend fun searchArtists(query: String, page: Int, perPage: Int): List<Artist> {
        TODO("Not yet implemented")
    }

    override suspend fun addArtist(
        name: String,
        bornDateString: String?,
        diedDateString: String?,
        portraitImage: Image,
        bio: String?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun updateArtist(
        id: Int,
        name: String,
        bornDateString: String?,
        diedDateString: String?,
        portraitImage: Image,
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