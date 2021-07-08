package com.example.ingame.ui.adapters.recyclerviews.home

interface IHomeListPresenter<V> {
    var itemClickListener: ((position: Int) -> Unit)?
    fun bindView(view: V, position: Int)
    fun getCount(): Int
    fun imageReady(position: Int)
}

interface IHomeGamesPresenter : IHomeListPresenter<IHomeGamesViewHolder>
