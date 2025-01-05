import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StudentManagementGUI extends JFrame {
    private JTextField idField, nameField, ageField, gradeField;
    private JTextArea outputArea;
    private StudentManagerImpl studentManager;

    public StudentManagementGUI() {
        studentManager = new StudentManagerImpl();

        // Ustawienia głównego okna
        setTitle("Student Management System");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        /// Panel do wprowadzania danych z układem GridBagLayout
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Tworzenie komponentów
        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(new JLabel("Student ID:"), gbc);
        idField = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 0;
        inputPanel.add(idField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        inputPanel.add(new JLabel("Name:"), gbc);
        nameField = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 1;
        inputPanel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        inputPanel.add(new JLabel("Age:"), gbc);
        ageField = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 2;
        inputPanel.add(ageField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        inputPanel.add(new JLabel("Grade:"), gbc);
        gradeField = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 3;
        inputPanel.add(gradeField, gbc);

        // Panel z przyciskami
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(e -> addStudent());
        buttonPanel.add(addButton);

        JButton removeButton = new JButton("Remove Student");
        removeButton.addActionListener(e -> removeStudent());
        buttonPanel.add(removeButton);

        JButton updateButton = new JButton("Update Student");
        updateButton.addActionListener(e -> updateStudent());
        buttonPanel.add(updateButton);

        JButton displayButton = new JButton("Display All Students");
        displayButton.addActionListener(e -> displayAllStudents());
        buttonPanel.add(displayButton);

        JButton averageButton = new JButton("Calculate Average");
        averageButton.addActionListener(e -> calculateAverageGrade());
        buttonPanel.add(averageButton);

        JButton clearButton = new JButton("Clear Fields");
        clearButton.addActionListener(e -> clearFields());
        buttonPanel.add(clearButton);

        // Panel wyjściowy
        outputArea = new JTextArea(10, 50);
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setBackground(Color.LIGHT_GRAY);
        outputArea.setBorder(BorderFactory.createEtchedBorder());
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Dodanie paneli do głównego okna
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }

    // Metody do obsługi działań użytkownika
    private void addStudent() {
        String id = idField.getText();
        String name = nameField.getText();
        int age;
        double grade;
        try {
            age = Integer.parseInt(ageField.getText());
            grade = Double.parseDouble(gradeField.getText());
            if (age <= 0 || grade < 0.0 || grade > 100.0) {
                outputArea.setText("Invalid input: Age must be positive, grade must be between 0.0 and 100.0.");
                return;
            }
            Student student = new Student(name, age, grade, id);
            studentManager.addStudent(student);
            outputArea.setText("Student added successfully.");
            clearFields();  // Czyszczenie pól po dodaniu
        } catch (NumberFormatException e) {
            outputArea.setText("Invalid input: Age and grade must be numbers.");
        }
    }

    private void removeStudent() {
        String id = idField.getText();
        studentManager.removeStudent(id);
        outputArea.setText("Student removed successfully.");
        clearFields(); // Czyszczenie pól po usunięciu
    }

    private void updateStudent() {
        String id = idField.getText();
        String name = nameField.getText();
        int age;
        double grade;
        try {
            age = Integer.parseInt(ageField.getText());
            grade = Double.parseDouble(gradeField.getText());
            if (age <= 0 || grade < 0.0 || grade > 100.0) {
                outputArea.setText("Invalid input: Age must be positive, grade must be between 0.0 and 100.0.");
                return;
            }
            Student updatedStudent = new Student(name, age, grade, id);
            studentManager.updateStudent(id, updatedStudent);
            outputArea.setText("Student updated successfully.");
            clearFields(); // Czyszczenie pól po aktualizacji
        } catch (NumberFormatException e) {
            outputArea.setText("Invalid input: Age and grade must be numbers.");
        }
    }

    private void displayAllStudents() {
        ArrayList<Student> students = studentManager.displayAllStudents();
        StringBuilder sb = new StringBuilder("All Students:\n");
        for (Student student : students) {
            sb.append("ID: ").append(student.getStudentID())
                    .append(", Name: ").append(student.getName())
                    .append(", Age: ").append(student.getAge())
                    .append(", Grade: ").append(student.getGrade())
                    .append("\n");
        }
        outputArea.setText(sb.toString());
    }

    private void calculateAverageGrade() {
        double average = studentManager.calculateAverageGrade();
        outputArea.setText("Average Grade: " + average);
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        gradeField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentManagementGUI gui = new StudentManagementGUI();
            gui.setVisible(true);
        });
    }
}
