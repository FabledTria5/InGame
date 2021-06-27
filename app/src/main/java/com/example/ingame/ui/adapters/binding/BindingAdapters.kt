package com.example.ingame.ui.adapters.binding

import android.annotation.SuppressLint
import android.os.Build
import android.text.Html
import android.text.TextUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.ingame.data.network.model.common.Platforms
import com.example.ingame.data.network.model.game_detail.Developer
import com.example.ingame.data.network.model.game_detail.Genre
import com.example.ingame.data.network.model.game_developers.DevelopersResult
import java.text.SimpleDateFormat
import java.util.*
import java.util.stream.Collectors

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view.context).asBitmap().load(url).into(view)
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("platforms")
fun setPlatforms(textView: TextView, platforms: List<Platforms>?) {
    if (platforms.isNullOrEmpty()) return
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        platforms
            .stream()
            .map { it.platform.name }
            .collect(Collectors.toList())
    } else {
        val strings = arrayListOf<String>()
        for (platform in platforms) {
            strings.add(platform.platform.name)
        }
        strings
    }.also { platformsNames ->
        textView.text = TextUtils.join(", ", platformsNames)
    }
}

@BindingAdapter("genres")
fun setGenres(textView: TextView, genres: List<Genre>?) {
    if (genres.isNullOrEmpty()) return
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        genres
            .stream()
            .map { it.name }
            .collect(Collectors.toList())
    } else {
        val strings = arrayListOf<String>()
        for (genre in genres) {
            strings.add(genre.name)
        }
        strings
    }.also { genres ->
        textView.text = TextUtils.join(", ", genres)
    }
}

@BindingAdapter("directors")
fun setDirectors(textView: TextView, developers: List<DevelopersResult>?) {
    if (developers.isNullOrEmpty()) return
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        developers
            .stream()
            .map {
                it.positions.map { position ->
                    position.name == "director"
                }
                it.name
            }
            .collect(Collectors.toList())
    } else {
        val strings = arrayListOf<String>()
        for (director in developers) {
            for (position in director.positions) {
                if (position.name != "director") continue
                else strings.add(director.name)
            }
        }
        strings
    }.also { names ->
        textView.text = TextUtils.join(", ", names)
    }
}

@BindingAdapter("writers")
fun setWriters(textView: TextView, developers: List<DevelopersResult>?) {
    if (developers.isNullOrEmpty()) return
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        developers
            .stream()
            .map {
                it.positions.map { position ->
                    position.name == "writer"
                }
                it.name
            }
            .collect(Collectors.toList())
    } else {
        val strings = arrayListOf<String>()
        for (director in developers) {
            for (position in director.positions) {
                if (position.name != "writer") continue
                else strings.add(director.name)
            }
        }
        strings
    }.also { names ->
        textView.text = TextUtils.join(", ", names)
    }
}

@BindingAdapter("developers")
fun setDevelopers(textView: TextView, developers: List<Developer>?) {
    if (developers.isNullOrEmpty()) return
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        developers
            .stream()
            .map { it.name }
            .collect(Collectors.toList())
    } else {
        val strings = arrayListOf<String>()
        for (developer in developers) {
            strings.add(developer.name)
        }
        strings
    }.also { names ->
        textView.text = TextUtils.join(", ", names)
    }
}

@BindingAdapter("fromHtml")
fun setTextFromHtml(textView: TextView, text: String?) {
    if (text.isNullOrEmpty()) return
    textView.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(text, Html.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH)
    } else {
        Html.fromHtml(text)
    }
}

@BindingAdapter("releaseDate")
fun setReleaseDate(textView: TextView, releaseDate: String?) {
    if (releaseDate.isNullOrEmpty()) return
    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(releaseDate)
        .also { (Calendar.getInstance()::setTime) }
        .let {
            (Calendar.getInstance()
                .getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()))
        }
        .let(textView::setText)
}