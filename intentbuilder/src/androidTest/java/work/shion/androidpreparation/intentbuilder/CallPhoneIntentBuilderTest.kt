package work.shion.androidpreparation.intentbuilder

import android.Manifest
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
import androidx.test.rule.GrantPermissionRule
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CallPhoneIntentBuilderTest {

    @get:Rule
    val grantRule = GrantPermissionRule.grant(Manifest.permission.CALL_PHONE)

    @get:Rule
    val testRule = IntentsTestRule(CallPhoneSample::class.java)

    @Before
    fun setUp() {
        intending(hasAction(Intent.ACTION_CALL))
                .respondWith(Instrumentation.ActivityResult(Activity.RESULT_OK, null))
    }


    @Test
    fun empty() {
        val data = "Empty".let { it to CallPhoneSample.testData[it] }
        Espresso.onView(ViewMatchers.withText(data.first)).perform(ViewActions.click())
    }

    @Test
    fun success1() {
        val data = "Success1".let { it to CallPhoneSample.testData[it] }
        Espresso.onView(ViewMatchers.withText(data.first)).perform(ViewActions.click())
        Intents.intended(CoreMatchers.allOf(
                hasAction(Intent.ACTION_CALL),
                hasData(Uri.parse("tel:${data.second}"))
        ))
    }

    @Test
    fun success2() {
        val data = "Success2".let { it to CallPhoneSample.testData[it] }
        Espresso.onView(ViewMatchers.withText(data.first)).perform(ViewActions.click())
        Intents.intended(CoreMatchers.allOf(
                hasAction(Intent.ACTION_CALL),
                hasData(Uri.parse("tel:${data.second}"))
        ))
    }

    @Test
    fun unknown1() {
        val data = "Unknown1".let { it to CallPhoneSample.testData[it] }
        Espresso.onView(ViewMatchers.withText(data.first)).perform(ViewActions.click())
    }

    @Test
    fun unknown2() {
        val data = "Unknown2".let { it to CallPhoneSample.testData[it] }
        Espresso.onView(ViewMatchers.withText(data.first)).perform(ViewActions.click())
    }
}
