package com.example.ingame.ui.adapters.recyclerviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ingame.R
import com.example.ingame.databinding.ItemHomeGamesListBinding

class HomeGamesListAdapter : RecyclerView.Adapter<HomeGamesListAdapter.HomeGamesListViewHolder>() {

    private val gamesList = arrayListOf<String>()

    inner class HomeGamesListViewHolder(binding: ItemHomeGamesListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeGamesListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemHomeGamesListBinding = DataBindingUtil
            .inflate(inflater, R.layout.item_home_games_list, parent, false)
        return HomeGamesListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeGamesListViewHolder, position: Int) =
        holder.bind(position)

    override fun getItemCount() = gamesList.count()

    fun addGames(items: List<String>) = gamesList.addAll(items)

}