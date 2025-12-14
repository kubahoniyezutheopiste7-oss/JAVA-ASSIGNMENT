package ui;

import model.Employee;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class EmployeeTableModel extends AbstractTableModel {
    private final String[] cols = {"ID","Name","Email","Position","Salary"};
    private List<Employee> data;

    public EmployeeTableModel(List<Employee> data) { this.data = data; }

    public void setData(List<Employee> data) { this.data = data; fireTableDataChanged(); }

    @Override public int getRowCount() { return data == null ? 0 : data.size(); }
    @Override public int getColumnCount() { return cols.length; }
    @Override public String getColumnName(int col) { return cols[col]; }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Employee e = data.get(rowIndex);
        switch (columnIndex) {
            case 0: return e.getId();
            case 1: return e.getName();
            case 2: return e.getEmail();
            case 3: return e.getPosition();
            case 4: return e.getSalary();
            default: return null;
        }
    }

    public Employee getEmployeeAt(int row) { return data.get(row); }
}
