package ru.job4j.generics;

public class RoleStore<Role extends Base> extends AbstractStore<Role> {

    public RoleStore(int size) {
        super(size);
    }
}
