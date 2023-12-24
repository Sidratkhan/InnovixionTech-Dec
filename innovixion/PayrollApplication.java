import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class Employee {
    private int employeeId;
    private String name;
    private double salary;

    public Employee(int employeeId, String name, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.salary = salary;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    // Add a method to set the salary
    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee ID: " + employeeId + ", Name: " + name + ", Salary: $" + salary;
    }
}

class PayrollSystem {
    private List<Employee> employees;

    public PayrollSystem() {
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void deleteEmployee(int employeeId) {
        employees.removeIf(employee -> employee.getEmployeeId() == employeeId);
    }

    public List<Employee> getEmployees() {
        return new ArrayList<>(employees);
    }

    public void calculateSalary(Employee employee) {
        // Perform salary calculations (e.g., add bonuses, deductions)
        // For simplicity, let's assume a fixed 10% tax deduction
        double taxDeduction = 0.1 * employee.getSalary();
        double netSalary = employee.getSalary() - taxDeduction;

        // Set the calculated net salary to the employee
        employee.setSalary(netSalary);
    }

    public void generatePayStub(Employee employee) {
        System.out.println("Pay Stub for Employee " + employee.getEmployeeId());
        System.out.println("Name: " + employee.getName());
        System.out.println("Gross Salary: $" + employee.getSalary());
        System.out.println("Tax Deduction: $" + (employee.getSalary() * 0.1));
        System.out.println("Net Salary: $" + (employee.getSalary() - (employee.getSalary() * 0.1)));
        System.out.println("-----------------------------------------");
    }

    public void processPayroll() {
        for (Employee employee : employees) {
            calculateSalary(employee);
            generatePayStub(employee);
        }
    }
}

 class PayrollApplicationGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Payroll Application");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            PayrollSystem payrollSystem = new PayrollSystem();

            JPanel panel = new JPanel();
            JButton addButton = new JButton("Add Employee");
            JButton deleteButton = new JButton("Delete Employee");
            JButton displayButton = new JButton("Display Employees");

            JTextArea textArea = new JTextArea(10, 30);
            textArea.setEditable(false);

            addButton.addActionListener(e -> {
                // Prompt user for employee details and add to PayrollSystem
                int employeeId = Integer.parseInt(JOptionPane.showInputDialog("Enter Employee ID:"));
                String employeeName = JOptionPane.showInputDialog("Enter Employee Name:");
                double employeeSalary = Double.parseDouble(JOptionPane.showInputDialog("Enter Employee Salary:"));
                Employee employee = new Employee(employeeId, employeeName, employeeSalary);
                payrollSystem.addEmployee(employee);
            });

            deleteButton.addActionListener(e -> {
                // Prompt user for employee ID to delete
                int employeeId = Integer.parseInt(JOptionPane.showInputDialog("Enter Employee ID to Delete:"));
                payrollSystem.deleteEmployee(employeeId);
            });

            displayButton.addActionListener(e -> {
                // Display the list of employees in the JTextArea
                List<Employee> employeeList = payrollSystem.getEmployees();
                textArea.setText("");
                for (Employee employee : employeeList) {
                    textArea.append(employee.toString() + "\n");
                }
            });

            panel.add(addButton);
            panel.add(deleteButton);
            panel.add(displayButton);
            panel.add(new JScrollPane(textArea));

            frame.add(panel);
            frame.setVisible(true);
        });
    }
}
