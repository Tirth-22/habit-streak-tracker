package app.panels;

import dao.HabitDAO;
import java.awt.*;
import java.time.LocalDate;
import javax.swing.*;
import model.Habit;

public class AddHabitPanel extends JPanel {

    private HabitDAO habitDAO = new HabitDAO();
    private JTextField nameField;
    private JTextPane outputPane;

    public AddHabitPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel title = new JLabel("➕ Add New Habit");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        formPanel.add(new JLabel("Habit Name:"));
        nameField = new JTextField(15); // fixed width field
        formPanel.add(nameField);

        JButton addBtn = new JButton("Add Habit");
        addBtn.setPreferredSize(new Dimension(120, 30)); // make button smaller
        formPanel.add(addBtn);

        add(formPanel, BorderLayout.CENTER);

        outputPane = new JTextPane();
        outputPane.setContentType("text/html");
        outputPane.setEditable(false);
        add(new JScrollPane(outputPane), BorderLayout.SOUTH);

        addBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                if (habitDAO.insertHabit(new Habit(name, LocalDate.now()))) {
                    setOutput("<p style='color:green;'>✅ Habit added!</p>");
                    nameField.setText("");
                } else {
                    setOutput("<p style='color:red;'>❌ Error adding habit.</p>");
                }
            } else {
                setOutput("<p style='color:orange;'>⚠ Please enter a habit name.</p>");
            }
        });
    }

    private void setOutput(String html) {
        outputPane.setText("<html><body style='font-family:Segoe UI;'>" + html + "</body></html>");
    }
}
