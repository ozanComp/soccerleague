@file:Suppress("DEPRECATION")

package com.sol.soccerleague

import com.sol.soccerleague.api.ApiResultWrapper
import com.sol.soccerleague.api.safeApiCall
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import java.io.IOException

@ExperimentalCoroutinesApi
class NetworkUnitTest : TestWatcher(){
    private val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }

    @Test
    fun whenReturnSuccessful() {
        runBlockingTest {
            val lambdaResult = true
            val result = safeApiCall(dispatcher) { lambdaResult }
            assertEquals(ApiResultWrapper.Success(lambdaResult), result)
        }
    }

    @Test
    fun whenThrowsIOException() {
        runBlockingTest {
            val result = safeApiCall(dispatcher) { throw IOException() }
            assertEquals(ApiResultWrapper.NetworkError, result)
        }
    }

    @Test
    fun whenThrowsUnknownException() {
        runBlockingTest {
            val result = safeApiCall(dispatcher) {
                throw IllegalStateException()
            }
            assertEquals(ApiResultWrapper.GenericError, result)
        }
    }
}