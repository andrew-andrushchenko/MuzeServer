package com.andrii_a.muze.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun String?.toLocalDate(dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy")): LocalDate {
    return LocalDate.parse(this, dateTimeFormatter)
}