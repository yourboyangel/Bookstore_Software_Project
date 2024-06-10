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
        loadEmployeeData();
    }

    public void registerEmployee(String username, String password, String name, Date birthday,
                                 String phone, String email, double salary, Role role) {
        User employee = new User(username, password, role, name, birthday, phone, email, salary);

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

    public void modifyEmployee(String username, String newPassword, String newName, Date newBirthday,
                               String newPhone, String newEmail, double newSalary, Role newRole) {

        boolean usernameExists = doesUsernameExist(username);

        if (usernameExists) {
            User foundEmployee = findEmployeeByUsername(username);

            try {
                if (newPassword != null) {
                    if (newPassword.matches("^.*[a-zA-Z].*$")) {
                        foundEmployee.setPassword(newPassword);
                    } else {
                        showAlert("Invalid Password", "Please enter a valid password.");
                        return;
                    }
                }
                foundEmployee.setName(newName);
                foundEmployee.setBirthday(newBirthday);
                foundEmployee.setPhone(newPhone);
                foundEmployee.setEmail(newEmail);
                foundEmployee.setSalary(newSalary);
                foundEmployee.setRole(newRole);
                saveEmployeeData();
            } catch (Exceptions.IncorrectPasswordException | Exceptions.InvalidPhoneNumberException |
                     Exceptions.InvalidEmailException e) {
            }
        } else {
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
        employees = fileHandler.readObjectFromFile("Bookstore_Software_Project/Bookstore/src/user_data.dat");
    }

    public User getUserByUsername(String username) {
        return employees.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public void saveEmployeeData() {
        fileHandler.writeObjectToFile(employees, "Bookstore_Software_Project/Bookstore/src/user_data.dat", false);
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