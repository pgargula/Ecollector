package com.example.ecollector;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private  SettingsButton _settingFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _settingFragment = new SettingsButton();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, _settingFragment)
                .commit();



    }
}
