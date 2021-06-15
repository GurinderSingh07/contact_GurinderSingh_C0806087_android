package com.example.contact_gurindersingh_c0806087_android.Room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "phone_book")
public class User {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    int id;

    @NonNull
    @ColumnInfo(name = "f_name")
    String fName;

    @NonNull
    @ColumnInfo(name = "l_name")
    String lName;

    @NonNull
    @ColumnInfo(name = "c_number")
    String contactNumber;

    @NonNull
    @ColumnInfo(name = "mail")
    String mail;

    @NonNull
    @ColumnInfo(name = "h_address")
    String hAddress;

    public User(@NonNull String fName, @NonNull String lName, @NonNull String contactNumber, @NonNull String mail, @NonNull String hAddress) {
        this.fName = fName;
        this.lName = lName;
        this.contactNumber = contactNumber;
        this.mail = mail;
        this.hAddress = hAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getfName() {
        return fName;
    }

    public void setfName(@NonNull String fName) {
        this.fName = fName;
    }

    @NonNull
    public String getlName() {
        return lName;
    }

    public void setlName(@NonNull String lName) {
        this.lName = lName;
    }

    @NonNull
    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(@NonNull String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @NonNull
    public String getMail() {
        return mail;
    }

    public void setMail(@NonNull String mail) {
        this.mail = mail;
    }

    @NonNull
    public String gethAddress() {
        return hAddress;
    }

    public void sethAddress(@NonNull String hAddress) {
        this.hAddress = hAddress;
    }
}

