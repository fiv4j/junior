package ru.job4j.tracker;

import java.util.function.Consumer;

public class ExitAction extends BaseAction {

    public ExitAction(String actionName) {
        super(actionName);
    }

    @Override
    public boolean execute(Input input, ITracker tracker, Consumer<String> output) {
        return false;
    }
}
