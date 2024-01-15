package Controller;

import Model.BinaryFileHandler;
import Model.Role;
import Model.User;
import Permissions.*;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.Serializable;
import java.util.*;
import java.util.logging.Logger;

public class ManageEmployeesController implements Serializable {
    private static final Logger LOGGER = Logger.getLogger(ManageEmployeesController.class.getName());


    private static final String FILE_PATH = "user_data.dat";
    private BinaryFileHandler<ArrayList<User>> fileHandler = new BinaryFileHandler<>();

    public void registerEmployee(String username, String password, String name, Date birthday,
                                 String phone, String email, double salary, Role role) {
        try {
            Map<Role, Permission> rolePermissionMap = new HashMap<>();
            rolePermissionMap.put(Role.LIBRARIAN, new LibrarianPermissions());
            rolePermissionMap.put(Role.MANAGER, new ManagerPermissions());
            rolePermissionMap.put(Role.ADMINISTRATOR, new AdministratorPermissions());

            Permission permissions = rolePermissionMap.get(role);

            User newUser = new User(username, password, role, name, birthday, phone, email, salary);
            newUser.setPermissions(new HashSet<>(Arrays.asList(permissions.getPermissions())));

            // Read existing list of users
            ArrayList<User> userList;
            File file = new File(FILE_PATH);
            if (file.exists()) {
                System.out.println("File exists. Reading existing users.");
                userList = fileHandler.readObjectFromFile(FILE_PATH);
            } else {
                System.out.println("File does not exist. Creating a new one.");
                userList = new ArrayList<>();
            }
            LOGGER.info("User Information:");
            LOGGER.info("Username: " + username);
            LOGGER.info("Password: " + password);
            LOGGER.info("Name: " + name);
            LOGGER.info("Birthday: " + birthday);
            LOGGER.info("Phone: " + phone);
            LOGGER.info("Email: " + email);
            LOGGER.info("Salary: " + salary);
            LOGGER.info("Role: " + role);
            LOGGER.info("Permissions: " + permissions);

            // Add the new user to the list
            userList.add(newUser);

            // Write the updated list back to the file
            fileHandler.writeObjectToFile(userList, FILE_PATH, false);

            // Additional debug statements
            System.out.println("File path: " + file.getAbsolutePath());
            System.out.println("File length: " + file.length());

            showAlert("Success", "Employee registered successfully!");
        } catch (NumberFormatException ex) {
            showAlert("Error", "Please enter a valid salary.");
            ex.printStackTrace(); // Print the stack trace for debugging
        } catch (Exception ex) {
            showAlert("Error", "An unexpected error occurred.");
            ex.printStackTrace(); // Print the stack trace for debugging
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
