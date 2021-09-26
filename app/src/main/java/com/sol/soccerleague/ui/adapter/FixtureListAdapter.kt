package com.sol.soccerleague.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class FixtureListAdapter (list: ArrayList<Fragment>, manager : FragmentManager, lifecycle: Lifecycle)
    : FragmentStateAdapter(manager,lifecycle) {
    private val fragmentList = list

    override fun getItemCount() = fragmentList.size

    override fun createFragment(position: Int) = fragmentList[position]
}