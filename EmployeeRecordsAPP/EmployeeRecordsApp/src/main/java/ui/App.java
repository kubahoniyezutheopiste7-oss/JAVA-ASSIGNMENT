package ui;

import db.Database;
import model.Employee;
import service.EmployeeService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class App {
    private final EmployeeService service = new EmployeeService();
    private final EmployeeTableModel tableModel = new EmployeeTableModel(service.getAll());

    private void createAndShowGui() {
        Database.init();
        JFrame frame = new JFrame("Employee Records");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel controls = new JPanel();
        JButton btnAdd = new JButton("Add");
        JButton btnRefresh = new JButton("Refresh");
        JButton btnEdit = new JButton("Edit");
        JButton btnDelete = new JButton("Delete");
        JButton btnClear = new JButton("Clear Selection");

        controls.add(btnAdd);
        controls.add(btnEdit);
        controls.add(btnDelete);
        controls.add(btnRefresh);
        controls.add(btnClear);

        btnRefresh.addActionListener(e -> refresh());
        btnAdd.addActionListener(e -> showAddDialog());
        btnEdit.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) { JOptionPane.showMessageDialog(frame, "Select a row to edit"); return; }
            Employee emp = tableModel.getEmployeeAt(row);
            showEditDialog(emp);
        });
        btnDelete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) { JOptionPane.showMessageDialog(frame, "Select a row to delete"); return; }
            Employee emp = tableModel.getEmployeeAt(row);
            int r = JOptionPane.showConfirmDialog(frame, "Delete employee " + emp.getName() + "?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) { service.delete(emp.getId()); refresh(); }
        });
        btnClear.addActionListener(e -> table.clearSelection());

        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(controls, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void refresh() { List<Employee> all = service.getAll(); tableModel.setData(all); }

    private void showAddDialog() {
        EmployeeForm f = new EmployeeForm(null);
        int res = JOptionPane.showConfirmDialog(null, f.getPanel(), "Add Employee", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (res == JOptionPane.OK_OPTION) {
            Employee e = f.toEmployee();
            if (e != null) { service.add(e); refresh(); }
        }
    }

    private void showEditDialog(Employee emp) {
        EmployeeForm f = new EmployeeForm(emp);
        int res = JOptionPane.showConfirmDialog(null, f.getPanel(), "Edit Employee", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (res == JOptionPane.OK_OPTION) {
            Employee e = f.toEmployee();
            if (e != null) { e.setId(emp.getId()); service.update(e); refresh(); }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App().createAndShowGui());
    }
}
