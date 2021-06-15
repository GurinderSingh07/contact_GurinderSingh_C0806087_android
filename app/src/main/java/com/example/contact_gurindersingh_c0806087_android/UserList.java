package com.example.contact_gurindersingh_c0806087_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.widget.ListView;


import com.example.contact_gurindersingh_c0806087_android.Room.PhoneBookRoomDatabase;
import com.example.contact_gurindersingh_c0806087_android.Room.User;

import java.util.List;

public class UserList extends AppCompatActivity {

    private List<User> userList;
    private ListView listView;
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        listView = findViewById(R.id.lv_user);
        searchView = findViewById(R.id.search_bar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadUsers();
    }

    private void loadUsers(){
        userList = PhoneBookRoomDatabase.getInstance(this).phoneBookDao().getAllUser();
        if (userList!=null)
            this.setTitle(userList.size()+" - Users");
        else
            this.setTitle(0+" - Users");

        UserListAdapter adapter = new UserListAdapter(this,R.layout.user_list_layout,userList,this);
        listView.setAdapter(adapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}