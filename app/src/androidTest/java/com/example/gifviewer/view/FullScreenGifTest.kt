package com.example.gifviewer.view

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.gifviewer.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class FullScreenGifAndroidTest {

    @get: Rule
    val activityRule = ActivityScenarioRule(FullScreenGif::class.java)

    @Test
    fun isFullScreenImageViewVisible_onActivityLaunch() {
        Espresso.onView(ViewMatchers.withId(R.id.fullScreenImageView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}