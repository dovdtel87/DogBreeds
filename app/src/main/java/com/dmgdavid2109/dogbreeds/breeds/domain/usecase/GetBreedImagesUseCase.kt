package com.dmgdavid2109.dogbreeds.breeds.domain.usecase

import com.dmgdavid2109.dogbreeds.breeds.data.model.ImagesResponse
import com.dmgdavid2109.dogbreeds.breeds.domain.model.Breed
import com.dmgdavid2109.dogbreeds.breeds.domain.repository.BreedsRepository
import com.dmgdavid2109.dogbreeds.common.data.Mapper
import com.dmgdavid2109.dogbreeds.common.network.SchedulerProvider
import io.reactivex.Single
import javax.inject.Inject

class GetBreedImagesUseCase @Inject constructor(
    private val breedsRepository: BreedsRepository,
    private val imagesMapper: Mapper<ImagesResponse, List<String>>,
    private val schedulerProvider: SchedulerProvider
) {
    operator fun invoke(breed: Breed): Single<List<String>> {
        return breedsRepository.getBreedImages(breed)
            .map(imagesMapper::map)
            .observeOn(schedulerProvider.ui())
            .subscribeOn(schedulerProvider.io())
    }
}
