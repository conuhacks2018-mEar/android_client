package com.dan190.mear.data.contentProvider

import net.simonvt.schematic.annotation.*

/**
 * Created by Dan on 27/01/2018.
 */
class AlarmColumns {
    companion object {
        @DataType(DataType.Type.INTEGER)
        @PrimaryKey(onConflict = ConflictResolutionType.REPLACE)
        @AutoIncrement
        const val COLUMN_ID = "_id"

        @DataType(DataType.Type.TEXT)
        @NotNull
        const val COLUMN_TITLE = "title"

        @DataType(DataType.Type.INTEGER)
        @NotNull
        const val COLUMN_DATE = "date"

        @DataType(DataType.Type.TEXT)
        @NotNull
        const val COLUMN_MESSAGE = "message"
    }



}