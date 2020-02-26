package ru.job4j.tracker;

import java.util.function.Consumer;

public class EditAction extends BaseAction {

    public EditAction(String actionName) {
        super(actionName);
    }

    @Override
    public boolean execute(Input input, ITracker tracker, Consumer<String> output) {
        String id = input.askStr("Enter item's id you would change: ");
        Item newItem = new Item(input.askStr("Enter new item's name: "));
        tracker.replace(id, newItem);
        return true;
    }
}
