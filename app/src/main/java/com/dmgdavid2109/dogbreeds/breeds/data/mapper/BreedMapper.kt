package com.dmgdavid2109.dogbreeds.breeds.data.mapper

import com.dmgdavid2109.dogbreeds.common.data.Mapper
import com.dmgdavid2109.dogbreeds.breeds.data.model.BreedResponse
import com.dmgdavid2109.dogbreeds.breeds.domain.model.Breed
import javax.inject.Inject

class BreedMapper @Inject constructor() : Mapper<BreedResponse, List<@JvmSuppressWildcards Breed>> {

    override fun map(input: BreedResponse): List<Breed> {
        val list = mutableListOf<Breed>()
        input.message.keys.toList().forEach{ key ->
            input.message[key]?.apply {
                when(this.isEmpty()) {
                    true -> list.add(Breed(key))
                    false -> {
                        this.forEach { value ->
                            list.add(Breed(key, value))
                        }
                    }
                }
            }
        }
        return list
    }
}
