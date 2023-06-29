package com.andrii_a.muze.data.dao

import com.andrii_a.muze.data.dbQuery
import com.andrii_a.muze.data.tables.Artists
import com.andrii_a.muze.domain.dao.ArtistsDao
import com.andrii_a.muze.domain.model.Artist
import com.andrii_a.muze.domain.model.Image
import com.andrii_a.muze.util.ImageType
import com.andrii_a.muze.util.constructUrlForImage
import com.andrii_a.muze.util.ilike
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.time.LocalDate

class ArtistsDaoImpl : ArtistsDao {

    private fun resultRowToArtist(row: ResultRow) = Artist(
        id = row[Artists.id].value,
        name = row[Artists.name],
        bornDateString = row[Artists.born].toString(),
        diedDateString = row[Artists.died].toString(),
        portraitImage = Image(
            width = row[Artists.portraitImageWidth],
            height = row[Artists.portraitImageHeight],
            url = constructUrlForImage(
                filename = row[Artists.portraitImage],
                imageType = ImageType.ARTIST_PORTRAIT
            )
        ),
        bio = row[Artists.bio]
    )

    override suspend fun getArtists(
        page: Int,
        perPage: Int
    ): List<Artist> = dbQuery {
        val offset = (page - 1) * perPage
        Artists
            .selectAll()
            .limit(perPage, offset = offset.toLong())
            .map(::resultRowToArtist)
    }

    override suspend fun getArtist(id: Int): Artist? = dbQuery {
        Artists
            .select(Artists.id eq id)
            .map(::resultRowToArtist)
            .firstOrNull()
    }

    override suspend fun getArtistsByQuery(
        query: String,
        page: Int,
        perPage: Int
    ): List<Artist> = dbQuery {
        val offset = (page - 1) * perPage
        Artists
            .select(Artists.name ilike "%$query%")
            .limit(perPage, offset = offset.toLong())
            .map(::resultRowToArtist)
    }

    override suspend fun insert(
        name: String,
        bornDate: LocalDate?,
        diedDate: LocalDate?,
        portraitImage: Image,
        bio: String?
    ): Boolean = dbQuery {
        Artists.insert {
            it[Artists.name] = name
            it[born] = bornDate
            it[died] = diedDate
            it[portraitImageWidth] = portraitImage.width
            it[portraitImageHeight] = portraitImage.height
            it[this.portraitImage] = portraitImage.url
            it[Artists.bio] = bio.orEmpty()
        }.insertedCount > 0
    }

    override suspend fun update(
        id: Int,
        name: String,
        bornDate: LocalDate?,
        diedDate: LocalDate?,
        portraitImage: Image,
        bio: String?
    ): Boolean = dbQuery {
        Artists.update({ Artists.id eq id }) {
            it[this.name] = name
            it[born] = bornDate
            it[died] = diedDate
            it[portraitImageWidth] = portraitImage.width
            it[portraitImageHeight] = portraitImage.height
            it[this.portraitImage] = portraitImage.url
            it[this.bio] = bio.orEmpty()
        } > 0
    }

    override suspend fun deleteAll(): Boolean = dbQuery {
        Artists.deleteAll() > 0
    }

    override suspend fun deleteById(id: Int): Boolean = dbQuery {
        Artists.deleteWhere { Artists.id eq id } > 0
    }
}