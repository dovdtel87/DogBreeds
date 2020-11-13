package com.dmgdavid2109.dogbreeds.breeds.ui.list

import androidx.fragment.app.Fragment
import com.dmgdavid2109.dogbreeds.di.FragmentKey
import com.dmgdavid2109.dogbreeds.breeds.ui.details.BreedDetailFragment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ActivityComponent::class)
abstract class BreedListFragmentBindingModule {

    @Binds
    @IntoMap
    @FragmentKey(BreedListFragment::class)
    abstract fun bindListFragment(mainFragment: BreedListFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(BreedDetailFragment::class)
    abstract fun bindDetailsFragment(mainFragment: BreedDetailFragment): Fragment

}
