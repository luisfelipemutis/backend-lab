package fm.sga.jee.servicio;

import fm.sga.jee.datos.PersonaDao;
import fm.sga.jee.domain.Persona;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.util.List;

/**
 *
 * @author Luis Mutis
 */
@Stateless
public class PersonaServiceImpl implements PersonaServiceRemote, PersonaService {
    
    @Inject
    private PersonaDao personaDao;
    
    @Override
    public List<Persona> listarPersonas() {
        return personaDao.finAllPersonas();
    }
    
    @Override
    public Persona getPersonaById(Persona persona) {
        return personaDao.findPersonaById(persona);
    }
    
    @Override
    public Persona getPersonaByEmail(Persona persona) {
        return personaDao.findPersonaByEmail(persona);
    }
    
    @Override
    public void registrarPersona(Persona persona) {
        personaDao.insertPersona(persona);
    }
    
    @Override
    public void modificarPersona(Persona persona) {
        personaDao.updatePersona(persona);
    }
    
    @Override
    public void eliminarPersona(Persona persona) {
        personaDao.deletePersona(persona);
    }
    
}
