package com.andradel.xous.commonui.diffs

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.andradel.xous.commonmodels.Item

open class ItemDiffUtils<T : Item> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = oldItem.id == newItem.id

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = oldItem == newItem
}