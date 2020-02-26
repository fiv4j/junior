package ru.job4j.tracker;

import java.util.function.Consumer;

public class StubAction extends BaseAction {
    private boolean call = false;

    public StubAction(String actionName) {
        super(actionName);
    }

    @Override
    public boolean execute(Input input, ITracker tracker, Consumer<String> output) {
        call = true;
        return false;
    }

    public boolean isCall() {
        return call;
    }
}
