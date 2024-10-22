package telran.employees;

import java.util.Arrays;
import java.util.HashSet;

import telran.view.InputOutput;
import telran.view.Item;

public class AddEmployeeItems {
    private static Company company;

    final static long MIN_ID = 1;
    final static long MAX_ID = 999999;
    final static int MIN_SALARY = 1000;
    final static int MAX_SALARY = 100000;
    final static String[] DEPARTMENTS = { "QA", "Development", "Audit", "HR" };
    final static HashSet<String> departmentsSet = new HashSet<String>(Arrays.asList(DEPARTMENTS));
    // for Wage Employee ext Employee
    final static int MIN_WAGE = 10;
    final static int MAX_WAGE = 100;
    final static int MIN_HOURS = 0;
    final static int MAX_HOURS = 300;
    // Sales Person ext Wage Empl
    final static float MIN_PERCENT = 0.01f;
    final static float MAX_PERCENT = 0.5f;
    final static long MIN_SALES = 0;
    final static long MAX_SALES = 99999;
    // Manager ext Empl
    final static float MIN_FACTOR = 1;
    final static float MAX_FACTOR = 5;

    public static Item[] getItemsAddEmployee(Company company) {
        AddEmployeeItems.company = company;
        Item[] res = {
                Item.of("Hire Employee", AddEmployeeItems::hireEmployee),
                Item.of("Hire Wage Employee", AddEmployeeItems::hireWageEmployee),
                Item.of("Hire Sales Person", AddEmployeeItems::hireSalesPerson),
                Item.of("Hire Manager", AddEmployeeItems::hireManager),
                Item.ofExit()
        };
        return res;
    }

    static Employee initEmployee(InputOutput io) {
        long id = io.readNumberRange(String.format("Enter ID value in the range [%d-%d]", MIN_ID, MAX_ID),
        "Wrong ID value", MIN_ID, MAX_ID).longValue();
        int basicSalary = io.readNumberRange(String.format("Enter salary value in the range [%d-%d]", MIN_SALARY, MAX_SALARY),
        "Wrong salary value", MIN_SALARY, MAX_SALARY).intValue();
        String department = io.readStringOptions("Enter department from " + departmentsSet, "Must be one out from " + departmentsSet, 
        departmentsSet);
        Employee employee = new Employee(id, basicSalary, department);
        return employee;
    }

    static void hireEmployee(InputOutput io) {
        company.addEmployee(initEmployee(io));
    }

    static void hireWageEmployee(InputOutput io) {
        Employee employee = initEmployee(io);
        int wage = io.readNumberRange(String.format("Enter Wage Employee wage in the range [%d-%d]", MIN_WAGE, MAX_WAGE),
        "Wrong wage value", MIN_WAGE, MAX_WAGE).intValue();
        int hours = io.readNumberRange(String.format("Enter Wage Employee hours in the range [%d-%d]",MIN_HOURS, MAX_HOURS),
        "Wrong hours value", MIN_HOURS, MAX_HOURS).intValue();
        WageEmployee wageEmployee = new WageEmployee(employee.getId(), employee.computeSalary(),
                employee.getDepartment(), wage, hours);
        company.addEmployee(wageEmployee);
    }

    static void hireSalesPerson(InputOutput io) {
        Employee employee = initEmployee(io);
        int wage = io.readNumberRange(String.format("Enter Sales Person wage in the range [%d-%d]", MIN_WAGE, MAX_WAGE),
        "Wrong wage value", MIN_WAGE, MAX_WAGE).intValue();
        int hours = io.readNumberRange(String.format("Enter Sales Person hours in the range [%d-%d]",MIN_HOURS, MAX_HOURS),
        "Wrong hours value", MIN_HOURS, MAX_HOURS).intValue();
        float percent = io.readNumberRange(String.format("Enter Sales Person percent in the range [%d-%d]", "", MIN_PERCENT, MAX_PERCENT),
        "Wrong percent value",MIN_PERCENT, MAX_PERCENT).floatValue();
        long sales = io.readNumberRange(String.format("Enter Sales Person sales in the range [%d-%d]", MIN_SALES, MAX_SALES),
        "Wrong sales value", MIN_SALES, MAX_SALES).longValue();
        SalesPerson salesPerson = new SalesPerson(employee.getId(), employee.computeSalary(), employee.getDepartment(),
                wage, hours, percent, sales);
        company.addEmployee(salesPerson);
    }

    static void hireManager(InputOutput io) {
        Employee employee = initEmployee(io);
        float factor = io.readNumberRange(String.format("Enter Manager factor in the range [%d-%d]", "", MIN_FACTOR, MAX_FACTOR),
        "Wrong factor value",MIN_FACTOR, MAX_FACTOR).floatValue();
        Manager manager = new Manager(employee.getId(), employee.computeSalary(), employee.getDepartment(), factor);
        company.addEmployee(manager);
    }
}
