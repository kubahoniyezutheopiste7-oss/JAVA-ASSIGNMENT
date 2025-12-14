package ui;

import model.Employee;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

public class EmployeeForm {
    private final JPanel panel;
    private final JTextField tfName = new JTextField(20);
    private final JTextField tfEmail = new JTextField(20);
    private final JTextField tfPosition = new JTextField(20);
    private final JTextField tfSalary = new JTextField(10);

    private static final Pattern EMAIL_RE = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public EmployeeForm(Employee e) {
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4,4,4,4);
        c.anchor = GridBagConstraints.WEST;

        c.gridx = 0; c.gridy = 0; panel.add(new JLabel("Name:"), c);
        c.gridx = 1; panel.add(tfName, c);
        c.gridx = 0; c.gridy++; panel.add(new JLabel("Email:"), c);
        c.gridx = 1; panel.add(tfEmail, c);
        c.gridx = 0; c.gridy++; panel.add(new JLabel("Position:"), c);
        c.gridx = 1; panel.add(tfPosition, c);
        c.gridx = 0; c.gridy++; panel.add(new JLabel("Salary:"), c);
        c.gridx = 1; panel.add(tfSalary, c);

        if (e != null) {
            tfName.setText(e.getName());
            tfEmail.setText(e.getEmail());
            tfPosition.setText(e.getPosition());
            tfSalary.setText(String.valueOf(e.getSalary()));
        }
    }

    public JPanel getPanel() { return panel; }

    public Employee toEmployee() {
        String name = tfName.getText().trim();
        String email = tfEmail.getText().trim();
        String pos = tfPosition.getText().trim();
        String sal = tfSalary.getText().trim();

        if (name.isEmpty()) { JOptionPane.showMessageDialog(panel, "Name is required"); return null; }
        if (!EMAIL_RE.matcher(email).matches()) { JOptionPane.showMessageDialog(panel, "Invalid email"); return null; }
        if (pos.isEmpty()) { JOptionPane.showMessageDialog(panel, "Position is required"); return null; }
        int salary;
        try { salary = Integer.parseInt(sal); if (salary < 0) throw new NumberFormatException(); }
        catch (NumberFormatException ex) { JOptionPane.showMessageDialog(panel, "Salary must be a non-negative integer"); return null; }

        return new Employee(name, email, pos, salary);
    }
}
