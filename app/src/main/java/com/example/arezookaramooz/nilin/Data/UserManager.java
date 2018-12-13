package com.example.arezookaramooz.nilin.Data;

import android.content.Context;

import java.util.ArrayList;

public class UserManager {

    private static UserManager instance;
    private ArrayList<User> users = new ArrayList<User>();

    private UserManager(Context context) {
    }

    public static UserManager getInstance(Context context) {
        if (instance == null)
            instance = new UserManager(context);
        return instance;
    }

    public void addUsers(ArrayList<User> arrayList) {
        users.addAll(arrayList);
    }

    public String getNameWithId(int id) {

        String name = null;

        for (int i = 0; i < users.size(); i++) {

            if (users.get(i).getId() == id)
                name = users.get(i).getName();
        }
        return name;
    }
}
