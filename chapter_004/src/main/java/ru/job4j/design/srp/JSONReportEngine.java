package ru.job4j.design.srp;

import java.util.function.Predicate;

public class JSONReportEngine implements Report {

    private final Store store;

    public JSONReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("{ \"employees\": [").append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append("\t\"employee\": {").append(System.lineSeparator())
                    .append("\t\t\"name\": \"").append(employee.getName()).append("\",").append(System.lineSeparator())
                    .append("\t\t\"hired\": ").append(employee.getHired()).append(",").append(System.lineSeparator())
                    .append("\t\t\"fired\": ").append(employee.getFired()).append(",").append(System.lineSeparator())
                    .append("\t\t\"salary\": ").append(employee.getSalary()).append(System.lineSeparator())
                    .append("\t\t},").append(System.lineSeparator());
        }
        text.append("\t]").append(System.lineSeparator());
        text.append("}").append(System.lineSeparator());

        return text.toString();
    }
}
