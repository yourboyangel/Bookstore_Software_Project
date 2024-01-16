import Controller.ManageEmployeesController;
import Exceptions.InvalidIsbnException;
import Model.*;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        // Create an instance of ManageEmployeesController
        ManageEmployeesController employeesController = new ManageEmployeesController();

        // Load and print employee data
        employeesController.loadEmployeeData();
        employeesController.printEmployeeDataToConsole();
    }
}
