package com.example.ecollector;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class SettingsButtonFragment extends Fragment  {
    Button button;
    public SettingsButtonFragment() {
        // Required empty public constructor
    }
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_settings_button, container, false);
        button = (Button)view.findViewById(R.id.button);
        button.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Settings.class);
                startActivity(intent);
            }
        });


        return inflater.inflate(R.layout.fragment_settings_button, container, false);
    }

/*    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), Settings.class);
        startActivity(intent);

    }*/
}
