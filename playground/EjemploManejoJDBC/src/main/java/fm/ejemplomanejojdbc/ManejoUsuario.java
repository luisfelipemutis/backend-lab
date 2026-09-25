package fm.ejemplomanejojdbc;

import datos.UsuarioJDBC;
import domain.Usuario;
import java.util.List;

/**
 *
 * @author Luis Mutis
 */
public class ManejoUsuario {
    
    public static void main(String[] args) {
        UsuarioJDBC usuarioJdbc = new UsuarioJDBC();

        // Listando usuarios
        List<Usuario> users = usuarioJdbc.seleccionar();
        users.forEach(u -> {
            System.out.println("Usuario: " + u);
        });

        // Insertar usuario nuevo
        //Usuario user = new Usuario("Carlos", "556");
        //usuarioJdbc.insertar(user);
        
        // Actualizar usuario
        //Usuario user = new Usuario(3, "Carlos", "123456");
        //usuarioJdbc.actualizar(user);
   
        // Eliminar usuario
        Usuario user = new Usuario(3);
        usuarioJdbc.eliminar(user);
    }
    
}
