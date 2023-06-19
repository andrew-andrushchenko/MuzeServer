package com.andrii_a.muze.data.dao

import com.andrii_a.muze.data.dbQuery
import com.andrii_a.muze.data.tables.Artists
import com.andrii_a.muze.domain.dao.ArtistsDao
import com.andrii_a.muze.domain.model.ArtistResponse
import com.andrii_a.muze.util.ilike
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.time.LocalDate

class ArtistsDaoImpl : ArtistsDao {

    private fun resultRowToArtist(row: ResultRow) = ArtistResponse(
        id = row[Artists.id].value,
        name = row[Artists.name],
        bornDateString = row[Artists.born].toString(),
        diedDateString = row[Artists.died].toString(),
        portraitImageUrl = row[Artists.portraitImageUrl],
        bio = row[Artists.bio]
    )

    override suspend fun getArtists(
        page: Int,
        perPage: Int
    ): List<ArtistResponse> = dbQuery {
        val offset = (page - 1) * perPage
        Artists
            .selectAll()
            .limit(perPage, offset = offset.toLong())
            .map(::resultRowToArtist)
    }

    override suspend fun getArtist(id: Int): ArtistResponse? = dbQuery {
        Artists
            .select(Artists.id eq id)
            .map(::resultRowToArtist)
            .firstOrNull()
    }

    override suspend fun getArtistsByQuery(
        query: String,
        page: Int,
        perPage: Int
    ): List<ArtistResponse> = dbQuery {
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
        portraitImageUrl: String,
        bio: String?
    ): Boolean = dbQuery {
        Artists.insert {
            it[Artists.name] = name
            it[born] = bornDate
            it[died] = diedDate
            it[Artists.portraitImageUrl] = portraitImageUrl
            it[Artists.bio] = bio.orEmpty()
        }.insertedCount > 0
    }

    override suspend fun update(
        id: Int,
        name: String,
        bornDate: LocalDate?,
        diedDate: LocalDate?,
        portraitImageUrl: String,
        bio: String?
    ): Boolean = dbQuery {
        Artists.update({ Artists.id eq id }) {
            it[this.name] = name
            it[born] = bornDate
            it[died] = diedDate
            it[this.portraitImageUrl] = portraitImageUrl
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