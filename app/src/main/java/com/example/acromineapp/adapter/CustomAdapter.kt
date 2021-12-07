package com.example.acromineapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.acromineapp.databinding.ItemBinding
import com.example.acromineapp.model.LongFormObject

class CustomAdapter : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

	private var moreItemList = mutableListOf<LongFormObject>()

	@SuppressLint("NotifyDataSetChanged")
	fun submitList(sf: String, items: List<LongFormObject>) {
		moreItemList.clear()
		moreItemList.addAll(items)
		notifyDataSetChanged()
	}

	@SuppressLint("NotifyDataSetChanged")
	fun clearList() {
		moreItemList.clear()
		notifyDataSetChanged()
	}
	// create new views
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		val binding = ItemBinding.inflate(inflater, parent, false)
		return ViewHolder(binding)
	}

	// binds the list items to a view
	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.bind(moreItemList[position])
	}

	// return the number of the items in the list
	override fun getItemCount() = moreItemList.size

	class ViewHolder(private val binding: ItemBinding ) : RecyclerView.ViewHolder(binding.root) {
		fun bind(item: LongFormObject) {
			binding.data = item
			binding.nameId.text = item.lf
			binding.frequencyId.text = item.freq.toString()
			binding.sinceId.text = item.since.toString()
		}
	}
}
