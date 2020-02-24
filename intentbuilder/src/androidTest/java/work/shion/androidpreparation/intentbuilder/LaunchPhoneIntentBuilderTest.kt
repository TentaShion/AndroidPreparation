package work.shion.androidpreparation.intentbuilder

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.net.Uri
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LaunchPhoneIntentBuilderTest {

    @get:Rule
    val testRule = IntentsTestRule(LaunchPhoneSample::class.java)

    @Before
    fun setUp() {
        intending(hasAction(Intent.ACTION_DIAL))
                .respondWith(Instrumentation.ActivityResult(Activity.RESULT_OK, null))
    }


    @Test
    fun testEmpty() {
        Espresso.onView(ViewMatchers.withText(LaunchPhoneSample.testEmpty.first)).perform(ViewActions.click())
        Intents.intended(CoreMatchers.allOf(
                hasAction(Intent.ACTION_DIAL)
        ))
    }

    @Test
    fun test1() {
        Espresso.onView(ViewMatchers.withText(LaunchPhoneSample.test1.first)).perform(ViewActions.click())
        Intents.intended(CoreMatchers.allOf(
                hasAction(Intent.ACTION_DIAL),
                hasData(Uri.parse("tel:${LaunchPhoneSample.test1.second}"))
        ))
    }

    @Test
    fun test2() {
        Espresso.onView(ViewMatchers.withText(LaunchPhoneSample.test2.first)).perform(ViewActions.click())
        Intents.intended(CoreMatchers.allOf(
                hasAction(Intent.ACTION_DIAL),
                hasData(Uri.parse("tel:${LaunchPhoneSample.test2.second}"))
        ))
    }
}