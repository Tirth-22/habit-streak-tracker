package app;

import dao.HabitDAO;
import dao.HabitLogDAO;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import model.Habit;
import model.HabitLog;

public class HabitTrackerApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HabitDAO habitDAO = new HabitDAO();
        HabitLogDAO logDAO = new HabitLogDAO();

        while (true) {
            // Display menu
            System.out.println("\n====== Habit Tracker Lite ======");
            System.out.println("1. Add a New Habit");
            System.out.println("2. Mark a Habit as Done Today");
            System.out.println("3. Show All Habits");
            System.out.println("4. Show Habit Log (History)");
            System.out.println("5. Show Current Streaks");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // clear the buffer

            switch (option) {
                case 1:
                    // Add new habit
                    System.out.print("Enter habit name: ");
                    String habitName = scanner.nextLine();
                    Habit habit = new Habit(habitName, LocalDate.now());
                    boolean added = habitDAO.insertHabit(habit);
                    if (added) {
                        System.out.println("✅ Habit added successfully!");
                    } else {
                        System.out.println("❌ Failed to add habit.");
                    }
                    break;

                case 2:
                    // Mark a habit as done today
                    List<Habit> habits = habitDAO.getAllHabits();
                    if (habits.isEmpty()) {
                        System.out.println("No habits found.");
                        break;
                    }
                    System.out.println("\nYour Habits:");
                    for (Habit h : habits) {
                        System.out.println(h.getId() + ": " + h.getName());
                    }
                    System.out.print("Enter habit ID to mark as done: ");
                    int habitIdToMark = scanner.nextInt();
                    boolean marked = logDAO.markHabitAsDone(habitIdToMark, LocalDate.now());
                    if (marked) {
                        System.out.println("✅ Habit marked as done for today!");
                    } else {
                        System.out.println("⚠️ Already marked or invalid ID.");
                    }
                    break;

                case 3:
                    // View all habits
                    List<Habit> allHabits = habitDAO.getAllHabits();
                    if (allHabits.isEmpty()) {
                        System.out.println("No habits to display.");
                    } else {
                        System.out.println("\nYour Habits:");
                        allHabits.forEach(System.out::println);
                    }
                    break;

                case 4:
                    // View habit log
                    System.out.print("Enter habit ID to view log: ");
                    int logHabitId = scanner.nextInt();
                    List<HabitLog> logs = logDAO.getHabitLogs(logHabitId);
                    if (logs.isEmpty()) {
                        System.out.println("No log entries found for this habit.");
                    } else {
                        System.out.println("Dates marked as done:");
                        for (HabitLog log : logs) {
                            System.out.println("✔️ " + log.getLogDate());
                        }
                    }
                    break;

                case 5:
                    // View current streaks
                    List<Habit> habitList = habitDAO.getAllHabits();
                    if (habitList.isEmpty()) {
                        System.out.println("No habits to calculate streaks.");
                    } else {
                        System.out.println("\nYour Habit Streaks:");
                        for (Habit h : habitList) {
                            int streak = logDAO.calculateStreak(h.getId());
                            System.out.println(h.getName() + " ➤ " + streak + " day(s)");
                        }
                    }
                    break;

                case 6:
                    // Exit
                    System.out.println("👋 Thanks for using Habit Tracker Lite!");
                    return;

                default:
                    System.out.println("❌ Invalid option. Please try again.");
            }
        }
    }
}
