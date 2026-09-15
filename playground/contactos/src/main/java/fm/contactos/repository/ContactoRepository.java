package fm.contactos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fm.contactos.model.Contacto;

public interface ContactoRepository extends JpaRepository<Contacto, Integer> {

}
