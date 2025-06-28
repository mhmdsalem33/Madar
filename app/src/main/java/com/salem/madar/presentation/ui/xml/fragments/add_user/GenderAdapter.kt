package com.salem.madar.presentation.ui.xml.fragments.add_user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.salem.madar.R
import com.salem.madar.databinding.ItemGenderBinding
import com.salem.madar.domain.models.GenderModel
import com.salem.madar.presentation.view_extensions.setStyle

class GenderAdapter :
    ListAdapter<GenderModel, GenderAdapter.GenderViewHolder>(GenderDiffCallback()) {

        var onGenderSelected: ((GenderModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenderViewHolder {
        val binding = ItemGenderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenderViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    private var selectedPosition: Int = -1

    inner class GenderViewHolder(
        private val binding: ItemGenderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(gender: GenderModel, position: Int) {

            binding.genderType.text = StringBuffer(gender.genderType).append(" ").append(gender.emoji)

            binding.root.setOnClickListener {
                onGenderSelected?.invoke(gender)
                changeSelectedPosition(position)
            }

            setBackground(position , binding)

        }

    }


    private fun changeSelectedPosition(position: Int) {
        selectedPosition = position
        notifyDataSetChanged()
    }

    fun clearSelection() {
        selectedPosition = -1
        notifyDataSetChanged()
    }

    private fun setBackground( position: Int , binding : ItemGenderBinding) {
        if (selectedPosition == position) {
            binding.genderType.setStyle(
                bgRes = R.drawable.bg_rounded_12sdp_blue_transparent,
                textColorRes = android.R.color.white
            )
        }else{
            binding.genderType.setStyle(
                bgRes = R.drawable.bg_rounded_12sdp_gray,
                textColorRes = R.color.gray_2
            )
        }
    }

    class GenderDiffCallback : DiffUtil.ItemCallback<GenderModel>() {
        override fun areItemsTheSame(oldItem: GenderModel, newItem: GenderModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GenderModel, newItem: GenderModel): Boolean {
            return oldItem == newItem
        }
    }
}