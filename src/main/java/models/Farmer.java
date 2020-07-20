package models;

public class Farmer {
    private int id;
    private String name;
    private String location;
    private String email;

    public Farmer(String name, String location, String email) {
        this.name = name;
        this.location = location;
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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
}
