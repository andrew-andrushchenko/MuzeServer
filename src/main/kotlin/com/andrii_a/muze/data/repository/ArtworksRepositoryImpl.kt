package com.andrii_a.muze.data.repository

import com.andrii_a.muze.domain.dao.ArtworksDao
import com.andrii_a.muze.domain.model.Artwork
import com.andrii_a.muze.domain.model.Image
import com.andrii_a.muze.domain.repository.ArtworksRepository

class ArtworksRepositoryImpl(private val dao: ArtworksDao) : ArtworksRepository {

    override suspend fun getArtworks(
        page: Int,
        perPage: Int
    ): List<Artwork> = dao.getArtworks(page, perPage)

    override suspend fun getArtwork(id: Int): Artwork? = dao.getArtwork(id)

    override suspend fun getArtworksByArtist(
        artistId: Int, page: Int,
        perPage: Int
    ): List<Artwork> = dao.getArtworksByArtist(artistId, page, perPage)

    override suspend fun searchArtworks(query: String, page: Int, perPage: Int): List<Artwork> =
        dao.getArtworksByQuery(query, page, perPage)

    override suspend fun addArtwork(
        name: String,
        year: String?,
        location: String,
        image: Image,
        description: String,
        artistId: Int,
    ): Boolean = dao.insert(name, year, location, image, description, artistId)

    override suspend fun updateArtwork(
        id: Int,
        name: String,
        year: String?,
        location: String,
        image: Image,
        description: String,
        artistId: Int
    ): Boolean = dao.update(id, name, year, location, image, description, artistId)

    override suspend fun deleteAll(): Boolean = dao.deleteAll()

    override suspend fun deleteById(id: Int): Boolean = dao.deleteById(id)
}