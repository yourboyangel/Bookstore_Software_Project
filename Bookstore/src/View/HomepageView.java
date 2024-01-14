package View;
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
    private  ComboBox<String> filterComboBox;
    private List<VBox> bookBoxes;
    private List<VBox> originalOrder;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
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
            List<VBox> filteredBooks = filterBooksByTitle(bookBoxes, searchBar.getText());
            updateBookDisplay(filteredBooks);
        });


        filterComboBox = new ComboBox<>();
        filterComboBox.getItems().addAll("Sort", "Title", "Category", "Authors");
        filterComboBox.setValue("Sort");
        filterComboBox.setStyle("-fx-font-size: 14pt; -fx-background-color: transparent; -fx-border-color: green; -fx-border-radius: 10; -fx-border-width: 2;");
// Modify the searchBar.textProperty().addListener(...) method
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            List<VBox> filteredBooks;

            if ("Title".equals(filterComboBox.getValue())) {
                filteredBooks = filterBooksByTitle(bookBoxes, newValue);
            } else if ("Category".equals(filterComboBox.getValue())) {
                filteredBooks = filterBooksByCategory(bookBoxes, newValue);
            } else if ("Authors".equals(filterComboBox.getValue())) {  // Update "Author" to "Authors" here
                filteredBooks = filterBooksByAuthor(bookBoxes, newValue);
            } else {
                // Handle other filters if needed
                return;
            }

            updateBookDisplay(filteredBooks);
        });


        filterComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equals("Title") && !newValue.equals("Category") && !newValue.equals("Authors")) {
                // Reset to default value if the selected value is not recognized
                filterComboBox.setValue("Sort");
            } else {
                // Handle the recognized filters if needed
                if (newValue.equals("Sort")) {
                    // Restore the original order
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

// Set the style for the ComboBox
        actionComboBox.setStyle("-fx-font-size: 14pt; -fx-background-color: transparent; -fx-border-color: green; -fx-border-radius: 10; -fx-border-width: 2;");

        actionComboBox.setOnAction(event -> {
            // Handle the selected action
            String selectedAction = actionComboBox.getSelectionModel().getSelectedItem();
            if (selectedAction != null) {
                handleComboBoxAction(selectedAction);
            }
        });

// Create buttons for each action
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
// Create rows of VBox
        int booksPerRow = 4;

        // Replace the loop where you create HBox for each row with the following
        for (int i = 0; i < bookBoxes.size(); i += booksPerRow) {
            StackPane row = new StackPane(); // Use StackPane for centering
            HBox booksHBox = new HBox(); // Use HBox for horizontal arrangement
            booksHBox.setAlignment(Pos.CENTER); // Center books in the row
            booksHBox.setSpacing(40);  // Adjust the spacing as needed

            // Calculate the number of books to add in this row
            int booksInThisRow = Math.min(booksPerRow, bookBoxes.size() - i);

            // Create HBox for each book in this row
            for (int j = i; j < i + booksInThisRow; j++) {
                VBox bookVBox = bookBoxes.get(j);
                booksHBox.getChildren().add(bookVBox);
            }

            row.getChildren().add(booksHBox);
            bookContainer.getChildren().add(row);

            // Add a separator line between rows
            if (i + booksInThisRow < bookBoxes.size()) {
                Separator separator = new Separator();
                separator.setOrientation(Orientation.HORIZONTAL);
                bookContainer.getChildren().add(separator);
            }
        }

// Set the alignment of bookContainer to center
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
            // Reset to original order and set filterComboBox to "Sort"
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

        // Use Platform.runLater to update UI on the JavaFX Application Thread
        Platform.runLater(stage::show);
    }

    private VBox createBook(String imageName, String title, String category, String isbn, String author, double sellingPrice, double purchasingPrice, int stock) {
        String imagePath = "/Images/" + imageName;
        InputStream imageStream = getClass().getResourceAsStream(imagePath);

        if (imageStream == null) {
            System.out.println("Image not found: " + imagePath);
            return new VBox(); // or handle the missing image in some way
        }

        Image bookImage = new Image(imageStream);

        ImageView bookImageView = new ImageView(bookImage);
        bookImageView.setFitWidth(200);
        bookImageView.setFitHeight(250);

        // VBox for book attributes
        VBox attributesBox = new VBox();
        attributesBox.setAlignment(Pos.CENTER_LEFT);
        attributesBox.setSpacing(5);

        // String title, String category, String isbn, String author, double sellingPrice, double purchasingPrice, int stock
        Label titleLabel = createLabel("Title: " + title);
        Label categoryLabel = createLabel("Category: " + category);
        Label isbnLabel = createLabel("ISBN: " + isbn);
        Label authorLabel = createLabel("Author: " + author);
        Label sellingPriceLabel = createLabel("Selling Price: $" + sellingPrice);
        Label purchasingPriceLabel = createLabel("Purchasing Price: $" + purchasingPrice); // New field
        Label stockLabel = createLabel("Stock: " + stock);

        attributesBox.getChildren().addAll(titleLabel, categoryLabel, isbnLabel, authorLabel, sellingPriceLabel, purchasingPriceLabel, stockLabel);

        // VBox for book and details
        VBox bookVBox = new VBox();
        bookVBox.getChildren().add(bookImageView);
        bookVBox.getChildren().addAll(attributesBox);

        // Box for book details
        bookVBox.setStyle("-fx-background-color: transparent; -fx-border-color: black; -fx-border-radius: 10; -fx-border-width: 3px; -fx-padding: 10px;");
        bookVBox.setAlignment(Pos.CENTER);
        bookVBox.setPadding(new Insets(10, 0, 0, 0));

        return bookVBox;
    }

    private VBox createBookFromData(String[] bookData) {
        // Extracting data from the array
        if (bookData.length < 8) {
            System.out.println("Invalid data format. Insufficient elements in the array. Data: " + String.join(",", bookData));
            return new VBox();  // or handle the situation in some way
        }

        String imageName = bookData[0];
        String title = bookData[1];
        String category = bookData[2];
        String isbn = bookData[3];
        String author = bookData[4];
        double sellingPrice = Double.parseDouble(bookData[5]);
        double purchasingPrice = Double.parseDouble(bookData[6]); // New field
        int stock = Integer.parseInt(bookData[7]);

        String imagePath = "/Images/" + imageName;
        InputStream imageStream = getClass().getResourceAsStream(imagePath);

        if (imageStream == null) {
            System.out.println("Image not found: " + imagePath);
            return new VBox(); // or handle the missing image in some way
        }

        Image bookImage = new Image(imageStream);

        ImageView bookImageView = new ImageView(bookImage);
        bookImageView.setFitWidth(200);
        bookImageView.setFitHeight(250);

        // VBox for book attributes
        VBox attributesBox = new VBox();
        attributesBox.setAlignment(Pos.CENTER_LEFT);
        attributesBox.setSpacing(5);

        // String title, String category, String isbn, String author, double sellingPrice, double purchasingPrice, int stock
        Label titleLabel = createLabel("Title: " + title);
        Label categoryLabel = createLabel("Category: " + category);
        Label isbnLabel = createLabel("ISBN: " + isbn);
        Label authorLabel = createLabel("Author: " + author);
        Label sellingPriceLabel = createLabel("Selling Price: $" + sellingPrice);
        Label purchasingPriceLabel = createLabel("Purchasing Price: $" + purchasingPrice); // New field
        Label stockLabel = createLabel("Stock: " + stock);

        attributesBox.getChildren().addAll(titleLabel, categoryLabel, isbnLabel, authorLabel, sellingPriceLabel, purchasingPriceLabel, stockLabel);

        // VBox for book and details
        VBox bookVBox = new VBox();
        bookVBox.getChildren().add(bookImageView);
        bookVBox.getChildren().addAll(attributesBox);

        // Box for book details
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
                return bookBoxes;  // Return an empty list or handle the situation
            }

            String line;
            while ((line = reader.readLine()) != null) {
                String[] bookData = line.split(",");

                // Add a check for insufficient elements
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
        // Clear the existing content
        bookContainer.getChildren().clear();

        if (!filteredBooks.isEmpty()) {
            // Determine the number of books per row
            int booksPerRow = 4;

            // Create rows of VBox
            for (int i = 0; i < filteredBooks.size(); i += booksPerRow) {
                StackPane row = new StackPane(); // Use StackPane for centering
                HBox booksHBox = new HBox(); // Use HBox for horizontal arrangement
                booksHBox.setAlignment(Pos.CENTER); // Center books in the row
                booksHBox.setSpacing(40);  // Adjust the spacing as needed

                // Calculate the number of books to add in this row
                int booksInThisRow = Math.min(booksPerRow, filteredBooks.size() - i);

                // Create HBox for each book in this row
                for (int j = i; j < i + booksInThisRow; j++) {
                    VBox bookVBox = filteredBooks.get(j);
                    booksHBox.getChildren().add(bookVBox);
                }

                row.getChildren().add(booksHBox);
                bookContainer.getChildren().add(row);

                // Add a separator line between rows
                if (i + booksInThisRow < filteredBooks.size()) {
                    Separator separator = new Separator();
                    separator.setOrientation(Orientation.HORIZONTAL);
                    bookContainer.getChildren().add(separator);
                }
            }
        } else {
            // Display a message if no books are found
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
            // Sort logic for default sorting (e.g., sort by title)
            filteredBooks.addAll(allBooks);
            Collections.sort(filteredBooks, Comparator.comparing(this::getTitleFromBookBox));
        } else {
            // Filter based on the selected category
            for (VBox bookBox : allBooks) {
                Label categoryLabel = (Label) ((VBox) bookBox.getChildren().get(1)).getChildren().get(1);
                String bookCategory = categoryLabel.getText().substring("Category: ".length());

                // Use trim() to remove leading/trailing whitespaces
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
            // Sort logic for default sorting (e.g., sort by title)
            filteredBooks.addAll(allBooks);
            Collections.sort(filteredBooks, Comparator.comparing(this::getTitleFromBookBox));
        } else {
            // Filter based on the selected author
            for (VBox bookBox : allBooks) {
                Label authorLabel = (Label) ((VBox) bookBox.getChildren().get(1)).getChildren().get(3);
                String bookAuthor = authorLabel.getText().substring("Author: ".length());

                // Use trim() to remove leading/trailing whitespaces and make the comparison case-insensitive
                if (bookAuthor.toLowerCase().trim().contains(author.toLowerCase().trim())) {
                    filteredBooks.add(bookBox);
                }
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
        // Implement the logic for each action
        switch (action) {
            case "Create Bill":
                try {
                    Stage billStage = new Stage();
                    new BillView().start(billStage);

                    // Prevent the new stage from closing the main stage
                    billStage.initModality(Modality.WINDOW_MODAL);
                    billStage.initOwner(bookContainer.getScene().getWindow());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "Add Book":
                try {
                    Stage addBookStage = new Stage();
                    new AddBookView().start(addBookStage);

                    // Prevent the new stage from closing the main stage
                    addBookStage.initModality(Modality.WINDOW_MODAL);
                    addBookStage.initOwner(bookContainer.getScene().getWindow());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "Check Librarian Performance":
                // Add your logic for 'Check Librarian Performance'
                break;
            case "Book Statistics":
                // Add your logic for 'Book Statistics'
                break;
            case "Manage Employees":
                // Add your logic for 'Manage Employees'
                break;
            case "Total Cost":
                // Add your logic for 'Total Cost'
                break;
            default:
                break;
        }
    }

}