package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tracker implements ITracker {
    /**
     * Массив для хранение заявок.
     */
    private final List<Item> items = new ArrayList<>();

    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        Random rnd = new Random();
        return String.valueOf(System.currentTimeMillis() + rnd.nextLong());
    }

    /**
     * Метод ищет заявку в хранилище по ее id
     *
     * @param id id необходимой заявки
     * @return найденная заявка
     */
    public Item findById(String id) {
        Item result = null;
        int index = this.findIndexById(id);
        if (index != -1) {
            result = this.items.get(index);
        }
        return result;
    }

    /**
     * Метод ищет заявки в хранилище по имени заявки
     *
     * @param key название заявки, по которой производится поиск
     * @return список заявок, у которых name совпадает с key
     */
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        for (Item item: this.items) {
            if (item.getName().equals(key)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Метод возвращает ссылку на список-хранилище
     *
     * @return Ссылка на список-хранилище
     */
    public List<Item> findAll() {
        return this.items;
    }

    /**
     * Метод заменяет в списке-хранилище элемент с данным id
     * на новый элемент item
     *
     * @param id id элемента хранилища, подлежащего замене
     * @param item элемент, которым заменяется существующий
     * @return true, если замену удалось осуществить, иначе false
     */
    public boolean replace(String id, Item item) {
        boolean isReplaced = false;
        for (Item currentItem: this.items) {
            if (isReplaced) {
                break;
            }
            if (currentItem.getId().equals(id)) {
                currentItem.setName(item.getName());
                isReplaced = true;
            }
        }
        return isReplaced;
    }

    /**
     * Метод удаляет из списка заявок элемент
     * с данным id. При этом все элементы справа от
     * удаляемого элемента смещаются на одну ячейку
     * влево
     *
     * @param id удаляемый элемент
     * @return true, если удаление прошло успешно, иначе false
     */
    public boolean delete(String id) {
        int indexForDelete = this.findIndexById(id);
        if (indexForDelete != -1) {
            this.items.remove(indexForDelete);
        }
        return indexForDelete != -1;
    }

    /**
     * Метод ищет индекс элемента по его id
     *
     * @param id id искомого элемента
     * @return индекс элемента в списке, или -1, если не найден
     */
    private int findIndexById(String id) {
        int result = -1;
        for (Item currentItem: this.items) {
            if (currentItem.getId().equals(id)) {
                result = this.items.indexOf(currentItem);
                break;
            }
        }
        return result;
    }
}
