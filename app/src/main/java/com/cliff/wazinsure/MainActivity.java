package com.cliff.wazinsure;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    EditText first_name, last_name, dob, kra_pin, occupation, mobile_no, email, location,
            postal_address, postal_code, town, country, nok_fullname, nok_mobileno, nok_relation,
            agent_code;
    Button selectImage, submit_details;
    String imageUrl;
    private final int PICK_IMAGE_REQUEST =71;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getIds();
        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });
        submit_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    newCustomer();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // selecting image
    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "SelectPicture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK &&
                data != null && data.getData() !=null){
            imageUrl=data.getData().toString();
        }
    }

    private void getIds() {
        first_name = (EditText) findViewById(R.id.first_name);
        last_name = (EditText) findViewById(R.id.last_name);
        dob = (EditText) findViewById(R.id.dob);
        kra_pin = (EditText) findViewById(R.id.kra_pin);
        occupation = (EditText) findViewById(R.id.occupation);
        mobile_no = (EditText) findViewById(R.id.mobile_no);
        email = (EditText) findViewById(R.id.email);
        location = (EditText) findViewById(R.id.location);
        postal_address = (EditText) findViewById(R.id.postal_address);
        postal_code = (EditText) findViewById(R.id.postal_code);
        town = (EditText) findViewById(R.id.town);
        country = (EditText) findViewById(R.id.country);
        nok_fullname = (EditText) findViewById(R.id.nok_fullname);
        nok_mobileno = (EditText) findViewById(R.id.nok_mobileno);
        nok_relation = (EditText) findViewById(R.id.nok_relation);
        agent_code = (EditText) findViewById(R.id.agent_code);
        selectImage = (Button) findViewById(R.id.selectImage);
        submit_details = (Button) findViewById(R.id.submit_details);
    }

    private void newCustomer() throws IOException {

        PostService postService = new PostService();

        String firstname = first_name.getText().toString();
        String lastname = last_name.getText().toString();
        String dofb = dob.getText().toString();
        String krapin = kra_pin.getText().toString();
        String Occupation = occupation.getText().toString();
        String mobileno = mobile_no.getText().toString();
        String e_mail = email.getText().toString();
        String Location = location.getText().toString();
        String postaladdress = postal_address.getText().toString();
        String postalcode = postal_code.getText().toString();
        String usertown = town.getText().toString();
        String Country = country.getText().toString();
        String photo_url = imageUrl;
        String nokfullname = nok_fullname.getText().toString();
        String nokmobileno = nok_mobileno.getText().toString();
        String nokrelation = nok_relation.getText().toString();
        String agentcode = agent_code.getText().toString();

        postService.userDetails(firstname,lastname,dofb,krapin, Occupation, mobileno,
                 e_mail,  Location,  postaladdress,  postalcode, usertown,  Country,
                 photo_url,  nokfullname,  nokmobileno,  nokrelation,  agentcode);
    }


}
