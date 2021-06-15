package com.example.contact_gurindersingh_c0806087_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.contact_gurindersingh_c0806087_android.Room.PhoneBookRoomDatabase;
import com.example.contact_gurindersingh_c0806087_android.Room.User;


public class EditUser extends AppCompatActivity {

    private EditText etFirstName, etLastName, etPhone, etEmail, etaddress;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        int id = getIntent().getIntExtra("id",-1);
        user = PhoneBookRoomDatabase.getInstance(this).phoneBookDao().getUser(id);

        etFirstName = findViewById(R.id.et_fname);
        etLastName = findViewById(R.id.et_lname);
        etPhone = findViewById(R.id.et_phone);
        etEmail = findViewById(R.id.et_mail);
        etaddress = findViewById(R.id.et_address);
        if (user != null){
            etFirstName.setText(user.getfName());
            etLastName.setText(user.getlName());
            etPhone.setText(user.getContactNumber());
            etEmail.setText(user.getMail());
            etaddress.setText(user.gethAddress());
        }

        findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditUser.this.finish();
            }
        });
        findViewById(R.id.btn_update_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateInfo(id);
            }
        });
    }

    private void updateInfo(int id){
        String firstName = etFirstName.getText().toString().trim();
        String lastName = etLastName.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String addrss = etaddress.getText().toString().trim();

        if (firstName.isEmpty()) {
            etFirstName.setError("first name cannot be empty");
            etFirstName.requestFocus();
            return;
        }
        if (lastName.isEmpty()) {
            etLastName.setError("last name cannot be empty");
            etLastName.requestFocus();
            return;
        }if (phone.isEmpty()) {
            etPhone.setError("phone cannot be empty");
            etPhone.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            etEmail.setError("email cannot be empty");
            etEmail.requestFocus();
            return;
        }if (addrss.isEmpty()) {
            etaddress.setError("address cannot be empty");
            etaddress.requestFocus();
            return;
        }

        PhoneBookRoomDatabase.getInstance(this).phoneBookDao().updateUser(id,firstName,lastName,phone,email,addrss);
        this.finish();

    }
}