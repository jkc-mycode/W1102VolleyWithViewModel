package kr.ac.kumoh.s20201414.w1102volleywithviewmodel

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kr.ac.kumoh.s20201414.w1102volleywithviewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var model: SongViewModel
    private var songs: Array<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)

        model = ViewModelProvider(this)[SongViewModel::class.java]
        model.requestSong()

        model.songs.observe(this, Observer<ArrayList<String>> {
            songs = model.songs.value?.toTypedArray()
            //Toast.makeText(this, model.songs.value.toString(), Toast.LENGTH_LONG).show()
            binding.listSongs.adapter = ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                songs as Array<out String>
            )
        })
    }
}