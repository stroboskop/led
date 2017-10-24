package com.example.ostrovskypc.sterowaniezdalne;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String ipDoWys;
    protected Button buttonBlueOn, buttonBlueOff, buttonGreenOn, buttonGreenOff, buttonRedOn, buttonRedOff, buttonSave;
    public EditText editTextIP;
    public final static String wartIp = "adresIP";
    private static final String PREFERENCE_NAME = "myPreference";

    private SharedPreferences sharedPreferences;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        final NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();


        sharedPreferences = getSharedPreferences(PREFERENCE_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        buttonBlueOn = (Button) findViewById(R.id.buttonBlueOn);
        buttonBlueOff = (Button) findViewById(R.id.buttonBlueOff);
        buttonGreenOn = (Button) findViewById(R.id.buttonGreenOn);
        buttonGreenOff = (Button) findViewById(R.id.buttonGreenOff);
        buttonRedOn = (Button) findViewById(R.id.buttonRedOn);
        buttonRedOff = (Button) findViewById(R.id.buttonRedOff);
        buttonSave = (Button) findViewById(R.id.buttonSave);

        editTextIP = (EditText) findViewById(R.id.editTextIP);
        editTextIP.setText(sharedPreferences.getString(PREFERENCE_NAME,""));

        buttonSave.setOnClickListener((view) -> {
            editor.putString(PREFERENCE_NAME, editTextIP.getText().toString());
            editor.commit();
            Toast.makeText(getApplicationContext(), editTextIP.getText().toString(), Toast.LENGTH_SHORT).show();
        });

        ipDoWys = editTextIP.getText().toString().trim();


        if (networkInfo != null && networkInfo.isConnected()) {
            buttonBlueOn.setOnClickListener((view) -> {
                new PolaczenieUrl(ipDoWys, "16", "1").execute();
            });

            buttonBlueOff.setOnClickListener((view) -> {
                new PolaczenieUrl(ipDoWys, "16", "0").execute();
            });

            buttonGreenOn.setOnClickListener((view) -> {
                new PolaczenieUrl(ipDoWys, "05", "1").execute();
            });

            buttonGreenOff.setOnClickListener((view) -> {
                new PolaczenieUrl(ipDoWys, "05", "0").execute();
            });

            buttonRedOn.setOnClickListener((view) -> {
                new PolaczenieUrl(ipDoWys, "04", "1").execute();
            });

            buttonRedOff.setOnClickListener((view) -> {
                new PolaczenieUrl(ipDoWys, "04", "0").execute();
            });
        }
    }

}