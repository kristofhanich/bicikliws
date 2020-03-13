package entity;

public class ShifterEntity {
    public int Id;
    public String Name;

    public ShifterEntity() {
    }

    public ShifterEntity(int id, String name) {
        Id = id;
        Name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
