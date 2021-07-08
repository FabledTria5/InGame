package com.example.ingame.ui.adapters.recyclerviews.home

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.transition.Transition
import com.example.ingame.databinding.ItemHomeGamesListBinding

class HomeGamesListAdapter(
    private val presenter: IHomeGamesPresenter
) : RecyclerView.Adapter<HomeGamesListAdapter.HomeGamesListViewHolderImpl>() {

    inner class HomeGamesListViewHolderImpl(val binding: ItemHomeGamesListBinding) :
        RecyclerView.ViewHolder(binding.root), IHomeGamesViewHolder {

        override fun bindImage(imageUrl: String, position: Int) {
            Glide.with(binding.ivGamePoster.context).asBitmap().load(imageUrl).into(
                object : CustomViewTarget<ImageView, Bitmap>(binding.ivGamePoster) {
                    override fun onLoadFailed(errorDrawable: Drawable?) = Unit

                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        binding.ivGamePoster.setImageBitmap(resource)
                        presenter.imageReady(position)
                    }

                    override fun onResourceCleared(placeholder: Drawable?) = Unit
                }
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HomeGamesListViewHolderImpl(
        ItemHomeGamesListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    ).apply {
        itemView.setOnClickListener {
            presenter.itemClickListener?.invoke(adapterPosition)
        }
    }

    override fun onBindViewHolder(holder: HomeGamesListViewHolderImpl, position: Int) =
        presenter.bindView(holder, position)

    override fun getItemCount() = presenter.getCount()

}