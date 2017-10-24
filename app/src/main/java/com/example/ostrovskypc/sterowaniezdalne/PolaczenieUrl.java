package com.example.ostrovskypc.sterowaniezdalne;

import android.os.AsyncTask;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ostrovskyPC on 24.10.2017.
 */

public class PolaczenieUrl extends AsyncTask<Void, Void, Void> {

    private String adresIp, numerPin, stan;

    public PolaczenieUrl(String adresIp, String numerPin, String stan) {
        this.adresIp = adresIp;
        this.numerPin = numerPin;
        this.stan = stan;
    }

    @Override
    protected Void doInBackground(Void... voids){

        try {
            noweTest(adresIp,numerPin,stan);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void noweTest(String adresIp, String pinNum, String stan) throws IOException {

        String komunikat = null;


        komunikat = "http://" + adresIp + "/=" + pinNum + "=" + stan + "$";

        URL url = new URL(komunikat);

        HttpURLConnection pol = (HttpURLConnection) url.openConnection();
        try {
            pol.setRequestMethod("POST");
            pol.setDoOutput(true);
            pol.setChunkedStreamingMode(0);
            OutputStream out = new BufferedOutputStream(pol.getOutputStream());
            pol.getResponseMessage();
            out.close();
            System.out.println(out);
        }finally {
            pol.disconnect();
        }

    }
}
