package com.cliff.wazinsure;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.ContentValues.TAG;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class PostService extends Application {

    Login login = new Login();

    //    Signup new user
    public void register(String fullname, String id_no, String mobile_no, String email, Uri profileurl, String username, String password) throws IOException {

        MediaType MEDIA_TYPE = MediaType.parse("application/json");
        String url = Constatnts.BASE_URL + "/auth/register";

        OkHttpClient client = new OkHttpClient();


        JSONObject postdata = new JSONObject();
        try {
            postdata.put("fullname", fullname);
            postdata.put("id_no", id_no);
            postdata.put("mobile_no", mobile_no);
            postdata.put("email", email);
            postdata.put("profileurl", profileurl);
            postdata.put("username", username);
            postdata.put("password", password);

        } catch(JSONException e){
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(MEDIA_TYPE, postdata.toString());

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();

        System.out.println(url);

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String mMessage = e.getMessage();
                Log.w("failure Response", mMessage);
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String mMessage = response.body().string();
                Log.e(TAG, mMessage);
                JSONObject responseJSON = null;
                try {
                    responseJSON = new JSONObject(mMessage);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void login(String username, String password, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constatnts.BASE_URL+ "/auth/login").newBuilder();

        String url = urlBuilder.build().toString();

        Request request= new Request.Builder()
                .url(url)
                .addHeader("username", username)
                .addHeader("password", password)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);

    }

    public ArrayList<Customers> processResults(Response response) {
        ArrayList<Customers> allCustomers = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            JSONObject customersJSON = new JSONObject(jsonData);

            Log.v(TAG, "Response " + customersJSON.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return allCustomers;
    }













    public void startActivity(Intent intent) {
        startActivity(intent);
    }

    //  user details
    public void userDetails(String first_name, String last_name, String dob, String kra_pin, String occupation, String mobile_no,
                            String email, String location, String postal_address, String postal_code, String town, String country,
                            String photo_url, String nok_fullname, String nok_mobileno, String nok_relation, String agent_code) throws IOException {

        MediaType MEDIA_TYPE = MediaType.parse("application/json");
        String url = Constatnts.BASE_URL + "/api/customers";

        OkHttpClient client = new OkHttpClient();


        JSONObject postdata = new JSONObject();
        try {
            postdata.put("first_name", first_name);
            postdata.put("last_name", last_name);
            postdata.put("dob", dob);
            postdata.put("kra_pin", kra_pin);
            postdata.put("occupation", occupation);
            postdata.put("mobile_no", mobile_no);
            postdata.put("email", email);
            postdata.put("location", location);
            postdata.put("postal_address", postal_address);
            postdata.put("postal_code", postal_code);
            postdata.put("town", town);
            postdata.put("country", country);
            postdata.put("photo_url", photo_url);
            postdata.put("nok_fullname", nok_fullname);
            postdata.put("nok_mobileno", nok_mobileno);
            postdata.put("nok_relation", nok_relation);
            postdata.put("agent_code", agent_code);

        } catch(JSONException e){
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(MEDIA_TYPE, postdata.toString());

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();

        System.out.println(url);

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String mMessage = e.getMessage();
                Log.w("failure Response", mMessage);
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String mMessage = response.body().string();
                Log.e(TAG, mMessage);
                JSONObject responseJSON = null;
                try {
                    responseJSON = new JSONObject(mMessage);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}

