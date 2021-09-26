package com.sol.soccerleague.ui.fixture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sol.soccerleague.App
import com.sol.soccerleague.databinding.FragmentFixtureBinding
import com.sol.soccerleague.di.factory.ViewModelFactory
import com.sol.soccerleague.ui.adapter.FixtureAdapter
import javax.inject.Inject

class FixtureFragment(private val week: Int) : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: FixtureViewModel

    private var _binding: FragmentFixtureBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: FixtureAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        App.instance.appComponent.inject(this)

        _binding = FragmentFixtureBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this, viewModelFactory).get(FixtureViewModel::class.java)

        binding.week = "$week. Week"

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
        viewModel.getFixtureByWeek(week)
    }

    private fun setupRecyclerView(){
        val rView: RecyclerView = binding.recyclerViewFixture
        val layoutManager = GridLayoutManager(activity, GridLayoutManager.VERTICAL)
        rView.layoutManager = layoutManager

        adapter = FixtureAdapter(listOf())
        rView.adapter = adapter
    }

    private fun observe(){
        viewModel.fixture.observe(viewLifecycleOwner, {
            adapter.update(it)
        })
    }
}