package com.example.universitiestest

import kotlinx.coroutines.test.runBlockingTest
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.universitiestest.model.University
import com.example.universitiestest.repository.UniversityRepository
import com.example.universitiestest.viewModel.UniversityViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
class UniversityViewModelTest {

    private lateinit var viewModel: UniversityViewModel
    private val universityRepository: UniversityRepository = mockk()
    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = UniversityViewModel(universityRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `getUniversitiesList should update universitiesList with the fetched data`() =
        testDispatcher.runBlockingTest {
            // Mock the API response
            val universities = listOf(
                University(
                    listOf("https://example.com"),
                    listOf("example.com"),
                    "Country",
                    "University",
                    "State",
                    "Code"
                )
            )
            coEvery { universityRepository.getUniversities() } returns universities

            // Call the function
            viewModel.getUniversitiesList()

            // Verify the result
            assertEquals(universities, viewModel.universitiesList.getOrAwaitValue())
        }

    @Test
    fun `getUniversitiesList should handle API error gracefully`() =
        testDispatcher.runBlockingTest {
            // Mock the API response
            coEvery { universityRepository.getUniversities() } throws Exception("API Error")

            // Call the function
            viewModel.getUniversitiesList()

            // Verify the result
            assertNull(viewModel.universitiesList.getOrAwaitValue())
        }

    private fun <T> LiveData<T>.getOrAwaitValue(
        time: Long = 2,
        timeUnit: TimeUnit = TimeUnit.SECONDS
    ): T? {
        var data: T? = null
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(t: T) {
                data = t
                latch.countDown()
                this@getOrAwaitValue.removeObserver(this)
            }
        }
        this.observeForever(observer)
        // Wait for the value to be set or timeout after the specified time
        latch.await(time, timeUnit)
        return data
    }
}
