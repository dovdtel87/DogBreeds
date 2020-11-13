package com.dmgdavid2109.dogbreeds.breeds.domain.usecase

import com.dmgdavid2109.dogbreeds.breeds.data.mapper.BreedMapper
import com.dmgdavid2109.dogbreeds.breeds.data.model.BreedResponse
import com.dmgdavid2109.dogbreeds.breeds.domain.repository.BreedsRepository
import com.dmgdavid2109.dogbreeds.common.network.TestSchedulerProvider
import com.dmgdavid2109.dogbreeds.helpers.mock
import io.mockk.every
import io.reactivex.Single
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object GetAllBreedsUseCaseSpec : Spek({

    val breedsRepository: BreedsRepository by mock<BreedsRepository>()

    val useCase: GetAllBreedsUseCase by memoized {
        GetAllBreedsUseCase(
            breedsRepository,
            BreedMapper(),
            TestSchedulerProvider()
        )
    }

    val mapResponse = mapOf<String, List<String>>(
        "doberman" to emptyList(),
        "schnauzer" to listOf("giant", "miniature"),
        "shiba" to emptyList(),
        "weimaraner" to emptyList()
    )
    val breadResponse = BreedResponse(mapResponse)

    describe("invoke") {
        context("when it success") {
            beforeEachTest {
                every { breedsRepository.getBreeds() } returns Single.just(breadResponse)
            }

            it("retrieves a list of dog breeds") {
                val expectedResult =
                    listOf(
                        "doberman",
                        "schnauzer giant",
                        "schnauzer miniature",
                        "shiba",
                        "weimaraner"
                    )
                val testObserver = useCase.invoke().test()
                testObserver.assertValue(expectedResult)
            }
        }

        context("when it fails") {
            val exception = Exception("An exception")
            beforeEachTest {
                every { breedsRepository.getBreeds() } returns Single.error(exception)
            }

            it("returns an error") {
                val testObserver = useCase.invoke().test()
                testObserver.assertError(exception)
            }
        }
    }
})
