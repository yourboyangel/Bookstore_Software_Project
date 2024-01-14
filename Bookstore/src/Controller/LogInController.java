package Controller;

import Exceptions.IncorrectPasswordException;
import Exceptions.InvalidEmailException;
import Exceptions.InvalidPhoneNumberException;
import Model.BinaryFileHandler;
import Model.User;
import View.HomepageView;
import View.LoginView;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class LogInController {
    private ArrayList<User> userList;
    private final String filePath="user_data.dat";


    public void initialize(LoginView loginView){
        TextField usernameTextField = loginView.getUsernameTextField();
        PasswordField passwordField = loginView.getPasswordField();
        Button loginButton = loginView.getLoginButton();

        loginButton.setOnAction(e->{
            String username=usernameTextField.getText();
            String password=passwordField.getText();

            try{
                userValidation(username,password);
                showInformationAlert("Login Successful","Welcome "+ username +"!");
                openHomepageView(loginView);
            } catch (InvalidEmailException | IncorrectPasswordException | InvalidPhoneNumberException ex){
                showErrorAlert("Login Failed",ex.getMessage());
            }
        });

        userList = loadUserFromFile();
    }
    private ArrayList<User> loadUserFromFile() {
        BinaryFileHandler<ArrayList<User>> fileHandler = new BinaryFileHandler<>();

        try {
            return fileHandler.readObjectFromFile(filePath);
        } catch (ClassCastException e) {
            System.err.println("Error reading user data from file. Check if the file contains the correct data.");
            return new ArrayList<>();
        }
    }

    private void userValidation(String username, String password) throws InvalidEmailException, IncorrectPasswordException, InvalidPhoneNumberException{
        boolean foundUser=false;
        for(User user : userList){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                foundUser=true;
                break;
            }
        }

        if(!foundUser){
            throw new IncorrectPasswordException();
        }
    }
    private void openHomepageView(LoginView loginView) {
        // Close the login stage
        Stage loginStage = (Stage) loginView.getLoginButton().getScene().getWindow();
        loginStage.close();

        // Open the HomepageView
        try {
            Stage homepageStage = new Stage();
            new HomepageView().start(homepageStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void showInformationAlert(String title, String content){
        Platform.runLater(()->{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(content);
            alert.showAndWait();
        });
    }

    private void showErrorAlert(String title, String content){
        Platform.runLater(()->{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(content);
            alert.showAndWait();
        });
    }


}
