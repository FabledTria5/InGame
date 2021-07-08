package com.example.ingame.ui.adapters.recyclerviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ingame.data.network.model.screenshots.ScreenshotsResult
import com.example.ingame.databinding.ItemSnapshotBinding


class SnapshotsAdapter(private val snapshots: List<ScreenshotsResult>) :
    RecyclerView.Adapter<SnapshotsAdapter.SnapshotsViewHolder>() {

    inner class SnapshotsViewHolder(val binding: ItemSnapshotBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SnapshotsViewHolder(
        ItemSnapshotBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: SnapshotsViewHolder, position: Int) {
        holder.binding.screenshot = snapshots[position]
    }

    override fun getItemCount() = snapshots.count()

}