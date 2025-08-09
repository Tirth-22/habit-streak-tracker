package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.HabitLog;

public class HabitLogDAO {

    public boolean markHabitAsDone(int habitId, LocalDate date) {
        if (isAlreadyMarked(habitId, date)) return false;

        String sql = "INSERT INTO habit_log (habit_id, log_date) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, habitId);
            stmt.setDate(2, Date.valueOf(date));
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error logging habit: " + e.getMessage());
            return false;
        }
    }
    public boolean deleteLogsForHabit(int habitId) {
    String sql = "DELETE FROM habit_log WHERE habit_id = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, habitId);
        stmt.executeUpdate();
        return true;
    } catch (SQLException e) {
        System.err.println("Error deleting logs: " + e.getMessage());
        return false;
    }
}

    private boolean isAlreadyMarked(int habitId, LocalDate date) {
        String sql = "SELECT COUNT(*) FROM habit_log WHERE habit_id = ? AND log_date = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, habitId);
            stmt.setDate(2, Date.valueOf(date));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;

        } catch (SQLException e) {
            System.err.println("Error checking log: " + e.getMessage());
        }
        return false;
    }

    public List<HabitLog> getHabitLogs(int habitId) {
        List<HabitLog> logs = new ArrayList<>();
        String sql = "SELECT * FROM habit_log WHERE habit_id = ? ORDER BY log_date DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, habitId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                LocalDate date = rs.getDate("log_date").toLocalDate();
                logs.add(new HabitLog(id, habitId, date));
            }

        } catch (SQLException e) {
            System.err.println("Error fetching logs: " + e.getMessage());
        }

        return logs;
    }

    public int calculateStreak(int habitId) {
        String sql = "SELECT log_date FROM habit_log WHERE habit_id = ? ORDER BY log_date DESC";
        int streak = 0;
        LocalDate today = LocalDate.now();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, habitId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                LocalDate date = rs.getDate("log_date").toLocalDate();
                if (date.equals(today.minusDays(streak))) {
                    streak++;
                } else {
                    break;
                }
            }

        } catch (SQLException e) {
            System.err.println("Error calculating streak: " + e.getMessage());
        }

        return streak;
    }
}
