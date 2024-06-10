package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Bill implements Serializable {
    private static int billNumber = 0;
    private int soldQuantity;
    private double totalPrice = 0;
    private GregorianCalendar transaction;
    private ArrayList<Book> soldBooks;

    public Bill() {
        billNumber++;
    }

    public Bill(int billNumber, GregorianCalendar transaction) {
        Bill.billNumber = billNumber;
        this.transaction = transaction;
    }

    public Bill(GregorianCalendar transaction) {
        billNumber++;
        this.transaction = transaction;
    }

    public static int getBillNumber() {
        return billNumber;
    }

    public int getSoldQuantity() {
        return soldQuantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public GregorianCalendar getTransaction() {
        return transaction;
    }

    public void setTransaction(GregorianCalendar transaction) {
        this.transaction = transaction;
    }

    public ArrayList<Book> getSoldBooks() {
        return soldBooks;
    }

    public void setSoldBooks(ArrayList<Book> soldBooks) {
        this.soldBooks = soldBooks;
    }

    public void addBookToBill(Book book) {
        soldBooks.add(book);
        totalPrice += book.getSellingPrice();
    }
}
