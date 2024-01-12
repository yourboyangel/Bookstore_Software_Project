package View;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ManagerView extends Application {
    public static void main(String[] args){
        launch(args);
    }
    private List<Librarian> librarians = new ArrayList<>();

    public void start(Stage managerStage) {
        librarians.add(new Librarian("James", 30, 1500.0, "2022-01-10"));
        librarians.add(new Librarian("Alice", 25, 1200.0, "2022-01-10"));

        VBox vbox = new VBox();
        double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        Rectangle headerRectangle = new Rectangle(screenWidth, 100, Color.BLUE);

        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.TOP_CENTER);

        Text headerText = new Text("Manager");
        headerText.setFont(Font.font("Times New Roman", 86));
        headerText.setFill(Color.WHITE);

        HBox controlsBox = new HBox();
        controlsBox.setPadding(new Insets(160, 0, 0, 0));
        controlsBox.setAlignment(Pos.CENTER);
        controlsBox.setSpacing(10);

        Label labelField = new Label("Statistics");
        labelField.setPrefSize(200, 40);
        labelField.setStyle("-fx-border-color: blue; -fx-border-radius:4px; -fx-border-width:2px;");

        ComboBox<String> dropdown = new ComboBox<>();
        dropdown.getItems().addAll("Select", "Daily", "Monthly", "Total");
        dropdown.setValue("Select");
        dropdown.setPrefSize(200, 40);


        Image addBook = new Image("https://static.thenounproject.com/png/111365-200.png");
        ImageView bookImageView = new ImageView(addBook);
        bookImageView.setFitHeight(40);
        bookImageView.setFitWidth(40);

        Button addBookButton = new Button("", bookImageView);
        addBookButton.setStyle("-fx-background-color: transparent;");

        Button textButton = new Button("Check Librarian Performance");
        textButton.setPrefHeight(40);

        TextArea statisticsArea = new TextArea();
        statisticsArea.setPrefWidth(700);
        statisticsArea.setMaxWidth(700);
        statisticsArea.setPrefHeight(400);
        statisticsArea.setMaxHeight(400);
        statisticsArea.setEditable(true);

        dropdown.setOnAction(event -> {
            String selectedOption = dropdown.getValue();
            if (!"Select".equals(selectedOption)) {
                String statistics = getStatistics(selectedOption);
                statisticsArea.setText(statistics);
            } else {
                statisticsArea.clear();
            }
        });

        textButton.setOnAction(event -> {
            String librarianData = getLibrarianData();
            statisticsArea.setText(librarianData);
        });

        Button saveButton = new Button("Save");
        saveButton.setPrefHeight(40);

        saveButton.setOnAction(event -> {
            String dataToSave = statisticsArea.getText();
            System.out.println("Save button works");
        });

        controlsBox.getChildren().addAll(labelField, dropdown, addBookButton, textButton, saveButton);

        VBox contentVBox = new VBox();
        contentVBox.getChildren().addAll(controlsBox, statisticsArea);
        contentVBox.setAlignment(Pos.CENTER);

        stackPane.getChildren().addAll(headerRectangle, headerText, contentVBox);
        stackPane.setAlignment(Pos.TOP_CENTER);

        vbox.getChildren().addAll(stackPane);


        Scene managerScene = new Scene(vbox, 800, 600);
        managerStage.setScene(managerScene);
        managerStage.setTitle("Manager View");
        managerStage.setFullScreen(true);
        managerStage.show();
    }

    private String getStatistics(String option) {
        return option + " Statistics go here...";
    }

    private String getLibrarianData(){
        StringBuilder data = new StringBuilder("Librarian Performance:\n\n");
        for(Librarian librarian : librarians){
            data.append("Name: ").append(librarian.getName()).append("\n");
            data.append("Books Sold: ").append(librarian.getName()).append("\n");
            data.append("Total Amount: ").append(librarian.getName()).append("\n");
            data.append("Date: ").append(librarian.getName()).append("\n\n\n");
        }
        return data.toString();
    }

    private static class Librarian{
        private String name;
        private int booksSold;
        private double totalAmount;
        private String date;


        public Librarian(String name, int booksSold, double totalAmount, String date){
            this.name = name;
            this.booksSold = booksSold;
            this.totalAmount = totalAmount;
            this.date = date;
        }

        public String getName(){
            return name;
        }

        public int getBooksSold(){
            return booksSold;
        }

        public double getTotalAmount(){
            return totalAmount;
        }

        public String getDate() {
            return date;
        }
    }
}
