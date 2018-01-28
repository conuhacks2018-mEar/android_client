package com.dan190.mear.data.contentProvider;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

/**
 * Created by Dan on 28/01/2018.
 */

@Database(version = AlarmDatabaseJava.VERSION)
public class AlarmDatabaseJava {

    public static final int VERSION = 4;

    @Table(AlarmColumns.class)
    public static final String ALARM_MESSAGES = "squawk_messages";

}
