package VVSSLab2.validation;


import VVSSLab2.domain.Student;

public class StudentValidator implements Validator<Student> {

    /**
     * Valideaza un student
     *
     * @param entity - studentul pe care il valideaza
     * @throws ValidationException - daca studentul nu e valid
     */
    @Override
    public void validate(Student entity) throws ValidationException {
        if (entity.getID() < 0) {
            throw new ValidationException("Id incorect!");
        }
        if (entity.getID() == null) {
            throw new ValidationException("Id incorect!");
        }
        if (entity.getNume() == null) {
            throw new ValidationException("Nume incorect!");
        }
        if (entity.getNume().equals("")) {
            throw new ValidationException("Nume incorect!");
        }
        if (!entity.getNume().matches("[a-zA-Z -]+")) {
            throw new ValidationException("Nume incorect!");
        }
        if (entity.getGrupa() < 0) {
            throw new ValidationException("Grupa incorecta!");
        }
        if (entity.getEmail() == null) {
            throw new ValidationException("Email incorect!");
        }
        if (entity.getEmail().equals("")) {
            throw new ValidationException("Email incorect!");
        }
        if (!entity.getEmail().matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b")) {
            throw new ValidationException("Email incorect!");
        }
    }
}
