package View;
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

public class BillView extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage billStage) {
        billStage.setTitle("Create bill");

        double rectangleWidth = 300;
        double rectangleHeight = 400;

        Rectangle background = new Rectangle(rectangleWidth, rectangleHeight);
        background.setFill(Color.LIGHTGREEN);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));

        Label isbnLabel = new Label("ISBN: ");
        TextField isbnField = new TextField();
        grid.add(isbnLabel, 0, 0);
        grid.add(isbnField, 1, 0);

        Label quantityLabel = new Label("Quantity: ");
        TextField quantityField = new TextField();
        grid.add(quantityLabel, 0, 1);
        grid.add(quantityField, 1, 1);


        Button submitButton = new Button("Create");
        submitButton.setOnAction(e -> {
            String isbn = isbnField.getText();
            int quantity = Integer.parseInt(quantityField.getText());

            processData(isbn, quantity);
            isbnField.clear();
            quantityField.clear();
        });

        submitButton.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-weight: bold; -fx-background-radius: 15; -fx-border-radius: 15;");
        grid.add(submitButton, 1, 2);

        Font timesNewRomanBold = Font.font("Times New Roman", FontWeight.BOLD, Font.getDefault().getSize());
        isbnLabel.setFont(timesNewRomanBold);
        quantityLabel.setFont(timesNewRomanBold);
        Pane root = new Pane(background, grid);

        Scene scene = new Scene(root, rectangleWidth, rectangleHeight);
        billStage.setScene(scene);
        billStage.show();
        //return isbnField;
    }

    private void processData(String isbn, int quantity) {
        System.out.println("ISBN: " + isbn + ", Quantity: " + quantity);
    }
}
