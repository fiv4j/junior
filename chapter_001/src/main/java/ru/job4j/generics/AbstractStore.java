package ru.job4j.generics;

public abstract class AbstractStore<T extends Base> implements Store<T> {
    private SimpleArray<T> store;

    protected AbstractStore(int size) {
        this.store = new SimpleArray<>(size);
    }

    public void add(T model) {
        this.store.add(model);
    }

    public T findById(String id) {
        T result = null;
        for (var elem : store) {
            if (elem.getId().equals(id)) {
                result = elem;
                break;
            }
        }
        return result;
    }

    private int getIndexById(String id) {
        var result = -1;
        var currentIndex = 0;
        for (var elem : store) {
            if (elem.getId().equals(id)) {
                result = currentIndex;
                break;
            }
            currentIndex++;
        }
        return result;
    }

    public boolean replace(String id, T model) {
        int index = getIndexById(id);
        if (index != -1) {
            store.set(index, model);
            return true;
        }
        return false;
    }

    public boolean delete(String id) {
        int index = getIndexById(id);
        if (index != -1) {
            store.remove(index);
            return true;
        }
        return false;
    }
}
