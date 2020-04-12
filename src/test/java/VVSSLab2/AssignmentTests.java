package VVSSLab2;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import VVSSLab2.domain.Nota;
import VVSSLab2.domain.Student;
import VVSSLab2.domain.Tema;
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
 * Unit test for Assignments.
 */
public class AssignmentTests {
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

    // Test for assignment success
    @Test
    public void testAssignment_success() {
        Tema tema = new Tema("1", "description", 5 ,4);
        Tema response = service.addTema(tema);

        assertEquals(tema, response);
        System.out.println();
    }

    @Test
    public void testAssignment_fail_IdNull() {
        Tema tema = new Tema(null, "description", 14 ,4);

        Exception exception = assertThrows(ValidationException.class, () -> service.addTema(tema));

        String expectedMessage = "Numar tema invalid!";
        String actualMessage = exception.getMessage();

        TestCase.assertTrue(actualMessage.contains(expectedMessage));
        System.out.println();
    }

    @Test
    public void testAssignment_fail_IdEmpty() {
        Tema tema = new Tema("", "description", 14 ,4);

        Exception exception = assertThrows(ValidationException.class, () -> service.addTema(tema));

        String expectedMessage = "Numar tema invalid!";
        String actualMessage = exception.getMessage();

        TestCase.assertTrue(actualMessage.contains(expectedMessage));
        System.out.println();
    }

    @Test
    public void testAssignment_fail_IdNegative() {
        Tema tema = new Tema("-1", "description", 14 ,4);

        Exception exception = assertThrows(ValidationException.class, () -> service.addTema(tema));

        String expectedMessage = "Numar tema invalid!";
        String actualMessage = exception.getMessage();

        TestCase.assertTrue(actualMessage.contains(expectedMessage));
        System.out.println();
    }

    @Test
    public void testAssignment_fail_IdNotInt() {
        Tema tema = new Tema("2.5", "description", 14 ,4);

        Exception exception = assertThrows(ValidationException.class, () -> service.addTema(tema));

        String expectedMessage = "Numar tema invalid!";
        String actualMessage = exception.getMessage();

        TestCase.assertTrue(actualMessage.contains(expectedMessage));
        System.out.println();
    }

    // Test for assignment fail
    @Test
    public void testAssignment_fail() {
        Tema tema = new Tema("1", "description", 15 ,4);

        Exception exception = assertThrows(ValidationException.class, () -> service.addTema(tema));

        String expectedMessage = "Deadlineul trebuie sa fie intre 1-14.";
        String actualMessage = exception.getMessage();

        TestCase.assertTrue(actualMessage.contains(expectedMessage));
        System.out.println();
    }

    @Test
    public void testAssignment_fail_DeadlineSmaller() {
        Tema tema = new Tema("1", "description", 0 ,4);

        Exception exception = assertThrows(ValidationException.class, () -> service.addTema(tema));

        String expectedMessage = "Deadlineul trebuie sa fie intre 1-14.";
        String actualMessage = exception.getMessage();

        TestCase.assertTrue(actualMessage.contains(expectedMessage));
        System.out.println();
    }

    @Test
    public void testAssignment_fail_DescriereNull() {
        Tema tema = new Tema("1", null, 1 ,4);

        Exception exception = assertThrows(ValidationException.class, () -> service.addTema(tema));

        String expectedMessage = "Descriere invalida!";
        String actualMessage = exception.getMessage();

        TestCase.assertTrue(actualMessage.contains(expectedMessage));
        System.out.println();
    }

    @Test
    public void testAssignment_fail_DescriereEmpty() {
        Tema tema = new Tema("1", "", 1 ,4);

        Exception exception = assertThrows(ValidationException.class, () -> service.addTema(tema));

        String expectedMessage = "Descriere invalida!";
        String actualMessage = exception.getMessage();

        TestCase.assertTrue(actualMessage.contains(expectedMessage));
        System.out.println();
    }

    @Test
    public void testAssignment_fail_SaptPrimireSmaller() {
        Tema tema = new Tema("1", "descriere", 1 ,0);

        Exception exception = assertThrows(ValidationException.class, () -> service.addTema(tema));

        String expectedMessage = "Saptamana primirii trebuie sa fie intre 1-14.";
        String actualMessage = exception.getMessage();

        TestCase.assertTrue(actualMessage.contains(expectedMessage));
        System.out.println();
    }

    @Test
    public void testAssignment_fail_SaptPrimireGreater() {
        Tema tema = new Tema("1", "descriere", 1 ,15);

        Exception exception = assertThrows(ValidationException.class, () -> service.addTema(tema));

        String expectedMessage = "Saptamana primirii trebuie sa fie intre 1-14.";
        String actualMessage = exception.getMessage();

        TestCase.assertTrue(actualMessage.contains(expectedMessage));
        System.out.println();
    }

    @Test
    public void testAssignment_fail_SaptPrimireGreaterThanDeadline() {
        Tema tema = new Tema("1", "descriere", 3 ,4);

        Exception exception = assertThrows(ValidationException.class, () -> service.addTema(tema));

        String expectedMessage = "Saptamana primirii trebuie sa fie intre 1-14.";
        String actualMessage = exception.getMessage();

        TestCase.assertTrue(actualMessage.contains(expectedMessage));
        System.out.println();
    }
}
