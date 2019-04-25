package com.media.gallery.controllers

import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dev.data.domain.entity.ImageModelEntity
import com.media.gallery.R
import com.media.gallery.fragment.ImageDialogFragment
import com.media.gallery.interfaces.ImageClickListener
import kotlinx.android.synthetic.main.item_image.view.*

class ImageAdapter(private val callbacks: ImageClickListener) :
    ListAdapter<ImageModelEntity, ImageAdapter.ImageViewHolder>(object :
        DiffUtil.ItemCallback<ImageModelEntity>() {
        override fun areItemsTheSame(oldItem: ImageModelEntity, newItem: ImageModelEntity): Boolean {
            return oldItem.id.equals(newItem.id, true)
        }

        override fun areContentsTheSame(oldItem: ImageModelEntity, newItem: ImageModelEntity): Boolean {
            return oldItem == newItem
        }

    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false))
    }


    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            callbacks.onImageClick(getItem(position).imageUrl, getItem(position).id)
        }
    }


    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(imageModelEntity: ImageModelEntity) {
            itemView.ivImage.setImageUri(
                imageModelEntity.imageUrl,
                ColorDrawable(ContextCompat.getColor(itemView.context, R.color.color_grey))
            )
        }
    }
}