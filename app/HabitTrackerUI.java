package app;

import app.panels.AddHabitPanel;
import app.panels.MarkHabitDonePanel;
import app.panels.ViewHabitsPanel;
import app.panels.ViewStreaksPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class HabitTrackerUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel contentPanel;

    public HabitTrackerUI() {
        setTitle("🌱 Habit Tracker Lite");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Sidebar Menu
        JPanel menuPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        menuPanel.setBackground(new Color(40, 44, 52));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JButton addBtn = createMenuButton("➕ Add Habit");
        JButton markBtn = createMenuButton("✅ Mark as Done");
        JButton viewBtn = createMenuButton("📜 View Habits");
        // JButton logBtn = createMenuButton("🗓 View Logs");
        JButton streakBtn = createMenuButton("🔥 View Streaks");
        JButton exitBtn = createMenuButton("🚪 Exit");

        menuPanel.add(addBtn);
        menuPanel.add(markBtn);
        menuPanel.add(viewBtn);
        // menuPanel.add(logBtn);
        menuPanel.add(streakBtn);
        menuPanel.add(exitBtn);

        add(menuPanel, BorderLayout.WEST);

        // Content Area with CardLayout
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        contentPanel.add(new AddHabitPanel(), "AddHabit");
        contentPanel.add(new MarkHabitDonePanel(), "MarkHabit");
        contentPanel.add(new ViewHabitsPanel(), "ViewHabits");
        // contentPanel.add(new ViewLogsPanel(), "ViewLogs");
        contentPanel.add(new ViewStreaksPanel(), "ViewStreaks");

        add(contentPanel, BorderLayout.CENTER);

        // Button Actions
        addBtn.addActionListener(e -> cardLayout.show(contentPanel, "AddHabit"));
        markBtn.addActionListener(e -> cardLayout.show(contentPanel, "MarkHabit"));
        viewBtn.addActionListener(e -> cardLayout.show(contentPanel, "ViewHabits"));
        // logBtn.addActionListener(e -> cardLayout.show(contentPanel, "ViewLogs"));
        streakBtn.addActionListener(e -> cardLayout.show(contentPanel, "ViewStreaks"));
        exitBtn.addActionListener(e -> System.exit(0));
    }

    private JButton createMenuButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(new Color(60, 63, 65));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HabitTrackerUI().setVisible(true));
    }
}
