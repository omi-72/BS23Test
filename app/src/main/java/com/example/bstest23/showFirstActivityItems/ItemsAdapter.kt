package com.example.bstest23.showFirstActivityItems

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bstest23.databinding.ItemListBinding
import com.example.bstest23.networkCommunication.ModelList

class ItemsAdapter(private var itemAdapterInterface: ItemAdapterInterface):  RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {
    private var dataList: MutableList<ModelList> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]

        holder.binding.textViewName.text = item.name
        holder.binding.textViewFullName.text = item.full_name



        holder.binding.root.setOnClickListener {
            itemAdapterInterface.onItemClick(item)
        }

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateData(updatedDataList: MutableList<ModelList>) {
        val diffCallback = DiffUtilItem(dataList, updatedDataList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        dataList.clear()
        dataList.addAll(updatedDataList)
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder (val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root)

    class DiffUtilItem(
        private val oldList: MutableList<ModelList>,
        private val newList: List<ModelList>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].name == newList[newItemPosition].name
        }

        override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
            val (_, value, name) = oldList
            val (_, value1, name1) = newList
            return name == name1 && value == value1
        }
    }

}

interface ItemAdapterInterface {
    fun  onItemClick(item : ModelList)
}