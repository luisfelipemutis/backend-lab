package fm.sga.jee.servicio;

import fm.sga.jee.domain.Persona;
import jakarta.ejb.Local;
import java.util.List;

/**
 *
 * @author Luis Mutis
 */
@Local
public interface PersonaService {

    public List<Persona> listarPersonas();

    public Persona getPersonaById(Persona persona);

    public Persona getPersonaByEmail(Persona persona);

    public void registrarPersona(Persona persona);

    public void modificarPersona(Persona persona);

    public void eliminarPersona(Persona persona);
}
