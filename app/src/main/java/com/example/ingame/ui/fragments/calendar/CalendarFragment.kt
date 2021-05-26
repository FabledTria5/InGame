package com.example.ingame.ui.fragments.calendar

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.ingame.R
import com.example.ingame.databinding.FragmentCalendarBinding
import com.example.ingame.utils.DayViewContainer
import com.example.ingame.utils.MonthViewContainer
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.CalendarMonth
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.MonthHeaderFooterBinder
import moxy.MvpAppCompatFragment
import java.text.SimpleDateFormat
import java.time.YearMonth
import java.time.temporal.WeekFields
import java.util.*

class CalendarFragment : MvpAppCompatFragment() {

    private lateinit var binding: FragmentCalendarBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_calendar, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupCalendar()
    }

    @SuppressLint("NewApi")
    private fun setupCalendar() {
        binding.calendarView.dayBinder = object : DayBinder<DayViewContainer> {
            override fun bind(container: DayViewContainer, day: CalendarDay) {
                container.textView.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    day.date.dayOfMonth.toString()
                } else {
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH).toString()
                }
                if (day.owner != DayOwner.THIS_MONTH) {
                    container.textView.setTextColor(Color.parseColor("#343434"))
                }
            }

            override fun create(view: View) = DayViewContainer(view)
        }

        binding.calendarView.monthHeaderBinder =
            object : MonthHeaderFooterBinder<MonthViewContainer> {
                override fun bind(container: MonthViewContainer, month: CalendarMonth) {
                    container.textView.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        month.yearMonth.month.name.lowercase()
                            .replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase(Locale.getDefault())
                                else it.toString()
                            }
                    } else {
                        SimpleDateFormat(
                            "MMM",
                            Locale.getDefault()
                        ).format(Calendar.getInstance().time)
                    }
                }

                override fun create(view: View) = MonthViewContainer(view)
            }

        val currentMonth = YearMonth.now()
        val firstMonth = currentMonth.minusMonths(10)
        val lastMonth = currentMonth.plusMonths(10)
        val firstDayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek
        binding.calendarView.setup(firstMonth, lastMonth, firstDayOfWeek)
        binding.calendarView.scrollToMonth(currentMonth)
    }
}