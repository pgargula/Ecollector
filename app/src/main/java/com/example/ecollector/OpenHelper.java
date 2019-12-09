package com.example.ecollector;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.ecollector.DAOs.CollectionItemDAO;
import com.example.ecollector.DAOs.CollectionsDao;
import com.example.ecollector.DAOs.ItemDao;
import com.example.ecollector.model.CollectionItemModel;
import com.example.ecollector.model.CollectionModel;
import com.example.ecollector.model.ItemModel;
import com.example.ecollector.tables.CollectionItemTable;
import com.example.ecollector.tables.CollectionTable;
import com.example.ecollector.tables.ItemTable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class OpenHelper  extends SQLiteOpenHelper {
    private Context context;


    OpenHelper(final Context context) {
        super(context, "bazka11", null, 11);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        ItemTable.onCreate(db);
        CollectionTable.onCreate(db);
        CollectionItemTable.onCreate(db);

        Random r = new Random();
        CollectionsDao cDao = new CollectionsDao(db);
        ItemDao iDao = new ItemDao(db);

        CollectionItemDAO ciDao = new CollectionItemDAO(db);

        List<ItemModel> items = Arrays.asList(new ItemModel((long) 1, "Gdańsk","Znaczek z Gdańska z 1888r.", 67),
                new ItemModel((long) 0, "100 lecie PZPN","znaczek wydany z okazji 100 lecia PZPN", 40),
                new ItemModel((long) 2, "Kraków","Znaczek z widokiem na wawel z 1800r", 100),
                new ItemModel((long) 3, "Dinar","Moneta o naminale 2 dinarów z 1590r", 400),
                new ItemModel((long) 4, "5 złoty","Moneta 5 złoty z 1995", 5),
                new ItemModel((long) 5, "1 Złoty"," 1 złoty z przełomu X wieku", 190),
                new ItemModel((long) 6, "Talar","Talar z 1281", 300));

        List<CollectionModel> collections = Arrays.asList(new CollectionModel((long) 0, "Modele"),
                new CollectionModel((long) 1, "Znaczki"),
                new CollectionModel((long) 2, "Monety"),
                new CollectionModel((long) 3, "Pocztówki"),
                new CollectionModel((long) 4, "Ksiązki"));


        for(ItemModel it : items) it.setId(iDao.save(it));
        for(CollectionModel co : collections) co.setId(cDao.save(co));

        for(ItemModel st: items) {
            Collections.shuffle(collections);
            int elements = r.nextInt(4) + 1;
            for(int i=0; i < elements; i++)
                ciDao.save(new CollectionItemModel(st.getId(), collections.get(i).getId()));
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
