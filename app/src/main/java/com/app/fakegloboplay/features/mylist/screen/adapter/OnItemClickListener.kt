package com.app.fakegloboplay.features.mylist.screen.adapter

import com.app.fakegloboplay.network.response.Movie

interface OnItemClickListener {

    fun onItemClick(item: Movie, position: Int)
}