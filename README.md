# Habit Streak Counter

A Java-based habit tracking application that helps you monitor and maintain daily streaks for your personal habits.  
Supports **JDBC** with MySQL for data storage, and includes both **CLI** and **Swing GUI** interfaces.
(Containig core java concepts)
---

## Features
- **User Login System** – Track habits for individual users.
- **Add, Update, and Delete Habits**.
- **Daily Streak Tracking** with visual indicators in the GUI.
- **MySQL Database Integration** using JDBC.
- **Modular Project Structure** with DAO, model, and UI layers.

---

## Setup Instructions

### 1. Clone the Repository
```bash
git clone https://github.com/Tirth-22/habit-streak-tracker.git
cd habit-streak-tracker
```
### 2. Configure Database
Create a MySQL database.
```bash
db.url=jdbc:mysql://localhost:3306/your_database
db.user=your_username
db.password=your_password
```
### 3. Compile and Run
```bash
javac -cp "lib/*" app/HabitTrackerApp.java
java -cp ".;lib/*" app.HabitTrackerApp
```
