package com.omarmattr.mcc_firebase1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.omarmattr.mcc_firebase1.R
import com.omarmattr.mcc_firebase1.databinding.RecycleUserBinding
import com.omarmattr.mcc_firebase1.model.User

class UserAdapter (val arrayList: ArrayList<User>) :
    RecyclerView.Adapter<UserAdapter.UserAdapterVH>() {
    inner class UserAdapterVH(private val mItemView: RecycleUserBinding) :
        RecyclerView.ViewHolder(mItemView.root) {
        fun bing(item: User) {
            mItemView.user = item
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapterVH {
        return UserAdapterVH(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.recycle_user, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: UserAdapterVH, position: Int) {
        holder.bing(arrayList[position])
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

   }