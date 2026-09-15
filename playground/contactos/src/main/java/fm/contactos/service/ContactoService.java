package fm.contactos.service;

import fm.contactos.model.Contacto;
import fm.contactos.repository.ContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactoService implements IContactoService {

    @Autowired
    private ContactoRepository contactoRepository;

    @Override
    public List<Contacto> getAllContacts() {
        return contactoRepository.findAll();
    }

    @Override
    public Contacto getContactById(Integer id) {
        return contactoRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteContactById(Integer id) {
        contactoRepository.deleteById(id);
    }

    @Override
    public void saveContact(Contacto contacto) {
        contactoRepository.save(contacto);
    }
}
