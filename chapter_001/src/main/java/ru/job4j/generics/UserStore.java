package ru.job4j.generics;

public class UserStore<User extends Base> extends AbstractStore<User> {

    public UserStore(int size) {
        super(size);
    }
}
