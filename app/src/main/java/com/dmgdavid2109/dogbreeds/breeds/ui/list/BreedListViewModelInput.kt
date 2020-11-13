package com.dmgdavid2109.dogbreeds.breeds.ui.list

import com.dmgdavid2109.dogbreeds.common.ui.LceViewModelInputs
import com.dmgdavid2109.dogbreeds.breeds.domain.model.Breed

interface BreedListViewModelInput : LceViewModelInputs {
    fun onBreedTapped(repoDetailsItem: Breed)
}
