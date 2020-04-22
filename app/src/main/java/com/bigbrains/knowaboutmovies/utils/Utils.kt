package com.bigbrains.knowaboutmovies.utils

object Utils {

    fun getDaySuffix(n: Int): String? {
        return if (n in 11..13) {
            "th"
        } else when (n % 10) {
            1 -> "st"
            2 -> "nd"
            3 -> "rd"
            else -> "th"
        }
    }

    fun getMonth(n: Int): String? {
        return when (n) {
            1 -> "January"
            2 -> "February"
            3 -> "March"
            4 -> "April"
            5 -> "May"
            6 -> "June"
            7 -> "July"
            8 -> "August"
            9 -> "September"
            10 -> "October"
            11 -> "November"
            12 -> "December"
            else -> ""
        }
    }

    fun truncateNumber(floatNumber: Float): String {
        val million = 1000000L
        val billion = 1000000000L
        val trillion = 1000000000000L
        val number = Math.round(floatNumber).toLong()
        if (number in million until billion) {
            val fraction: Float = calculateFraction(number, million)
            return "$fraction Million"
        } else if (number in billion until trillion) {
            val fraction: Float = calculateFraction(number, billion)
            return "$fraction Billion"
        }
        return number.toString()
    }

    private fun calculateFraction(number: Long, divisor: Long): Float {
        val truncate = (number * 10L + divisor / 2L) / divisor
        return truncate.toFloat() * 0.10f
    }
}