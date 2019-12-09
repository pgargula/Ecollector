package com.example.ecollector.tables;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public final class ItemTable {
    public static final String TABLE_NAME = "Items";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String VALUE="value";

    public static void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + ItemTable.TABLE_NAME + " (");
        sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
        sb.append(ItemTable.NAME + " TEXT, ");
        sb.append(ItemTable.DESCRIPTION + " TEXT ");
        sb.append(ItemTable.VALUE + " REAL ");
        sb.append(");");
        db.execSQL(sb.toString());
    }
}
