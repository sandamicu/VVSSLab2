package VVSSLab2;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import VVSSLab2.domain.Student;
import VVSSLab2.repository.NotaXMLRepo;
import VVSSLab2.repository.StudentXMLRepo;
import VVSSLab2.repository.TemaXMLRepo;
import VVSSLab2.service.Service;
import VVSSLab2.validation.NotaValidator;
import VVSSLab2.validation.StudentValidator;
import VVSSLab2.validation.TemaValidator;
import VVSSLab2.validation.ValidationException;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    private StudentValidator studentValidator;
    private TemaValidator temaValidator;
    String filenameStudent = "fisiere/Studenti.xml";
    String filenameTema = "fisiere/Teme.xml";
    String filenameNota = "fisiere/Note.xml";
    private StudentXMLRepo studentXMLRepository;
    private TemaXMLRepo temaXMLRepository;
    private NotaValidator notaValidator;
    private NotaXMLRepo notaXMLRepository;
    private Service service;

    @BeforeEach
    public void setup() {
        studentValidator = new StudentValidator();
        temaValidator = new TemaValidator();
        studentXMLRepository = new StudentXMLRepo(filenameStudent);
        temaXMLRepository = new TemaXMLRepo(filenameTema);
        notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
    }

    // Test for student success: TestCase #7
    @Test
    public void testStudent() {
        Student student = new Student(0, "Pop Ana-Maria", 0, "ana@gmail.com");
        assertEquals(service.addStudent(student), student);
    }

    // Test for student success when id=MAX: TestCase #5
    @Test
    public void testStudentSuccess_IDMAX() {
        Student student = new Student(2147483647, "Pop Ana-Maria", 0, "ana@gmail.com");
        assertEquals(service.addStudent(student), student);
    }

    // Test for student fails when id=-1: TestCase #1
    @Test
    public void testStudentFail_IDNegative() {
        Student student = new Student(-1, "Pop Ana-Maria", 0, "ana@gmail.com");
        Exception exception = assertThrows(ValidationException.class, () -> service.addStudent(student));

        String expectedMessage = "Id incorect!";
        String actualMessage = exception.getMessage();

        TestCase.assertTrue(actualMessage.contains(expectedMessage));
    }

    // Test for student fails when id=MAX+1: TestCase #2
    @Test
    public void testStudentFail_IDMAX() {
        Student student = new Student(2147483647 + 1, "Pop Ana-Maria", 0, "ana@gmail.com");
        Exception exception = assertThrows(ValidationException.class, () -> service.addStudent(student));

        String expectedMessage = "Id incorect!";
        String actualMessage = exception.getMessage();

        TestCase.assertTrue(actualMessage.contains(expectedMessage));
    }

    // Test for student success when name=Ana: TestCase #9
    @Test
    public void testStudentSuccess_Name() {
        Student student = new Student(0, "Ana", 0, "ana@gmail.com");
        assertEquals(service.addStudent(student), student);
    }

    // Test for student fail when name=An!: TestCase #8
    @Test
    public void testStudentFail_Name() {
        Student student = new Student(0, "An!", 0, "ana@gmail.com");
        Exception exception = assertThrows(ValidationException.class, () -> service.addStudent(student));

        String expectedMessage = "Nume incorect!";
        String actualMessage = exception.getMessage();

        TestCase.assertTrue(actualMessage.contains(expectedMessage));
    }

    // Test for student success when group=MAX: TestCase #16
    @Test
    public void testStudentSuccess_GroupMAX() {
        Student student = new Student(0, "Pop Ana-Maria", 2147483647, "sanda@gmail.com");
        assertEquals(service.addStudent(student), student);
    }

    // Test for student fail when group=-1: TestCase #14
    @Test
    public void testStudentFail() {
        Student student = new Student(0, "Pop Ana-Maria", -1, "ana@gmail.com");
        Exception exception = assertThrows(ValidationException.class, () -> service.addStudent(student));

        String expectedMessage = "Grupa incorecta!";
        String actualMessage = exception.getMessage();

        TestCase.assertTrue(actualMessage.contains(expectedMessage));
    }


    // Test for student fail when group=MAX+1: TestCase #18
    @Test
    public void testStudentFail_GroupMAX() {
        Student student = new Student(0, "Pop Ana-Maria", 2147483647 + 1, "sanda@gmail.com");
        Exception exception = assertThrows(ValidationException.class, () -> service.addStudent(student));

        String expectedMessage = "Grupa incorecta!";
        String actualMessage = exception.getMessage();

        TestCase.assertTrue(actualMessage.contains(expectedMessage));
    }

    // Test for student fail when email=ana.com: TestCase #11
    @Test
    public void testStudentFail_Email0() {
        Student student = new Student(0, "Pop Ana-Maria", 0, "ana.com");
        Exception exception = assertThrows(ValidationException.class, () -> service.addStudent(student));

        String expectedMessage = "Email incorect!";
        String actualMessage = exception.getMessage();

        TestCase.assertTrue(actualMessage.contains(expectedMessage));
    }

    // Test for student fail when email=gmail.com: TestCase #12
    @Test
    public void testStudentFail_Email1() {
        Student student = new Student(0, "Pop Ana-Maria", 0, "gmail.com");
        Exception exception = assertThrows(ValidationException.class, () -> service.addStudent(student));

        String expectedMessage = "Email incorect!";
        String actualMessage = exception.getMessage();

        TestCase.assertTrue(actualMessage.contains(expectedMessage));
    }

    // Test for student fail when email=ana@gmail: TestCase #13
    @Test
    public void testStudentFail_Email2() {
        Student student = new Student(0, "Pop Ana-Maria", 0, "ana@gmail");
        Exception exception = assertThrows(ValidationException.class, () -> service.addStudent(student));

        String expectedMessage = "Email incorect!";
        String actualMessage = exception.getMessage();

        TestCase.assertTrue(actualMessage.contains(expectedMessage));
    }
}
