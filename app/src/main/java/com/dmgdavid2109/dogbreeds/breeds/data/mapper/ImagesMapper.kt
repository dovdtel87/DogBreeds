package com.dmgdavid2109.dogbreeds.breeds.data.mapper

import com.dmgdavid2109.dogbreeds.common.data.Mapper
import com.dmgdavid2109.dogbreeds.breeds.data.model.ImagesResponse
import javax.inject.Inject

class ImagesMapper @Inject constructor() : Mapper<ImagesResponse, List<@JvmSuppressWildcards String>> {
    override fun map(input: ImagesResponse) = input.message
}
