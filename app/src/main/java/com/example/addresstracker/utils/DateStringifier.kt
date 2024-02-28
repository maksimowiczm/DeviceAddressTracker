package com.example.addresstracker.utils

import android.content.Context
import java.util.Date

class DateStringifier(
    private val context: Context,
) {
    fun stringifyWithCurrentLocale(date: Date): String {
        val timeFormat = android.text.format.DateFormat.getTimeFormat(context)
        val dateFormat = android.text.format.DateFormat.getDateFormat(context)
        return "${timeFormat.format(date)}, ${dateFormat.format(date)}"
    }
}