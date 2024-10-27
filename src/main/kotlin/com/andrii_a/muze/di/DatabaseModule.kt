package com.andrii_a.muze.di

import com.andrii_a.muze.util.Environment
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.koin.dsl.module

val databaseModule = module {
    single<HikariConfig> {
        HikariConfig().apply {
            driverClassName = Environment.driverClassName
            jdbcUrl = Environment.databaseUrl
            maximumPoolSize = 3
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        }
    }

    single<HikariDataSource> { HikariDataSource(get()) }
}