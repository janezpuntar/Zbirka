package com.zbirka.janez.zbirka_app.activity.splash

import android.support.test.espresso.ViewInteraction
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.test.suitebuilder.annotation.LargeTest
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent

import com.zbirka.janez.R
import com.zbirka.janez.zbirka_app.activity.main.MainActivity

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.closeSoftKeyboard
import android.support.test.espresso.action.ViewActions.pressImeActionButton
import android.support.test.espresso.action.ViewActions.replaceText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import com.zbirka.janez.rotateScreen
import org.hamcrest.Matchers.allOf

@RunWith(AndroidJUnit4::class)
class RotateActivityTest {

    @get:Rule
    val mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun rotateActivityTest() {
        val appCompatEditText = onView(
                allOf(withId(R.id.et_input),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()))
        appCompatEditText.perform(replaceText("rotate"), closeSoftKeyboard())

        rotateScreen(mActivityTestRule.activity)

        val appCompatEditText2 = onView(
                allOf(withId(R.id.et_input), withText("rotate"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()))
        appCompatEditText2.perform(pressImeActionButton())

        val textView = onView(
                allOf(withId(R.id.detail_search_fragment), withText("rotate"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()))
        textView.check(matches(withText("rotate")))

        rotateScreen(mActivityTestRule.activity)
    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return (parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position))
            }
        }
    }
}
