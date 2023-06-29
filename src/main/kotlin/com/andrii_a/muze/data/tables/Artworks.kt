package com.andrii_a.muze.data.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object Artworks : IntIdTable() {
    val name = varchar("name", 255)
    val year = varchar("year", 16).nullable()
    val location = text("location")
    val description = text("description")
    val image = text("image")
    val imageWidth = integer("image_width")
    val imageHeight = integer("image_height")
    val artistId = reference("artist_id", Artists)
}