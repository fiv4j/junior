package ru.job4j.design.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import java.util.Calendar;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expected.toString()));
    }

    @Test
    public void whenAccountingReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new AccountingReport(store);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append("$").append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expected.toString()));
    }

    @Test
    public void whenITReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ITReport(store);
        StringBuilder expected = new StringBuilder()
                .append("<html>").append(System.lineSeparator())
                .append("<body>").append(System.lineSeparator())
                .append("<h1>Name; Hired; Fired; Salary;</h1>").append(System.lineSeparator())
                .append("<h1>")
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append("</h1>")
                .append(System.lineSeparator())
                .append("</body>").append(System.lineSeparator())
                .append("</html>").append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expected.toString()));
    }

    @Test
    public void whenHRReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee ivan = new Employee("Ivan", now, now, 100);
        store.add(ivan);
        Employee mary = new Employee("Mary", now, now, 200);
        store.add(mary);
        Report engine = new HRReport(store);
        StringBuilder expected = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(mary.getName()).append(";")
                .append(mary.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(ivan.getName()).append(";")
                .append(ivan.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expected.toString()));
    }

    @Test
    public void whenXMLReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new XMLReportEngine(store);

        StringBuilder expected = new StringBuilder()
                .append("<employees>").append(System.lineSeparator())
                .append("\t<employee>").append(System.lineSeparator())
                .append("\t\t<name>").append(worker.getName()).append("</name>").append(System.lineSeparator())
                .append("\t\t<hired>").append(worker.getHired()).append("</hired>").append(System.lineSeparator())
                .append("\t\t<fired>").append(worker.getFired()).append("</fired>").append(System.lineSeparator())
                .append("\t\t<salary>").append(worker.getSalary()).append("</salary>").append(System.lineSeparator())
                .append("\t</employee>").append(System.lineSeparator())
                .append("</employees>").append(System.lineSeparator());

        assertThat(engine.generate(em -> true), is(expected.toString()));
    }

    @Test
    public void whenHTMLReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new HTMLReportEngine(store);

        StringBuilder expected = new StringBuilder()
                .append("<html>").append(System.lineSeparator())
                .append("<body>").append(System.lineSeparator())
                .append("\t<h1>Employees</h1>").append(System.lineSeparator())
                .append("\t\t<h3>Employee</h3>").append(System.lineSeparator())
                .append("\t\t\t<h6>Name ").append(worker.getName()).append("</h6>").append(System.lineSeparator())
                .append("\t\t\t<h6>Hired ").append(worker.getHired()).append("</h6>").append(System.lineSeparator())
                .append("\t\t\t<h6>Fired ").append(worker.getFired()).append("</h6>").append(System.lineSeparator())
                .append("\t\t\t<h6>Salary ").append(worker.getSalary()).append("</h6>").append(System.lineSeparator())
                .append("</body>").append(System.lineSeparator())
                .append("</html>").append(System.lineSeparator());

        assertThat(engine.generate(em -> true), is(expected.toString()));
    }

    @Test
    public void whenJSONReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new JSONReportEngine(store);

        StringBuilder expected = new StringBuilder()
                .append("{ \"employees\": [").append(System.lineSeparator())
                .append("\t\"employee\": {").append(System.lineSeparator())
                .append("\t\t\"name\": \"").append(worker.getName()).append("\",").append(System.lineSeparator())
                .append("\t\t\"hired\": ").append(worker.getHired()).append(",").append(System.lineSeparator())
                .append("\t\t\"fired\": ").append(worker.getFired()).append(",").append(System.lineSeparator())
                .append("\t\t\"salary\": ").append(worker.getSalary()).append(System.lineSeparator())
                .append("\t\t},").append(System.lineSeparator())
                .append("\t]").append(System.lineSeparator())
                .append("}").append(System.lineSeparator());

        assertThat(engine.generate(em -> true), is(expected.toString()));
    }
}
