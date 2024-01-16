package View;

import Controller.ManageEmployeesController;
import Model.Role;
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
                showPermissionsOptions(dataContainer); // Swapped functionality

            } else if (selectedAction.equals("Modify")) {
                showUsernamePrompt(dataContainer); // Swapped functionality

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

            // Register the employee using the controller
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
                // Call the controller method to check if the username exists
                boolean usernameExists = controller.doesUsernameExist(username);

                if (usernameExists) {
                    // If the username exists, proceed with deletion
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

            // Call the modifyEmployee method in the controller to update the employee data
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
                // Call the controller method to check if the username exists
                boolean usernameExists = controller.doesUsernameExist(username);

                if (usernameExists) {
                    // If the username exists, proceed with modification options
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

        TextArea inputTextArea = new TextArea();
        inputTextArea.setPromptText("Enter username...");

        inputTextArea.setPrefHeight(50);

        Button createBillButton = new Button("CREATE BILL");
        Button addBookButton = new Button("ADD BOOK");
        Button checkPerformanceButton = new Button("CHECK LIBRARIAN PERFORMANCE");
        Button seeStatisticsButton = new Button("SEE BOOK STATISTICS");
        Button manageEmployeesButton = new Button("MANAGE EMPLOYEES");
        Button totalCostButton = new Button("TOTAL COST");
        Button submitButton = new Button("Submit");

        VBox buttonsVBox = new VBox(
                createBillButton, addBookButton,
                checkPerformanceButton, seeStatisticsButton,
                manageEmployeesButton, totalCostButton, submitButton);
        dataContainer.getChildren().addAll(
                inputTextArea,
                buttonsVBox);

        VBox.setMargin(buttonsVBox, new Insets(10, 0, 0, 20));
        VBox.setMargin(inputTextArea, new Insets(10, 0, 0, 0));

        submitButton.setOnAction(e -> {
            String modifications = inputTextArea.getText().trim();
            if (!modifications.isEmpty()) {
                showAlert("Success", "Modifications saved: " + modifications);
            } else {
                showAlert("Modifications are empty", "Please enter modifications.");
            }
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