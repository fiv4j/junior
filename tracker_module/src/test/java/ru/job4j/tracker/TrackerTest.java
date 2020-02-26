package ru.job4j.tracker;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result, is(item));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1");
        tracker.add(previous);
        Item next = new Item("test2");
        next.setId(previous.getId());
        tracker.replace(previous.getId(), next);
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    @Test
    public void whenItemFoundById() {
        Tracker tracker = new Tracker();
        Item item = new Item("test");
        tracker.add(item);
        String testId = item.getId();
        Item result = tracker.findById(testId);
        assertThat(result, is(item));
    }

    @Test
    public void whenFoundAllItemsByName() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("test"));
        tracker.add(new Item("abfdu"));
        tracker.add(new Item("test"));
        tracker.add(new Item("test"));
        List<Item> result = tracker.findByName("test");
        assertThat(result.get(0).getName(), is("test"));
        assertThat(result.get(1).getName(), is("test"));
        assertThat(result.get(2).getName(), is("test"));
    }

    @Test
    public void whenDeleteFirst() {
        Tracker tracker = new Tracker();
        Item first = new Item("first");
        tracker.add(first);
        Item second = new Item("second");
        tracker.add(second);
        tracker.delete(first.getId());
        boolean notInTracker = tracker.findById(first.getId()) == null;
        assertThat(notInTracker, is(true));
    }

    @Test
    public void whenFoundAllItems() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("test"));
        tracker.add(new Item("abfdu"));
        tracker.add(new Item("test"));
        tracker.add(new Item("test"));
        List<Item> result = tracker.findAll();
        assertThat(result.size(), is(4));
    }
}
