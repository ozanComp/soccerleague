package com.sol.soccerleague.ui.fixture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sol.soccerleague.App
import com.sol.soccerleague.databinding.FragmentFixturelistBinding
import com.sol.soccerleague.di.factory.ViewModelFactory
import com.sol.soccerleague.ui.adapter.FixtureListAdapter
import kotlinx.android.synthetic.main.fragment_fixturelist.*
import javax.inject.Inject


class FixtureListFragment: Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: FixtureViewModel

    private var _binding: FragmentFixturelistBinding? = null
    private val binding get() = _binding!!

    private val fragmentList = arrayListOf<Fragment>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        App.instance.appComponent.inject(this)

        _binding = FragmentFixturelistBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this, viewModelFactory).get(FixtureViewModel::class.java)

        observe()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        viewModel.getFixtureCount()
    }

    private fun setupViewPager(fixtureCount: Int){
        for (i in 0 until fixtureCount ){
            fragmentList.add(FixtureFragment(i + 1))
        }

        val adapter = FixtureListAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        viewPagerFixtureList.adapter = adapter
    }

    private fun observe(){
        viewModel.fixtureCount.observe(viewLifecycleOwner, {
            setupViewPager(it)
            viewModel.doneLoading()
        })

        viewModel.isLoading.observe(viewLifecycleOwner, {
            if(it){
                progressBarFixtureList.visibility = View.VISIBLE
                viewPagerFixtureList.visibility = View.GONE
            }
            else{
                progressBarFixtureList.visibility = View.GONE
                viewPagerFixtureList.visibility = View.VISIBLE
            }
        })
    }
}