package com.example.contact_gurindersingh_c0806087_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.contact_gurindersingh_c0806087_android.Room.PhoneBookRoomDatabase;
import com.example.contact_gurindersingh_c0806087_android.Room.User;

public class MainActivity extends AppCompatActivity {

    private Button createBtn,showListBtn;
    private EditText et_fName,et_lName,et_phone,et_mail,et_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_fName = findViewById(R.id.et_fname);
        et_lName = findViewById(R.id.et_lname);
        et_phone = findViewById(R.id.et_phone);
        et_mail = findViewById(R.id.et_mail);
        et_address = findViewById(R.id.et_address);
        createBtn = findViewById(R.id.btn_add_user);
        showListBtn = findViewById(R.id.btn_list_user);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUser();
            }
        });
        showListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UserList.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        clearFields();
    }

    private void saveUser(){
        String fName = et_fName.getText().toString().trim();
        String lName = et_lName.getText().toString().trim();
        String phoneNumber = et_phone.getText().toString().trim();
        String mailAddress = et_mail.getText().toString().trim();
        String hAddress = et_address.getText().toString().trim();

        if (fName.isEmpty()) {
            et_fName.setError("name field cannot be empty");
            et_fName.requestFocus();
            return;
        }
        if (lName.isEmpty()) {
            et_lName.setError("salary cannot be empty");
            et_lName.requestFocus();
            return;
        }
        if (phoneNumber.isEmpty()) {
            et_phone.setError("salary cannot be empty");
            et_phone.requestFocus();
            return;
        }
        if (mailAddress.isEmpty()) {
            et_mail.setError("salary cannot be empty");
            et_mail.requestFocus();
            return;
        }
        if (hAddress.isEmpty()) {
            et_address.setError("salary cannot be empty");
            et_address.requestFocus();
            return;
        }


        InputMethodManager imm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et_address.getWindowToken(), 0);

        User user = new User(fName,lName,phoneNumber,mailAddress,hAddress);
        PhoneBookRoomDatabase.getInstance(this).phoneBookDao().insertContact(user);
        Toast.makeText(this,"User Added Successfully",Toast.LENGTH_SHORT).show();
        clearFields();
    }

    private void clearFields(){

        et_fName.setText("");
        et_lName.setText("");
        et_phone.setText("");
        et_mail.setText("");
        et_address.setText("");
    }
}