package com.example.bstest23.showFirstActivityItems

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.bstest23.databinding.ActivityMainBinding
import com.example.bstest23.networkCommunication.ModelList
import com.example.bstest23.nextItemsActivity.DetailsActivity
import com.google.gson.Gson

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
        followingTAdapter = ItemsAdapter(object : ItemAdapterInterface {

            override fun onItemClick(item: ModelList) {
                Log.d(
                    this@MainActivity::class.java.simpleName,
                    "item.ingredients.toString()::" + item.details.toString()
                )

                nextActivity(DetailsActivity::class.java) {
                    putSerializable(
                        "COFFEE_ITEM",
                        Gson().toJson(item)
                    )
                }
            }
        })

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
