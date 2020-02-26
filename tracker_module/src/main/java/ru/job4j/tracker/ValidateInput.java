package ru.job4j.tracker;

public class ValidateInput implements Input {

    private Input input;

    public ValidateInput(Input input) {
        this.input = input;
    }

    @Override
    public String askStr(String question) {
        return input.askStr(question);
    }

    @Override
    public int askInt(String question, int max) {
        int value = -1;
        boolean invalid = true;
        while (invalid) {
            try {
                value = input.askInt(question, max);
                if (value < 0 || value >= max) {
                    throw new IllegalStateException(String.format("Out of about %s > [0, %s]", value, max));
                }
                invalid = false;
            } catch (IllegalStateException ex) {
                System.out.println("Please select key from menu.");
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid data again.");
            }
        }
        return value;
    }
}
