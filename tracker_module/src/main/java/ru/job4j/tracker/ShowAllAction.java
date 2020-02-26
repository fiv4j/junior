package ru.job4j.tracker;

import java.util.function.Consumer;

public class ShowAllAction extends BaseAction {

    public ShowAllAction(String actionName) {
        super(actionName);
    }

    @Override
    public boolean execute(Input input, ITracker tracker, Consumer<String> output) {
        for (Item item : tracker.findAll()) {
            output.accept(item.getId() + " : " + item.getName());
        }
        return true;
    }
}
