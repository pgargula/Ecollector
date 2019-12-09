package com.example.ecollector.tables;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class CollectionItemTable {
    public static final String TABLE_NAME = "Collection_Item";
    public static final String IDCOLLECTION = "idCollection";
    public static final String IDITEM = "idItem";

    public static void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + CollectionItemTable.TABLE_NAME + " (");
        sb.append(CollectionItemTable.IDITEM + " INTEGER NOT NULL, ");
        sb.append(CollectionItemTable.IDCOLLECTION + " INTEGER NOT NULL, ");
        sb.append("FOREIGN KEY(" + CollectionItemTable.IDITEM
                + ") REFERENCES " + ItemTable.TABLE_NAME + "("
                + BaseColumns._ID + "), ");
        sb.append("FOREIGN KEY(" + CollectionItemTable.IDCOLLECTION
                + ") REFERENCES " + CollectionTable.TABLE_NAME + "("
                + BaseColumns._ID + ") , ");
        sb.append("PRIMARY KEY ( " + CollectionItemTable.IDITEM + ", "
                + CollectionItemTable.IDCOLLECTION + ")");
        sb.append(");");
        db.execSQL(sb.toString());
    }
}
