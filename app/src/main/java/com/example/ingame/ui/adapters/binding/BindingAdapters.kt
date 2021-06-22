package com.example.ingame.ui.adapters.binding

import android.annotation.SuppressLint
import android.os.Build
import android.text.TextUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.ingame.R
import com.example.ingame.data.network.model.common.Platforms
import java.util.stream.Collectors

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view.context).asBitmap().load(url).into(view)
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("platforms")
fun setPlatforms(textView: TextView, platforms: List<Platforms>) {

    val platformsNames = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        platforms
            .stream()
            .limit(3)
            .map { it.platform.name }
            .collect(Collectors.toList())
    } else {
        val strings = arrayListOf<String>()
        for (platform in platforms) {
            strings.add(platform.platform.name)
        }
        strings.subList(0, 2)
    }

    if (platforms.count() > 2)
        textView.text =
            TextUtils.join(", ", platformsNames) + textView.context.getString(R.string.dots)
    else textView.text = TextUtils.join(", ", platformsNames)
}
