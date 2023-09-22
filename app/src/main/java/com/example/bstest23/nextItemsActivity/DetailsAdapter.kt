package com.example.bstest23.nextItemsActivity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bstest23.databinding.ItemDetailsBinding

class DetailsAdapter(private var showDetailsAdapterInterface: ShowDetailsAdapterInterface): RecyclerView.Adapter<DetailsAdapter.ViewHolder>() {
    private var dataList: MutableList<String> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDetailsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateData(updatedDataList: MutableList<String>) {
        val diffCallback = DiffUtilItem(dataList, updatedDataList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        dataList.clear()
        dataList.addAll(updatedDataList)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]

        holder.binding.textViewIngredients.text = item

        holder.binding.rootShowIngredient.setOnClickListener {
            showDetailsAdapterInterface.onItemClick(item)
        }
    }

    class ViewHolder (val binding: ItemDetailsBinding) : RecyclerView.ViewHolder(binding.root)


    class DiffUtilItem(
        private val oldList: MutableList<String>,
        private val newList: List<String>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
            val (_, value, name) = oldList
            val (_, value1, name1) = newList
            return name == name1 && value == value1
        }
    }
}

interface ShowDetailsAdapterInterface {
    fun  onItemClick(item: String)
}