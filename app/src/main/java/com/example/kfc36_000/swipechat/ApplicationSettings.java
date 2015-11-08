package com.example.kfc36_000.swipechat;

import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class ApplicationSettings extends AppCompatActivity {

    private ImageButton update;
    private EditText text;
    private ImageButton logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initializing values
        update = (ImageButton)findViewById(R.id.updateName);
        text = (EditText)findViewById(R.id.displayName);
        logout = (ImageButton)findViewById(R.id.logout);

        // TODO: set name in the data array


        // TODO: implement log out command
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

}
