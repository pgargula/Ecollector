package com.example.ecollector;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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

        _settingFragment = new SettingsButtonFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, _settingFragment)
                .commit();

        items.add(new ItemModel((long) 1,"aaa", "Lorem ipsum", 2));
        items.add(new ItemModel((long) 2,"aadasda", "Lorem ipsums", 3));

        button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent myIntent = new Intent(CollectionActivity.this,
                        NewItem.class);
                startActivity(myIntent);
            }
        });

        final ListView listView = findViewById(R.id.listView1);
        ArrayAdapter<ItemModel> itemsAdapter = new ArrayAdapter<>(this, R.layout.rowitem, items);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent myIntent = new Intent(CollectionActivity.this,
                        Item.class);
                startActivity(myIntent);

            }
        });
    }

}
