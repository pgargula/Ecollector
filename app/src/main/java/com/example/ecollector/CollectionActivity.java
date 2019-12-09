package com.example.ecollector;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.ecollector.model.ItemModel;

import java.util.ArrayList;

public class CollectionActivity extends AppCompatActivity {
    Button button;
    ArrayList<ItemModel> items = new ArrayList<>();
    private SettingsButtonFragment _settingFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        DataManager dataManager;
        _settingFragment = new SettingsButtonFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, _settingFragment)
                .commit();

        items.add(new ItemModel((long) 1,"aaa", "Lorem ipsum", (long) 2));
        items.add(new ItemModel((long) 2,"aadasda", "Lorem ipsums", (long) 3));

        Integer temp;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
             temp = extras.getInt("id");
            Toast.makeText(getApplicationContext(),
                    "Position :"+temp , Toast.LENGTH_LONG)
                    .show();
        }

        dataManager = new DataManager(getApplicationContext());
        button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent myIntent = new Intent(CollectionActivity.this,
                        NewItem.class);
                startActivity(myIntent);
            }
        });

        final ListView listView = findViewById(R.id.listView1);
        ArrayAdapter<ItemModel> itemsAdapter = new ArrayAdapter<>(this, R.layout.rowitem, dataManager.getItems());
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                int itemPosition     = position;
                Intent myIntent = new Intent(CollectionActivity.this,
                        Item.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                myIntent.putExtra("id", itemPosition);
                startActivity(myIntent);

            }
        });
    }

}
