package View;
import javafx.application.Application;
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

public class LibrarianView extends Application{
    public static void main(String[] args){
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

        Image image = new Image("https://cdn-icons-png.flaticon.com/512/107/107799.png");
        ImageView filterImageView = new ImageView(image);
        filterImageView.setFitHeight(30);
        filterImageView.setFitWidth(30);
        filterImageView.setPreserveRatio(true);
        Button filterButton = new Button("", filterImageView);
        filterButton.setStyle("-fx-background-color:transparent;-fx-border-color:green;-fx-border-radius:10;-fx-border-width:2;");

        Image secondImage = new Image("https://cdn-icons-png.flaticon.com/512/992/992651.png");
        ImageView addImageView = new ImageView(secondImage);
        addImageView.setFitHeight(30);
        addImageView.setFitWidth(30);
        addImageView.setPreserveRatio(true);
        Button addButton = new Button("", addImageView);
        addButton.setStyle("-fx-background-color:transparent;-fx-border-color:green;-fx-border-radius:10;-fx-border-width:2;");


        //FIRST BOOK

        Rectangle book1 = new Rectangle(200, 250);
        Image bookImage = new Image("https://bookland.al/wp-content/uploads/2023/05/rich-dad.jpg");
        ImageView bookImageView = new ImageView(bookImage);
        bookImageView.setFitWidth(book1.getWidth());
        bookImageView.setFitHeight(book1.getHeight());


        TextField searchBook = new TextField();
        searchBook.setMinHeight(38);
        searchBook.setPrefWidth(140);
        searchBook.setMaxWidth(150);
        searchBook.setStyle("-fx-border-color:green;-fx-border-radius:6px;");
        searchBook.setPromptText("Quantity");

        Image cartImage = new Image("https://www.iconpacks.net/icons/2/free-add-to-cart-icon-3046-thumb.png");
        ImageView addCartImage = new ImageView(cartImage);
        addCartImage.setFitHeight(30);
        addCartImage.setFitWidth(40);
        addCartImage.setPreserveRatio(true);
        Button addCart = new Button("", addCartImage);
        addCart.setStyle("-fx-background-color:transparent;-fx-border-color:green;-fx-border-radius:10;-fx-border-width:2;");

        HBox cartAndQuantity = new HBox(searchBook, addCart);
        cartAndQuantity.setAlignment(Pos.CENTER);
        cartAndQuantity.setSpacing(10);

        VBox bookContent1 = new VBox(bookImageView, cartAndQuantity);
        bookContent1.setAlignment(Pos.TOP_CENTER);

        StackPane bookPane = new StackPane(book1, bookContent1);
        bookPane.setAlignment(Pos.TOP_LEFT);
        bookPane.setPadding(new Insets(10, 0, 0, 20));


        //SECOND BOOK


        Rectangle book2 = new Rectangle(200, 250);
        Image bookImage2 = new Image("https://upload.wikimedia.org/wikipedia/commons/0/06/Atomic_habits.jpg");
        ImageView bookImageView2 = new ImageView(bookImage2);
        bookImageView2.setFitWidth(book2.getWidth());
        bookImageView2.setFitHeight(book2.getHeight());


        TextField searchBook2 = new TextField();
        searchBook2.setMinHeight(38);
        searchBook2.setPrefWidth(140);
        searchBook2.setMaxWidth(150);
        searchBook2.setStyle("-fx-border-color:green;-fx-border-radius:6px;");
        searchBook2.setPromptText("Quantity");

        Image cartImage2 = new Image("https://www.iconpacks.net/icons/2/free-add-to-cart-icon-3046-thumb.png");
        ImageView addCartImage2 = new ImageView(cartImage2);
        addCartImage2.setFitHeight(30);
        addCartImage2.setFitWidth(40);
        addCartImage2.setPreserveRatio(true);
        Button addCart2 = new Button("", addCartImage2);
        addCart2.setStyle("-fx-background-color:transparent;-fx-border-color:green;-fx-border-radius:10;-fx-border-width:2;");

        HBox cartAndQuantity2 = new HBox(searchBook2, addCart2);
        cartAndQuantity2.setAlignment(Pos.CENTER);
        cartAndQuantity2.setSpacing(10);

        VBox bookContent2 = new VBox(bookImageView2, cartAndQuantity2);
        bookContent2.setAlignment(Pos.TOP_CENTER);

        StackPane bookPane2 = new StackPane(book2, bookContent2);
        bookPane2.setAlignment(Pos.TOP_LEFT);
        bookPane2.setPadding(new Insets(10, 0, 0, 20));


        //THIRD BOOK


        Rectangle book3 = new Rectangle(200, 250);
        Image bookImage3 = new Image("https://m.media-amazon.com/images/W/MEDIAX_792452-T1/images/I/81gTRv2HXrL._AC_UF1000,1000_QL80_.jpg");
        ImageView bookImageView3 = new ImageView(bookImage3);
        bookImageView3.setFitWidth(book3.getWidth());
        bookImageView3.setFitHeight(book3.getHeight());


        TextField searchBook3 = new TextField();
        searchBook3.setMinHeight(38);
        searchBook3.setPrefWidth(140);
        searchBook3.setMaxWidth(150);
        searchBook3.setStyle("-fx-border-color:green;-fx-border-radius:6px;");
        searchBook3.setPromptText("Quantity");

        Image cartImage3 = new Image("https://www.iconpacks.net/icons/2/free-add-to-cart-icon-3046-thumb.png");
        ImageView addCartImage3 = new ImageView(cartImage3);
        addCartImage3.setFitHeight(30);
        addCartImage3.setFitWidth(40);
        addCartImage3.setPreserveRatio(true);
        Button addCart3 = new Button("", addCartImage3);
        addCart3.setStyle("-fx-background-color:transparent;-fx-border-color:green;-fx-border-radius:10;-fx-border-width:2;");

        HBox cartAndQuantity3 = new HBox(searchBook3, addCart3);
        cartAndQuantity3.setAlignment(Pos.CENTER);
        cartAndQuantity3.setSpacing(10);

        VBox bookContent3 = new VBox(bookImageView3, cartAndQuantity3);
        bookContent3.setAlignment(Pos.TOP_CENTER);

        StackPane bookPane3 = new StackPane(book3, bookContent3);
        bookPane3.setAlignment(Pos.TOP_LEFT);
        bookPane3.setPadding(new Insets(10, 0, 0, 20));


        //FOURTH BOOK


        Rectangle book4 = new Rectangle(200, 250);
        Image bookImage4 = new Image("https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1596384027i/50542735.jpg");
        ImageView bookImageView4 = new ImageView(bookImage4);
        bookImageView4.setFitWidth(book4.getWidth());
        bookImageView4.setFitHeight(book4.getHeight());


        TextField searchBook4 = new TextField();
        searchBook4.setMinHeight(38);
        searchBook4.setPrefWidth(140);
        searchBook4.setMaxWidth(150);
        searchBook4.setStyle("-fx-border-color:green;-fx-border-radius:6px;");
        searchBook4.setPromptText("Quantity");

        Image cartImage4 = new Image("https://www.iconpacks.net/icons/2/free-add-to-cart-icon-3046-thumb.png");
        ImageView addCartImage4 = new ImageView(cartImage4);
        addCartImage4.setFitHeight(30);
        addCartImage4.setFitWidth(40);
        addCartImage4.setPreserveRatio(true);
        Button addCart4 = new Button("", addCartImage4);
        addCart4.setStyle("-fx-background-color:transparent;-fx-border-color:green;-fx-border-radius:10;-fx-border-width:2;");

        HBox cartAndQuantity4 = new HBox(searchBook4, addCart4);
        cartAndQuantity4.setAlignment(Pos.CENTER);
        cartAndQuantity4.setSpacing(10);

        VBox bookContent4 = new VBox(bookImageView4, cartAndQuantity4);
        bookContent4.setAlignment(Pos.TOP_CENTER);

        StackPane bookPane4 = new StackPane(book4, bookContent4);
        bookPane4.setAlignment(Pos.TOP_LEFT);
        bookPane4.setPadding(new Insets(10, 0, 0, 20));


        //FIFTH BOOK


        Rectangle book5 = new Rectangle(200, 250);
        Image bookImage5 = new Image("https://m.media-amazon.com/images/W/MEDIAX_792452-T1/images/I/71t4GuxLCuL._AC_UF1000,1000_QL80_.jpg");
        ImageView bookImageView5 = new ImageView(bookImage5);
        bookImageView5.setFitWidth(book5.getWidth());
        bookImageView5.setFitHeight(book5.getHeight());


        TextField searchBook5 = new TextField();
        searchBook5.setMinHeight(38);
        searchBook5.setPrefWidth(140);
        searchBook5.setMaxWidth(150);
        searchBook5.setStyle("-fx-border-color:green;-fx-border-radius:6px;");
        searchBook5.setPromptText("Quantity");

        Image cartImage5 = new Image("https://www.iconpacks.net/icons/2/free-add-to-cart-icon-3046-thumb.png");
        ImageView addCartImage5 = new ImageView(cartImage5);
        addCartImage5.setFitHeight(30);
        addCartImage5.setFitWidth(40);
        addCartImage5.setPreserveRatio(true);
        Button addCart5 = new Button("", addCartImage5);
        addCart5.setStyle("-fx-background-color:transparent;-fx-border-color:green;-fx-border-radius:10;-fx-border-width:2;");

        HBox cartAndQuantity5 = new HBox(searchBook5, addCart5);
        cartAndQuantity5.setAlignment(Pos.CENTER);
        cartAndQuantity5.setSpacing(10);

        VBox bookContent5 = new VBox(bookImageView5, cartAndQuantity5);
        bookContent5.setAlignment(Pos.TOP_CENTER);

        StackPane bookPane5 = new StackPane(book5, bookContent5);
        bookPane5.setAlignment(Pos.TOP_LEFT);
        bookPane5.setPadding(new Insets(10, 0, 0, 20));


        //SIXTH BOOK
        Rectangle book6 = new Rectangle(200, 250);
        Image bookImage6 = new Image("https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1694722764i/1303.jpg");
        ImageView bookImageView6 = new ImageView(bookImage6);
        bookImageView6.setFitWidth(book6.getWidth());
        bookImageView6.setFitHeight(book6.getHeight());

        TextField searchBook6 = new TextField();
        searchBook6.setMinHeight(38);
        searchBook6.setPrefWidth(140);
        searchBook6.setMaxWidth(150);
        searchBook6.setStyle("-fx-border-color:green;-fx-border-radius:6px;");
        searchBook6.setPromptText("Quantity");

        Image cartImage6 = new Image("https://www.iconpacks.net/icons/2/free-add-to-cart-icon-3046-thumb.png");
        ImageView addCartImage6 = new ImageView(cartImage6);
        addCartImage6.setFitHeight(30);
        addCartImage6.setFitWidth(40);
        addCartImage6.setPreserveRatio(true);
        Button addCart6 = new Button("", addCartImage6);
        addCart6.setStyle("-fx-background-color:transparent;-fx-border-color:green;-fx-border-radius:10;-fx-border-width:2;");

        HBox cartAndQuantity6 = new HBox(searchBook6, addCart6);
        cartAndQuantity6.setAlignment(Pos.CENTER);
        cartAndQuantity6.setSpacing(10);

        VBox bookContent6 = new VBox(bookImageView6, cartAndQuantity6);
        bookContent6.setAlignment(Pos.TOP_CENTER);

        StackPane bookPane6 = new StackPane(book6, bookContent6);
        bookPane6.setAlignment(Pos.TOP_LEFT);
        bookPane6.setPadding(new Insets(10, 0, 0, 20));

        //SEVENTH BOOK
        Rectangle book7 = new Rectangle(200, 250);
        Image bookImage7 = new Image("https://upload.wikimedia.org/wikipedia/en/thumb/c/c1/Thinking%2C_Fast_and_Slow.jpg/220px-Thinking%2C_Fast_and_Slow.jpg");
        ImageView bookImageView7 = new ImageView(bookImage7);
        bookImageView7.setFitWidth(book7.getWidth());
        bookImageView7.setFitHeight(book7.getHeight());

        TextField searchBook7 = new TextField();
        searchBook7.setMinHeight(38);
        searchBook7.setPrefWidth(140);
        searchBook7.setMaxWidth(150);
        searchBook7.setStyle("-fx-border-color:green;-fx-border-radius:6px;");
        searchBook7.setPromptText("Quantity");

        Image cartImage7 = new Image("https://www.iconpacks.net/icons/2/free-add-to-cart-icon-3046-thumb.png");
        ImageView addCartImage7 = new ImageView(cartImage7);
        addCartImage7.setFitHeight(30);
        addCartImage7.setFitWidth(40);
        addCartImage7.setPreserveRatio(true);
        Button addCart7 = new Button("", addCartImage7);
        addCart7.setStyle("-fx-background-color:transparent;-fx-border-color:green;-fx-border-radius:10;-fx-border-width:2;");

        HBox cartAndQuantity7 = new HBox(searchBook7, addCart7);
        cartAndQuantity7.setAlignment(Pos.CENTER);
        cartAndQuantity7.setSpacing(10);

        VBox bookContent7 = new VBox(bookImageView7, cartAndQuantity7);
        bookContent7.setAlignment(Pos.TOP_CENTER);

        StackPane bookPane7 = new StackPane(book7, bookContent7);
        bookPane7.setAlignment(Pos.TOP_LEFT);
        bookPane7.setPadding(new Insets(10, 0, 0, 20));


        //EIGHTH BOOK
        Rectangle book8 = new Rectangle(200, 250);
        Image bookImage8 = new Image("https://www.adrionltd.com/95898-large_default/the-power-of-habit-why-we-do-what-we-do-and-how-to-change.jpg");
        ImageView bookImageView8 = new ImageView(bookImage8);
        bookImageView8.setFitWidth(book8.getWidth());
        bookImageView8.setFitHeight(book8.getHeight());

        TextField searchBook8 = new TextField();
        searchBook8.setMinHeight(38);
        searchBook8.setPrefWidth(140);
        searchBook8.setMaxWidth(150);
        searchBook8.setStyle("-fx-border-color:green;-fx-border-radius:6px;");
        searchBook8.setPromptText("Quantity");

        Image cartImage8 = new Image("https://www.iconpacks.net/icons/2/free-add-to-cart-icon-3046-thumb.png");
        ImageView addCartImage8 = new ImageView(cartImage8);
        addCartImage8.setFitHeight(30);
        addCartImage8.setFitWidth(40);
        addCartImage8.setPreserveRatio(true);
        Button addCart8 = new Button("", addCartImage8);
        addCart8.setStyle("-fx-background-color:transparent;-fx-border-color:green;-fx-border-radius:10;-fx-border-width:2;");

        HBox cartAndQuantity8 = new HBox(searchBook8, addCart8);
        cartAndQuantity8.setAlignment(Pos.CENTER);
        cartAndQuantity8.setSpacing(10);

        VBox bookContent8 = new VBox(bookImageView8, cartAndQuantity8);
        bookContent8.setAlignment(Pos.TOP_CENTER);

        StackPane bookPane8 = new StackPane(book8, bookContent8);
        bookPane8.setAlignment(Pos.TOP_LEFT);
        bookPane8.setPadding(new Insets(10, 0, 0, 20));


        //SAME FOR ALL BOOKS
        HBox bookPair1 = new HBox(bookPane);
        HBox bookPair2 = new HBox(bookPane2);
        HBox bookPair3 = new HBox(bookPane3);
        HBox bookPair4 = new HBox(bookPane4);
        HBox bookPair5 = new HBox(bookPane5);
        HBox bookPair6 = new HBox(bookPane6);
        HBox bookPair7 = new HBox(bookPane7);
        HBox bookPair8 = new HBox(bookPane8);


        HBox bookContainer = new HBox(bookPair1, bookPair2, bookPair3, bookPair4);
        bookContainer.setAlignment(Pos.TOP_CENTER);
        bookContainer.setSpacing(20);

        HBox bookContainer2 = new HBox(bookPair5, bookPair6, bookPair7, bookPair8);
        bookContainer.setAlignment(Pos.TOP_CENTER);
        bookContainer.setSpacing(20);

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
        stage.setFullScreen(true);
        stage.show();

    }

}
