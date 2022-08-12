package com.example.globoplay.views.adapter

import com.example.globoplay.database.models.MyList
import com.example.globoplay.domain.PopularMovie

interface ClickItemMediaDetails {
    fun onItemCLickListener(media: MyList)
}
