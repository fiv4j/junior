package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ITReport extends ReportEngine {

    public ITReport(Store store) {
        super(store);
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<html>").append(System.lineSeparator())
                .append("<body>").append(System.lineSeparator())
                .append("<h1>Name; Hired; Fired; Salary;</h1>").append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append("<h1>").append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append("</h1>").append(System.lineSeparator());
        }
        text.append("</body>").append(System.lineSeparator())
                .append("</html>").append(System.lineSeparator());
        return text.toString();
    }
}
