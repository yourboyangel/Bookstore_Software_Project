import Model.BinaryFileHandler;
import Model.User;

import java.util.List;

public class Test{

    public static void main(String[] args) {
        // Specify the path to the "user_data" file
        String filePath = "Bookstore_Software_Project/Bookstore/src/user_data.dat";

        // Create an instance of BinaryFileHandler to read the List<User> from the file
        BinaryFileHandler<List<User>> fileHandler = new BinaryFileHandler<>();

        // Read the List<User> from the file
        List<User> userList = fileHandler.readObjectFromFile(filePath);

        // Display user data on the console
        if (userList != null) {
            for (User user : userList) {
                System.out.println("Username: " + user.getUsername());
                System.out.println("Password: " + user.getPassword()); // Note: You might want to avoid displaying passwords in a real application
                System.out.println("Role: " + user.getRole());
                System.out.println("Name: " + user.getName());
                System.out.println("Birthday: " + user.getBirthday());
                System.out.println("Phone: " + user.getPhone());
                System.out.println("Email: " + user.getEmail());
                System.out.println("Salary: " + user.getSalary());
                System.out.println("Permissions: " + user.getPermissions());
                System.out.println("------------------------");
            }
        } else {
            System.out.println("Failed to read user data from the file.");
 }
}
}