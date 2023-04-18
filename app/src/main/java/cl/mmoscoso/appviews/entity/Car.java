package cl.mmoscoso.appviews.entity;

public class Car {
    String manufacture;
    String name;
    int year;

    public Car(String manufacture, String name, int year) {
        this.manufacture = manufacture;
        this.name = name;
        this.year = year;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
