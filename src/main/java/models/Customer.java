package models;

public class Customer {
    private int id;
    private String name;
    private String location;
    private String email;

    public Customer(String name, String location, String email) {
        this.name = name;
        this.location = location;
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }
}
