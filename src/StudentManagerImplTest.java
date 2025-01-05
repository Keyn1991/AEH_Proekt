import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentManagerImplTest {

    private StudentManagerImpl studentManager;

    @BeforeEach
    public void setUp() {
        studentManager = new StudentManagerImpl();
    }

    @AfterEach
    public void tearDown() {
        studentManager.closeConnection();
    }

    @Test
    public void testAddStudent() {
        Student student = new Student("John Doe", 20, 85.0, "1");
        studentManager.addStudent(student);

        ArrayList<Student> students = studentManager.displayAllStudents();
        assertEquals(1, students.size());
        assertEquals("John Doe", students.get(0).getName());
        assertEquals(20, students.get(0).getAge());
        assertEquals(85.0, students.get(0).getGrade());
        assertEquals("1", students.get(0).getStudentID());
    }

    @Test
    public void testRemoveStudent() {
        Student student = new Student("Jane Doe", 22, 90.0, "2");
        studentManager.addStudent(student);
        studentManager.removeStudent("2");

        ArrayList<Student> students = studentManager.displayAllStudents();
        assertEquals(0, students.size());
    }

    @Test
    public void testUpdateStudent() {
        Student student = new Student("Mike Smith", 19, 75.0, "3");
        studentManager.addStudent(student);

        Student updatedStudent = new Student("Mike Johnson", 20, 80.0, "3");
        studentManager.updateStudent("3", updatedStudent);

        ArrayList<Student> students = studentManager.displayAllStudents();
        assertEquals(1, students.size());
        assertEquals("Mike Johnson", students.get(0).getName());
        assertEquals(20, students.get(0).getAge());
        assertEquals(80.0, students.get(0).getGrade());
    }

    @Test
    public void testDisplayAllStudents() {
        Student student1 = new Student("Alice", 21, 88.0, "4");
        Student student2 = new Student("Bob", 23, 92.0, "5");
        studentManager.addStudent(student1);
        studentManager.addStudent(student2);

        ArrayList<Student> students = studentManager.displayAllStudents();
        assertEquals(2, students.size());
        assertEquals("Alice", students.get(0).getName());
        assertEquals("Bob", students.get(1).getName());
    }

    @Test
    public void testCalculateAverageGrade() {
        studentManager.addStudent(new Student("Charlie", 22, 70.0, "6"));
        studentManager.addStudent(new Student("Diana", 20, 90.0, "7"));

        double average = studentManager.calculateAverageGrade();
        assertEquals(80.0, average, 0.01);
    }
}
