package fm.sga.jee.datos;

import fm.sga.jee.domain.Usuario;
import java.util.List;

/**
 *
 * @author Luis Mutis
 */
public interface UsuarioDao {

    public List<Usuario> findAllUsuarios();

    public Usuario findUsuarioById(Usuario usuario);

    public void insertarUsuario(Usuario usuario);

    public void updateUsuario(Usuario usuario);

    public void deleteUsuario(Usuario usuario);

}
