package com.antiglukapps.monikaapp.ui.storage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.antiglukapps.monikaapp.R
import com.antiglukapps.monikaapp.data.StorageItem
import com.antiglukapps.monikaapp.helpers.ExpirationHelpers

class StorageItemAdapter(var storageItemsList: List<StorageItem>? = ArrayList()) :
    RecyclerView.Adapter<StorageItemAdapter.StorageItemViewHolder>() {

    private var onStorageItemClickedListener: OnStorageItemClickedListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StorageItemViewHolder {
        val layout = R.layout.storage_item_view
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return StorageItemViewHolder(view, storageItemsList!!)
    }

    override fun getItemCount(): Int {
        return if (storageItemsList!!.isEmpty()) 0 else storageItemsList!!.size
    }

    override fun onBindViewHolder(holder: StorageItemViewHolder, position: Int) {
        holder.view.setOnClickListener {
            onStorageItemClickedListener!!.onStorageItemClicked(
                storageItemsList!!.get(position)
            )
        }
        holder.onBindViews(position)
    }

    inner class StorageItemViewHolder(val view: View, val storageItemsList: List<StorageItem>) :
        RecyclerView.ViewHolder(view) {
        fun onBindViews(position: Int) {
            view.findViewById<TextView>(R.id.storageItemName).text =
                storageItemsList.get(position).title

            val item = storageItemsList.get(position)

            val expiresDays =
                ExpirationHelpers.daysToExpire(item.expiration_dt)
            val expiresDate = ExpirationHelpers.format(item.expiration_dt)

            val dayWord = if (expiresDays > 1) {
                "days"
            } else {
                "day"
            }

            view.findViewById<TextView>(R.id.storageItemExpiration).text =
                "$expiresDate ($expiresDays $dayWord)"

            view.findViewById<Button>(R.id.storageItemDeleteButton).setOnClickListener {
                onStorageItemClickedListener!!.onStorageItemDeleteClicked(
                    storageItemsList.get(
                        position
                    )
                )
            }
        }
    }

    fun setStorageItemClickedListener(onStorageItemClickedListener: OnStorageItemClickedListener) {
        this.onStorageItemClickedListener = onStorageItemClickedListener
    }

    interface OnStorageItemClickedListener {
        fun onStorageItemClicked(storageItem: StorageItem)
        fun onStorageItemDeleteClicked(storageItem: StorageItem)
    }
}
