package com.example.globechallenge

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.globechallenge.ui.splash.SplashActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SplashInstrumentationTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(SplashActivity::class.java)

    @Test
    fun test_isListFragmentHomeVisible_onAppLaunch_thenShowLoading() {
        onView(ViewMatchers.withId(R.id.splashImage))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}