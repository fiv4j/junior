package ru.job4j.maps;

import java.util.*;

public class UserApp {
    public static void main(String[] args) {
        User me = new User("Ivan", 3, 1990, 0, 14);
        User miniMe = new User("Ivan", 3, 1990, 0, 14);


        Map<User, Object> map = new HashMap<>();
        map.put(me, new Object());
        map.put(miniMe, new Object());

        System.out.println(map);
    }
}
