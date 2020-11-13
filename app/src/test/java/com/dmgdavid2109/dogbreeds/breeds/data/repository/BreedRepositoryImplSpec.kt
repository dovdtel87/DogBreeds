package com.dmgdavid2109.dogbreeds.breeds.data.repository

import com.dmgdavid2109.dogbreeds.breeds.data.api.BreedsApi
import com.dmgdavid2109.dogbreeds.breeds.data.model.BreedResponse
import com.dmgdavid2109.dogbreeds.breeds.data.model.ImagesResponse
import com.dmgdavid2109.dogbreeds.helpers.mock
import io.mockk.every
import io.reactivex.Single
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class BreedRepositoryImplSpec : Spek({

    val api: BreedsApi by mock<BreedsApi>()

    val repository: BreedsRepositoryImpl by memoized {
        BreedsRepositoryImpl(api)
    }

    val mapResponse = mapOf<String, List<String>>(
        "doberman" to emptyList(),
        "schnauzer" to listOf("giant", "miniature"),
        "shiba" to emptyList(),
        "weimaraner" to emptyList()
    )
    val breadResponse = BreedResponse(mapResponse)
    val listImages = listOf(
        "http://image1.png",
        "http://image2.png",
        "http://image3.png",
        "http://image4.png"
    )
    val imageResponse = ImagesResponse(listImages)

    describe("getBreeds") {
        beforeEachTest {
            every {
                api.retrieveBreeds()
            } returns Single.just(breadResponse)
        }

        it("return all breeds") {
            val testObserver =  repository.getBreeds().test()
            testObserver.assertValue(breadResponse)
        }
    }

    describe("getBreedImages") {
        beforeEachTest {
            every {
                api.retrieveBreedImages("schnauzer")
            } returns Single.just(imageResponse)
        }

        it("return list of images") {
            val testObserver =  repository.getBreedImages("schnauzer").test()
            testObserver.assertValue(imageResponse)
        }
    }
})
