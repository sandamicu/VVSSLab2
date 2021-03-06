package VVSSLab2.validation;

import VVSSLab2.domain.Tema;

public class TemaValidator implements Validator<Tema> {

    /**
     * Valideaza o tema
     * @param entity - tema pe care o valideaza
     * @throws ValidationException daca tema nu e valida
     */
    @Override
    public void validate(Tema entity) throws ValidationException {
        if(entity.getID() == null || entity.getID().equals("") || !entity.getID().matches("[1-9][0-9]*")) {
            throw new ValidationException("Numar tema invalid!");
        }
        if(entity.getDescriere() == null ||entity.getDescriere().equals("")){
            throw new ValidationException("Descriere invalida!");
        }
        if(entity.getDeadline() < 1 || entity.getDeadline() > 14) {
            throw new ValidationException("Deadlineul trebuie sa fie intre 1-14.");
        }
        if(entity.getPrimire() < 1 || entity.getPrimire() > 14 || entity.getPrimire()>entity.getDeadline()) {
            throw new ValidationException("Saptamana primirii trebuie sa fie intre 1-14.");
        }
    }
}
