package Model;

import Exceptions.InvalidIsbnException;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private String author;
    private int stock;
    private String imageName;
    private int quantity;
    private double totalPrice;
    public Book(){};
    public Book(String isbn, String title, String category, String supplier, String purchasedDate, double purchasedPrice, double sellingPrice, String author, int stock, String imageName){
        this.isbn = isbn;
        this.title = title;
        this.category = category;
        this.supplier = supplier;
        try{
            SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy");
            this.purchasedDate=dateFormat.parse(purchasedDate);
        } catch (ParseException ex){
            System.out.println("Parse exception!");
        }
        this.purchasedPrice = purchasedPrice;
        this.sellingPrice = sellingPrice;
        this.author = author;
        this.stock = stock;
        this.imageName=imageName;
    }
    public Book(String title,String category,String isbn,String author, double sellingPrice, int quantity){
        this.title = title;
        this.category = category;
        this.isbn = isbn;
        this.author = author;
        this.sellingPrice = sellingPrice;
        this.quantity = quantity;
    }
    public Book(String title, int quantity, double totalPrice){
        this.title = title;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
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
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getImageName() {
        return imageName;
    }
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
    public int getQuantity() {
        return quantity;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
}

