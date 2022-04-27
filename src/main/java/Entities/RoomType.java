package main.java.Entities;

public class RoomType {
    private int id;
    private String typeName;
    private int capacity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType_name() {
        return typeName;
    }

    public void setType_name(String type_name) {
        this.typeName = type_name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
