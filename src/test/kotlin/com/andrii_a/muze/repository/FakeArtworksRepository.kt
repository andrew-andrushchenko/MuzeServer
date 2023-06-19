package com.andrii_a.muze.repository

import com.andrii_a.muze.domain.dao.ArtworksDao
import com.andrii_a.muze.domain.model.ArtworkResponse
import com.andrii_a.muze.domain.repository.ArtworksRepository

class FakeArtworksRepository(private val dao: ArtworksDao) : ArtworksRepository {

    override suspend fun getArtworks(page: Int, perPage: Int): List<ArtworkResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getArtwork(id: Int): ArtworkResponse? {
        TODO("Not yet implemented")
    }

    override suspend fun getArtworksByArtist(artistId: Int, page: Int, perPage: Int): List<ArtworkResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun searchArtworks(query: String, page: Int, perPage: Int): List<ArtworkResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun addArtwork(
        name: String,
        year: String?,
        location: String,
        imageUrl: String,
        description: String,
        artistId: Int
    ): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun updateArtwork(
        id: Int,
        name: String,
        year: String?,
        location: String,
        imageUrl: String,
        description: String,
        artistId: Int
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