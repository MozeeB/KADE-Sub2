package com.zeeb.footballmatchschedule.screen.home

import android.widget.AutoCompleteTextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.zeeb.footballmatchschedule.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class HomeFragmentTest{

    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testRecyclerViewBehaviour() {
        onView(withId(R.id.rv_league)).check(matches(isDisplayed()))
        Thread.sleep(3000)
        onView(withId(R.id.rv_league)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(2))
        onView(withId(R.id.rv_league)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))

        Thread.sleep(3000)
        onView(withId(R.id.searchDetailFragmentIV))
            .check(matches(isDisplayed()))
        onView(withId(R.id.searchDetailFragmentIV))
            .perform(click())

        Thread.sleep(1500)
        onView(withId(R.id.searchFragmentSV))
            .check(matches(isDisplayed()))
        onView(withId(R.id.searchFragmentSV)).perform(click())
        onView(withId(R.id.searchFragmentSV))
            .check(matches(isDisplayed()))

        onView(isAssignableFrom(AutoCompleteTextView::class.java))
            .perform(typeText("chelsea"))
        onView(isAssignableFrom(AutoCompleteTextView::class.java))
            .perform(pressImeActionButton())
        Thread.sleep(2000)
        onView(withId(R.id.searchFragmentRV)).check(matches(isDisplayed()))
        Thread.sleep(1500)
        onView(withId(R.id.searchFragmentRV)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))
        onView(withId(R.id.searchFragmentRV)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
        Thread.sleep(2000)






    }

}

