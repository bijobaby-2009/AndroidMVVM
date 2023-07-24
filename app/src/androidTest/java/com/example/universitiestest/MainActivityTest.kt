package com.example.universitiestest

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
    }


    @Test
    fun recyclerView_displayedProperly() {
        onView(withId(R.id.recyclerView))
            .check(matches(isDisplayed()))
    }

    @Test
    fun recyclerView_hasItems() {
        onView(withId(R.id.recyclerView))
            .check(matches(hasMinimumChildCount(1)))
    }

    @Test
    fun recyclerView_itemTextDisplayed() {
        val exampleUniversityName = "MaryWood University"
        val exampleCountryName = "United States"

        onView(withText(exampleUniversityName))
            .check(matches(isDisplayed()))

        onView(withText(exampleCountryName))
            .check(matches(isDisplayed()))
    }

    @Test
    fun recyclerView_itemClick() {
        // Assuming there's an item at position 0
        onView(withId(R.id.recyclerView))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        // Verify the desired behavior after item click
        // For example, check if a new activity or dialog is displayed
        // You can use Espresso's onView() with appropriate ViewMatchers to verify the behavior
    }
}
