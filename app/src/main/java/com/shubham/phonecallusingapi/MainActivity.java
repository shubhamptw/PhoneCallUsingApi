package com.shubham.phonecallusingapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.HttpUrl;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    public static String customer_number = "8936871984";
    public static String to_number= "8936871984";
    public static String callerId="08047192856";
    public static String exotel_sid = "nitpatna2";
    public static String api_key = "5bfdaf4e35869d41b0d45de79c23bb67bf16bef0d243254f";
    public static String api_token = "5505be7ed2567c3b13b43e149fd7591cbdee470040d5d1d1";
    public static String full_url="https://"+api_key+":"+api_token+"@api.exotel.in/v1/Accounts/"+exotel_sid+"/Calls/connect";
      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void makeCall(View view) {
        final String credentials = Credentials.basic(api_key, api_token);
        new Thread(){
            public void run(){
                try {
                    OkHttpClient client = new OkHttpClient().newBuilder().build();
                    RequestBody bodyBuilder=new MultipartBody.Builder().setType(MultipartBody.FORM)
                            .addFormDataPart("From",customer_number)     // added the three mandatory part
                            .addFormDataPart("To", to_number)
                            .addFormDataPart("CallerId",callerId).build();

                    Request request=new Request.Builder()
                            .url(full_url)
                            .method("POST",bodyBuilder)
                            .addHeader("Authorization", credentials)   // added authorization credentials i.e api key and token for verification
                            .addHeader("Content-Type", "application/json").build();


                    Response response = client.newCall(request).execute();
                    Integer status= response.code();
                    Log.d("status", status.toString());

                    //http okay
                    if(status==200){
                       runOnUiThread(new Runnable() {
                           @Override
                           public void run() {
                               Toast.makeText(MainActivity.this, "Connecting...",Toast.LENGTH_SHORT).show();
                           }
                       });
                    }
                    else{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "Connection failed",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    //to get response message
                    try {
                        final String res = response.body().string();
                        Log.d("Error: ", res);
                        final String finalRes = res;
                    } catch (IOException e) {

                        e.printStackTrace();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }.start();



    }

}