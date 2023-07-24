package com.example.universitiestest

import com.example.universitiestest.repository.UniversityRepository
import com.example.universitiestest.viewModel.UniversityViewModel
import com.example.universitiestest.viewModelFactory.UniversityViewModelFactory
import io.mockk.mockk
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import org.junit.Test

class UniversityViewModelFactoryTest {
    private val universityRepository: UniversityRepository = mockk()
    private val factory = UniversityViewModelFactory(universityRepository)

    @Test
    fun `create should return UniversityViewModel instance`() {
        val viewModel = factory.create(UniversityViewModel::class.java)

        assertNotNull(viewModel)
        assertTrue(viewModel is UniversityViewModel)
    }

//    @Test(expected = IllegalArgumentException::class)
//    fun `create should throw IllegalArgumentException for unknown ViewModel class`() {
//        factory.create(UnrelatedViewModel::class.java)
//    }
}
