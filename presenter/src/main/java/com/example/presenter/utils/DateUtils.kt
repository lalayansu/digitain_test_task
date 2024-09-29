package com.example.presenter.utils

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun String.parseAndFormatDate(): String {
    val normalizedInput = replace('\u202F', ' ')
    val inputFormatter = DateTimeFormatter.ofPattern("M/d/yyyy h:mm:ss a XXX", Locale.US)
    val outputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy | HH:mm", Locale.US)
    val zonedDateTime = ZonedDateTime.parse(normalizedInput, inputFormatter)

    return zonedDateTime.format(outputFormatter)
}