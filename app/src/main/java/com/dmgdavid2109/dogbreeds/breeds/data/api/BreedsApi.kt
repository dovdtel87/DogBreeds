package com.dmgdavid2109.dogbreeds.breeds.data.api

import com.dmgdavid2109.dogbreeds.breeds.data.model.BreedResponse
import com.dmgdavid2109.dogbreeds.breeds.data.model.ImagesResponse
import com.dmgdavid2109.dogbreeds.breeds.domain.model.Breed
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface BreedsApi {

    @GET("breeds/list/all")
    fun retrieveBreeds(): Single<BreedResponse>

    @GET("breed/{breed}/images/random/{number}")
    fun retrieveBreedImages(
        @Path("breed") breed: String,
        @Path("number") number: Int = 10
    ): Single<ImagesResponse>

    @GET("breed/{breed}/{subbreed}/images/random/{number}")
    fun retrieveSubBreedImages(
        @Path("breed") breed: String,
        @Path("subbreed") subBreed: String,
        @Path("number") number: Int = 10
    ): Single<ImagesResponse>
}
