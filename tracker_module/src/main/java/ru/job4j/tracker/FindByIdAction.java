package ru.job4j.tracker;

import java.util.function.Consumer;

public class FindByIdAction extends BaseAction {

    public FindByIdAction(String actionName) {
        super(actionName);
    }

    @Override
    public boolean execute(Input input, ITracker tracker, Consumer<String> output) {
        Item item = tracker.findById(input.askStr("Enter item's id you would find: "));
        if (item == null) {
            output.accept("Item not found.");
        } else {
            output.accept(item.getId() + " : " + item.getName());
        }
        return true;
    }
}
