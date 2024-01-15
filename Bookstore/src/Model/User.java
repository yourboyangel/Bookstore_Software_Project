package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import Exceptions.IncorrectPasswordException;
import Exceptions.InvalidEmailException;
import Exceptions.InvalidPhoneNumberException;
import Permissions.Permission;
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
    private Set<String> permissions;


    private transient SimpleStringProperty nameProperty;
    private transient SimpleStringProperty usernameProperty;
    private transient SimpleStringProperty emailProperty;
    private transient SimpleStringProperty phoneProperty;

    public User()throws Exception{};
    public User(String username, String password, Role role) throws Exception {
        this.username=username;
        this.password=password;
        this.role=role;

    }
    public User(String username, String password, Role role, String name, Date birthday, String phone, String email, double salary){
        this.username = username;
        this.password = password;
        this.role = role;
        this.name = name;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
        this.salary = salary;
        this.permissions=new HashSet<>();
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
            throw new IncorrectPasswordException();
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
            throw new InvalidPhoneNumberException();
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
            throw new InvalidEmailException();
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

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }
    public void addPermission(String permission) {
        permissions.add(permission);
    }

    // Method to remove a permission
    public void removePermission(String permission) {
        permissions.remove(permission);
    }

    // Method to check if a user has a specific permission
    public boolean hasPermission(String permission) {
        return permissions.contains(permission);
    }
}

