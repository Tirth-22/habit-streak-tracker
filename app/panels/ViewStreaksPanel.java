package app.panels;

import dao.HabitDAO;
import dao.HabitLogDAO;
import java.awt.*;
import javax.swing.*;
import model.Habit;

public class ViewStreaksPanel extends JPanel {
    private HabitDAO habitDAO = new HabitDAO();
    private HabitLogDAO logDAO = new HabitLogDAO();
    private JTextPane outputPane;

    public ViewStreaksPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel title = new JLabel("🔥 View Streaks");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        outputPane = new JTextPane();
        outputPane.setContentType("text/html");
        outputPane.setEditable(false);
        add(new JScrollPane(outputPane), BorderLayout.CENTER);

        refresh();
    }

    public void refresh() {
        StringBuilder sb = new StringBuilder("<h3>🔥 Streaks</h3><ul>");
        for (Habit h : habitDAO.getAllHabits()) {
            int streak = logDAO.calculateStreak(h.getId());
            sb.append("<li>").append(h.getName()).append(" ➤ <b style='color:orange;'>")
                    .append(streak).append("</b> day(s)</li>");
        }
        sb.append("</ul>");
        outputPane.setText("<html><body style='font-family:Segoe UI;'>" + sb + "</body></html>");
    }
}
