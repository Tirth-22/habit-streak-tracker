package app.panels;

import dao.HabitDAO;
import model.*;
import javax.swing.*;
import java.awt.*;

public class ViewHabitsPanel extends JPanel {
    private HabitDAO habitDAO = new HabitDAO();
    private JTextPane outputPane;

    public ViewHabitsPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel title = new JLabel("📜 View All Habits");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        outputPane = new JTextPane();
        outputPane.setContentType("text/html");
        outputPane.setEditable(false);
        add(new JScrollPane(outputPane), BorderLayout.CENTER);

        refresh();
    }

    public void refresh() {
        StringBuilder sb = new StringBuilder("<h3>📋 Your Habits</h3><ul>");
        for (Habit h : habitDAO.getAllHabits()) {
            sb.append("<li><b>").append(h.getId()).append("</b>: ").append(h.getName()).append("</li>");
        }
        sb.append("</ul>");
        outputPane.setText("<html><body style='font-family:Segoe UI;'>" + sb + "</body></html>");
    }
}
