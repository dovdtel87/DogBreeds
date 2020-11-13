package com.dmgdavid2109.dogbreeds.breeds.domain.repository

import com.dmgdavid2109.dogbreeds.breeds.data.model.BreedResponse
import com.dmgdavid2109.dogbreeds.breeds.data.model.ImagesResponse
import com.dmgdavid2109.dogbreeds.breeds.domain.model.Breed
import io.reactivex.Single

interface BreedsRepository {
    fun getBreeds(): Single<BreedResponse>
    fun getBreedImages(breed: Breed): Single<ImagesResponse>
}
