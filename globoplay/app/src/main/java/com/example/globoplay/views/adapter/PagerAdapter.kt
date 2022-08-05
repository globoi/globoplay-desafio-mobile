package com.example.globoplay.views.adapter

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.globoplay.views.AssistaTambem
import com.example.globoplay.views.Details

class PagerAdapter(fragmentActivity: FragmentActivity, desc:String):FragmentStateAdapter(fragmentActivity) {
    private val descricao = desc
    override fun getItemCount(): Int = 2
    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->{ AssistaTambem() }
            1->{ Details(descricao) }
            else ->{throw  Resources.NotFoundException("Position Not FOund")}
        }
    }
}