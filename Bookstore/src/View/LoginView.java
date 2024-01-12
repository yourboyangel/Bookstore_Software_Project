package View;
import Controller.LogInController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;

public class LoginView extends Application {
    private TextField usernameTextField;
    private PasswordField passwordField;
    private Button loginButton;
    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage stage) {
        Image image = new Image("https://images.unsplash.com/photo-1568667256531-7d5ac92eaa7a?q=80&w=1430&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        ImageView imageView = new ImageView(image);
        StackPane libraryStackPane = new StackPane(imageView);

        usernameTextField = new TextField("Enter username");
        usernameTextField.setMaxWidth(200);

        passwordField = new PasswordField();
        passwordField.setMaxWidth(200);

        loginButton = new Button("Log in");
        loginButton.setStyle("-fx-border-radius: 8px; -fx-border-color:black; -fx-font-weight:bold; -fx-background-color:#F4A460;");
        loginButton.setPrefHeight(20);

        VBox loginPane = new VBox();
        loginPane.setAlignment(Pos.CENTER);
        loginPane.setPadding(new Insets(11,12,13,14));
        loginPane.setSpacing(10);
        loginPane.setMaxWidth(300);
        loginPane.getChildren().addAll(usernameTextField,passwordField, loginButton);

        Rectangle rectangle = new Rectangle(250, 130);
        rectangle.setFill(Color.SANDYBROWN);
        rectangle.setArcHeight(20);
        rectangle.setArcWidth(20);

        StackPane rootPane = new StackPane(libraryStackPane,rectangle, loginPane);

        Scene scene = new Scene(rootPane, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Login Page");
        Screen screen = Screen.getPrimary();
        stage.setX(screen.getVisualBounds().getMinX());
        stage.setY(screen.getVisualBounds().getMinY());
        stage.setWidth(screen.getVisualBounds().getWidth());
        stage.setHeight(screen.getVisualBounds().getHeight());

        LogInController logInController = new LogInController();
        logInController.initialize(this);

        stage.show();


    }
    public TextField getUsernameTextField(){
        return usernameTextField;
    }

    public PasswordField getPasswordField(){
        return passwordField;
    }

    public Button getLoginButton(){
        return loginButton;
    }



}