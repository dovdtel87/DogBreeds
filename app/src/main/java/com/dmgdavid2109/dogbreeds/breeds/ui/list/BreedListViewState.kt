package com.dmgdavid2109.dogbreeds.breeds.ui.list

import com.dmgdavid2109.dogbreeds.common.ui.LceViewState
import com.dmgdavid2109.dogbreeds.breeds.domain.model.Breed

data class BreedListViewState(
    override val isLoading: Boolean = true,
    override val errorMessage: Int? = null,
    val breedList: List<Breed> = emptyList()
) : LceViewState
