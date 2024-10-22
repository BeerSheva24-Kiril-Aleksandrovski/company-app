package telran.employees;

import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;

public class CompanyItems {
    private static Company company;      

    public static Item [] getItems(Company company) {
        CompanyItems.company = company;
        Item[] res = {
                Item.of("Add Employee", CompanyItems::addEmployee),
                Item.of("Display Employee Data", CompanyItems::displayEmployeeData),
                Item.of("Fire Employee", CompanyItems::fireEmployee),
                Item.of("Show Department Salary Budget", CompanyItems::showDepartmentSalaryBudget),
                Item.of("Show List of Departments", CompanyItems::showListOfDepartments),
                Item.of("Display Managers with Most Factor", CompanyItems::displayManagersWithMostFactor),
                Item.ofExit()
        };
        return res;
    }

    private static void addEmployee(InputOutput io) {
        Menu menu = new Menu("Select type of Employee", AddEmployeeItems.getItemsAddEmployee(company));
        menu.perform(io);
    }

    static void displayEmployeeData(InputOutput io) {
        long id = io.readLong("Enter employee ID", "");
        Employee employee = company.getEmployee(id);
        if (employee != null) {
            io.writeLine(employee);
        } else {
            io.writeLine(String.format("Employee with ID %d not found", id));
        } 
    }

    static void fireEmployee(InputOutput io) {
        long id = io.readLong("Enter employee ID", "");
        Employee employee = company.getEmployee(id);
        if (employee != null) {
            company.removeEmployee(id);
            io.writeLine(String.format("Employee with ID %d fired", id));
        } else {
            io.writeLine(String.format("Employee with ID %d not found", id));
        } 
    }

    static void showDepartmentSalaryBudget(InputOutput io) {
        String department = io.readString("Enter department");
        io.writeLine(company.getDepartmentBudget(department));
    }
    static void showListOfDepartments(InputOutput io) {
        io.writeLine(String.join(", ", company.getDepartments()));
    }

    static void displayManagersWithMostFactor(InputOutput io) {
        for (Manager manager : company.getManagersWithMostFactor()) {
            io.writeLine(manager);
        }
    }

}