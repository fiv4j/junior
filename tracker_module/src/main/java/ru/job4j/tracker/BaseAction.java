package ru.job4j.tracker;

public abstract class BaseAction implements UserAction {

    private String actionName;

    public BaseAction(String actionName) {
        this.actionName = actionName;
    }

    @Override
    public String info() {
        return this.actionName;
    }
}
