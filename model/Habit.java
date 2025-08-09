package model;

import java.time.LocalDate;

public class Habit {
    private int id;
    private String name;
    private LocalDate createdAt;

    public Habit() {}

    public Habit(String name, LocalDate createdAt) {
        this.name = name;
        this.createdAt = createdAt;
    }

    public Habit(int id, String name, LocalDate createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public LocalDate getCreatedAt() { return createdAt; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setCreatedAt(LocalDate createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return id + ". " + name + " (Started: " + createdAt + ")";
    }
}
