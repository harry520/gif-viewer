package com.example.gifviewer.view

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.gifviewer.R
import com.example.gifviewer.view.GifsAdapter.GifsViewHolder
import com.tenor.android.core.constant.MediaCollectionFormats
import com.tenor.android.core.model.impl.Result
import com.tenor.android.core.response.impl.GifsResponse
import com.tenor.android.core.response.impl.TrendingGifResponse
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityAndroidTest {

    @get: Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun isGifsRecyclerViewVisible_onAppLaunch() {
        onView(withId(R.id.gifs_rv)).check(matches(isDisplayed()))
    }

}