package com.sol.soccerleague.ui.teamlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sol.soccerleague.App
import com.sol.soccerleague.databinding.FragmentTeamlistBinding
import com.sol.soccerleague.di.factory.ViewModelFactory
import javax.inject.Inject

class TeamListFragment: Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: TeamListViewModel
    private var _binding: FragmentTeamlistBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        App.instance.appComponent.inject(this)
        _binding = FragmentTeamlistBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this, viewModelFactory).get(TeamListViewModel::class.java)

        observe()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        viewModel.getAllTeams()
    }

    private fun observe(){
        viewModel.teamList.observe(viewLifecycleOwner, {
            println("team count ${it?.size}")
            viewModel.doneLoading()
        })

        viewModel.isLoading.observe(viewLifecycleOwner, {
            println("is loading $it")
        })

        viewModel.isApiError.observe(viewLifecycleOwner, {
            if(it){
                viewModel.doneLoading()
                viewModel.doneApiError()
                Toast.makeText(requireContext(), "Check internet connection", Toast.LENGTH_SHORT).show()
            }
        })
    }
}