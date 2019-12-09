package com.example.ecollector;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.ecollector.DAOs.CollectionItemDAO;
import com.example.ecollector.DAOs.CollectionsDao;
import com.example.ecollector.DAOs.ItemDao;
import com.example.ecollector.model.CollectionItemModel;
import com.example.ecollector.model.CollectionModel;
import com.example.ecollector.model.ItemModel;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private Context context;
    private SQLiteDatabase db;
    private ItemDao itemDao;
    private CollectionsDao collectionDao;
    private CollectionItemDAO collectionItemDao;

    public DataManager(Context context) {
        this.context = context;
        SQLiteOpenHelper openHelper = new OpenHelper(this.context);
        db = openHelper.getWritableDatabase();
        itemDao = new ItemDao(db);
        collectionDao = new CollectionsDao(db);
        collectionItemDao = new CollectionItemDAO(db);
    }

    public ItemModel getItem(long itemId) {
        ItemModel item = itemDao.get(itemId);
        if (item != null) {
            List<CollectionModel> collections = new ArrayList<>();
            for(long id : collectionItemDao.getGroupIds(item.getId()))
                collections.add(getCollection(id));
            item.getCollections().addAll(collections);
        }
        return item;
    }

    public List<ItemModel> getItems() {
        return itemDao.getAll();
    }

    public long saveItem(ItemModel item) {
        long itemId = 0L;
        try {
            db.beginTransaction();
            itemId = itemDao.save(item);
            if (item.getCollections().size() > 0) {
                for (CollectionModel c : item.getCollections()) {
                    long collectionId = 0L;
                    CollectionModel dbCollection = collectionDao.find(c.getName());
                    if (dbCollection == null) {
                        collectionId = collectionDao.save(c);
                    } else {
                        collectionId = dbCollection.getId();
                    }
                    CollectionItemModel sgKey =
                            new CollectionItemModel(itemId, collectionId);
                    if (!collectionItemDao.exists(sgKey)) {
                        collectionItemDao.save(sgKey);
                    }
                }
            }
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            itemId = 0L;
        } finally {
            db.endTransaction();
        }
        return itemId;
    }

    public CollectionModel getCollection(long collectionId) {
        return collectionDao.get(collectionId);
    }
    public List<CollectionModel> getAllCollections() {
        return collectionDao.getAll();
    }
    public CollectionModel findCollection(String name) {
        return collectionDao.find(name);
    }
    public long saveGroup(CollectionModel collection) {
        return collectionDao.save(collection);
    }

}
