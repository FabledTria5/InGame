package com.example.ingame.utils

import android.view.View
import com.example.ingame.databinding.MonthLayoutBinding
import com.kizitonwose.calendarview.ui.ViewContainer

class MonthViewContainer(view: View) : ViewContainer(view) {
    val textView = MonthLayoutBinding.bind(view).tvMonth
}