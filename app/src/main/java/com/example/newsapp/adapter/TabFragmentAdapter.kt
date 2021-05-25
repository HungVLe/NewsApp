package com.example.newsapp.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.newsapp.fragments.TabNewsFragment
import com.example.newsapp.vo.Constants.Companion.TAB_LIST

class TabFragmentAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return TAB_LIST.size
    }

    override fun createFragment(position: Int): Fragment {
        return TabNewsFragment(position)
    }
}