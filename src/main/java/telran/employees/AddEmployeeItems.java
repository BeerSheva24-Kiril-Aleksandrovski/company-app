package telran.employees;

import static telran.employees.CompanyConfigProperties.*;
import telran.view.*;

public class AddEmployeeItems {
    static Company company;
    public static Item[] getItemsAddEmployee(Company company) {
        AddEmployeeItems.company = company;
        Item[] res = {
                Item.of("Hire Employee", AddEmployeeItems::hireEmployee),
                Item.of("Hire Wage Employee", AddEmployeeItems::hireWageEmployee),
                Item.of("Hire Sales Person", AddEmployeeItems::hireSalesPerson),
                Item.of("Hire Manager", AddEmployeeItems::hireManager),
                Item.of("Back to main menu", io ->{}, true)
        };
        return res;
    }

    static Employee initEmployee(InputOutput io) {
        long id = io.readNumberRange(String.format("Enter ID value in the range [%d-%d]", MIN_ID, MAX_ID),
        "Wrong ID value", MIN_ID, MAX_ID).longValue();
        int basicSalary = io.readNumberRange(String.format("Enter salary value in the range [%d-%d]", MIN_BASIC_SALARY, MAX_BASIC_SALARY),
        "Wrong salary value", MIN_BASIC_SALARY, MAX_BASIC_SALARY).intValue();
        String department = io.readStringOptions("Enter department from " + departmentsSet, "Must be one out from " + departmentsSet, 
        departmentsSet);
        Employee employee = new Employee(id, basicSalary, department);
        return employee;
    }

    static WageEmployee initWageEmployee(InputOutput io) {
        Employee employee = initEmployee(io);
        int wage = io.readNumberRange(String.format("Enter Wage Employee wage in the range [%d-%d]", MIN_WAGE, MAX_WAGE),
        "Wrong wage value", MIN_WAGE, MAX_WAGE).intValue();
        int hours = io.readNumberRange(String.format("Enter Wage Employee hours in the range [%d-%d]",MIN_HOURS, MAX_HOURS),
        "Wrong hours value", MIN_HOURS, MAX_HOURS).intValue();
        WageEmployee wageEmployee = new WageEmployee(employee.getId(), employee.getBasicSalary(),
                employee.getDepartment(), wage, hours);
        return wageEmployee;
    }

    static SalesPerson initSalesPerson(InputOutput io) {
        WageEmployee wageEmployee = initWageEmployee(io);
        float percent = io.readNumberRange(String.format("Enter Sales Person percent in the range [%d-%d]", "", MIN_PERCENT, MAX_PERCENT),
        "Wrong percent value",MIN_PERCENT, MAX_PERCENT).floatValue();
        long sales = io.readNumberRange(String.format("Enter Sales Person sales in the range [%d-%d]", MIN_SALES, MAX_SALES),
        "Wrong sales value", MIN_SALES, MAX_SALES).longValue();
        SalesPerson salesPerson = new SalesPerson(wageEmployee.getId(), wageEmployee.getBasicSalary(), wageEmployee.getDepartment(),
                wageEmployee.getWage(), wageEmployee.getHours(), percent, sales);
        return salesPerson;
    }

    static Manager initManager(InputOutput io) {
        Employee employee = initEmployee(io);
        float factor = io.readNumberRange(String.format("Enter Manager factor in the range [%d-%d]", "", MIN_FACTOR, MAX_FACTOR),
        "Wrong factor value",MIN_FACTOR, MAX_FACTOR).floatValue();
        Manager manager = new Manager(employee.getId(), employee.getBasicSalary(), employee.getDepartment(), factor);
        return manager;
    }

    static void hireEmployee(InputOutput io) {
        company.addEmployee(initEmployee(io));
    }

    static void hireWageEmployee(InputOutput io) {
        company.addEmployee(initWageEmployee(io));
    }

    static void hireSalesPerson(InputOutput io) {    
        company.addEmployee(initSalesPerson(io));
    }

    static void hireManager(InputOutput io) {
        company.addEmployee(initManager(io));
    }
}
