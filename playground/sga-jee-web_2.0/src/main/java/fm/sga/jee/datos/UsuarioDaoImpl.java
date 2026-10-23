package fm.sga.jee.datos;

import fm.sga.jee.domain.Usuario;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import java.util.List;

/**
 *
 * @author Luis Mutis
 */
@Stateless
public class UsuarioDaoImpl implements UsuarioDao {
    
    @PersistenceContext(unitName = "personaPU")
    EntityManager em;
    
    @Override
    public List<Usuario> findAllUsuarios() {
        return em.createNamedQuery("Usuario.findAll").getResultList();
    }
    
    @Override
    public Usuario findUsuarioById(Usuario usuario) {
        return em.find(Usuario.class, usuario.getIdUsuario());
    }
    
    @Override
    public void insertarUsuario(Usuario usuario) {
        em.persist(usuario);
    }
    
    @Override
    public void updateUsuario(Usuario usuario) {
        em.merge(usuario);
    }
    
    @Override
    public void deleteUsuario(Usuario usuario) {
        em.remove(em.merge(usuario));
    }
    
}
