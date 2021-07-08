package com.example.ingame.utils

import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

object DateFormatter {

    fun getToday(): String =
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

    fun convertDateToApiForm(rawDate: String): String {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = simpleDateFormat.parse(rawDate) ?: Date()
        return simpleDateFormat.format(date)
    }

    fun getMonthName(formattedDate: String) = DateFormatSymbols.getInstance()
        .months[(formattedDate.split("-")[1].toInt()) - 1].makeCapital()

    fun getLastMonthsInApiForm(count: Int): String {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val cal = Calendar.getInstance()
        cal.add(Calendar.MONTH, -count)
        return "${simpleDateFormat.format(cal.time)},${getToday()}"
    }
}