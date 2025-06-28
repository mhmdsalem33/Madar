package com.salem.madar.presentation.ui.xml.fragments.show_users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.salem.madar.R
import com.salem.madar.databinding.ItemUsersBinding
import com.salem.madar.domain.models.User
import com.salem.madar.presentation.view_extensions.hide

class ShowUsersAdapter :
    ListAdapter<User, ShowUsersAdapter.UsersViewHolder>(UsersDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val binding = ItemUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }


    inner class UsersViewHolder(
        private val binding: ItemUsersBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User, position: Int) {
            binding.apply {
                tvUserName.text = user.name
                tvUserAge.text = StringBuffer("${user.age}").append(" ").append(itemView.context.getString(R.string.user_gender_age))
                tvUserJobTitle.text = user.jobTitle
                tvUserGender.text = user.gender
            }

            if (position == currentList.size - 1) {
                binding.view.hide()
            }
        }
    }

    class UsersDiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }
}