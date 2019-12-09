package com.example.ecollector.DAOs;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;
import com.example.ecollector.model.CollectionModel;
import com.example.ecollector.tables.CollectionTable;

import java.util.ArrayList;
import java.util.List;

public class CollectionsDao implements IDao<CollectionModel>{
    private static final String INSERT =
            "insert into " + CollectionTable.TABLE_NAME
                    + "(" + CollectionTable.NAME + ") values (?)";
    private SQLiteDatabase db;
    private SQLiteStatement insertStatement;

    public CollectionsDao(SQLiteDatabase db) {
        this.db = db;
        insertStatement = db.compileStatement(CollectionsDao.INSERT);
    }

    @Override
    public long save(CollectionModel entity) {
        insertStatement.clearBindings();
        insertStatement.bindString(1, entity.getName());

        return insertStatement.executeInsert();
    }

    private CollectionModel buildCollectionFromCursor(Cursor c) {
        CollectionModel collection = null;
        if (c != null) {
            collection = new CollectionModel(c.getLong(0), c.getString(1));
        }
        return collection;
    }

    public CollectionModel find(String name) {
        long collectionId = 0L;
        String sql = "select _id from " + CollectionTable.TABLE_NAME
                + " where upper(" + CollectionTable.NAME + ") = ? limit 1";
        Cursor c = db.rawQuery(sql,
                new String[]
                        { name.toUpperCase() });
        if (c.moveToFirst()) {
            collectionId = c.getLong(0);
        }
        if (!c.isClosed()) {
            c.close();
        }
        return this.get(collectionId);
    }

    @Override
    public CollectionModel get(long id) {
        CollectionModel collection = null;
        Cursor c = db.query(CollectionTable.TABLE_NAME, new String[] {
                        BaseColumns._ID, CollectionTable.NAME},
                BaseColumns._ID + " = ?", new String[] { String.valueOf(id) }, null, null, null, "1");
        if (c.moveToFirst()) {
            collection = this.buildCollectionFromCursor(c);
        }
        if (!c.isClosed()) {
            c.close();
        }
        return collection;
    }

    @Override
    public List<CollectionModel> getAll() {
        List<CollectionModel> list = new ArrayList<CollectionModel>();
        Cursor c = db.query(CollectionTable.TABLE_NAME, new String[] {
                        BaseColumns._ID, CollectionTable.NAME},
                null, null, null, null, CollectionTable.NAME, null);
        if (c.moveToFirst()) {
            do {
                CollectionModel collection = this.buildCollectionFromCursor(c);
                if (collection != null) {
                    list.add(collection);
                }
            } while (c.moveToNext());
        }
        if (!c.isClosed()) {
            c.close();
        }
        return list;
    }

}
