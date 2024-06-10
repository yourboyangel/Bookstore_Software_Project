package View;
import Controller.ManageEmployeesController;
import javafx.application.Application;
import java.util.Collections;
import java.util.Comparator;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class HomepageView extends Application {
    private VBox bookContainer;
    private ComboBox<String> filterComboBox;
    private List<VBox> bookBoxes;
    private List<VBox> originalOrder;
    private ManageEmployeesController controller;
    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage stage) {
        this.controller = controller;

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.TOP_CENTER);

        double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        Rectangle headerRectangle = new Rectangle(screenWidth, 100, Color.BLACK);

        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.TOP_CENTER);

        Text headerText = new Text("HOMEPAGE");
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

        searchBar.setOnAction(event -> {
            List<VBox> filteredBooks = filterBooksByISBN(bookBoxes, searchBar.getText());
            updateBookDisplay(filteredBooks);
        });

        filterComboBox = new ComboBox<>();
        filterComboBox.getItems().addAll("Sort", "Title", "Category", "Authors", "ISBN");
        filterComboBox.setValue("Sort");
        filterComboBox.setStyle("-fx-font-size: 14pt; -fx-background-color: transparent; -fx-border-color: green; -fx-border-radius: 10; -fx-border-width: 2;");

        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            List<VBox> filteredBooks;

            if ("Title".equals(filterComboBox.getValue())) {
                filteredBooks = filterBooksByTitle(bookBoxes, newValue);
            } else if ("Category".equals(filterComboBox.getValue())) {
                filteredBooks = filterBooksByCategory(bookBoxes, newValue);
            } else if ("Authors".equals(filterComboBox.getValue())) {
                filteredBooks = filterBooksByAuthor(bookBoxes, newValue);
            } else if ("ISBN".equals(filterComboBox.getValue())) {
                filteredBooks = filterBooksByISBN(bookBoxes, newValue);
            } else {
                return;
            }
            updateBookDisplay(filteredBooks);
        });
        filterComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equals("Title") && !newValue.equals("Category") && !newValue.equals("Authors") && !newValue.equals("ISBN")) {

                filterComboBox.setValue("Sort");
            } else {
                if (newValue.equals("Sort")) {
                    updateBookDisplay(originalOrder);
                }
            }
        });
        Image secondImage = new Image("/Images/down-arrow.png");
        ImageView addImageView = new ImageView(secondImage);

        addImageView.setFitHeight(30);
        addImageView.setFitWidth(30);
        addImageView.setPreserveRatio(true);

        Button addButton = new Button("", addImageView);
        addButton.setStyle("-fx-background-color:transparent;-fx-border-color:green;-fx-border-radius:10;-fx-border-width:2;");

        ComboBox<String> actionComboBox = new ComboBox<>();
        actionComboBox.getItems().addAll("Create Bill", "Add Book", "Check Librarian Performance", "Book Statistics", "Manage Employees", "Total Cost");
        actionComboBox.setStyle("-fx-font-size: 14pt; -fx-background-color: transparent; -fx-border-color: green; -fx-border-radius: 10; -fx-border-width: 2;");

        actionComboBox.setOnAction(event -> {
            String selectedAction = actionComboBox.getSelectionModel().getSelectedItem();
            if (selectedAction != null) {
                handleComboBoxAction(selectedAction);
            }
        });

        Button createBillButton = createComboBoxButton("Create Bill");
        Button addBookButton = createComboBoxButton("Add Book");
        Button checkLibrarianPerformanceButton = createComboBoxButton("Check Librarian Performance");
        Button bookStatisticsButton = createComboBoxButton("Book Statistics");
        Button manageEmployeesButton = createComboBoxButton("Manage Employees");
        Button totalCostButton = createComboBoxButton("Total Cost");

        bookContainer = new VBox();
        bookContainer.setAlignment(Pos.TOP_CENTER);
        bookContainer.setSpacing(40);
        bookContainer.setPadding(new Insets(10, 40, 10, 140));
        bookBoxes = readBookDataFromFile("bookData.txt");

        originalOrder = new ArrayList<>(bookBoxes);
        int booksPerRow = 4;

        for (int i = 0; i < bookBoxes.size(); i += booksPerRow) {
            StackPane row = new StackPane();
            HBox booksHBox = new HBox();
            booksHBox.setAlignment(Pos.CENTER);
            booksHBox.setSpacing(40);

            int booksInThisRow = Math.min(booksPerRow, bookBoxes.size() - i);

            for (int j = i; j < i + booksInThisRow; j++) {
                VBox bookVBox = bookBoxes.get(j);
                booksHBox.getChildren().add(bookVBox);
            }
            row.getChildren().add(booksHBox);
            bookContainer.getChildren().add(row);

            if (i + booksInThisRow < bookBoxes.size()) {
                Separator separator = new Separator();
                separator.setOrientation(Orientation.HORIZONTAL);
                bookContainer.getChildren().add(separator);
            }
        }

        bookContainer.setAlignment(Pos.CENTER);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(bookContainer);
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: transparent; -fx-border-color: green; -fx-border-radius: 10; -fx-border-width: 2;-fx-font-weight:bold");
        backButton.setPrefHeight(38);
        backButton.setMaxHeight(38);
        backButton.setMinHeight(38);
        backButton.setOnAction(event -> {
            updateBookDisplay(originalOrder);
            filterComboBox.setValue("Sort");
            searchBar.clear();
        });
        hbox.getChildren().addAll(backButton, searchBar, filterComboBox, actionComboBox);
        vbox.getChildren().addAll(stackPane, hbox, scrollPane);
        Scene librarianScene = new Scene(vbox, 800, 600);
        stage.setScene(librarianScene);
        stage.setTitle("Librarian View");
        Screen screen = Screen.getPrimary();
        stage.setX(screen.getVisualBounds().getMinX());
        stage.setY(screen.getVisualBounds().getMinY());
        stage.setWidth(screen.getVisualBounds().getWidth());
        stage.setHeight(screen.getVisualBounds().getHeight());

        Platform.runLater(stage::show);
    }

    private VBox createBook(String imageName, String title, String category, String isbn, String author, double sellingPrice, double purchasingPrice, int stock) {
        String imagePath = "/Images/" + imageName;
        InputStream imageStream = getClass().getResourceAsStream(imagePath);

        if (imageStream == null) {
            System.out.println("Image not found: " + imagePath);
            return new VBox();
        }

        Image bookImage = new Image(imageStream);
        ImageView bookImageView = new ImageView(bookImage);
        bookImageView.setFitWidth(200);
        bookImageView.setFitHeight(250);

        VBox attributesBox = new VBox();
        attributesBox.setAlignment(Pos.CENTER_LEFT);
        attributesBox.setSpacing(5);

        Label titleLabel = createLabel("Title: " + title);
        Label categoryLabel = createLabel("Category: " + category);
        Label isbnLabel = createLabel("ISBN: " + isbn);
        Label authorLabel = createLabel("Author: " + author);
        Label sellingPriceLabel = createLabel("Selling Price: $" + sellingPrice);
        Label purchasingPriceLabel = createLabel("Purchasing Price: $" + purchasingPrice);
        Label stockLabel = createLabel("Stock: " + stock);

        attributesBox.getChildren().addAll(titleLabel, categoryLabel, isbnLabel, authorLabel, sellingPriceLabel, purchasingPriceLabel, stockLabel);

        VBox bookVBox = new VBox();
        bookVBox.getChildren().add(bookImageView);
        bookVBox.getChildren().addAll(attributesBox);

        bookVBox.setStyle("-fx-background-color: transparent; -fx-border-color: black; -fx-border-radius: 10; -fx-border-width: 3px; -fx-padding: 10px;");
        bookVBox.setAlignment(Pos.CENTER);
        bookVBox.setPadding(new Insets(10, 0, 0, 0));
        return bookVBox;
    }

    private VBox createBookFromData(String[] bookData) {
        if (bookData.length < 8) {
            System.out.println("Invalid data format. Insufficient elements in the array. Data: " + String.join(",", bookData));
            return new VBox();
        }

        String imageName = bookData[0];
        String title = bookData[1];
        String category = bookData[2];
        String isbn = bookData[3];
        String author = bookData[4];
        double sellingPrice = Double.parseDouble(bookData[5]);
        double purchasingPrice = Double.parseDouble(bookData[6]);
        int stock = Integer.parseInt(bookData[7]);

        String imagePath = "/Images/" + imageName;
        InputStream imageStream = getClass().getResourceAsStream(imagePath);

        if (imageStream == null) {
            System.out.println("Image not found: " + imagePath);
            return new VBox();
        }

        Image bookImage = new Image(imageStream);
        ImageView bookImageView = new ImageView(bookImage);
        bookImageView.setFitWidth(200);
        bookImageView.setFitHeight(250);

        VBox attributesBox = new VBox();
        attributesBox.setAlignment(Pos.CENTER_LEFT);
        attributesBox.setSpacing(5);

        Label titleLabel = createLabel("Title: " + title);
        Label categoryLabel = createLabel("Category: " + category);
        Label isbnLabel = createLabel("ISBN: " + isbn);
        Label authorLabel = createLabel("Author: " + author);
        Label sellingPriceLabel = createLabel("Selling Price: $" + sellingPrice);
        Label purchasingPriceLabel = createLabel("Purchasing Price: $" + purchasingPrice);
        Label stockLabel = createLabel("Stock: " + stock);

        attributesBox.getChildren().addAll(titleLabel, categoryLabel, isbnLabel, authorLabel, sellingPriceLabel, purchasingPriceLabel, stockLabel);

        VBox bookVBox = new VBox();
        bookVBox.getChildren().add(bookImageView);
        bookVBox.getChildren().addAll(attributesBox);

        bookVBox.setStyle("-fx-background-color: transparent; -fx-border-color: black; -fx-border-radius: 10; -fx-border-width: 3px; -fx-padding: 10px;");
        bookVBox.setAlignment(Pos.CENTER);
        bookVBox.setPadding(new Insets(10, 0, 0, 0));

        return bookVBox;
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setFont(Font.font("Times New Roman", FontWeight.BOLD, 14));
        return label;
    }

    private List<VBox> readBookDataFromFile(String filePath) {
        List<VBox> bookBoxes = new ArrayList<>();

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            if (inputStream == null) {
                System.out.println("File not found: " + filePath);
                return bookBoxes;
            }
            String line;
            while ((line = reader.readLine()) != null) {
                String[] bookData = line.split(",");

                if (bookData.length >= 7) {
                    VBox bookBox = createBookFromData(bookData);
                    bookBoxes.add(bookBox);
                } else {
                    System.out.println("Invalid data format. Insufficient elements in the array. Data: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookBoxes;
    }

    private List<VBox> filterBooksByTitle(List<VBox> allBooks, String searchText) {
        List<VBox> filteredBooks = new ArrayList<>();
        if (allBooks == null) {
            System.out.println("Book data is not initialized.");
            return filteredBooks;
        }
        for (VBox bookBox : allBooks) {
            Label titleLabel = (Label) ((VBox) bookBox.getChildren().get(1)).getChildren().get(0);
            String title = titleLabel.getText().substring("Title: ".length());
            if (title.toLowerCase().contains(searchText.toLowerCase())) {
                filteredBooks.add(bookBox);
            }
        }
        return filteredBooks;
    }


    private void updateBookDisplay(List<VBox> filteredBooks) {
        bookContainer.getChildren().clear();

        if (!filteredBooks.isEmpty()) {
            int booksPerRow = 4;

            for (int i = 0; i < filteredBooks.size(); i += booksPerRow) {
                StackPane row = new StackPane();
                HBox booksHBox = new HBox();
                booksHBox.setAlignment(Pos.CENTER);
                booksHBox.setSpacing(40);

                int booksInThisRow = Math.min(booksPerRow, filteredBooks.size() - i);

                for (int j = i; j < i + booksInThisRow; j++) {
                    VBox bookVBox = filteredBooks.get(j);
                    booksHBox.getChildren().add(bookVBox);
                }
                row.getChildren().add(booksHBox);
                bookContainer.getChildren().add(row);

                if (i + booksInThisRow < filteredBooks.size()) {
                    Separator separator = new Separator();
                    separator.setOrientation(Orientation.HORIZONTAL);
                    bookContainer.getChildren().add(separator);
                }
            }
        } else {
            Text noBooksText = new Text("No matching books found.");
            noBooksText.setFont(Font.font("Arial", FontWeight.BOLD, 16));
            bookContainer.getChildren().add(noBooksText);
        }
    }

    private List<VBox> filterBooksByCategory(List<VBox> allBooks, String category) {
        List<VBox> filteredBooks = new ArrayList<>();
        if (allBooks == null) {
            System.out.println("Book data is not initialized.");
            return filteredBooks;
        }

        if ("Sort".equals(filterComboBox.getValue())) {
            filteredBooks.addAll(allBooks);
            Collections.sort(filteredBooks, Comparator.comparing(this::getTitleFromBookBox));
        } else {
            for (VBox bookBox : allBooks) {
                Label categoryLabel = (Label) ((VBox) bookBox.getChildren().get(1)).getChildren().get(1);
                String bookCategory = categoryLabel.getText().substring("Category: ".length());

                if (bookCategory.toLowerCase().trim().contains(category.toLowerCase().trim())) {
                    filteredBooks.add(bookBox);
                }
            }
        }
        return filteredBooks;
    }

    private String getTitleFromBookBox(VBox bookBox) {
        Label titleLabel = (Label) ((VBox) bookBox.getChildren().get(1)).getChildren().get(0);
        return titleLabel.getText().substring("Title: ".length()).toLowerCase();
    }

    private List<VBox> filterBooksByAuthor(List<VBox> allBooks, String author) {
        List<VBox> filteredBooks = new ArrayList<>();
        if (allBooks == null) {
            System.out.println("Book data is not initialized.");
            return filteredBooks;
        }

        if ("Sort".equals(filterComboBox.getValue())) {
            filteredBooks.addAll(allBooks);
            Collections.sort(filteredBooks, Comparator.comparing(this::getTitleFromBookBox));
        } else {
            for (VBox bookBox : allBooks) {
                Label authorLabel = (Label) ((VBox) bookBox.getChildren().get(1)).getChildren().get(3);
                String bookAuthor = authorLabel.getText().substring("Author: ".length());

                if (bookAuthor.toLowerCase().trim().contains(author.toLowerCase().trim())) {
                    filteredBooks.add(bookBox);
                }
            }
        }
        return filteredBooks;
    }

    private List<VBox> filterBooksByISBN(List<VBox> allBooks, String partialISBN) {
        List<VBox> filteredBooks = new ArrayList<>();

        if (allBooks == null) {
            System.out.println("Book data is not initialized.");
            return filteredBooks;
        }

        for (VBox bookBox : allBooks) {
            Label isbnLabel = (Label) ((VBox) bookBox.getChildren().get(1)).getChildren().get(2);
            String bookISBN = isbnLabel.getText().substring("ISBN: ".length());

            if (bookISBN.toLowerCase().startsWith(partialISBN.toLowerCase())) {
                filteredBooks.add(bookBox);
            }
        }
        return filteredBooks;
    }


    private Button createComboBoxButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: transparent; -fx-border-color: green; -fx-border-radius: 10; -fx-border-width: 2; -fx-font-weight:bold");
        button.setPrefHeight(38);
        button.setMaxHeight(38);
        button.setMinHeight(38);
        return button;
    }


    private void handleComboBoxAction(String action) {
        try {
            Stage newStage = new Stage();
            newStage.initModality(Modality.WINDOW_MODAL);


            newStage.initOwner(bookContainer.getScene().getWindow());

            switch (action) {
                case "Create Bill":
                    new BillView().start(newStage);
                    break;
                case "Add Book":
                    new AddBookView().start(newStage);
                    break;
                case "Check Librarian Performance":
                    new CheckLibrarianPerformanceView().start(newStage);
                    break;
                case "Book Statistics":
                    new BookStatisticsView().start(newStage);
                    break;
                case "Manage Employees":
                    new manageEmployeesView().start(newStage);
                    break;
                case "Total Cost":
                    new TotalCostView().start(newStage);
                    break;
                default:
                    break;
            }


            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}