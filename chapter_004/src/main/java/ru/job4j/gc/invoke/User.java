package ru.job4j.gc.invoke;

public class User {

    long id;
    int age;
    double salary;

    public User(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", age=" + age + ", salary=" + salary + '}';
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.printf("Deleted %s.%n", this);
    }
}
