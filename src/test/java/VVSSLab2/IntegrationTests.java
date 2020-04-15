package VVSSLab2;

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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class IntegrationTests {
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
    public void testStudent_success() {
        Student student = new Student(0, "Pop Ana-Maria", 0, "ana@gmail.com");
        assertEquals(service.addStudent(student), student);
    }

    // Test for assignment success
    @Test
    public void testAssignment_success() {
        Tema tema = new Tema("1", "description", 5 ,4);
        Tema response = service.addTema(tema);

        assertEquals(tema, response);
        System.out.println();
    }

    // Test for assignment success
    @Test
    public void testNota_success() {
        Nota nota = new Nota("1", "1", "1", 9.4 , LocalDate.parse("2020-12-03"));
        double response = service.addNota(nota, "feedback");

        assertEquals (9, (int)response);
        System.out.println();
    }

    @Test
    public void testBigBang_success() {
        testStudent_success();
        testAssignment_success();
        testNota_success();
    }
}
