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
public class AppTest 
{
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

    @Test
    public void testStudent() {
        Student student = new Student("1234", "Sanda", 934, "sanda@gmail.com");
        assertEquals(service.addStudent(student), student);
    }

    @Test
    public void testStudentFail() {
        Student student = new Student("1234", "Sanda", -934, "sanda@gmail.com");
        Exception exception = assertThrows(ValidationException.class, () -> service.addStudent(student));

        String expectedMessage = "Grupa incorecta!";
        String actualMessage = exception.getMessage();

        TestCase.assertTrue(actualMessage.contains(expectedMessage));
    }
}
