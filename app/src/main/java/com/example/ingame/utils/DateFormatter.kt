package com.example.ingame.utils

import java.text.SimpleDateFormat
import java.util.*

object DateFormatter {

    fun getToday(): String =
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

}