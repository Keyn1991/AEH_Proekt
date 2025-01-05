import java.sql.*;
import java.util.ArrayList;

public class StudentManagerImpl implements StudentManager {
    private Connection connection;

    // Konstruktor, który tworzy połączenie z bazą danych
    public StudentManagerImpl() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:src/Database/students.db");
            createTableIfNotExists();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Tworzenie tabeli, jeśli nie istnieje
    private void createTableIfNotExists() throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS students (" +
                "name TEXT, " +
                "age INTEGER, " +
                "grade REAL, " +
                "studentID TEXT PRIMARY KEY)";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSQL);
        }
    }

    // Dodawanie studenta
    @Override
    public void addStudent(Student student) {
        String sql = "INSERT INTO students (name, age, grade, studentID) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setInt(2, student.getAge());
            pstmt.setDouble(3, student.getGrade());
            pstmt.setString(4, student.getStudentID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Usuwanie studenta po ID
    @Override
    public void removeStudent(String studentID) {
        String sql = "DELETE FROM students WHERE studentID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, studentID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Aktualizacja danych studenta
    @Override
    public void updateStudent(String studentID, Student updatedStudent) {
        String sql = "UPDATE students SET name = ?, age = ?, grade = ? WHERE studentID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, updatedStudent.getName());
            pstmt.setInt(2, updatedStudent.getAge());
            pstmt.setDouble(3, updatedStudent.getGrade());
            pstmt.setString(4, studentID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Wyświetlanie wszystkich studentów
    @Override
    public ArrayList<Student> displayAllStudents() {
        ArrayList<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Student student = new Student(
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getDouble("grade"),
                        rs.getString("studentID")
                );
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    // Obliczanie średniej oceny
    @Override
    public double calculateAverageGrade() {
        String sql = "SELECT AVG(grade) AS avg_grade FROM students";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                double avgGrade = rs.getDouble("avg_grade");
                // Zaokrąglenie do dwóch miejsc po przecinku
                return Math.round(avgGrade * 100.0) / 100.0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }


    // Zamknięcie połączenia
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
