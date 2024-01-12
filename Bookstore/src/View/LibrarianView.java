package View;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.io.InputStream;

public class LibrarianView extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.TOP_CENTER);

        double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        Rectangle headerRectangle = new Rectangle(screenWidth, 100, Color.GREEN);

        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.TOP_CENTER);

        Text headerText = new Text("Librarian");
        headerText.setFont(Font.font("Times New Roman", 86));
        headerText.setFill(Color.WHITE);

        stackPane.getChildren().addAll(headerRectangle, headerText);

        HBox hbox = new HBox();
        hbox.setSpacing(12);
        hbox.setPadding(new Insets(10, 10, 10, 10));
        hbox.setAlignment(Pos.TOP_CENTER);

        TextField searchBar = new TextField();
        searchBar.setMinHeight(38);
        searchBar.setPrefWidth(400);
        searchBar.setStyle("-fx-border-color:green;-fx-border-radius:6px;");
        searchBar.setPromptText("Search book");

        Image image = new Image("/Images/filter.png");
        ImageView filterImageView = new ImageView(image);
        filterImageView.setFitHeight(30);
        filterImageView.setFitWidth(30);
        filterImageView.setPreserveRatio(true);
        Button filterButton = new Button("", filterImageView);
        filterButton.setStyle("-fx-background-color:transparent;-fx-border-color:green;-fx-border-radius:10;-fx-border-width:2;");

        Image secondImage = new Image("/Images/addbill.png");
        ImageView addImageView = new ImageView(secondImage);
        addImageView.setFitHeight(30);
        addImageView.setFitWidth(30);
        addImageView.setPreserveRatio(true);
        Button addButton = new Button("", addImageView);
        addButton.setStyle("-fx-background-color:transparent;-fx-border-color:green;-fx-border-radius:10;-fx-border-width:2;");

        // Example usage of the createBook function
        HBox bookPair1 = createBook("book1.jpg", "Quantity");
        HBox bookPair2 = createBook("book2.jpg", "Quantity");
        HBox bookPair3 = createBook("book3.png", "Quantity");
        HBox bookPair4 = createBook("book4.jpg", "Quantity");
        HBox bookPair5 = createBook("book5.jpg", "Quantity");
        HBox bookPair6 = createBook("book6.jpg", "Quantity");
        HBox bookPair7 = createBook("book7.jpg", "Quantity");
        HBox bookPair8 = createBook("book8.jpg", "Quantity");

        HBox bookContainer = new HBox(bookPair1, bookPair2, bookPair3, bookPair4);
        bookContainer.setAlignment(Pos.TOP_CENTER);
        bookContainer.setSpacing(20);

        HBox bookContainer2 = new HBox(bookPair5, bookPair6, bookPair7, bookPair8);
        bookContainer2.setAlignment(Pos.TOP_CENTER);
        bookContainer2.setSpacing(20);

        HBox.setHgrow(bookPair1, Priority.ALWAYS);
        HBox.setHgrow(bookPair2, Priority.ALWAYS);
        HBox.setHgrow(bookPair3, Priority.ALWAYS);
        HBox.setHgrow(bookPair4, Priority.ALWAYS);
        HBox.setHgrow(bookPair5, Priority.ALWAYS);
        HBox.setHgrow(bookPair6, Priority.ALWAYS);
        HBox.setHgrow(bookPair7, Priority.ALWAYS);
        HBox.setHgrow(bookPair8, Priority.ALWAYS);

        bookPair1.setAlignment(Pos.CENTER);
        bookPair2.setAlignment(Pos.CENTER);
        bookPair3.setAlignment(Pos.CENTER);
        bookPair4.setAlignment(Pos.CENTER);
        bookPair5.setAlignment(Pos.CENTER);
        bookPair6.setAlignment(Pos.CENTER);
        bookPair7.setAlignment(Pos.CENTER);
        bookPair8.setAlignment(Pos.CENTER);

        hbox.getChildren().addAll(searchBar, filterButton, addButton);
        vbox.getChildren().addAll(stackPane, hbox, bookContainer, bookContainer2);

        Scene librarianScene = new Scene(vbox, 800, 600);
        stage.setScene(librarianScene);
        stage.setTitle("Librarian View");
        Screen screen = Screen.getPrimary();
        stage.setX(screen.getVisualBounds().getMinX());
        stage.setY(screen.getVisualBounds().getMinY());
        stage.setWidth(screen.getVisualBounds().getWidth());
        stage.setHeight(screen.getVisualBounds().getHeight());

        // Use Platform.runLater to update UI on the JavaFX Application Thread
        Platform.runLater(stage::show);
    }

    private HBox createBook(String imageName, String quantityPromptText) {
        String imagePath = "/Images/" + imageName;  // Note the leading slash
        InputStream imageStream = getClass().getResourceAsStream(imagePath);

        if (imageStream == null) {
            System.out.println("Image not found: " + imagePath);
            return new HBox(); // or handle the missing image in some way
        }

        Image bookImage = new Image(imageStream);

        ImageView bookImageView = new ImageView(bookImage);
        bookImageView.setFitWidth(200); // Set the width directly
        bookImageView.setFitHeight(250); // Set the height directly

        TextField searchBook = new TextField();
        searchBook.setMinHeight(38);
        searchBook.setPrefWidth(140);
        searchBook.setMaxWidth(150);
        searchBook.setStyle("-fx-border-color:green;-fx-border-radius:6px;");
        searchBook.setPromptText(quantityPromptText);


        Image cartImage = new Image("/Images/cart.jpg");
        ImageView addCartImage = new ImageView(cartImage);
        addCartImage.setFitHeight(30);
        addCartImage.setFitWidth(40);
        addCartImage.setPreserveRatio(true);

        Button addCart = new Button("", addCartImage);
        addCart.setStyle("-fx-background-color:transparent;-fx-border-color:green;-fx-border-radius:10;-fx-border-width:2;");

        HBox cartAndQuantity = new HBox(searchBook, addCart);
        cartAndQuantity.setAlignment(Pos.CENTER);
        cartAndQuantity.setSpacing(10);

        VBox bookContent = new VBox(bookImageView, cartAndQuantity);
        bookContent.setAlignment(Pos.TOP_CENTER);

        StackPane bookPane = new StackPane(bookContent);
        bookPane.setAlignment(Pos.TOP_LEFT);
        bookPane.setPadding(new Insets(10, 0, 0, 20));

        HBox bookPair = new HBox(bookPane);
        HBox.setHgrow(bookPair, Priority.ALWAYS);
        bookPair.setAlignment(Pos.CENTER);
        return bookPair;
    }
    private Button createStyledButton(String imageUrl) {
        Image image = new Image(imageUrl);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        imageView.setPreserveRatio(true);
        Button button = new Button("", imageView);
        button.setStyle("-fx-background-color:transparent;-fx-border-color:green;-fx-border-radius:10;-fx-border-width:2;");
        return button;
    }
}