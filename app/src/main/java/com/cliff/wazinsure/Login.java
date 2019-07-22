package com.cliff.wazinsure;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class Login extends AppCompatActivity {

    EditText username,user_password;
    Button login;
    TextView sign_up;
    String status = "ggg";
    Context context = this;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText)findViewById(R.id.username);
        user_password = (EditText)findViewById(R.id.user_password);
        login = (Button)findViewById(R.id.btn_login);
        sign_up = (TextView)findViewById(R.id.sign_up);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    try {
                        userLogin();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });

    }

//    private void userLogin() throws IOException, InterruptedException {
//        String user_name = username.getText().toString();
//        String password = user_password.getText().toString();
//        Context context = getApplicationContext();
//        PostService postService = new PostService();
//        postService.login(user_name, password);
//
//    }

    private void userLogin() throws IOException, InterruptedException {

        String user_name = username.getText().toString();
        String password = user_password.getText().toString();
        Context context = getApplicationContext();
        final PostService postService = new PostService();

        postService.login(user_name, password, new Callback(){

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                postService.processResults(response);

                    if (response.message().equals("success")){

                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    }


            }
        });
    }

}
