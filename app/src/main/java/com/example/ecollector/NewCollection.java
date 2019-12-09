package com.example.ecollector;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class NewCollection extends AppCompatActivity {
    private SettingsButtonFragment _settingFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_collection);
        _settingFragment = new SettingsButtonFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, _settingFragment)
                .commit();
    }
}
