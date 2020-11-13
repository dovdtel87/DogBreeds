package com.dmgdavid2109.dogbreeds.breeds.data.mapper

import com.dmgdavid2109.dogbreeds.breeds.data.model.ImagesResponse
import junit.framework.TestCase.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object ImagesMapperTest : Spek({

    val mapper: ImagesMapper by memoized {
        ImagesMapper()
    }

    val listImages = listOf(
        "http://image1.png",
        "http://image2.png",
        "http://image3.png",
        "http://image4.png"
    )

    val imagesResponse = ImagesResponse(listImages)

    describe("map") {
        it("then returns the a list of Images") {
            val result = mapper.map(imagesResponse)
            assertEquals(listImages, result)
        }
    }
})
