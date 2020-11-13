package com.dmgdavid2109.dogbreeds.breeds.data.mapper

import com.dmgdavid2109.dogbreeds.breeds.data.model.BreedResponse
import junit.framework.TestCase.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object BreedsMapperTest : Spek({

    val mapper: BreedMapper by memoized {
        BreedMapper()
    }

    val expectedResult =
        listOf(
            "doberman",
            "schnauzer giant",
            "schnauzer miniature",
            "shiba",
            "weimaraner"
        )

    val mapResponse = mapOf<String, List<String>>(
        "doberman" to emptyList(),
        "schnauzer" to listOf("giant", "miniature"),
        "shiba" to emptyList(),
        "weimaraner" to emptyList()
    )
    val breadResponse = BreedResponse(mapResponse)

    describe("map") {
        it("then returns the a list of Breeds") {
            val result = mapper.map(breadResponse)
            assertEquals(expectedResult, result)
        }
    }
})
