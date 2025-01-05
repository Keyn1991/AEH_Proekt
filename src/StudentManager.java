import java.util.ArrayList;

public interface StudentManager {
    void addStudent(Student student); // Dodaj studenta
    void removeStudent(String studentID); // Usuń studenta na podstawie ID
    void updateStudent(String studentID, Student updatedStudent); // Zaktualizuj dane studenta na podstawie ID
    ArrayList<Student> displayAllStudents(); // Wyświetl wszystkich studentów
    double calculateAverageGrade(); // Oblicz średnią ocenę studentów
}
