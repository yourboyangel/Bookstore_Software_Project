package Model;

import java.io.Serializable;
import java.util.Date;

import Exceptions.IncorrectPasswordException;
import Exceptions.InvalidEmailException;
import Exceptions.InvalidPhoneNumberException;
import javafx.beans.property.SimpleStringProperty;

public class User implements Serializable{
    private String username;
    private String password;
    private Role role;
    private String name;
    private Date birthday;
    private String phone;
    private String email;
    private double salary;

    private transient SimpleStringProperty nameProperty;
    private transient SimpleStringProperty usernameProperty;
    private transient SimpleStringProperty emailProperty;
    private transient SimpleStringProperty phoneProperty;

    public User()throws Exception{};
    public User(String username, String password, Role role) throws Exception {
        this.username=username;
        setPassword(password);
        this.role=role;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword (String password) throws IncorrectPasswordException{
        String regex="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}";
        if(password.matches(regex)){
            this.password=password;
        }
        else{
            throw new IncorrectPasswordException("The password must be at least 8 characters long and it must contain an Uppercase letter, Lowercase letter and Digit!");
        }
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) throws InvalidPhoneNumberException {
        String regex="^\\+3556[789]\\d{7}";
        if(phone.matches(regex)){
            this.phone=phone;
        }
        else{
            throw new InvalidPhoneNumberException("Invalid phone number!");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws InvalidEmailException {
        String regex="^[^@]+@(gmail\\.com|yahoo\\.com|hotmail\\.com)";
        if(email.matches(regex)){
            this.email=email;
        }
        else{
            throw new InvalidEmailException("Invalid email address. Only gmail, yahoo and hotmail are supported!");
        }
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getNameProperty() {
        return nameProperty.get();
    }

    public SimpleStringProperty namePropertyProperty() {
        return nameProperty;
    }

    public void setNameProperty(String nameProperty) {
        this.nameProperty.set(nameProperty);
    }

    public String getUsernameProperty() {
        return usernameProperty.get();
    }

    public SimpleStringProperty usernamePropertyProperty() {
        return usernameProperty;
    }

    public void setUsernameProperty(String usernameProperty) {
        this.usernameProperty.set(usernameProperty);
    }

    public String getEmailProperty() {
        return emailProperty.get();
    }

    public SimpleStringProperty emailPropertyProperty() {
        return emailProperty;
    }

    public void setEmailProperty(String emailProperty) {
        this.emailProperty.set(emailProperty);
    }

    public String getPhoneProperty() {
        return phoneProperty.get();
    }

    public SimpleStringProperty phonePropertyProperty() {
        return phoneProperty;
    }

    public void setPhoneProperty(String phoneProperty) {
        this.phoneProperty.set(phoneProperty);
    }
}

