package br.com.common.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding


abstract class BaseViewHolder<in T, out VB : ViewBinding>(
    val binding: VB
) : RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(item: T)
}
