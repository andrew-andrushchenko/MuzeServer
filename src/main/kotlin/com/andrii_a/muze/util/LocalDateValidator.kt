package com.andrii_a.muze.util

import java.time.DateTimeException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LocalDateValidator(private val dateTimeFormatter: DateTimeFormatter) : DateValidator {

    override fun isValidDate(dateString: String?): Boolean {
        return try {
            LocalDate.parse(dateString, dateTimeFormatter)
            true
        } catch (ex: DateTimeException) {
            false
        }
    }
}