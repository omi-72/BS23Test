package com.example.bstest23.nextItemsActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.bstest23.R
import com.example.bstest23.databinding.ActivityDetailsBinding
import com.example.bstest23.networkCommunication.ModelList
import com.google.gson.Gson

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    private lateinit var ingredientsListAdapter: DetailsAdapter

    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getBundleData()
        prepareViews()
    }

    private fun prepareViews() {
        binding.recyclerViewDetails.adapter = ingredientsListAdapter
        viewModel.dataList.observe(this) {
            ingredientsListAdapter.updateData(it)

        }
    }

    private fun getBundleData() {
        intent.extras?.let {
            val item: ModelList = Gson().fromJson(intent.extras?.getString("COFFEE_ITEM"), ModelList::class.java)

            Toast.makeText(this,item.details.toString(), Toast.LENGTH_SHORT).show()
            viewModel.dataList.postValue(item.details)
        }    }
}