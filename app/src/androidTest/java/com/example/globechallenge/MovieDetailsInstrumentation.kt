package com.example.globechallenge

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieDetailsInstrumentation {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_isListFragmentHomeVisible_onAppLaunch_thenShowLoading() {
        onView(withId(R.id.homeProgressBar)).check(matches(isDisplayed()))
    }

    @Test
    fun test_isListFragmentHomeVisible_onAppLaunch_thenShowToolbar() {
        onView(withId(R.id.includeToolbar)).check(matches(isDisplayed()))
    }

    @Test
    fun test_isListFragmentHomeVisible_onAppLaunch_whenInternetIsDisable_thenErrorDialog() {
        onView(withId(R.id.errorDialog)).check(matches(isDisplayed()))
    }
}