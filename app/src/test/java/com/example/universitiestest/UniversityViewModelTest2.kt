package com.example.universitiestest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.universitiestest.model.University
import com.example.universitiestest.repository.UniversityRepository
import com.example.universitiestest.viewModel.UniversityViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.junit.Assert.assertEquals

@ExperimentalCoroutinesApi
class UniversityViewModelTest2 {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var mockRepository: UniversityRepository

    private lateinit var universityViewModel: UniversityViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.initMocks(this)
        universityViewModel = UniversityViewModel(mockRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }


    @Test
    fun testGetUniversitiesList() = testDispatcher.runBlockingTest {
        val universitiesTestData = listOf(
            University(
                listOf("https://example.com"),
                listOf("example.com"),
                "Country",
                "University",
                "State",
                "Code"
            )
        )
        `when`(mockRepository.getUniversities()).thenReturn(universitiesTestData)

        universityViewModel.getUniversitiesList()

        assertEquals(universitiesTestData, universityViewModel.universitiesList.value)
    }
}
