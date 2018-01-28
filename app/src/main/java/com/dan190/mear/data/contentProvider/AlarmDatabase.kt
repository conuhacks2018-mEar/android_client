package com.dan190.mear.data.contentProvider

import net.simonvt.schematic.annotation.Database
import net.simonvt.schematic.annotation.Table

/**
 * Created by Dan on 27/01/2018.
 */
@Database(version = AlarmDatabase.VERSION)
class AlarmDatabase {
    companion object {
        const val VERSION = 1

        @Table(AlarmColumns::class)
        const val ALARM_MESSAGES = "alarm_messagse"
    }
}