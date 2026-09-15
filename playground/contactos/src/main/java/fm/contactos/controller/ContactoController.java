package fm.contactos.controller;

import fm.contactos.model.Contacto;
import fm.contactos.service.ContactoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ContactoController {

    private static final Logger log = LoggerFactory.getLogger(ContactoController.class);

    @Autowired
    private ContactoService contactoService;

    @GetMapping("/")
    public String started(ModelMap model) {
        List<Contacto> contacts = contactoService.getAllContacts();
        contacts.forEach(c -> log.info(c.toString()));
        model.put("contacts", contacts); // Add the list of contacts to the model for the view to render
        return "index"; // Return the name of the view (index.html) to be rendered
    }

    @GetMapping("/agregar")
    public String addContact() {
        return "addContact"; // Return the name of the view (addContact.html) to be rendered
    }

    @PostMapping("/agregar")
    public String saveContact(@ModelAttribute("ContactForm") Contacto contact) {
        log.info("Agregando contacto : {}", contact);
        contactoService.saveContact(contact); // Save the contact using the service
        return "redirect:/"; // Redirect to controller path "/" to show the updated list of contacts
    }

    @GetMapping("/editar/{id}")
    public String showEditContactForm(@PathVariable(value = "id") Integer id, ModelMap model) {
        Contacto contact = contactoService.getContactById(id);
        log.info("Editando contacto - mostrar formulario : {}", contact);
        model.put("contact", contact); // Add the contact to the model for the view to render
        return "editContact";
    }

    @PostMapping("/editar")
    public String updateContact(@ModelAttribute("contact") Contacto contact) {
        log.info("Editando contacto - actualizar : {}", contact);
        contactoService.saveContact(contact); // Update the contact using the service
        return "redirect:/"; // Redirect to controller path "/" to show the updated list of contacts
    }

    @GetMapping("/eliminar/{id}")
    public String deleteContact(@PathVariable(value = "id") Integer id) {
        log.info("Eliminando contacto : {}", id);
        contactoService.deleteContactById(id);
        return "redirect:/"; // Redirect to controller path "/" to show the updated list of contacts
    }
}
