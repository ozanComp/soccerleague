package com.sol.soccerleague.ui.fixture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sol.soccerleague.App
import com.sol.soccerleague.databinding.FragmentFixtureBinding
import com.sol.soccerleague.di.factory.ViewModelFactory
import javax.inject.Inject

class FixtureFragment: Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: FixtureViewModel
    private var _binding: FragmentFixtureBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        App.instance.appComponent.inject(this)
        _binding = FragmentFixtureBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this, viewModelFactory).get(FixtureViewModel::class.java)

        return binding.root
    }
}