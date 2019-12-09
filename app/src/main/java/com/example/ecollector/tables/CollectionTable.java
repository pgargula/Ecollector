package com.example.ecollector.tables;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public final class CollectionTable {
    public static final String TABLE_NAME = "Collections";
    public static final String NAME = "name";

    public static void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + CollectionTable.TABLE_NAME + " (");
        sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
        sb.append(CollectionTable.NAME + " TEXT ");
        sb.append(");");
        db.execSQL(sb.toString());
    }
}
