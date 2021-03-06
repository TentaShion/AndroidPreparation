package work.shion.androidpreparation.intentbuilder

import android.content.Intent
import android.net.Uri
import work.shion.androidpreparation.intentbuilder.basis.CallIntent
import work.shion.androidpreparation.intentbuilder.basis.IntentBuilder
import work.shion.androidpreparation.intentbuilder.basis.PhoneNumberContract

/**
 * Call voice mail directly
 *
 * ### Example1
 * ``` kotlin
 * val intent = CallVoiceMailIntentBuilder().apply {
 *     phoneNumber = "phone number"
 * }.build()
 *
 * val checkResult = ContextCompat.checkSelfPermission(from, Manifest.permission.CALL_PHONE)
 * if (checkResult == PackageManager.PERMISSION_GRANTED) {
 *     intent.start(from)
 * }
 * ```
 *
 * ### Example2
 * ``` kotlin
 * val intent = CallVoiceMailIntentBuilder()
 *     .phoneNumber("phone number")
 *     .build()
 *
 * val checkResult = ContextCompat.checkSelfPermission(from, Manifest.permission.CALL_PHONE)
 * if (checkResult == PackageManager.PERMISSION_GRANTED) {
 *     intent.start(from)
 * }
 * ```
 *
 * ### References
 * * [Common Intents | Android Developers](https://developer.android.com/guide/components/intents-common#DialPhone)
 */
class CallVoiceMailIntentBuilder : IntentBuilder<CallIntent>(), PhoneNumberContract {

    private val scheme = "voicemail"


    override var phoneNumber: String? = null
        set(value) {
            if (value == null || value.isNotBlank()) {
                phoneUri = value?.let { Uri.parse("$scheme:$it") }
                field = value
            }
        }

    override var phoneUri: Uri? = null
        private set


    fun phoneNumber(input: String?) = apply { phoneNumber = input }


    /**
     * Generate an intent by builder's settings.
     */
    override fun build() = CallIntent().apply {
        action = Intent.ACTION_CALL
        phoneUri?.also { data = it }
    }
}
