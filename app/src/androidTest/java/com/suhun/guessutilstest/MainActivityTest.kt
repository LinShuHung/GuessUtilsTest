package com.suhun.guessutilstest

import android.content.res.Resources
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    @Rule
    @JvmField
    val activityScenarioRule = ActivityScenarioRule<MainActivity>(MainActivity::class.java)

    @Test
    fun guessWrongtCheck(){
        lateinit var resource:Resources
        var secret:Int = 0
        activityScenarioRule.scenario.onActivity {
            secret = it.secretNumber.secretRandom
            resource = it.resources
        }
        for(num in 1..100){
            if(num != secret){
                var message = if(secret > num) resource.getString(R.string.bigger) else resource.getString(R.string.smaller)
                onView(ViewMatchers.withId(R.id.userInputEditView)).perform(ViewActions.clearText())
                onView(ViewMatchers.withId(R.id.userInputEditView)).perform(ViewActions.typeText(num.toString()))
                onView(ViewMatchers.withId(R.id.guessButton)).perform(ViewActions.click())
                onView(ViewMatchers.withText(message)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                onView(ViewMatchers.withText("Ok")).perform(ViewActions.click())
            }
        }
    }
}