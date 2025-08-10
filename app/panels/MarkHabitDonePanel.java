package app.panels;

import dao.HabitDAO;
import java.awt.*;
import java.time.LocalDate;
import javax.swing.*;

public class MarkHabitDonePanel extends JPanel {
    private HabitDAO habitDAO = new HabitDAO();
    private JTextField habitIdField;
    private JTextPane outputPane;

    public MarkHabitDonePanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel title = new JLabel("✅ Mark Habit as Done");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        // Form panel for entering habit ID
        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        formPanel.add(new JLabel("Habit ID:"));
        habitIdField = new JTextField(10);
        formPanel.add(habitIdField);

        JButton markBtn = new JButton("Mark Done");
        markBtn.setPreferredSize(new Dimension(120, 30));
        formPanel.add(markBtn);

        add(formPanel, BorderLayout.CENTER);

        // Output area
        outputPane = new JTextPane();
        outputPane.setContentType("text/html");
        outputPane.setEditable(false);
        add(new JScrollPane(outputPane), BorderLayout.SOUTH);

        // Button action
        markBtn.addActionListener(e -> markHabitAsDone());
    }

    private void markHabitAsDone() {
        String idText = habitIdField.getText().trim();
        if (!idText.isEmpty()) {
            try {
                int id = Integer.parseInt(idText);
                if (habitDAO.markHabitDone(id, LocalDate.now())) {
                    setOutput("<p style='color:green;'>✅ Habit marked as done!</p>");
                    habitIdField.setText("");
                } else {
                    setOutput("<p style='color:red;'>❌ No habit found with that ID.</p>");
                }
            } catch (NumberFormatException ex) {
                setOutput("<p style='color:orange;'>⚠ Please enter a valid number.</p>");
            }
        } else {
            setOutput("<p style='color:orange;'>⚠ Please enter a habit ID.</p>");
        }
    }

    private void setOutput(String html) {
        outputPane.setText("<html><body style='font-family:Segoe UI;'>" + html + "</body></html>");
    }
}
