package com.example.ecollector;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Locale;

public class Settings extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        loadLocale();

        Button buttonPolish = findViewById(R.id.polishBtn);
        Button buttonEnglish = findViewById(R.id.englishBtn);

        buttonPolish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                changeLang("pl");
            }
        });

        buttonEnglish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                changeLang("en");
            }
        });
    }

    public void changeLang(String lang) {
        if (lang.equalsIgnoreCase(""))
            return;
        Locale myLocale = new Locale(lang);
        saveLocale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        getBaseContext().getResources()
                .updateConfiguration(config,getBaseContext()
                        .getResources()
                        .getDisplayMetrics());
    }

    public void loadLocale() {
        String langPref = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        String language = prefs.getString(langPref, "");
        changeLang(language);
    }

    private void saveLocale(String lang) {
        String langPref = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(langPref, lang);
        editor.apply();
    }
}
