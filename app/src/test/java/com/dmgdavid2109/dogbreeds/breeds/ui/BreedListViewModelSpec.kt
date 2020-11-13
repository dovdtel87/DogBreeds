package com.dmgdavid2109.dogbreeds.breeds.ui

import com.dmgdavid2109.dogbreeds.R
import com.dmgdavid2109.dogbreeds.breeds.domain.usecase.GetAllBreedsUseCase
import com.dmgdavid2109.dogbreeds.breeds.ui.list.BreedListViewModel
import com.dmgdavid2109.dogbreeds.breeds.ui.list.BreedListViewState
import com.dmgdavid2109.dogbreeds.helpers.getValueTest
import com.dmgdavid2109.dogbreeds.helpers.mock
import com.dmgdavid2109.dogbreeds.helpers.withInstantTaskExecutor
import io.mockk.coEvery
import io.mockk.every
import io.reactivex.Single
import junit.framework.TestCase.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class BreedListViewModelSpec : Spek({
    withInstantTaskExecutor()

    val getAllBreedsUseCase: GetAllBreedsUseCase by mock<GetAllBreedsUseCase>()

    val viewModel: BreedListViewModel by memoized {
        BreedListViewModel(
            getAllBreedsUseCase
        )
    }

    val list = listOf("doberman","entlebucher","schipperke")

    describe("init") {
        context("when list successfully retrieved") {
            val expectedViewState =
                BreedListViewState(
                    false,
                    null,
                    list
                )
            beforeEachTest {
                every { getAllBreedsUseCase.invoke() } returns Single.just(list)
            }
            it("displays the correct result") {
                assertEquals(expectedViewState, viewModel.viewState.getValueTest())
            }
        }

        context("when there is an error") {
            val expectedViewStateError =
                BreedListViewState(
                    false,
                    R.string.generic_error,
                    emptyList()
                )
            beforeEachTest {
                every { getAllBreedsUseCase.invoke() } returns Single.error(Exception())
            }
            it("displays an error") {
                assertEquals(expectedViewStateError, viewModel.viewState.getValueTest())
            }
        }
    }
})
