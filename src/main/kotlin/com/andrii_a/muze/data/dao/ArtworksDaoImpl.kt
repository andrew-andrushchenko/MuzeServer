package com.andrii_a.muze.data.dao

import com.andrii_a.muze.data.dbQuery
import com.andrii_a.muze.data.tables.Artists
import com.andrii_a.muze.data.tables.Artworks
import com.andrii_a.muze.domain.dao.ArtworksDao
import com.andrii_a.muze.domain.model.Artist
import com.andrii_a.muze.domain.model.Artwork
import com.andrii_a.muze.domain.model.Image
import com.andrii_a.muze.util.ilike
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class ArtworksDaoImpl : ArtworksDao {

    private fun resultRowToPaintingResponse(row: ResultRow) = Artwork(
        id = row[Artworks.id].value,
        name = row[Artworks.name],
        year = row[Artworks.year],
        location = row[Artworks.location],
        image = Image(
            width = row[Artworks.imageWidth],
            height = row[Artworks.imageHeight],
            url = row[Artworks.imageUrl]
        ),
        description = row[Artworks.description],
        artist = Artist(
            id = row[Artists.id].value,
            name = row[Artists.name],
            bornDateString = row[Artists.born].toString(),
            diedDateString = row[Artists.died].toString(),
            portraitImage = Image(
                width = row[Artists.portraitImageWidth],
                height = row[Artists.portraitImageHeight],
                url = row[Artists.portraitImageUrl]
            ),
            bio = row[Artists.bio]
        )
    )

    override suspend fun getArtworks(
        page: Int,
        perPage: Int
    ): List<Artwork> = dbQuery {
        val offset = (page - 1) * perPage
        Artworks
            .leftJoin(Artists)
            .selectAll()
            .limit(perPage, offset = offset.toLong())
            .map(::resultRowToPaintingResponse)
    }

    override suspend fun getArtwork(id: Int): Artwork? = dbQuery {
        Artworks
            .leftJoin(Artists)
            .select(Artworks.id eq id)
            .map(::resultRowToPaintingResponse)
            .firstOrNull()
    }

    override suspend fun getArtworksByArtist(
        artistId: Int,
        page: Int,
        perPage: Int
    ): List<Artwork> = dbQuery {
        val offset = (page - 1) * perPage
        Artworks
            .leftJoin(Artists)
            .select(Artworks.artistId eq artistId)
            .limit(perPage, offset = offset.toLong())
            .map(::resultRowToPaintingResponse)
    }

    override suspend fun getArtworksByQuery(
        query: String,
        page: Int,
        perPage: Int
    ): List<Artwork> = dbQuery {
        val offset = (page - 1) * perPage
        Artworks
            .leftJoin(Artists)
            .select(Artworks.name ilike "%$query%")
            .limit(perPage, offset = offset.toLong())
            .map(::resultRowToPaintingResponse)
    }

    override suspend fun insert(
        name: String,
        year: String?,
        location: String,
        image: Image,
        description: String,
        artistId: Int
    ): Boolean = dbQuery {
        Artworks.insert {
            it[Artworks.name] = name
            it[Artworks.year] = year
            it[Artworks.location] = location
            it[imageWidth] = image.width
            it[imageHeight] = image.height
            it[imageUrl] = image.url
            it[Artworks.description] = description
            it[Artworks.artistId] = artistId
        }.insertedCount > 0
    }

    override suspend fun update(
        id: Int,
        name: String,
        year: String?,
        location: String,
        image: Image,
        description: String,
        artistId: Int
    ): Boolean = dbQuery {
        Artworks.update({ Artworks.id eq id }) {
            it[this.name] = name
            it[this.year] = year
            it[this.location] = location
            it[imageWidth] = image.width
            it[imageHeight] = image.height
            it[imageUrl] = image.url
            it[this.description] = description
            it[this.artistId] = artistId
        } > 0
    }

    override suspend fun deleteAll(): Boolean = dbQuery {
        Artworks.deleteAll() > 0
    }

    override suspend fun deleteById(id: Int): Boolean = dbQuery {
        Artworks.deleteWhere { Artworks.id eq id } > 0
    }
}