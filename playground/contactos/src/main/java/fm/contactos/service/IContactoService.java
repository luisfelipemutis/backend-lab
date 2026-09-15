package fm.contactos.service;

import fm.contactos.model.Contacto;

import java.util.List;

public interface IContactoService {

    List<Contacto> getAllContacts();

    Contacto getContactById(Integer id);

    void deleteContactById(Integer id);

    void saveContact(Contacto contacto);

}
