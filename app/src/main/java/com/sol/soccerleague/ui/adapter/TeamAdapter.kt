package com.sol.soccerleague.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sol.soccerleague.databinding.ItemTeamBinding
import com.sol.soccerleague.model.Team

class TeamAdapter(private var teamList: List<Team>): RecyclerView.Adapter<TeamAdapter.PostsViewHolder>() {
    inner class PostsViewHolder(private val binding: ItemTeamBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun databind(team: Team){
            binding.team = team
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemTeamBinding.inflate(layoutInflater)

        return PostsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int)
            = holder.databind(teamList[position])

    override fun getItemCount(): Int {
        return teamList.size
    }

    fun update(posts: List<Team>){
        this.teamList = posts
        notifyDataSetChanged()
    }
}