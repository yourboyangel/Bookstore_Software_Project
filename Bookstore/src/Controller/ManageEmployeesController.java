package Controller;

import Model.User;
import Model.Role;
import Model.BinaryFileHandler;
import Permissions.Permission;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ManageEmployeesController {
    private List<User> employees;
    private BinaryFileHandler<List<User>> fileHandler;

    public ManageEmployeesController() {
        this.employees = new ArrayList<>();
        this.fileHandler = new BinaryFileHandler<>();
        // Load existing employee data from the binary file, if available
        loadEmployeeData();
    }

    public void registerEmployee(String username, String password, String name, Date birthday,
                                 String phone, String email, double salary, Role role) {
        User employee = new User(username, password, role, name, birthday, phone, email, salary);

        // Set permissions based on the user's role
        switch (role) {
            case LIBRARIAN:
                employee.addPermission(Permission.ADD_BOOK);
                employee.addPermission(Permission.CHECK_LIBRARIAN_PERFORMANCE);
                break;
            case MANAGER:
                employee.addPermission(Permission.CREATE_BILL);
                employee.addPermission(Permission.SEE_BOOK_STATISTICS);
                break;
            case ADMINISTRATOR:
                employee.addPermission(Permission.MANAGE_EMPLOYEES);
                employee.addPermission(Permission.TOTAL_COST);
                break;
            default:
                break;
        }

        employees.add(employee);
        saveEmployeeData();
    }


    public boolean doesUsernameExist(String username) {
        return employees.stream().anyMatch(employee -> employee.getUsername().equals(username));
    }

    // In ManageEmployeesController class

    public void modifyEmployee(String username, String newPassword, String newName, Date newBirthday,
                               String newPhone, String newEmail, double newSalary, Role newRole) {
        // Check if the username exists
        boolean usernameExists = doesUsernameExist(username);

        if (usernameExists) {
            User foundEmployee = findEmployeeByUsername(username);

            try {
                // Update employee data
                if (newPassword != null) {
                    // Check if newPassword is not null before using matches
                    if (newPassword.matches("^.*[a-zA-Z].*$")) {
                        foundEmployee.setPassword(newPassword);
                    } else {
                        // Handle invalid password case (show an alert or take appropriate action)
                        showAlert("Invalid Password", "Please enter a valid password.");
                        return; // Return to avoid further processing with invalid password
                    }
                }

                // Update other employee data
                foundEmployee.setName(newName);
                foundEmployee.setBirthday(newBirthday);
                foundEmployee.setPhone(newPhone);
                foundEmployee.setEmail(newEmail);
                foundEmployee.setSalary(newSalary);
                foundEmployee.setRole(newRole);

                // Save the updated employee data
                saveEmployeeData();
            } catch (Exceptions.IncorrectPasswordException | Exceptions.InvalidPhoneNumberException |
                     Exceptions.InvalidEmailException e) {
                // Handle or log the exceptions here
            }
        } else {
            // If the username doesn't exist, display a pop-up
            showAlert("Username Not Found", "The entered username does not exist.");
        }
    }

    public void deleteEmployee(String username) {
        User employeeToRemove = findEmployeeByUsername(username);

        if (employeeToRemove != null) {
            employees.remove(employeeToRemove);
            saveEmployeeData();
        } else {
            showAlert("Username Not Found", "The entered username does not exist.");
        }
    }

    private User findEmployeeByUsername(String username) {
        return employees.stream()
                .filter(employee -> employee.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public void loadEmployeeData() {
        // Use the file handler to read data from the file
        employees = fileHandler.readObjectFromFile("employeeData.bin");
    }

    private void saveEmployeeData() {
        // Use the file handler to write data to the file
        fileHandler.writeObjectToFile(employees, "employeeData.bin", false);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    public BinaryFileHandler<List<User>> getFileHandler() {
        return fileHandler;
    }
    public void printEmployeeDataToConsole() {
        System.out.println("Employee Data:");

        for (User employee : employees) {
            System.out.println("Username: " + employee.getUsername());
            System.out.println("Name: " + employee.getName());
            System.out.println("Birthday: " + employee.getBirthday());
            System.out.println("Phone: " + employee.getPhone());
            System.out.println("Email: " + employee.getEmail());
            System.out.println("Salary: " + employee.getSalary());
            System.out.println("Role: " + employee.getRole());
            System.out.println("---------------------------------------");
        }
    }
}
