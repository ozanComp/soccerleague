package com.sol.soccerleague.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sol.soccerleague.databinding.ItemFixtureBinding
import com.sol.soccerleague.model.Fixture

class FixtureAdapter(private var fixtureList: List<Fixture>)
    : RecyclerView.Adapter<FixtureAdapter.FixtureViewHolder>() {

    inner class FixtureViewHolder(private val binding: ItemFixtureBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun databind(fixture: Fixture){
            binding.fixture = fixture
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FixtureViewHolder {
        val binding = ItemFixtureBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)

        return FixtureViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FixtureViewHolder, position: Int)
            = holder.databind(fixtureList[position])

    override fun getItemCount(): Int {
        return fixtureList.size
    }

    fun update(list: List<Fixture>){
        this.fixtureList = list
        notifyDataSetChanged()
    }
}