package com.dmgdavid2109.dogbreeds.breeds.ui.details

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.dmgdavid2109.dogbreeds.R
import com.dmgdavid2109.dogbreeds.common.ui.setToolbar
import com.dmgdavid2109.dogbreeds.common.ui.setViewModelInputs
import com.dmgdavid2109.dogbreeds.common.ui.setViewState
import com.dmgdavid2109.dogbreeds.common.ui.viewBinding
import com.dmgdavid2109.dogbreeds.databinding.BreedDetailFragmentBinding
import javax.inject.Inject

class BreedDetailFragment @Inject constructor(
    private val viewModelFactory: BreedDetailViewModel.Factory
) : Fragment(R.layout.breed_detail_fragment) {

    private val fragmentArguments: BreedDetailFragmentArgs by navArgs()
    private val binding by viewBinding(BreedDetailFragmentBinding::bind)

    private val viewModel: BreedDetailViewModel by viewModels {
        viewModelFactory.create(fragmentArguments.breed)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar()
        bindView()
    }

    private fun setUpToolbar() {
        val title = fragmentArguments.breed.toString().capitalize()
        (activity as AppCompatActivity).setToolbar(binding.toolbar, title, true)
    }

    private fun bindView() {
        binding.loadingView.setViewModelInputs(viewModel)
        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->
            with(viewState.images) {
                val listAdapter = ViewPagerAdapter()
                binding.viewpager.adapter = listAdapter
                listAdapter.submitList(this)
                binding.loadingView.setViewState(viewState)
            }
        })
    }
}
