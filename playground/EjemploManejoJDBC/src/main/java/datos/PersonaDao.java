package datos;

import domain.PersonaDTO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Luis Mutis
 */
public interface PersonaDao {

    public List<PersonaDTO> getPersona() throws SQLException;

    public int insert(PersonaDTO personaDto) throws SQLException;

    public int update(PersonaDTO personaDto) throws SQLException;

    public int delete(PersonaDTO personaDto) throws SQLException;

}
