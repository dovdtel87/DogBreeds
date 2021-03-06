package com.dmgdavid2109.dogbreeds.breeds.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BreedResponse(
   @Json(name = "message") val message : Map<String, List<String>>
)
