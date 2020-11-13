package com.dmgdavid2109.dogbreeds.breeds.data

import com.dmgdavid2109.dogbreeds.common.data.Mapper
import com.dmgdavid2109.dogbreeds.breeds.data.api.BreedsApi
import com.dmgdavid2109.dogbreeds.breeds.data.mapper.BreedMapper
import com.dmgdavid2109.dogbreeds.breeds.data.mapper.ImagesMapper
import com.dmgdavid2109.dogbreeds.breeds.data.model.BreedResponse
import com.dmgdavid2109.dogbreeds.breeds.data.model.ImagesResponse
import com.dmgdavid2109.dogbreeds.breeds.data.repository.BreedsRepositoryImpl
import com.dmgdavid2109.dogbreeds.breeds.domain.model.Breed
import com.dmgdavid2109.dogbreeds.breeds.domain.repository.BreedsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.Retrofit

@Module
@InstallIn(ActivityComponent::class)
abstract class BreedsRepositoryModule {

    @Binds
    @ActivityScoped
    internal abstract fun breedsRepository(repository: BreedsRepositoryImpl): BreedsRepository

    @Binds
    internal abstract fun mapper(mapper: BreedMapper): Mapper<BreedResponse, List<Breed>>

    @Binds
    internal abstract fun imagesMapper(mapper: ImagesMapper): Mapper<ImagesResponse, List<String>>

    companion object {
        @Provides
        @ActivityScoped
        internal fun provideApi(
            retrofit: Retrofit
        ): BreedsApi {
            return retrofit.create(BreedsApi::class.java)
        }
    }
}
