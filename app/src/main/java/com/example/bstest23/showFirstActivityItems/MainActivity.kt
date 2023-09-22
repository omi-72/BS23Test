package com.example.bstest23.showFirstActivityItems

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bstest23.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var followingTAdapter: ItemsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}