package View;

import View.BillView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BillView extends Application {
    private static final String FILE_PATH = "src/billData.txt";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage billStage) {
        billStage.setTitle("Create bill");

        double rectangleWidth = 400;
        double rectangleHeight = 500;

        Rectangle background = new Rectangle(rectangleWidth, rectangleHeight);
        background.setFill(Color.LIGHTGREEN);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));

        Label titleLabel = new Label("Title: ");
        TextField titleField = new TextField();
        grid.add(titleLabel, 0, 0);
        grid.add(titleField, 1, 0);

        Label categoryLabel = new Label("Category: ");
        TextField categoryField = new TextField();
        grid.add(categoryLabel, 0, 1);
        grid.add(categoryField, 1, 1);

        Label isbnLabel = new Label("ISBN: ");
        TextField isbnField = new TextField();
        grid.add(isbnLabel, 0, 2);
        grid.add(isbnField, 1, 2);

        Label authorLabel = new Label("Author: ");
        TextField authorField = new TextField();
        grid.add(authorLabel, 0, 3);
        grid.add(authorField, 1, 3);

        Label priceLabel = new Label("Price: ");
        TextField priceField = new TextField();
        grid.add(priceLabel, 0, 4);
        grid.add(priceField, 1, 4);

        Label quantityLabel = new Label("Quantity: ");
        TextField quantityField = new TextField();
        grid.add(quantityLabel, 0, 5);
        grid.add(quantityField, 1, 5);

        Button submitButton = new Button("Create");
        submitButton.setOnAction(e -> {
            String title = titleField.getText();
            String category = categoryField.getText();
            String isbn = isbnField.getText();
            String author = authorField.getText();
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());

            processData(title, category, isbn, author, price, quantity);

            titleField.clear();
            categoryField.clear();
            isbnField.clear();
            authorField.clear();
            priceField.clear();
            quantityField.clear();
        });

        Button addAnotherBookButton = new Button("Add Another Book");
        addAnotherBookButton.setOnAction(e -> {
            String title = titleField.getText();
            String category = categoryField.getText();
            String isbn = isbnField.getText();
            String author = authorField.getText();
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());

            processData(title, category, isbn, author, price, quantity); // Save current data

            titleField.clear();
            categoryField.clear();
            isbnField.clear();
            authorField.clear();
            priceField.clear();
            quantityField.clear();
        });

        submitButton.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-weight: bold; -fx-background-radius: 15; -fx-border-radius: 15;");
        addAnotherBookButton.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-weight: bold; -fx-background-radius: 15; -fx-border-radius: 15;");

        grid.add(submitButton, 1, 6);
        grid.add(addAnotherBookButton, 0, 6);

        Font timesNewRomanBold = Font.font("Times New Roman", FontWeight.BOLD, Font.getDefault().getSize());
        titleLabel.setFont(timesNewRomanBold);
        categoryLabel.setFont(timesNewRomanBold);
        isbnLabel.setFont(timesNewRomanBold);
        authorLabel.setFont(timesNewRomanBold);
        priceLabel.setFont(timesNewRomanBold);
        quantityLabel.setFont(timesNewRomanBold);

        Pane root = new Pane(background, grid);

        Scene scene = new Scene(root, rectangleWidth, rectangleHeight);
        billStage.setScene(scene);
        billStage.show();
    }

    private void processData(String title, String category, String isbn, String author, double price, int quantity) {
        String bookInfo = "Title: " + title + ", Category: " + category + ", ISBN: " + isbn + ", Author: " + author +
                ", Price: " + price + ", Quantity: " + quantity;

        System.out.println(bookInfo); // Optional: print to console

        // Write the book information to a text file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(bookInfo);
            writer.newLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
