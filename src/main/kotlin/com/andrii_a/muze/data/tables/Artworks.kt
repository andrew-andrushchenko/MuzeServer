package com.andrii_a.muze.data.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object Artworks : IntIdTable() {
    val name = varchar("name", 255)
    val year = varchar("year", 16).nullable()
    val location = text("location")
    val description = text("description")
    val imageUrl = text("image_url")
    val artistId = reference("artist_id", Artists)
}