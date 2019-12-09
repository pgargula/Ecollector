package com.example.ecollector;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.ecollector.DAOs.CollectionItemDAO;
import com.example.ecollector.DAOs.CollectionsDao;
import com.example.ecollector.DAOs.ItemDao;
import com.example.ecollector.model.ItemModel;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private Context context;
    private SQLiteDatabase db;
    private ItemDao itemDao;
    private CollectionsDao collectionDao;
    private CollectionItemDAO collectionItemDao;

 /*   public DataManager(Context context) {
        this.context = context;
        SQLiteOpenHelper openHelper = new OpenHelper(this.context);
        db = openHelper.getWritableDatabase();
        itemDao = new ItemDao(db);
        collectionDao = new CollectionsDao(db);
        collectionItemDao = new CollectionItemDAO(db);
    }

    public ItemModel getStudent(long itemId) {
        ItemModel item = itemDao.get(itemId);
        if (item != null) {
            List<Collection> groups = new ArrayList<>();
            for(long id : collectionItemDao.getGroupIds(item.getId()))
                groups.add(getGroup(id));
            item.getGroups().addAll(groups);
        }
        return item;
    }

    public List<Student> getStudents() {
        return itemDao.getAll();
    }

    public long saveStudent(Student student) {
        long studentId = 0L;
        try {
            db.beginTransaction();
            studentId = itemDao.save(student);
            if (student.getGroups().size() > 0) {
                for (Group c : student.getGroups()) {
                    long groupId = 0L;
                    Group dbGroup = collectionDao.find(c.getName());
                    if (dbGroup == null) {
                        groupId = collectionDao.save(c);
                    } else {
                        groupId = dbGroup.getId();
                    }
                    Student_Group sgKey =
                            new Student_Group(studentId, groupId);
                    if (!collectionItemDao.exists(sgKey)) {
                        collectionItemDao.save(sgKey);
                    }
                }
            }
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            studentId = 0L;
        } finally {
            db.endTransaction();
        }
        return studentId;
    }

    public Group getGroup(long groupId) {
        return collectionDao.get(groupId);
    }
    public List<Group> getAllGroups() {
        return collectionDao.getAll();
    }
    public Group findGroup(String name) {
        return collectionDao.find(name);
    }
    public long saveGroup(Group group) {
        return collectionDao.save(group);
    }*/
}
