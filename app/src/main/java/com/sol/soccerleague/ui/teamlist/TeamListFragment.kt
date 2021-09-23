package com.sol.soccerleague.ui.teamlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sol.soccerleague.App
import com.sol.soccerleague.databinding.FragmentTeamlistBinding
import com.sol.soccerleague.di.factory.ViewModelFactory
import com.sol.soccerleague.ui.adapter.TeamAdapter
import kotlinx.android.synthetic.main.fragment_teamlist.*
import javax.inject.Inject

class TeamListFragment: Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: TeamListViewModel
    private var _binding: FragmentTeamlistBinding? = null

    private val binding get() = _binding!!
    private lateinit var teamAdapter: TeamAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        App.instance.appComponent.inject(this)
        _binding = FragmentTeamlistBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this, viewModelFactory).get(TeamListViewModel::class.java)

        setupRecyclerView()

        observe()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()

        viewModel.getAllTeamFromLocal()
    }

    private fun setupRecyclerView() {
        val rView: RecyclerView = binding.recyclerViewTeams
        val layoutManager = GridLayoutManager(activity, 3)
        rView.layoutManager = layoutManager

        teamAdapter = TeamAdapter(listOf())
        rView.adapter = teamAdapter
    }

    private fun observe(){
        viewModel.isLoading.observe(viewLifecycleOwner, {
            println("is loading $it")
            if(it)
                progressBarNews.visibility = View.VISIBLE
            else
                progressBarNews.visibility = View.GONE
        })

        viewModel.isApiError.observe(viewLifecycleOwner, {
            println("is api error $it")
            if(it){
                viewModel.doneLoading()
                viewModel.doneApiError()
                Toast.makeText(requireContext(), "Check internet connection", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.hasTeamFromLocal.observe(viewLifecycleOwner, {
            println("has team from local $it")
            if(it){
                buttonDrawFixture.visibility = View.VISIBLE
                viewModel.getCurrentFixture()
            }else{
                viewModel.getAllTeams()
            }
        })

        viewModel.hasCurrentFixture.observe(viewLifecycleOwner, {
            println("has current fixture $it")
            if(it)
                buttonShowFixture.visibility = View.VISIBLE
            else
                buttonShowFixture.visibility = View.INVISIBLE
        })

        viewModel.teamList.observe(viewLifecycleOwner, {
            println("team count ${it?.size}")

            it?.let { it1 -> teamAdapter.update(it1) }
            viewModel.doneLoading()

            buttonDrawFixture.visibility = View.VISIBLE
        })

    }
}