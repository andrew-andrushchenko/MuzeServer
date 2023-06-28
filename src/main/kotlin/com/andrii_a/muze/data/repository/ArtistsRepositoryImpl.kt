package com.andrii_a.muze.data.repository

import com.andrii_a.muze.domain.dao.ArtistsDao
import com.andrii_a.muze.domain.model.ArtistResponse
import com.andrii_a.muze.domain.model.ImageResponse
import com.andrii_a.muze.domain.repository.ArtistsRepository
import com.andrii_a.muze.util.DateValidator
import com.andrii_a.muze.util.toLocalDate

class ArtistsRepositoryImpl(
    private val dao: ArtistsDao,
    private val dateValidator: DateValidator
) : ArtistsRepository {

    override suspend fun getAllArtists(page: Int, perPage: Int): List<ArtistResponse> =
        dao.getArtists(page, perPage)

    override suspend fun getArtist(id: Int): ArtistResponse? {
        val artist = dao.getArtist(id)

        return artist?.let {
            ArtistResponse(
                id = it.id,
                name = it.name,
                bornDateString = it.bornDateString,
                diedDateString = it.diedDateString,
                portraitImage = it.portraitImage,
                bio = it.bio
            )
        }
    }

    override suspend fun searchArtists(
        query: String,
        page: Int,
        perPage: Int
    ): List<ArtistResponse> = dao.getArtistsByQuery(query, page, perPage)

    override suspend fun addArtist(
        name: String,
        bornDateString: String?,
        diedDateString: String?,
        portraitImage: ImageResponse,
        bio: String?
    ): Boolean {
        val isBirthDateValid = dateValidator.isValidDate(bornDateString)
        val isDiedDateValid = dateValidator.isValidDate(diedDateString)

        return if (isBirthDateValid && isDiedDateValid) {
            dao.insert(
                name = name,
                bornDate = bornDateString.toLocalDate(),
                diedDate = diedDateString.toLocalDate(),
                portraitImage = portraitImage,
                bio = bio
            )
        } else {
            false
        }
    }

    override suspend fun updateArtist(
        id: Int,
        name: String,
        bornDateString: String?,
        diedDateString: String?,
        portraitImage: ImageResponse,
        bio: String?
    ): Boolean {
        val isBirthDateValid = dateValidator.isValidDate(bornDateString)
        val isDiedDateValid = dateValidator.isValidDate(diedDateString)

        return if (isBirthDateValid && isDiedDateValid) {
            dao.update(
                id = id,
                name = name,
                bornDate = bornDateString.toLocalDate(),
                diedDate = diedDateString.toLocalDate(),
                portraitImage = portraitImage,
                bio = bio
            )
        } else {
            false
        }
    }

    override suspend fun deleteAll(): Boolean = dao.deleteAll()

    override suspend fun deleteById(id: Int): Boolean = dao.deleteById(id)
}