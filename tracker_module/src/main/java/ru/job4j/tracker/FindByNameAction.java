package ru.job4j.tracker;

import java.util.List;
import java.util.function.Consumer;

public class FindByNameAction extends BaseAction {

    public FindByNameAction(String actionName) {
        super(actionName);
    }

    @Override
    public boolean execute(Input input, ITracker tracker, Consumer<String> output) {
        List<Item> result = tracker.findByName(input.askStr("Enter item's name you would find: "));
        if (result.isEmpty()) {
            output.accept("No items found.");
        } else {
            for (Item item: result) {
                output.accept(item.getId() + " : " + item.getName());
            }
        }
        return true;
    }
}
