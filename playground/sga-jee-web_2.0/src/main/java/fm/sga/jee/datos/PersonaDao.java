package fm.sga.jee.datos;

import fm.sga.jee.domain.Persona;
import java.util.List;

/**
 *
 * @author Luis Mutis
 */
public interface PersonaDao {

    public List<Persona> finAllPersonas();

    public Persona findPersonaById(Persona persona);

    public Persona findPersonaByEmail(Persona persona);

    public void insertPersona(Persona persona);

    public void updatePersona(Persona persona);

    public void deletePersona(Persona persona);
}
