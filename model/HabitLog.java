package model;

import java.time.LocalDate;

public class HabitLog {
    private int id;
    private int habitId;
    private LocalDate logDate;

    public HabitLog() {}

    public HabitLog(int habitId, LocalDate logDate) {
        this.habitId = habitId;
        this.logDate = logDate;
    }

    public HabitLog(int id, int habitId, LocalDate logDate) {
        this.id = id;
        this.habitId = habitId;
        this.logDate = logDate;
    }

    public int getId() { return id; }
    public int getHabitId() { return habitId; }
    public LocalDate getLogDate() { return logDate; }

    public void setId(int id) { this.id = id; }
    public void setHabitId(int habitId) { this.habitId = habitId; }
    public void setLogDate(LocalDate logDate) { this.logDate = logDate; }
}
