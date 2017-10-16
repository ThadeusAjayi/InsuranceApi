package com.example.thadeus.insuranceapiconnect;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.thadeus.insuranceapiconnect.Data.Users;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


    EditText name, email, address, phone;

    public static final String API = "http://insuranceapi.azurewebsites.net/interview";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        address = (EditText) findViewById(R.id.address);
        phone = (EditText) findViewById(R.id.phone);

        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uName  = name.getText().toString();
                String uEmail  = email.getText().toString();
                String uAddress  = address.getText().toString();
                String uPhone  = phone.getText().toString();


                Users user = new Users(uName, uEmail, uAddress, uPhone);

                new AsyncTask<String, Void, String>() {
                    @Override
                    protected String doInBackground(String... params) {

                        try {
                           return post(API, params[0]);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        Log.v("Result", s);
                    }
                }.execute(String.valueOf(user));
            }
        });
    }

    OkHttpClient client = new OkHttpClient();

    //GET
    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }


    //POST
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
