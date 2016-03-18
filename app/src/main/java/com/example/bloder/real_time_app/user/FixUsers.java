package com.example.bloder.real_time_app.user;

import java.util.ArrayList;
import java.util.List;

public class FixUsers {

    public static List<User> fixUserInList() {
        return populateUser();
    }

    private static List<User> populateUser() {
        List<User> users = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            users .add(new User("Daniel", 18));
        }
        return users;
    }
}
