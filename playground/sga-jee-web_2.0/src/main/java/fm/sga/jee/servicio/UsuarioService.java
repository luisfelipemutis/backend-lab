package fm.sga.jee.servicio;

import fm.sga.jee.domain.Usuario;
import jakarta.ejb.Local;
import java.util.List;

/**
 *
 * @author Luis Mutis
 */
@Local
public interface UsuarioService {

    public List<Usuario> listarUsuarios();

    public Usuario getUsuarioById(Usuario usuario);

    public void insertarUsuario(Usuario usuario);

    public void modificarUsuario(Usuario usuario);

    public void eliminarUsuario(Usuario usuario);

}
