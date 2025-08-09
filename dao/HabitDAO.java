package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Habit;

public class HabitDAO {

    public boolean insertHabit(Habit habit) {
        String sql = "INSERT INTO habits(name, created_at) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, habit.getName());
            stmt.setDate(2, Date.valueOf(habit.getCreatedAt()));
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error inserting habit: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteHabit(int habitId) {
    String sql = "DELETE FROM habits WHERE id = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, habitId);
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        System.err.println("Error deleting habit: " + e.getMessage());
        return false;
    }
}


    public List<Habit> getAllHabits() {
        List<Habit> habits = new ArrayList<>();
        String sql = "SELECT * FROM habits";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                LocalDate createdAt = rs.getDate("created_at").toLocalDate();
                habits.add(new Habit(id, name, createdAt));
            }

        } catch (SQLException e) {
            System.err.println("Error fetching habits: " + e.getMessage());
        }

        return habits;
    }
}
