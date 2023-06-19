package com.andrii_a.muze.data

import com.andrii_a.muze.data.tables.Artists
import com.andrii_a.muze.data.tables.Artworks
import com.andrii_a.muze.util.Environment
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    private val hikariDataSource: HikariDataSource by lazy {
        val config = HikariConfig().apply {
            driverClassName = Environment.driverClassName
            jdbcUrl = Environment.databaseUrl
            maximumPoolSize = 3
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        }

        HikariDataSource(config)
    }

    fun init() {
        val database = Database.connect(hikariDataSource)

        val tables = arrayOf(Artworks, Artists)

        transaction(database) {
            SchemaUtils.create(*tables)
        }

    }
}

suspend fun <T> dbQuery(block: () -> T): T =
    newSuspendedTransaction(Dispatchers.IO) { block() }