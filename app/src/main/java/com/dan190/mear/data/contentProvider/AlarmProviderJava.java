package com.dan190.mear.data.contentProvider;

import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

/**
 * Created by Dan on 28/01/2018.
 */


@ContentProvider(
        authority = AlarmProviderJava.AUTHORITY,
        database = AlarmDatabaseJava.class)
public final class AlarmProviderJava {

    public static final String AUTHORITY = "com.dan190.mear.data.contentProvider";


    @TableEndpoint(table = AlarmDatabaseJava.ALARM_MESSAGES)
    public static class AlarmMessages {

        @ContentUri(
                path = "messages",
                type = "vnd.android.cursor.dir/messages",
                defaultSort = AlarmColumns.COLUMN_DATE + " DESC")
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/messages");
    }
}