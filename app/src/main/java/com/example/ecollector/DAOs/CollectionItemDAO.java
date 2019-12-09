package com.example.ecollector.DAOs;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;
import com.example.ecollector.model.CollectionItemModel;
import com.example.ecollector.tables.CollectionItemTable;

import java.util.ArrayList;
import java.util.List;

public class CollectionItemDAO implements IDao<CollectionItemModel>{

    private static final String INSERT =
            "insert into " + CollectionItemTable.TABLE_NAME
                    + "(" + CollectionItemTable.IDITEM + ", " + CollectionItemTable.IDCOLLECTION + ") values (?, ?)";

    private SQLiteDatabase db;
    private SQLiteStatement insertStatement;

    public CollectionItemDAO(SQLiteDatabase db) {
        this.db = db;
        insertStatement = db.compileStatement(CollectionItemDAO.INSERT);
    }

    private CollectionItemModel buildCollectionItemFromCursor(Cursor c) {
        CollectionItemModel collectionItem = null;
        if (c != null) {
            collectionItem = new CollectionItemModel(c.getLong(1), c.getLong(2));
        }
        return collectionItem;
    }

    @Override
    public long save(CollectionItemModel entity) {
        insertStatement.clearBindings();
        insertStatement.bindLong(1, entity.getIdStudent());
        insertStatement.bindLong(2, entity.getIdGroup());

        return insertStatement.executeInsert();
    }

    public List<Long> getGroupIds(long studentId) {
        List<Long> list = new ArrayList<Long>();
        Cursor c = db.query(CollectionItemTable.TABLE_NAME, new String[] {
                        CollectionItemTable.IDITEM,
                        CollectionItemTable.IDCOLLECTION },
                CollectionItemTable.IDITEM + " = ?", new String[] { String.valueOf(studentId) },
                null, null, null);
        if (c.moveToFirst()) {
            do {
                long groupId = 0;
                if (c != null) {
                    groupId = c.getLong(1);
                }
                if (groupId != 0) {
                    list.add(groupId);
                }
            } while (c.moveToNext());
        }
        if (!c.isClosed()) {
            c.close();
        }
        return list;
    }

    @Override
    public List<CollectionItemModel> getAll() {
        List<CollectionItemModel> list = new ArrayList<CollectionItemModel>();
        Cursor c = db.query(CollectionItemTable.TABLE_NAME, new String[] {
                        CollectionItemTable.IDITEM,
                        CollectionItemTable.IDCOLLECTION },
                null, null, null, null, CollectionItemTable.IDITEM, null);
        if (c.moveToFirst()) {
            do {
                CollectionItemModel student_Group = this.buildCollectionItemFromCursor(c);
                if (student_Group != null) {
                    list.add(student_Group);
                }
            } while (c.moveToNext());
        }
        if (!c.isClosed()) {
            c.close();
        }
        return list;
    }

    public boolean exists(CollectionItemModel sg) {
        long student_GroupId = 0L;
        String sql = "select " + CollectionItemTable.IDITEM + " from " + CollectionItemTable.TABLE_NAME
                + " where " + CollectionItemTable.IDITEM + " = ? and "
                + CollectionItemTable.IDCOLLECTION + " = ? limit 1";
        Cursor c = db.rawQuery(sql,
                new String[]
                        { Long.toString(sg.getIdStudent()), Long.toString(sg.getIdGroup()) });
        if (c.moveToFirst()) {
            student_GroupId = c.getLong(0);
        }
        if (!c.isClosed()) {
            c.close();
        }
        return this.get(student_GroupId) != null;
    }

    @Override
    public CollectionItemModel get(long id) {
        CollectionItemModel student_Group = null;
        Cursor c = db.query(CollectionItemTable.TABLE_NAME, new String[] {
                        CollectionItemTable.IDITEM,
                        CollectionItemTable.IDCOLLECTION },
                BaseColumns._ID + " = ?", new String[] { String.valueOf(id) }, null, null, null, "1");
        if (c.moveToFirst()) {
            student_Group = this.buildCollectionItemFromCursor(c);
        }
        if (!c.isClosed()) {
            c.close();
        }
        return student_Group;
    }
}
