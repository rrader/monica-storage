package com.antiglukapps.monikaapp.ui.storage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.antiglukapps.monikaapp.R
import com.antiglukapps.monikaapp.data.StorageItem

class StorageItemAdapter(var storageItemsList: List<StorageItem>? = ArrayList()): RecyclerView.Adapter<StorageItemAdapter.StorageItemViewHolder>(){

    private var onStorageItemClickedListener: OnStorageItemClickedListener?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StorageItemViewHolder {
        val layout = if (itemCount == 0) R.layout.empty_view else R.layout.storage_item_view
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return StorageItemViewHolder(view, storageItemsList!!)
    }

    override fun getItemCount(): Int {
        return if(storageItemsList!!.isEmpty()) 0 else storageItemsList!!.size
    }

    override fun onBindViewHolder(holder: StorageItemViewHolder, position: Int){
        holder.view.setOnClickListener{onStorageItemClickedListener!!.onStorageItemClicked(storageItemsList!!.get(position))}
        holder.view.setOnLongClickListener{
            onStorageItemClickedListener!!.onStorageItemLongClicked(storageItemsList!!.get(position))
            true
        }
        holder.onBindViews(position)
    }

    inner class StorageItemViewHolder(val view: View, val storageItemsList: List<StorageItem>): RecyclerView.ViewHolder(view){
        fun onBindViews(position: Int){
            if (itemCount != 0){
                view.findViewById<TextView>(R.id.storageItemName).text = storageItemsList.get(position).title
//                view.findViewById<TextView>(R.id.first_letter).text = storageItemsList.get(position).title.first().toUpperCase().toString()
//                view.findViewById<ImageView>(R.id.priority_imgView).setImageResource(getImage(storageItemsList.get(position).priority))
            }

        }
//        private fun getImage(priority: Int): Int
//                = if (priority == 1) R.drawable.low_priority else if(priority == 2) R.drawable.medium_priority else R.drawable.high_priority
    }

    fun setStorageItemClickedListener(onStorageItemClickedListener: OnStorageItemClickedListener){
        this.onStorageItemClickedListener = onStorageItemClickedListener
    }

    interface OnStorageItemClickedListener{
        fun onStorageItemClicked(storageItem: StorageItem)
        fun onStorageItemLongClicked(storageItem: StorageItem)
    }
}
