package com.dmgdavid2109.dogbreeds.breeds.ui.details

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dmgdavid2109.dogbreeds.R
import com.dmgdavid2109.dogbreeds.databinding.ImageItemBinding

class ViewPagerAdapter(
) : ListAdapter<String, ImageItemViewHolder>(object :
    DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ImageItemBinding.inflate(inflater, parent, false)
        return ImageItemViewHolder(
            parent.context,
            binding
        )
    }

    override fun onBindViewHolder(holder: ImageItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ImageItemViewHolder(
    private val context: Context,
    private val binding: ImageItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: String) {
        binding.position.text = (adapterPosition+1).toString()
        Glide.with(context)
            .load(item)
            .placeholder(R.drawable.progress_animation)
            .into(binding.image)
    }
}
