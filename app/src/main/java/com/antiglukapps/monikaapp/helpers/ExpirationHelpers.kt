package com.antiglukapps.monikaapp.helpers

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class ExpirationHelpers {
    companion object {
        val dateFormat = "dd.MM.yyyy"
        val simpleDateFormatter = SimpleDateFormat(dateFormat, Locale.US)

        fun toLong(year: Int, monthOfYear: Int, dayOfMonth: Int): Long {
            return Date(year, monthOfYear, dayOfMonth).time
        }

        fun parse(time: Long): Calendar {
            val c = Calendar.getInstance()
            c.time = Date(time)
            return c
        }

        fun format(time: Long): String {
            return simpleDateFormatter.format(Date(time))
        }

        fun daysToExpire(time: Long): Long {
            val diffInMillies: Long = Math.abs(Date().getTime() - Date(time).getTime())
            return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS)
        }
    }
}
