package com.example.contact_gurindersingh_c0806087_android;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.example.contact_gurindersingh_c0806087_android.Room.PhoneBookRoomDatabase;
import com.example.contact_gurindersingh_c0806087_android.Room.User;

import java.util.ArrayList;
import java.util.List;

public class UserListAdapter extends ArrayAdapter implements Filterable {

    private Context context;
    private List<User> userList;
    private List<User> totalUsers;
    private Activity parent;
    private int layoutResource;

    public UserListAdapter(Context context, int resource, List<User> userList, Activity parent) {
        super(context, resource, userList);
        this.context = context;
        this.layoutResource = resource;
        this.userList = userList;
        this.totalUsers = userList;
        this.parent = parent;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = convertView;
        if (v == null) v = inflater.inflate(layoutResource, null);

        TextView et_fName = v.findViewById(R.id.first_name_list);
        TextView et_lName = v.findViewById(R.id.last_name_list);
        TextView et_phone_number = v.findViewById(R.id.phone_number_list);

        et_fName.setText(userList.get(position).getfName());
        et_lName.setText(userList.get(position).getlName());
        et_phone_number.setText(userList.get(position).getContactNumber());
        v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                longPressFunction(userList.get(position));
                return false;
            }
        });
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,EditUser.class);
                intent.putExtra("id",userList.get(position).getId());
                context.startActivity(intent);
            }
        });
        v.findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUser(userList.get(position).getId());
            }
        });
        return v;
    }

        @Override
    public int getCount() {
        return userList.size();
    }


    private void deleteUser(int id){
        new AlertDialog.Builder(context)
                .setTitle("Delete User")
                .setMessage("Are you sure to delete this?")
                .setPositiveButton("Cancel",null)
                .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PhoneBookRoomDatabase.getInstance(context).phoneBookDao().deleteUser(id);
                        loadEmployees();
                    }
                })
                .show();
    }

    private void loadEmployees() {
        totalUsers = PhoneBookRoomDatabase.getInstance(context).phoneBookDao().getAllUser();
        userList = totalUsers;
        parent.setTitle(totalUsers.size()+" - Users");
        notifyDataSetChanged();
    }

    private void longPressFunction(User user){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.long_press_dialog, null);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        view.findViewById(R.id.call_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+user.getContactNumber()));
                context.startActivity(intent);
            }
        });

        view.findViewById(R.id.sms_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ActivityCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS) !=
                        PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{Manifest.permission.SEND_SMS}, 123);
                    return;
                }
                Uri uri = Uri.parse("smsto:"+user.getContactNumber());
                String message = "Sample message";
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.putExtra("sms_body", message);
                context.startActivity(intent);
            }
        });

        view.findViewById(R.id.mail_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"+user.getMail()));
                intent.putExtra(Intent.EXTRA_EMAIL, user.getMail());
                context.startActivity(intent);
            }
        });

    }

    @NonNull
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint==null || constraint.length()==0){
                    filterResults.count = totalUsers.size();
                    filterResults.values = totalUsers;
                }else{
                    List<User> resultList = new ArrayList<>();
                    String search = constraint.toString().toLowerCase();
                    for (User user : totalUsers){
                        if(user.getfName().toLowerCase().contains(search)){
                            resultList.add(user);
                        }
                        filterResults.count = resultList.size();
                        filterResults.values = resultList;
                    }
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                userList = (List<User>)results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }
}
