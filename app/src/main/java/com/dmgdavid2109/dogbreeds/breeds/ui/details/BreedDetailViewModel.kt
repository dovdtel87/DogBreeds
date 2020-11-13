package com.dmgdavid2109.dogbreeds.breeds.ui.details

import androidx.lifecycle.LiveData
import com.dmgdavid2109.dogbreeds.R
import com.dmgdavid2109.dogbreeds.common.ui.ViewStateLiveData
import com.dmgdavid2109.dogbreeds.di.ViewModelFactoryCreator
import com.dmgdavid2109.dogbreeds.breeds.domain.model.Breed
import com.dmgdavid2109.dogbreeds.breeds.domain.usecase.GetBreedImagesUseCase
import com.dmgdavid2109.dogbreeds.common.ui.BaseViewModel
import com.uber.autodispose.autoDispose
import io.reactivex.functions.Consumer
import javax.inject.Inject

class BreedDetailViewModel(
    private val breed: Breed,
    private val getBreedImagesUseCase: GetBreedImagesUseCase
) : BaseViewModel(),
    BreedDetailViewModelInput {

    private val _viewState = ViewStateLiveData(BreedDetailViewState()).apply {
        updateCurrentState {
            copy(breed = this@BreedDetailViewModel.breed)
        }
    }
    val viewState: LiveData<BreedDetailViewState>
        get() = _viewState

    init {
        loadImages(breed)
    }

    private fun loadImages(breed: Breed) {
        getBreedImagesUseCase
            .invoke(breed)
            .doOnSubscribe{
                showStartLoading()
            }
            .autoDispose(this)
            .subscribe(Consumer {
                showImages(it)
            }, this)
    }

    override fun retry() {
        loadImages(breed)
    }

    private fun showStartLoading() {
        _viewState.updateCurrentState {
            copy(isLoading = true)
        }
    }

    private fun showImages(items: List<Breed>) {
        _viewState.updateCurrentState {
            copy(
                isLoading = false,
                errorMessage = null,
                images = items
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

    class Factory @Inject constructor(private val getBreedImagesUseCase: GetBreedImagesUseCase) {
        fun create(breed: Breed) =
            ViewModelFactoryCreator.createForViewModel { BreedDetailViewModel(breed, getBreedImagesUseCase) }
    }
}
