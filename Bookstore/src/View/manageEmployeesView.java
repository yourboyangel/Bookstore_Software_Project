package View;

import Controller.ManageEmployeesController;
import Model.Role;
import Model.User;
import Permissions.Permission;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javax.swing.text.View;
import java.time.ZoneId;
import java.util.Date;

public class manageEmployeesView extends Application {
    private ManageEmployeesController controller;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        controller = new ManageEmployeesController();
        primaryStage.setTitle("Employee Management");
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));
        Label actionLabel = new Label("Select Action:");
        actionLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 14));
        grid.add(actionLabel, 0, 0);
        ComboBox<String> actionComboBox = new ComboBox<>();
        actionComboBox.getItems().addAll("Select", "Register", "Modify", "Delete", "Permissions");
        actionComboBox.setValue("Select");
        grid.add(actionComboBox, 1, 0);
        VBox dataContainer = new VBox();
        dataContainer.setAlignment(Pos.CENTER);
        dataContainer.setPadding(new Insets(20, 20, 20, 20));
        dataContainer.setStyle(
                "-fx-background-color: transparent; " +
                        "-fx-border-color: black; " +
                        "-fx-border-width: 4px; " +
                        "-fx-border-radius: 6px; " +
                        "-fx-padding: 10px;"
        );
        grid.add(dataContainer, 0, 1, 2, 1);
        Scene scene = new Scene(grid, 400, 600);
        primaryStage.setScene(scene);
        actionComboBox.setOnAction(e -> {
            String selectedAction = actionComboBox.getValue();
            if (selectedAction.equals("Register")) {
                showRegistrationForm(dataContainer);
            } else if (selectedAction.equals("Delete")) {
                showDeleteOptions(dataContainer);
            } else if (selectedAction.equals("Permissions")) {
                showPermissionsOptions(dataContainer);
            } else if (selectedAction.equals("Modify")) {
                showUsernamePrompt(dataContainer);
            } else {
                dataContainer.getChildren().clear();
            }
        });
        primaryStage.show();
        centerStage(primaryStage);
    }

    private void centerStage(Stage stage) {
        Screen screen = Screen.getPrimary();
        stage.setX((screen.getVisualBounds().getWidth() - stage.getWidth()) / 2);
        stage.setY((screen.getVisualBounds().getHeight() - stage.getHeight()) / 2);
    }

    private void showRegistrationForm(VBox dataContainer) {
        dataContainer.getChildren().clear();

        Label usernameLabel = new Label("Username:");
        TextField usernameTextField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Label nameLabel = new Label("Name:");
        TextField nameTextField = new TextField();
        Label birthdayLabel = new Label("Birthday:");
        DatePicker birthdayDatePicker = new DatePicker();
        Label phoneLabel = new Label("Phone:");
        TextField phoneTextField = new TextField();
        Label emailLabel = new Label("Email:");
        TextField emailTextField = new TextField();
        Label salaryLabel = new Label("Salary:");
        TextField salaryTextField = new TextField();

        Label roleLabel = new Label("Role:");
        ComboBox<Role> roleComboBox = new ComboBox<>();
        roleComboBox.getItems().addAll(Role.LIBRARIAN, Role.MANAGER, Role.ADMINISTRATOR);
        roleComboBox.setValue(Role.LIBRARIAN);

        Button registerButton = new Button("Register");
        dataContainer.getChildren().addAll(
                usernameLabel, usernameTextField,
                passwordLabel, passwordField,
                nameLabel, nameTextField,
                birthdayLabel, birthdayDatePicker,
                phoneLabel, phoneTextField,
                emailLabel, emailTextField,
                salaryLabel, salaryTextField,
                roleLabel, roleComboBox,
                registerButton
        );

        registerButton.setOnAction(e -> {
            String username = usernameTextField.getText();
            String password = passwordField.getText();
            String name = nameTextField.getText();
            Date birthday = Date.from(birthdayDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            String phone = phoneTextField.getText();
            String email = emailTextField.getText();
            double salary = Double.parseDouble(salaryTextField.getText());
            Role role = roleComboBox.getValue();


            controller.registerEmployee(username, password, name, birthday, phone, email, salary, role);

            showAlert("Registration Successful", "Employee registered successfully.");
        });
    }

    private void showDeleteOptions(VBox dataContainer) {
        dataContainer.getChildren().clear();

        Label usernameLabel = new Label("Enter Username:");
        TextField usernameTextField = new TextField();

        Button deleteButton = new Button("Delete");
        dataContainer.getChildren().addAll(usernameLabel, usernameTextField, deleteButton);

        deleteButton.setOnAction(e -> {
            String username = usernameTextField.getText().trim();
            if (!username.isEmpty()) {

                boolean usernameExists = controller.doesUsernameExist(username);

                if (usernameExists) {

                    controller.deleteEmployee(username);
                    showAlert("Deletion Successful", "Employee with username " + username + " deleted successfully.");
                } else {
                    showAlert("Username Not Found", "The entered username does not exist.");
                }
            } else {
                showAlert("Username is empty", "Please enter a username.");
            }
        });
    }


    private void showModifyOptions(VBox dataContainer, String username) {
        dataContainer.getChildren().clear();

        Label nameLabel = new Label("Name:");
        TextField nameTextField = new TextField();
        Label birthdayLabel = new Label("Birthday:");
        DatePicker birthdayDatePicker = new DatePicker();
        Label phoneLabel = new Label("Phone:");
        TextField phoneTextField = new TextField();
        Label emailLabel = new Label("Email:");
        TextField emailTextField = new TextField();
        Label salaryLabel = new Label("Salary:");
        TextField salaryTextField = new TextField();

        Label roleLabel = new Label("Role:");
        ComboBox<Role> roleComboBox = new ComboBox<>();
        roleComboBox.getItems().addAll(Role.LIBRARIAN, Role.MANAGER, Role.ADMINISTRATOR);
        roleComboBox.setValue(Role.LIBRARIAN);

        Button saveButton = new Button("Save");
        dataContainer.getChildren().addAll(
                nameLabel, nameTextField,
                birthdayLabel, birthdayDatePicker,
                phoneLabel, phoneTextField,
                emailLabel, emailTextField,
                salaryLabel, salaryTextField,
                roleLabel, roleComboBox,
                saveButton
        );

        saveButton.setOnAction(e -> {
            String name = nameTextField.getText();
            Date birthday = Date.from(birthdayDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            String phone = phoneTextField.getText();
            String email = emailTextField.getText();
            double salary = Double.parseDouble(salaryTextField.getText());
            Role role = roleComboBox.getValue();


            controller.modifyEmployee(username, null, name, birthday, phone, email, salary, role);

            showAlert("Success", "Modifications saved.");
        });
    }

    private void showUsernamePrompt(VBox dataContainer) {
        dataContainer.getChildren().clear();
        Label usernameLabel = new Label("Enter Username:");
        TextField usernameTextField = new TextField();
        Button submitButton = new Button("Submit");
        dataContainer.getChildren().addAll(usernameLabel, usernameTextField, submitButton);
        submitButton.setOnAction(e -> {
            String username = usernameTextField.getText().trim();
            if (!username.isEmpty()) {

                boolean usernameExists = controller.doesUsernameExist(username);

                if (usernameExists) {

                    showModifyOptions(dataContainer, username);
                } else {
                    showAlert("Username Not Found", "The entered username does not exist.");
                }
            } else {
                showAlert("Username is empty", "Please enter a username.");
            }
        });
    }


    private void showPermissionsOptions(VBox dataContainer) {
        dataContainer.getChildren().clear();

        Label usernameLabel = new Label("Enter Username:");
        TextField usernameTextField = new TextField();
        Button submitButton = new Button("Submit");

        CheckBox createBillCheckbox = new CheckBox("CREATE BILL");
        CheckBox addBookCheckbox = new CheckBox("ADD BOOK");
        CheckBox checkPerformanceCheckbox = new CheckBox("CHECK LIBRARIAN PERFORMANCE");
        CheckBox seeStatisticsCheckbox = new CheckBox("SEE BOOK STATISTICS");
        CheckBox manageEmployeesCheckbox = new CheckBox("MANAGE EMPLOYEES");
        CheckBox totalCostCheckbox = new CheckBox("TOTAL COST");

        VBox checkboxesVBox = new VBox(
                createBillCheckbox, addBookCheckbox,
                checkPerformanceCheckbox, seeStatisticsCheckbox,
                manageEmployeesCheckbox, totalCostCheckbox);

        dataContainer.getChildren().addAll(usernameLabel, usernameTextField, submitButton, checkboxesVBox);

        VBox.setMargin(checkboxesVBox, new Insets(10, 0, 0, 20));
        VBox.setMargin(usernameLabel, new Insets(10, 0, 0, 0));

        submitButton.setOnAction(e -> {
            String enteredUsername = usernameTextField.getText().trim();

            if (enteredUsername.isEmpty()) {
                showAlert("Username is empty", "Please enter a username.");
                return;
            }


            if (!controller.doesUsernameExist(enteredUsername)) {
                showAlert("Username Not Found", "The entered username does not exist.");
                return;
            }


            User user = controller.getUserByUsername(enteredUsername);


            if (createBillCheckbox.isSelected()) {
                user.addPermission(Permission.CREATE_BILL);
            } else {
                user.removePermission(Permission.CREATE_BILL);
            }

            if (addBookCheckbox.isSelected()) {
                user.addPermission(Permission.ADD_BOOK);
            } else {
                user.removePermission(Permission.ADD_BOOK);
            }

            if (checkPerformanceCheckbox.isSelected()) {
                user.addPermission(Permission.CHECK_LIBRARIAN_PERFORMANCE);
            } else {
                user.removePermission(Permission.CHECK_LIBRARIAN_PERFORMANCE);
            }

            if (seeStatisticsCheckbox.isSelected()) {
                user.addPermission(Permission.SEE_BOOK_STATISTICS);
            } else {
                user.removePermission(Permission.SEE_BOOK_STATISTICS);
            }

            if (manageEmployeesCheckbox.isSelected()) {
                user.addPermission(Permission.MANAGE_EMPLOYEES);
            } else {
                user.removePermission(Permission.MANAGE_EMPLOYEES);
            }

            if (totalCostCheckbox.isSelected()) {
                user.addPermission(Permission.TOTAL_COST);
            } else {
                user.removePermission(Permission.TOTAL_COST);
            }

            controller.saveEmployeeData();


            showAlert("Success", "Permissions updated for user " + enteredUsername + ".");
        });
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}