package com.example.universitiestest

import com.example.universitiestest.model.University
import com.example.universitiestest.model.UniversityApiService
import com.example.universitiestest.repository.UniversityRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class UniversityRepositoryTest {

    private val universityApiService: UniversityApiService = mockk()
    private val repository = UniversityRepository(universityApiService)

    @Test
    fun `getUniversities should return the list of universities from the API`() = runBlocking {
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
        coEvery { universityApiService.getUniversities() } returns universities

        // Call the function
        val result = repository.getUniversities()

        // Verify the result
        assertEquals(universities, result)
    }
}