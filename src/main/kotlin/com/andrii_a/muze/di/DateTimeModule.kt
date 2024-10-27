package com.andrii_a.muze.di

import com.andrii_a.muze.util.DateValidator
import com.andrii_a.muze.util.LocalDateValidator
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import java.time.format.DateTimeFormatter

val dateTimeModule = module {
    single<DateTimeFormatter> { DateTimeFormatter.ofPattern("d/MM/yyyy") }
    singleOf(::LocalDateValidator) { bind<DateValidator>() }
}