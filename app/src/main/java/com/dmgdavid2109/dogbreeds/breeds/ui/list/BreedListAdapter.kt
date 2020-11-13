package com.dmgdavid2109.dogbreeds.breeds.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dmgdavid2109.dogbreeds.breeds.domain.model.Breed
import com.dmgdavid2109.dogbreeds.databinding.BreedListItemBinding

class BreedListAdapter(
    private val onItemTapped: (repoItem: Breed) -> Unit
) : ListAdapter<Breed, BreedItemViewHolder>(object :
    DiffUtil.ItemCallback<Breed>() {
    override fun areItemsTheSame(oldItem: Breed, newItem: Breed) = oldItem == newItem
    override fun areContentsTheSame(oldItem: Breed, newItem: Breed) = oldItem == newItem
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BreedListItemBinding.inflate(inflater, parent, false)
        return BreedItemViewHolder(
            binding,
            onItemTapped
        )
    }

    override fun onBindViewHolder(holder: BreedItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class BreedItemViewHolder(
    private val binding: BreedListItemBinding,
    private val onItemTapped: (breedItem: Breed) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Breed) {
        binding.breedName.text = item.toString().capitalize()

        binding.cardView.setOnClickListener {
            onItemTapped(item)
        }
    }
}
