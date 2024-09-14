package com.example.timnasfootballplayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.timnasfootballplayer.databinding.ActivityDetailPlayerBinding
import com.example.timnasfootballplayer.model.Player

class DetailPlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailPlayerBinding

    companion object {
        const val EXTRA_PLAYER = "extra_player"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val player = intent.getParcelableExtra<Player>(EXTRA_PLAYER) as Player

        binding.tvItemName.text = player.name
        binding.tvItemDescription.text = player.description
        binding.tvItemPosition.text = player.position
        binding.tvItemHeight.text = player.height
        binding.tvItemWeight.text = player.weight
        binding.tvItemClub.text = player.club

        Glide.with(this)
            .load(player.photo)
            .into(binding.imgItemPhoto)
    }
}