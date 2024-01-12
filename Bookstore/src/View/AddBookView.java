
package View;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;


public class AddBookView extends Application {
    private ImageView imageView;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage addBookStage) {
        addBookStage.setTitle("Add Book");

        double rectangleWidth = 400;
        double rectangleHeight = 500;

        Rectangle background = new Rectangle(rectangleWidth, rectangleHeight);
        background.setFill(Color.LIGHTBLUE);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));

        Label bookCategoryLabel = new Label("Book Category: ");
        TextField bookCategoryField = new TextField();
        grid.add(bookCategoryLabel, 0, 0);
        grid.add(bookCategoryField, 1, 0);

        Label authorLabel = new Label("Authors: ");
        TextField authorField = new TextField();
        grid.add(authorLabel, 0, 1);
        grid.add(authorField, 1, 1);



        Label pictureBookLabel = new Label("Book URL Picture: ");
        TextField pictureUrlField = new TextField();
        pictureUrlField.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER){
                String pictureBookUrl = pictureUrlField.getText();
                if(!pictureBookUrl.isEmpty()){
                    Image image = new Image(pictureBookUrl);
                    imageView.setImage(image);
                }
            }
        });
        grid.add(pictureBookLabel, 0, 2);
        grid.add(pictureUrlField, 1,2);

        imageView = new ImageView();
        imageView.setFitWidth(200);
        imageView.setFitHeight(300);
        grid.add(imageView, 1,3);

        Button chooseImageButton = new Button("Choose Image");
        chooseImageButton.setOnAction(e -> chooseImage());
        grid.add(chooseImageButton, 1, 4);



        Button submitButton = new Button("Create");
        submitButton.setOnAction(e -> {
            String bookCategory = bookCategoryField.getText();
            String author = authorField.getText();

            processData(bookCategory, author);
            bookCategoryField.clear();
            authorField.clear();
        });

        submitButton.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-weight: bold; -fx-background-radius: 15; -fx-border-radius: 15;");
        grid.add(submitButton, 1, 5);

        Font timesNewRomanBold = Font.font("Times New Roman", FontWeight.BOLD, Font.getDefault().getSize());
        bookCategoryLabel.setFont(timesNewRomanBold);
        authorLabel.setFont(timesNewRomanBold);
        pictureBookLabel.setFont(timesNewRomanBold);

        Pane root = new Pane(background, grid);
        root.setPrefSize(rectangleWidth, rectangleHeight);

        Scene scene = new Scene(root, rectangleWidth, rectangleHeight);
        addBookStage.setScene(scene);
        addBookStage.show();
    }

    private void chooseImage(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Book Image");
        File file = fileChooser.showOpenDialog(null);
        if(file!=null){
            Image image = new Image(file.toURI().toString());
            imageView.setImage(image);
        }
    }
    private void processData(String bookCategory, String author) {
        System.out.println("Book Category: " + bookCategory + ", Author: " + author);
    }
}