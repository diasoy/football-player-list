package com.example.timnasfootballplayer

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.timnasfootballplayer.adapter.ListPlayerAdapter
import com.example.timnasfootballplayer.databinding.ActivityMainBinding
import com.example.timnasfootballplayer.model.Player

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<Player>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.rvPlayers.setHasFixedSize(true)
        list.addAll(getListPlayers())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListPlayers(): ArrayList<Player> {
        val dataName = resources.getStringArray(R.array.player_name)
        val dataDescription = resources.getStringArray(R.array.player_description)
        val dataPhoto = resources.obtainTypedArray(R.array.player_photo)
        val dataPosition = resources.getStringArray(R.array.player_position)
        val dataHeight = resources.getStringArray(R.array.player_height)
        val dataWeight = resources.getStringArray(R.array.player_weight)
        val dataClub = resources.getStringArray(R.array.player_club)
        val listPlayer = ArrayList<Player>()
        for (i in dataName.indices) {
            val player = Player(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1), dataPosition[i], dataHeight[i], dataWeight[i], dataClub[i])
            listPlayer.add(player)
        }
        dataPhoto.recycle()
        return listPlayer
    }

    private fun showRecyclerList() {
        binding.rvPlayers.layoutManager = LinearLayoutManager(this)
        val listPlayerAdapter = ListPlayerAdapter(list)
        binding.rvPlayers.adapter = listPlayerAdapter

        listPlayerAdapter.setOnItemClickCallback(object : ListPlayerAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Player) {
                showSelectedPlayer(data)
            }
        })
    }

    private fun showSelectedPlayer(player: Player) {
        val intent = Intent(this, DetailPlayerActivity::class.java)
        intent.putExtra(DetailPlayerActivity.EXTRA_PLAYER, player)
        startActivity(intent)
    }
}