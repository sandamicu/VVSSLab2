package VVSSLab2.repository;

import VVSSLab2.domain.Student;

public class StudentFileRepository extends AbstractFileRepository<Integer, Student> {

    /**
     * Class constructor
     * @param filename - numele fisierului
     */
    public StudentFileRepository(String filename) {
        super(filename);
    }

    /**
     * Extrage informatia despre student dintr-un string
     * @param linie - stringul din care ia datele studentului
     * @return studentul
     */
    @Override
    public Student extractEntity(String linie) {
        String[] cuvinte = linie.split(",");
        return new Student(Integer.parseInt(cuvinte[0]), cuvinte[1], Integer.parseInt(cuvinte[2]), cuvinte[3]);
    }
}
