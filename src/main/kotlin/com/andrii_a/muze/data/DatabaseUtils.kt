package com.andrii_a.muze.data

import com.andrii_a.muze.data.tables.Artists
import com.andrii_a.muze.data.tables.Artworks
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

fun configureDatabase(hikariDataSource: HikariDataSource) {
    val database = Database.connect(hikariDataSource)

    val tables = arrayOf(Artworks, Artists)

    transaction(database) {
        SchemaUtils.create(*tables)
    }
}

suspend fun <T> dbQuery(block: () -> T): T =
    newSuspendedTransaction(Dispatchers.IO) { block() }