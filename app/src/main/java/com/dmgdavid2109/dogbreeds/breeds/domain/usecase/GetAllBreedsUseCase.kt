package com.dmgdavid2109.dogbreeds.breeds.domain.usecase

import com.dmgdavid2109.dogbreeds.breeds.data.model.BreedResponse
import com.dmgdavid2109.dogbreeds.breeds.domain.model.Breed
import com.dmgdavid2109.dogbreeds.breeds.domain.repository.BreedsRepository
import com.dmgdavid2109.dogbreeds.common.data.Mapper
import com.dmgdavid2109.dogbreeds.common.network.SchedulerProvider
import io.reactivex.Single
import javax.inject.Inject

class GetAllBreedsUseCase @Inject constructor(
    private val breedsRepository: BreedsRepository,
    private val breedsListMapper: Mapper<BreedResponse, List<Breed>>,
    private val schedulerProvider: SchedulerProvider
) {
    operator fun invoke(): Single<List<Breed>> {
        return breedsRepository.getBreeds()
            .map(breedsListMapper::map)
            .observeOn(schedulerProvider.ui())
            .subscribeOn(schedulerProvider.io())
    }
}
