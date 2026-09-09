package fm.zona_fit.controlador;

import fm.zona_fit.modelo.Cliente;
import fm.zona_fit.servicio.IClienteServicio;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.primefaces.PrimeFaces;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import org.slf4j.Logger;

@Component
@Data // Anotación de Lombok para generar automáticamente los métodos getters y setters
@ViewScoped // Anotación de JSF para indicar que el bean tiene un alcance de vista (Solo tiene 1 vista)
public class IndexControlador {

    @Autowired
    IClienteServicio clientService;
    private List<Cliente> clients;
    private Cliente selectedClient;

    private static final Logger logger = LoggerFactory.getLogger(IndexControlador.class.getName());

    // Anotación de JSF para indicar que el método se ejecutará después de la construcción del bean
    @PostConstruct
    public void init() {
        loadData();
    }

    public void loadData() {
        this.clients = this.clientService.obtenerTodosLosClientes();
        this.clients.forEach(client -> logger.info(client.toString()));
    }

    public void addClient() {
        // Metodo que se invoca cuando se da clic en boton agregar cliente
        // se crea un nuevo objeto cliente y se asigna a la variable selectedClient
        this.selectedClient = new Cliente();
    }

    public void saveClient() {
        logger.info("Cliente a guardar: " + this.selectedClient);

        if (this.selectedClient.getId() == null) {
            // agregar cliente
            this.clientService.agregarCliente(this.selectedClient);
            this.clients.add(this.selectedClient);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cliente Agregado"));
        } else {
            // actualizar cliente
            this.clientService.agregarCliente(this.selectedClient);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cliente Actualizado"));
        }

        // Ocultar vtn modal
        PrimeFaces.current().executeScript("PF('ventanaModalCliente').hide()");
        // Actualizar tabla de clientes y mensajes - usando Ajax
        PrimeFaces.current().ajax().update("forma-clientes:mensajes",
                "forma-clientes:clients-table");
        // Reset del objeto cliente seleccionado
        this.selectedClient = null;
    }

    public void deleteClient() {
        logger.info("Cliente a eliminar: " + this.selectedClient);
        this.clientService.eliminarCliente(this.selectedClient.getId());
        this.clients.remove(this.selectedClient);
        // Reset del objeto cliente seleccionado
        this.selectedClient = null;

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Cliente Eliminado"));
        // Actualizar tabla de clientes y mensajes - usando Ajax
        PrimeFaces.current().ajax().update("forma-clientes:mensajes",
                "forma-clientes:clients-table");

    }
}
