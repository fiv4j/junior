package ru.job4j.gc.invoke;

public class GCInvoker {

    public static void main(String[] args) {
        int numberOfInstances = 140_000;

        System.out.printf("Create %d elements of User.%n", numberOfInstances);
        System.out.println("=========== Start ==============");
        for (int i = 0; i < numberOfInstances; i++) {
            new User(i);
        }
        System.out.println("============== End ===============");
    }
}
