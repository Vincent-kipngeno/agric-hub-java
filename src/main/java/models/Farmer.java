package models;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Farmer farmer = (Farmer) o;
        return id == farmer.id &&
                Objects.equals(name, farmer.name) &&
                Objects.equals(location, farmer.location) &&
                Objects.equals(email, farmer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, location, email);
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
