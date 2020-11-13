package com.dmgdavid2109.dogbreeds.breeds.domain.usecase

import com.dmgdavid2109.dogbreeds.breeds.data.mapper.ImagesMapper
import com.dmgdavid2109.dogbreeds.breeds.data.model.ImagesResponse
import com.dmgdavid2109.dogbreeds.breeds.domain.repository.BreedsRepository
import com.dmgdavid2109.dogbreeds.common.network.TestSchedulerProvider
import com.dmgdavid2109.dogbreeds.helpers.mock
import io.mockk.every
import io.reactivex.Single
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object GetBreedImageUseCaseSpec : Spek({

    val breedsRepository: BreedsRepository by mock<BreedsRepository>()

    val useCase: GetBreedImagesUseCase by memoized {
        GetBreedImagesUseCase(
            breedsRepository,
            ImagesMapper(),
            TestSchedulerProvider()
        )
    }

    val listImages = listOf(
        "http://image1.png",
        "http://image2.png",
        "http://image3.png",
        "http://image4.png"
    )

    val imagesResponse = ImagesResponse(listImages)

    describe("invoke") {
        context("when it success") {
            beforeEachTest {
                every { breedsRepository.getBreedImages("doberman") } returns Single.just(imagesResponse)
            }

            it("retrieves a list of images") {
                val testObserver = useCase.invoke("doberman").test()
                testObserver.assertValue(listImages)
            }
        }

        context("when it fails") {
            val exception = Exception("An exception")
            beforeEachTest {
                every { breedsRepository.getBreedImages("doberman") } returns Single.error(exception)
            }

            it("returns an error") {
                val testObserver = useCase.invoke("doberman").test()
                testObserver.assertError(exception)
            }
        }
    }
})
