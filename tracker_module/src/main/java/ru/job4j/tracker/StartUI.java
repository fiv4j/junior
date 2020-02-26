package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class StartUI {
    private final Input input;
    private final ITracker tracker;
    private final Consumer<String> output;

    public StartUI(Input input, ITracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }

    public void init(List<UserAction> actions) {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = this.input.askInt("Select: ", actions.size());
            UserAction action = actions.get(select);
            run = action.execute(this.input, this.tracker, this.output);
        }
    }

    private void showMenu(List<UserAction> actions) {
        output.accept("Menu.");
        for (int index = 0; index < actions.size(); index++) {
            output.accept(index + ". " + actions.get(index).info());
        }
    }

    public static void main(String[] args) {
        UserAction[] actionsArray = {
                new CreateAction("Create Item."),
                new ShowAllAction("Show all."),
                new EditAction("Edit item."),
                new DeleteAction("Delete item."),
                new FindByIdAction("Find item by id."),
                new FindByNameAction("Find item by name."),
                new ExitAction("Exit.")
        };
        List<UserAction> actions = new ArrayList<>(Arrays.asList(actionsArray));
        new StartUI(
                new ValidateInput(new ConsoleInput()),
                new Tracker(),
                System.out::println
        ).init(actions);
    }
}
