package com.example.ecollector;

import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.ecollector.model.ItemModel;

public class Item extends AppCompatActivity {
    DataManager dataManager;
    private SettingsButtonFragment _settingFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        _settingFragment = new SettingsButtonFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, _settingFragment)
                .commit();

        dataManager = new DataManager(getApplicationContext());
        Integer temp;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            temp = extras.getInt("id");
            ItemModel item = dataManager.getItem(temp);
            Toast.makeText(getApplicationContext(),
                    "Position :"+temp , Toast.LENGTH_LONG)
                    .show();
            EditText editText = (EditText) findViewById(R.id.editText7);
            editText.getText().clear();
            editText.append(item.getName());
            EditText editText1 = (EditText) findViewById(R.id.editText5);
            editText1.getText().clear();
            editText1.append(item.getDescription());
            EditText editText2 = (EditText) findViewById(R.id.editText6);
            editText2.getText().clear();
            editText2.append(Double.toString(item.getValue()));


        }



    }
}
