package fm.zona_fit.servicio;

import fm.zona_fit.repositorio.ClienteRepository;
import fm.zona_fit.modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ClienteServicio implements IClienteServicio {

    // Auto inyectar referencia al repositorio de clientes
    @Autowired
    private ClienteRepository clienteRepositorio;

    @Override
    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepositorio.findAll();
    }

    @Override
    public Cliente obtenerClientePorId(Integer id) {
        return clienteRepositorio.findById(id).orElse(null);
    }

    @Override
    public Cliente agregarCliente(Cliente cliente) {
        return clienteRepositorio.save(cliente);
    }

    @Override
    public void eliminarCliente(Integer id) {
        clienteRepositorio.deleteById(id);
    }
}
