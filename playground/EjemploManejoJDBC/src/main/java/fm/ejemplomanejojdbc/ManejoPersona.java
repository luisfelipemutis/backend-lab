package fm.ejemplomanejojdbc;

import datos.Conexion;
import datos.PersonaDao;
import datos.PersonaDaoJDBC;
import domain.PersonaDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Luis Mutis
 */
public class ManejoPersona {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        Connection conexion = null;

        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(Boolean.FALSE);
            }

            PersonaDao personaDao = new PersonaDaoJDBC(conexion);

            List<PersonaDTO> personas = personaDao.getPersona();
            personas.forEach(p -> {
                System.out.println("Persona: " + p);
            });

            PersonaDTO persona = new PersonaDTO(1, "Royal54321", "Sanchez00", "gmail.com", "");
            personaDao.update(persona);
            
            conexion.commit();
            System.out.println("Se ha realizado el commit de la transaccion");
        } catch (SQLException ex) {
            System.out.println("Ingreso al rollback");
            ex.printStackTrace(System.out);
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }

    }
}
