package com.dmgdavid2109.dogbreeds.breeds.data.repository

import com.dmgdavid2109.dogbreeds.breeds.data.api.BreedsApi
import com.dmgdavid2109.dogbreeds.breeds.data.model.BreedResponse
import com.dmgdavid2109.dogbreeds.breeds.data.model.ImagesResponse
import com.dmgdavid2109.dogbreeds.breeds.domain.model.Breed
import com.dmgdavid2109.dogbreeds.breeds.domain.model.getBread
import com.dmgdavid2109.dogbreeds.breeds.domain.model.getSubbread
import com.dmgdavid2109.dogbreeds.breeds.domain.model.hasSubBread
import com.dmgdavid2109.dogbreeds.breeds.domain.repository.BreedsRepository
import io.reactivex.Single
import javax.inject.Inject

class BreedsRepositoryImpl @Inject constructor(
    private val breedsApi: BreedsApi
) : BreedsRepository {

    override fun getBreeds(): Single<BreedResponse> {
        return breedsApi.retrieveBreeds()
    }

    override fun getBreedImages(breed: Breed): Single<ImagesResponse> {
        return when(breed.hasSubBread()) {
            true -> breedsApi.retrieveSubBreedImages(breed.getBread(), breed.getSubbread())
            false -> breedsApi.retrieveBreedImages(breed)
        }
    }
}
