package com.example.ecollector.DAOs;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;
import com.example.ecollector.model.ItemModel;
import com.example.ecollector.tables.ItemTable;

import java.util.ArrayList;
import java.util.List;

public class ItemDao implements IDao<ItemModel> {

    private static final String INSERT =
            "insert into " + ItemTable.TABLE_NAME
                    + "(" + ItemTable.NAME + ", " + ItemTable.DESCRIPTION + ", " + ItemTable.VALUE + ") values (?, ?, ?)";
    private SQLiteDatabase db;
    private SQLiteStatement insertStatement;

    public ItemDao(SQLiteDatabase db) {
        this.db = db;
        insertStatement = db.compileStatement(ItemDao.INSERT);
    }

    @Override
    public long save(ItemModel entity) {
        insertStatement.clearBindings();
        insertStatement.bindString(1, entity.getName());
        insertStatement.bindString(2, entity.getDescription());
        insertStatement.bindDouble(3, entity.getValue());
        return insertStatement.executeInsert();
    }

    private ItemModel buildItemFromCursor(Cursor c) {
        ItemModel item = null;
        if (c != null) {
            item = new ItemModel(c.getLong(0), c.getString(1), c.getString(2), c.getLong(2));
        }
        return item;
    }

    @Override
    public ItemModel get(long id) {
        ItemModel item = null;
        Cursor c = db.query(ItemTable.TABLE_NAME, new String[] {
                        BaseColumns._ID, ItemTable.NAME,
                        ItemTable.DESCRIPTION, ItemTable.VALUE },
                BaseColumns._ID + " = ?", new String[] { String.valueOf(id) }, null, null, null, "1");
        if (c.moveToFirst()) {
            item = this.buildItemFromCursor(c);
        }
        if (!c.isClosed()) {
            c.close();
        }
        return item;
    }

    @Override
    public List<ItemModel> getAll() {
        List<ItemModel> list = new ArrayList<ItemModel>();
        Cursor c = db.query(ItemTable.TABLE_NAME, new String[] {
                        BaseColumns._ID, ItemTable.NAME,
                        ItemTable.DESCRIPTION, ItemTable.VALUE },
                null, null, null, null, ItemTable.NAME, null);
        if (c.moveToFirst()) {
            do {
                ItemModel item = this.buildItemFromCursor(c);
                if (item != null) {
                    list.add(item);
                }
            } while (c.moveToNext());
        }
        if (!c.isClosed()) {
            c.close();
        }
        return list;
    }

}
