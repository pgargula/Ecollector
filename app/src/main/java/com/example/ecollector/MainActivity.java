package com.example.ecollector;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.ecollector.model.CollectionModel;

import java.util.ArrayList;
import java.util.zip.DataFormatException;

public class MainActivity extends AppCompatActivity {

    DataManager dataManager;
    Button button;
    ArrayList<CollectionModel> collections = new ArrayList<>();
    private SettingsButtonFragment _settingFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _settingFragment = new SettingsButtonFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, _settingFragment)
                .commit();

        dataManager = new DataManager(getApplicationContext());
        collections.add(new CollectionModel((long) 1,"znaczki"));
        collections.add(new CollectionModel((long) 2,"pocztówki"));
        collections.add(new CollectionModel((long) 3,"monety"));
        collections.add(new CollectionModel((long) 4,"książki"));

        button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent myIntent = new Intent(MainActivity.this,
                        NewCollection.class);
                startActivity(myIntent);
            }
        });

        final ListView listView = findViewById(R.id.listView1);
        ArrayAdapter<CollectionModel> itemsAdapter = new ArrayAdapter<>(this, R.layout.row, dataManager.getAllCollections());
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent myIntent = new Intent(MainActivity.this,
                        CollectionActivity.class);
                startActivity(myIntent);

            }
        });

    }
}
