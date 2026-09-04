package fm.zona_fit.servicio;
import fm.zona_fit.modelo.Cliente;
import java.util.List;
public interface IClienteServicio {
    List<Cliente> obtenerTodosLosClientes();

    Cliente obtenerClientePorId(Integer id);

    Cliente agregarCliente(Cliente cliente);

    void eliminarCliente(Integer id);

}