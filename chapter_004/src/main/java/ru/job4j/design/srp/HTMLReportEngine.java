package ru.job4j.design.srp;

import java.util.function.Predicate;

public class HTMLReportEngine implements Report {

    private final Store store;

    public HTMLReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<html>").append(System.lineSeparator());
        text.append("<body>").append(System.lineSeparator());
        text.append("\t<h1>Employees</h1>").append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append("\t\t<h3>Employee</h3>").append(System.lineSeparator())
                    .append("\t\t\t<h6>Name ").append(employee.getName()).append("</h6>")
                    .append(System.lineSeparator())
                    .append("\t\t\t<h6>Hired ").append(employee.getHired()).append("</h6>")
                    .append(System.lineSeparator())
                    .append("\t\t\t<h6>Fired ").append(employee.getFired()).append("</h6>")
                    .append(System.lineSeparator())
                    .append("\t\t\t<h6>Salary ").append(employee.getSalary()).append("</h6>")
                    .append(System.lineSeparator());
        }
        text.append("</body>").append(System.lineSeparator());
        text.append("</html>").append(System.lineSeparator());

        return text.toString();
    }
}
