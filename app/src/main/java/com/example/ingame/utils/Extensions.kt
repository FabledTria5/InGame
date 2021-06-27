package com.example.ingame.utils

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Build
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.ingame.data.network.model.game_detail.Genre
import com.google.android.material.tabs.TabLayout
import com.google.android.material.textview.MaterialTextView
import moxy.MvpAppCompatFragment
import java.util.stream.Collectors

fun TabLayout.Tab.selectTab() {
    val textView = view.getChildAt(1) as MaterialTextView
    val paint = textView.paint
    val width = paint.measureText(text.toString())
    val textShader = LinearGradient(
        0f,
        0f,
        width,
        textView.textSize,
        intArrayOf(Color.parseColor("#2de1f3"), Color.parseColor("#747efb")),
        null, Shader.TileMode.CLAMP
    )
    textView.paint.shader = textShader
}

fun TabLayout.Tab.unselectTab() {
    val textView = view.getChildAt(1) as MaterialTextView
    textView.paint.shader = null
}

fun MvpAppCompatFragment.toast(message: String) =
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

fun Fragment.arguments(vararg arguments: Pair<String, Any>): Fragment {
    this.arguments = bundleOf(*arguments)
    return this
}