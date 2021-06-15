package com.example.contact_gurindersingh_c0806087_android.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PhoneBookDao {

    @Insert
    void insertContact(User user);

    @Query("DELETE FROM phone_book")
    void deleteAllContacts();

    @Query("SELECT * FROM phone_book ORDER BY f_name")
    List<User> getAllUser();

    @Query("SELECT * FROM phone_book WHERE id=:id")
    User getUser(int id);

    @Query("UPDATE phone_book SET f_name = :f_name, l_name = :l_name, c_number = :phone, mail = :mail, h_address = :hAddress WHERE id = :id")
    int updateUser(int id,String f_name,String l_name,String phone,String mail,String hAddress);

    @Query("DELETE FROM phone_book WHERE id = :id" )
    int deleteUser(int id);
}
