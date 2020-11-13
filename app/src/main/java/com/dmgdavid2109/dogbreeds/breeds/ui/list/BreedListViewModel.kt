package com.dmgdavid2109.dogbreeds.breeds.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dmgdavid2109.dogbreeds.R
import com.dmgdavid2109.dogbreeds.common.ui.Event
import com.dmgdavid2109.dogbreeds.common.ui.ViewStateLiveData
import com.dmgdavid2109.dogbreeds.common.ui.toEvent
import com.dmgdavid2109.dogbreeds.breeds.domain.model.Breed
import com.dmgdavid2109.dogbreeds.breeds.domain.usecase.GetAllBreedsUseCase
import com.dmgdavid2109.dogbreeds.common.ui.BaseViewModel
import com.uber.autodispose.autoDispose
import io.reactivex.functions.Consumer
import javax.inject.Inject

class BreedListViewModel @Inject constructor(
    private val getAllBreedsUseCase: GetAllBreedsUseCase
) : BaseViewModel(),
    BreedListViewModelInput {

    private val _viewState = ViewStateLiveData(BreedListViewState())
    val viewState: LiveData<BreedListViewState>
        get() = _viewState

    private val _navigateToDetails = MutableLiveData<Event<Breed>>()
    val navigateToDetails: LiveData<Event<Breed>>
        get() = _navigateToDetails

    init {
        loadList()
    }

    private fun loadList() {
        getAllBreedsUseCase
            .invoke()
            .doOnSubscribe{
               showStartLoading()
            }
            .autoDispose(this)
            .subscribe(Consumer {
                showBreedList(it)
            }, this)
    }


    override fun onBreedTapped(breedItem: Breed) {
        navigateToBreedDetails(breedItem)
    }

    private fun navigateToBreedDetails(breedItem: Breed) {
        _navigateToDetails.value = breedItem.toEvent()
    }

    private fun showStartLoading() {
        _viewState.updateCurrentState {
            copy(isLoading = true)
        }
    }

    private fun showBreedList(items: List<Breed>) {
        _viewState.updateCurrentState {
            copy(
                isLoading = false,
                errorMessage = null,
                breedList = items
            )
        }
    }

    override fun accept(t: Throwable?) {
        super.accept(t)
        showError()
    }

    private fun showError() {
        _viewState.updateCurrentState {
            copy(
                isLoading = false,
                errorMessage = R.string.generic_error
            )
        }
    }

    override fun retry() {
        loadList()
    }
}
