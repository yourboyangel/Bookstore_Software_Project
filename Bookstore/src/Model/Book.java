package Model;

import Exceptions.InvalidIsbnException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Book implements Serializable {
    private String isbn;
    private String title;
    private String category;
    private String supplier;
    private Date purchasedDate;
    private double purchasedPrice;
    private double sellingPrice;
    private List<Author> authors;
    private int stock;

    public Book(){};
    public Book(String isbn, String title, String category, String supplier, Date purchasedDate, double purchasedPrice, double sellingPrice, List<Author> authors, int stock){

        this.isbn = isbn;
        this.title = title;
        this.category = category;
        this.supplier = supplier;
        this.purchasedDate = purchasedDate;
        this.purchasedPrice = purchasedPrice;
        this.sellingPrice = sellingPrice;
        this.authors = authors;
        this.stock = stock;
    }
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) throws InvalidIsbnException {
        String regex="^\\d{3}-\\d{1,5}-\\d{5}-\\d{2}-\\d$";
        if(isbn.matches(regex)){
            this.isbn=isbn;
        }
        else{
            throw new InvalidIsbnException();
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Date getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(Date purchasedDate) {
        this.purchasedDate = purchasedDate;
    }

    public double getPurchasedPrice() {
        return purchasedPrice;
    }

    public void setPurchasedPrice(double purchasedPrice) {
        this.purchasedPrice = purchasedPrice;
    }


    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
