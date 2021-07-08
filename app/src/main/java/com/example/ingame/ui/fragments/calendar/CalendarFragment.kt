package com.example.ingame.ui.fragments.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.ingame.R
import com.example.ingame.databinding.FragmentCalendarBinding
import com.example.ingame.ui.di_base.BaseDaggerFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class CalendarFragment : BaseDaggerFragment() {

    private lateinit var binding: FragmentCalendarBinding

    @Inject
    lateinit var calendarPresenterFactory: CalendarPresenterFactory

    private val calendarPresenter by moxyPresenter {
        calendarPresenterFactory.create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_calendar, container, false)
        return binding.root
    }
}