package com.example.bstest23.showFirstActivityItems

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.bstest23.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var followingTAdapter: ItemsAdapter

    private val viewModel: ItemViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.loadFollowingData()
        prepareViews()
    }

    private fun prepareViews() {
        binding.recyclerView.adapter = followingTAdapter
        viewModel.followingTagDataList.observe(this) {
            followingTAdapter.updateData(it)

        }
    }
}

fun <T> Context.nextActivity(it: Class<T>, extras: Bundle.() -> Unit = {}) {
    val intent = Intent(this, it)
    intent.putExtras(Bundle().apply(extras))
    startActivity(intent)
}