package com.shubham.phonecallusingapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.HttpUrl;
import okhttp3.MultipartBody;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    Button callButton;
    public static String customer_number = "8936871984";
    public static String to_number= "8709350903";
    public static String callerId="09513886363";
    public static String exotel_url = "http://my.exotel.com/";
    public static String exotel_sid = "nitpatna2";
    public static String flow_id = "307632";
    public static String api_Key = "5bfdaf4e35869d41b0d45de79c23bb67bf16bef0d243254f";
    public static String api_token = "5505be7ed2567c3b13b43e149fd7591cbdee470040d5d1d1";
    public static String full_url=
//            "https://<5bfdaf4e35869d41b0d45de79c23bb67bf16bef0d243254f>:<5505be7ed2567c3b13b43e149fd7591cbdee470040d5d1d1><@api.in.exotel.com>/v1/Accounts/<nitpatna2>/Calls/connect.json";

            "https://5bfdaf4e35869d41b0d45de79c23bb67bf16bef0d243254f:" +
                    "5505be7ed2567c3b13b43e149fd7591cbdee470040d5d1d1@api.in.exotel.com/v1/Accounts/nitpatna2/Calls/connect.json";
      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callButton=findViewById(R.id.make_call);

    }
    public void makeCall(View view) {
//        final OkHttpClient client = new OkHttpClient().newBuilder().build();
//        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
//                .addFormDataPart("From", customer_number)
//                .addFormDataPart("To",to_number)
//                .addFormDataPart("Url",  exotel_url+exotel_sid + "/exoml/start_voice/" + flow_id).build()
//                ;
        final String credentials = Credentials.basic(api_Key, api_token);
//
//        final Request request = new Request.Builder()
//                .url(String.format("https://api.exotel.com/v1/Accounts/%s/Calls/connect.json", exotel_sid)).method("POST", body)
//                .addHeader("Authorization", credentials).addHeader("Content-Type", "application/json").build();




        new Thread(){
            public void run(){
                try {
                    OkHttpClient client = new OkHttpClient().newBuilder().build();
                    RequestBody bodyBuilder=new MultipartBody.Builder().setType(MultipartBody.FORM)
                            .addFormDataPart("From",customer_number)
                            .addFormDataPart("To", to_number)
                            .addFormDataPart("CallerId",callerId).build();

                    Request request=new Request.Builder()
                            .url(String.format("https://api.exotel.com/v1/Accounts/%s/Calls/connect.json",exotel_sid))
                            .method("POST",bodyBuilder)
                            .addHeader("Authorization", credentials).addHeader("Content-Type", "application/json").build();

                    Response response = client.newCall(request).execute();
                    Integer status= response.code();
                    Log.d("status", status.toString());
//                    if(status==200){
//                        Toast.makeText(MainActivity.this, "Successfully connected",Toast.LENGTH_SHORT).show();
//                    }
//                    else{
//                        Toast.makeText(MainActivity.this, "Connection failed",Toast.LENGTH_SHORT).show();
//                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }.start();



    }

}