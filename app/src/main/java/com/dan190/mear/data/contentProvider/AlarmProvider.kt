package com.dan190.mear.data.contentProvider

import android.net.Uri
import net.simonvt.schematic.annotation.ContentProvider
import net.simonvt.schematic.annotation.ContentUri
import net.simonvt.schematic.annotation.TableEndpoint

/**
 * Created by Dan on 27/01/2018.
 */
@ContentProvider(
        authority = AlarmProvider.AUTHORITY,
        database = AlarmDatabase::class
)
class AlarmProvider {
    companion object {
        const val AUTHORITY = "com.dan190.mear.data.contentProvider.provider"

        @TableEndpoint(table = AlarmDatabase.ALARM_MESSAGES)
        class AlarmMessages {
            @ContentUri(
                    path = "messages",
                    type = "vnd.android.cursor.dir/messages",
                    defaultSort = AlarmColumns.COLUMN_DATE + "DESC")
            val CONTENT_URI = Uri.parse("content://${AUTHORITY}/messages")
        }
    }
}