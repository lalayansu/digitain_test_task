package com.example.presenter.utils

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

fun BigDecimal.split(): Pair<String, String> {
    val digitCount = scale()
    val decimalFormatSymbols = DecimalFormatSymbols(Locale.US).apply {
        groupingSeparator = ' '
    }

    val integerFormat = DecimalFormat("#,###", decimalFormatSymbols)
    val formattedIntegerPart = integerFormat.format(toBigInteger())

    val scaledValue = setScale(digitCount, RoundingMode.HALF_UP)

    val fractionalPart = scaledValue.remainder(BigDecimal.ONE)
        .movePointRight(digitCount)
        .abs()
        .toPlainString()
        .padStart(length = digitCount, padChar = '0')

    return Pair(formattedIntegerPart, fractionalPart)
}

fun BigDecimal.formatWithDigits(digitCount: Int = 2): BigDecimal =
    setScale(digitCount, RoundingMode.HALF_UP)

fun String.groupWithSpaces(): String {
    val decimalFormatSymbols = DecimalFormatSymbols(Locale.US).apply {
        groupingSeparator = ' '
    }
    val integerFormat = DecimalFormat("#,###", decimalFormatSymbols)
    return integerFormat.format(replace(oldValue = " ", newValue = "").toBigInteger())
}

fun prepareBigDecimalForReel(
    startNumber: BigDecimal,
    endNumber: BigDecimal
): Pair<Pair<List<Int>, List<Int>>, Pair<List<Int>, List<Int>>> {
    val startFormatted = if (startNumber == BigDecimal.ZERO) {
        endNumber.setScale(endNumber.scale(), RoundingMode.HALF_UP).split()
    } else {
        startNumber.split()
    }

    val endFormatted = endNumber.split()

    val maxIntegerLength = maxOf(startFormatted.first.length, endFormatted.first.length)
    val maxFractionLength = maxOf(startFormatted.second.length, endFormatted.second.length)

    val startIntegerPart = startFormatted.first
        .padStart(length = maxIntegerLength, padChar = '0')
        .groupWithSpaces()
        .map { it.toString().toIntOrNull() ?: -1 }

    val endIntegerPart = endFormatted.first
        .padStart(length = maxIntegerLength, padChar = '0')
        .groupWithSpaces()
        .map { it.toString().toIntOrNull() ?: -1 }

    val startFractionalPart = startFormatted.second
        .padEnd(length = maxFractionLength, padChar = '0')
        .map { it.toString().toIntOrNull() ?: -1 }

    val endFractionalPart = endFormatted.second
        .padEnd(length = maxFractionLength, padChar = '0')
        .map { it.toString().toIntOrNull() ?: -1 }

    return Pair(
        first = Pair(startIntegerPart, startFractionalPart),
        second = Pair(endIntegerPart, endFractionalPart)
    )
}

fun BigDecimal.formatToSeparatedString(digitCount: Int = 2): String {
    val decimalFormatSymbols = DecimalFormatSymbols(Locale.US).apply {
        groupingSeparator = ' '
    }

    val decimalFormat =
        DecimalFormat("#,###." + "0".repeat(digitCount), decimalFormatSymbols).apply {
            roundingMode = RoundingMode.HALF_UP
        }

    return decimalFormat.format(setScale(digitCount, RoundingMode.HALF_UP))
}