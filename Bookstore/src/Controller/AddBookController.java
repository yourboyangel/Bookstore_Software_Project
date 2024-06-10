package Controller;

import View.AddBookView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AddBookController {
    private AddBookView addBookView;
    public AddBookController(AddBookView addBookView) {
        this.addBookView = addBookView;
        initialize();
    }
    private void initialize() {
        addBookView.getSubmitButton().setOnAction(event -> handleSubmitButtonClick());
    }
    private void handleSubmitButtonClick() {
        String bookImageUrl = addBookView.getBookUrlField().getText();
        String bookName = addBookView.getBookNameField().getText();
        String category = addBookView.getCategoryField().getText();
        String isbn = addBookView.getIsbnField().getText();
        String author = addBookView.getAuthorField().getText();
        String sellingPrice = addBookView.getSellingPriceField().getText();
        String purchasingPrice = addBookView.getPurchasingPriceField().getText();
        String stock = addBookView.getStockField().getText();

        String data = String.format("%s,%s,%s,%s,%s,%s,%s,%s",
                bookImageUrl, bookName, category, isbn, author, sellingPrice, purchasingPrice, stock);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Bookstore_Software_Project/Bookstore/src/bookData.txt", true))) {
            writer.write(data);
            writer.newLine();
            writer.flush();

            showAlert("Book Added", "Book data added successfully!");

            addBookView.getBookUrlField().clear();
            addBookView.getBookNameField().clear();
            addBookView.getCategoryField().clear();
            addBookView.getIsbnField().clear();
            addBookView.getAuthorField().clear();
            addBookView.getSellingPriceField().clear();
            addBookView.getPurchasingPriceField().clear();
            addBookView.getStockField().clear();

        } catch (IOException e) {
            showAlert("Error", "An error occurred while adding the book data to the file.");
            e.printStackTrace();
        }
    }
    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
