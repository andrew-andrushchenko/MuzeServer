package com.andrii_a.muze.data.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.date

object Artists : IntIdTable() {
    val name = varchar("name", 255)
    val born = date("born").nullable()
    val died = date("died").nullable()
    val portraitImageUrl = text("portrait_image_url")
    val bio = text("bio")
}