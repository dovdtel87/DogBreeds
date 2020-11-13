package com.dmgdavid2109.dogbreeds.breeds.ui

import com.dmgdavid2109.dogbreeds.R
import com.dmgdavid2109.dogbreeds.breeds.domain.model.Breed
import com.dmgdavid2109.dogbreeds.breeds.domain.usecase.GetBreedImagesUseCase
import com.dmgdavid2109.dogbreeds.breeds.ui.details.BreedDetailViewModel
import com.dmgdavid2109.dogbreeds.breeds.ui.details.BreedDetailViewState
import com.dmgdavid2109.dogbreeds.helpers.getValueTest
import com.dmgdavid2109.dogbreeds.helpers.mock
import com.dmgdavid2109.dogbreeds.helpers.withInstantTaskExecutor
import io.mockk.every
import io.reactivex.Single
import junit.framework.TestCase.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class BreedDetailViewModelSpec : Spek({
    withInstantTaskExecutor()

    val getBreedImagesUseCase: GetBreedImagesUseCase by mock<GetBreedImagesUseCase>()
    val breed = Breed("entlebucher")

    val viewModel: BreedDetailViewModel by memoized {
        BreedDetailViewModel(
            breed,
            getBreedImagesUseCase
        )
    }

    val list = listOf(
        "https://image1.png",
        "https://image2.png",
        "https://image3.png",
        "https://image4.png"
    )

    describe("init") {
        context("when list successfully retrieved") {
            val expectedViewState =
                BreedDetailViewState(
                    false,
                    null,
                    breed,
                    list
                )
            beforeEachTest {
                every { getBreedImagesUseCase.invoke(breed) } returns Single.just(list)
            }
            it("displays the correct result") {
                assertEquals(expectedViewState, viewModel.viewState.getValueTest())
            }
        }

        context("when there is an error") {
            val expectedViewStateError =
                BreedDetailViewState(
                    false,
                    R.string.generic_error,
                    breed,
                    emptyList()
                )
            beforeEachTest {
                every { getBreedImagesUseCase.invoke(breed) } returns Single.error(Exception())
            }
            it("displays an error") {
                assertEquals(expectedViewStateError, viewModel.viewState.getValueTest())
            }
        }
    }
})
