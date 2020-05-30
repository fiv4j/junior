package ru.job4j.design.srp;

import java.util.function.Predicate;

public class XMLReportEngine implements Report {

    private final Store store;

    public XMLReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<employees>").append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append("\t<employee>").append(System.lineSeparator())
                    .append("\t\t<name>").append(employee.getName()).append("</name>").append(System.lineSeparator())
                    .append("\t\t<hired>").append(employee.getHired()).append("</hired>").append(System.lineSeparator())
                    .append("\t\t<fired>").append(employee.getFired()).append("</fired>").append(System.lineSeparator())
                    .append("\t\t<salary>").append(employee.getSalary()).append("</salary>").append(System.lineSeparator())
                    .append("\t</employee>").append(System.lineSeparator());
        }
        text.append("</employees>").append(System.lineSeparator());

        return text.toString();
    }
}
