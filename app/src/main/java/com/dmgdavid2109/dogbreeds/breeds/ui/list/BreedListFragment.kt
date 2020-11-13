package com.dmgdavid2109.dogbreeds.breeds.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.dmgdavid2109.dogbreeds.common.ui.viewBinding
import com.dmgdavid2109.dogbreeds.R
import com.dmgdavid2109.dogbreeds.common.ui.EventObserver
import com.dmgdavid2109.dogbreeds.di.ViewModelFactory
import com.dmgdavid2109.dogbreeds.breeds.domain.model.Breed
import com.dmgdavid2109.dogbreeds.common.ui.setViewModelInputs
import com.dmgdavid2109.dogbreeds.common.ui.setViewState
import com.dmgdavid2109.dogbreeds.databinding.BreedListFragmentBinding
import javax.inject.Inject

class BreedListFragment @Inject constructor(
    private val viewModelFactory: ViewModelFactory<BreedListViewModel>
) : Fragment((R.layout.breed_list_fragment)) {
    private val binding by viewBinding(BreedListFragmentBinding::bind)

    private val breedListViewModel: BreedListViewModel by viewModels {
        viewModelFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView()
    }

    private fun bindView() {
        val listAdapter = BreedListAdapter(breedListViewModel::onBreedTapped)

        binding.list.adapter = listAdapter
        binding.loadingView.setViewModelInputs(breedListViewModel)

        breedListViewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->
            listAdapter.submitList(viewState.breedList)
            binding.loadingView.setViewState(viewState)
        })

        breedListViewModel.navigateToDetails.observe(viewLifecycleOwner, EventObserver { item ->
            navigateToDetailsScreen(item)
        })
    }

    private fun navigateToDetailsScreen(item: Breed) {
        val action =
            BreedListFragmentDirections.actionRepositoriesListFragmentToRepositoriesDetailFragment(item)
        findNavController().navigate(action)
    }
}
