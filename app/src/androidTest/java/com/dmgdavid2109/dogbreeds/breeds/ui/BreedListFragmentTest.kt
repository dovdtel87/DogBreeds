package com.dmgdavid2109.dogbreeds.breeds.ui

import com.dmgdavid2109.dogbreeds.R
import com.dmgdavid2109.dogbreeds.utils.createFactoryWithNavController
import com.dmgdavid2109.dogbreeds.utils.toFactory
import com.dmgdavid2109.dogbreeds.utils.toLiveData
import android.os.Bundle
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dmgdavid2109.dogbreeds.breeds.ui.list.BreedListFragment
import com.dmgdavid2109.dogbreeds.breeds.ui.list.BreedListViewModel
import com.dmgdavid2109.dogbreeds.breeds.ui.list.BreedListViewState
import io.mockk.every
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RepositoriesListFragmentTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var fragmentScenario: FragmentScenario<BreedListFragment>

    @Test
    fun itDisplaysRepositoriesList() {
        // Given
        val breedListViewState = BreedListViewState(
            isLoading = false,
            errorMessage = null,
            breedList = mutableListOf(
                "doberman"
            )
        )
        val viewModel = mockk<BreedListViewModel>(relaxed = true) {
            every { viewState } returns breedListViewState.toLiveData()
        }

        // When
        startFragment(viewModel)

        // Then
        onView(withId(R.id.breed_name)).check(ViewAssertions.matches(ViewMatchers.withText("Doberman")))
    }

    @Test
    fun itDisplaysLoadingView() {
        // Given
        val breedListViewState = BreedListViewState(
            isLoading = true,
            errorMessage = null,
            breedList = emptyList()
        )
        val viewModel: BreedListViewModel = mockk(relaxed = true) {
            every { viewState } returns breedListViewState.toLiveData()
        }

        // When
        startFragment(viewModel)

        // Then
        onView(withId(R.id.progress_bar)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun itDisplaysErrorView() {
        // Given
        val breedListViewState = BreedListViewState(
            isLoading = false,
            errorMessage = R.string.generic_error,
            breedList = emptyList()
        )
        val viewModel: BreedListViewModel = mockk(relaxed = true) {
            every { viewState } returns breedListViewState.toLiveData()
        }

        // When
        startFragment(viewModel)

        // Then
        onView(withId(R.id.error_description)).check(ViewAssertions.matches(ViewMatchers.withText(R.string.generic_error)))
    }

    private fun startFragment(viewModel: BreedListViewModel) {
        fragmentScenario = launchFragmentInContainer(
            Bundle(),
            themeResId = R.style.AppTheme,
            factory = createFactoryWithNavController {
                BreedListFragment(viewModel.toFactory())
            }
        )
    }
}
