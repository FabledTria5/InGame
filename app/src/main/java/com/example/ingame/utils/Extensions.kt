package com.example.ingame.utils

import android.graphics.LinearGradient
import android.graphics.Shader
import android.text.TextUtils
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.example.ingame.R
import com.example.ingame.data.network.model.common.Platforms
import com.example.ingame.data.network.model.game_detail.GameDetails
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.internal.BaselineLayout
import com.google.android.material.tabs.TabLayout
import com.google.android.material.textview.MaterialTextView
import moxy.MvpAppCompatFragment
import java.util.*

fun TabLayout.Tab.setGradientText() {
    (view.getChildAt(1) as MaterialTextView).setGradientText()
}

fun TabLayout.Tab.clearGradient() {
    (view.getChildAt(1) as MaterialTextView).clearGradient()
}

fun MaterialTextView.setGradientText() {
    paint.shader = LinearGradient(
        0f,
        0f,
        paint.measureText(text.toString()),
        textSize,
        intArrayOf(
            ContextCompat.getColor(context, R.color.main_gradient_start),
            ContextCompat.getColor(context, R.color.main_gradient_end)
        ),
        null, Shader.TileMode.CLAMP
    )
}

fun BottomNavigationView.getTextView(position: Int) = ((((this[0]
        as BottomNavigationMenuView)[position]
        as BottomNavigationItemView)
    .getChildAt(1) as BaselineLayout)
    .getChildAt(1) as MaterialTextView)

fun MaterialTextView.clearGradient() {
    paint.shader = null
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