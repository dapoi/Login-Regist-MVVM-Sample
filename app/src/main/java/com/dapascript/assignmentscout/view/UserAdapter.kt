package com.dapascript.assignmentscout.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dapascript.assignmentscout.data.remote.model.DataItem
import com.dapascript.assignmentscout.databinding.ItemListUserBinding

/**
 * Copyright (c) 2022 DapaScript. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */
class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val data = ArrayList<DataItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<DataItem>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(
        private val binding: ItemListUserBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DataItem) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(item.avatar)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imgUser)

                tvFirstName.text = item.first_name
                tvLastName.text = item.last_name
                tvEmail.text = item.email
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemListUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) =
        holder.bind(data[position])

    override fun getItemCount(): Int = data.size
}