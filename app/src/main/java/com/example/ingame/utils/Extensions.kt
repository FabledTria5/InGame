package com.example.ingame.utils

import android.graphics.LinearGradient
import android.graphics.Shader
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.ingame.R
import com.example.ingame.data.network.model.common.Platforms
import com.example.ingame.data.network.model.game_detail.GameDetails
import com.google.android.material.tabs.TabLayout
import com.google.android.material.textview.MaterialTextView
import moxy.MvpAppCompatFragment
import java.util.*

fun TabLayout.Tab.selectTab() {
    val textView = view.getChildAt(1) as MaterialTextView
    val paint = textView.paint
    val width = paint.measureText(text.toString())
    val textShader = LinearGradient(
        0f,
        0f,
        width,
        textView.textSize,
        intArrayOf(
            ContextCompat.getColor(textView.context, R.color.main_gradient_start),
            ContextCompat.getColor(textView.context, R.color.main_gradient_end)
        ),
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

fun String.makeCapital() = replaceFirstChar { char ->
    if (char.isLowerCase()) char.titlecase(Locale.getDefault()) else char.toString()
}

fun GameDetails.getMinRequirements(): String {
    platforms.forEach { platforms ->
        if (platforms.platform.name == "PC" && platforms.requirements.minimum != null)
            return platforms.requirements.minimum.dropWhile { it != ' ' }
    }
    return ""
}

fun GameDetails.getRecRequirements(): String {
    platforms.forEach { platforms ->
        if (platforms.platform.name == "PC" && platforms.requirements.recommended != null)
            return platforms.requirements.recommended.dropWhile { it != ' ' }
    }
    return ""
}

fun List<Platforms>.getNamesWhile(platformsCount: Int): String {
    val strings = arrayListOf<String>()
    for (platform in this.take(platformsCount)) {
        strings.add(platform.platform.name)
    }
    return TextUtils.join(", ", strings)
}