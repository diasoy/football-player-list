package com.example.timnasfootballplayer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.timnasfootballplayer.databinding.ItemRowPlayerBinding
import com.example.timnasfootballplayer.model.Player

class ListPlayerAdapter(private val listPlayer: ArrayList<Player>) : RecyclerView.Adapter<ListPlayerAdapter.ListViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(private val binding: ItemRowPlayerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(player: Player) {
            binding.itemName.text = player.name
            binding.itemDescription.text = player.description
            Glide.with(binding.itemPhoto.context)
                .load(player.photo)
                .into(binding.itemPhoto)

            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(player)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listPlayer[position])
    }

    override fun getItemCount(): Int = listPlayer.size

    interface OnItemClickCallback {
        fun onItemClicked(data: Player)
    }
}