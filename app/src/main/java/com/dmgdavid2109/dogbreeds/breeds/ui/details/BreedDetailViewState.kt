package com.dmgdavid2109.dogbreeds.breeds.ui.details

import com.dmgdavid2109.dogbreeds.common.ui.LceViewState
import com.dmgdavid2109.dogbreeds.breeds.domain.model.Breed

data class BreedDetailViewState(
    override val isLoading: Boolean = true,
    override val errorMessage: Int? = null,
    val breed: Breed = "",
    val images : List<String> = emptyList()
) : LceViewState
