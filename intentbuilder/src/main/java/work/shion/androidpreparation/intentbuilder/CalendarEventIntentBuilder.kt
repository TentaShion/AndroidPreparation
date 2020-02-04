package work.shion.androidpreparation.intentbuilder

import android.content.Intent
import android.provider.CalendarContract
import java.util.*

/**
 * カレンダーイベントの設定ビルダー
 *
 * ### 実装例
 * ``` kotlin
 * CalendarEventIntentBuilder().apply {
 *     setBeginTime(begin)
 *     setEndTime(end)
 *     setLocation(location)
 *     setTitle(title)
 * }.build()?.launch(activity!!)
 * ```
 *
 * ### 参考文献
 * [一般的なインテント | Android デベロッパー](https://developer.android.com/guide/components/intents-common?hl=ja#Clock)
 */
class CalendarEventIntentBuilder : IntentBuilder<ConsumerIntent>() {

    /**
     * イベントの開始時間（エポックからのミリ秒単位）。
     */
    var beginTime: Long? = null

    /**
     * イベントの説明。
     */
    var description: String? = null

    /**
     * 参加者のメールアドレスのコンマ区切りリスト。
     */
    var email: String? = null

    /**
     * イベントの終了時間（エポックからのミリ秒単位）。
     */
    var endTime: Long? = null

    /**
     * 終日のイベントかどうかを示すブール値。
     */
    var isAllDay: Boolean? = null

    /**
     * イベントの場所。
     */
    var location: String? = null

    /**
     * イベントのタイトル。
     */
    var title: String? = null


    /**
     * イベントの開始時間の設定
     */
    fun setBeginTime(input: Date) {
        beginTime = input.time * 1000
    }

    /**
     * イベントの終了時間の設定
     */
    fun setEndTime(input: Date) {
        endTime = input.time * 1000
    }


    /**
     * 与えられた設定からIntent を生成する
     */
    override fun build(): ConsumerIntent? {
        beginTime ?: endTime ?: return null

        val intent = ConsumerIntent().apply {
            action = Intent.ACTION_INSERT
            data = CalendarContract.Events.CONTENT_URI
        }
        beginTime?.also { intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, it) }
        description?.also { intent.putExtra(CalendarContract.Events.DESCRIPTION, it) }
        email?.also { intent.putExtra(Intent.EXTRA_EMAIL, it) }
        endTime?.also { intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, it) }
        isAllDay?.also { intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, it) }
        location?.also { intent.putExtra(CalendarContract.Events.EVENT_LOCATION, it) }
        title?.also { intent.putExtra(CalendarContract.Events.TITLE, it) }
        return intent
    }
}
