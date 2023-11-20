package com.astontraineeship.fragmentstraining.task2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.astontraineeship.fragmentstraining.databinding.ItemViewBinding

class UsersAdapter(
    private var onListItemClickListener: OnListItemClickListener
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var users: MutableList<User> = mutableListOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemViewBinding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UsersViewHolder(itemViewBinding)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as UsersViewHolder
        holder.bind(users[position])
    }

    fun setUsers(newUsers: MutableList<User>) {
        val diffCallback = UsersDiffCallback(users, newUsers)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        users = newUsers
        diffResult.dispatchUpdatesTo(this)
    }

    fun addUser(user: User): Int {
        users.add(user)
        val index = users.size
        this.notifyItemInserted(index)
        return index
    }

    fun replaceUser(user: User, position: Int) {
        users[position] = user
        this.notifyItemChanged(position)
    }



    inner class UsersViewHolder(private val itemViewBinding: ItemViewBinding) : RecyclerView.ViewHolder(itemViewBinding.root) {
        fun bind(user: User) {
            with(itemViewBinding) {
                rvImage.load(user.photoUrl) {build()}
                rvFirstName.text = user.firstName
                rvLastName.text = user.lastName
                rvPhoneNumber.text = user.phoneNumber
                root.setOnClickListener {
                    onListItemClickListener.onItemClick(user)
                }
            }
        }
    }

    private class UsersDiffCallback(
        private val oldList: List<User>,
        private val newList: List<User>
        ): DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].userId == newList[newItemPosition].userId
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

    }
}