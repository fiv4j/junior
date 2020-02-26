package ru.job4j.tracker;

import java.util.function.Consumer;

public class DeleteAction extends BaseAction {

    public DeleteAction(String actionName) {
        super(actionName);
    }

    @Override
    public boolean execute(Input input, ITracker tracker, Consumer<String> output) {
        tracker.delete(input.askStr("Enter item's id you would delete: "));
        return true;
    }
}
