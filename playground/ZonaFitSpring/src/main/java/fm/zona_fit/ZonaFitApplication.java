package fm.zona_fit;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;
import java.util.List;
import fm.zona_fit.modelo.Cliente;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import fm.zona_fit.servicio.IClienteServicio;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class ZonaFitApplication implements CommandLineRunner {

	@Autowired
	private IClienteServicio clienteServicio;

	private static final Logger logger = LoggerFactory.getLogger(ZonaFitApplication.class);

	String nl = System.lineSeparator();

	public static void main(String[] args) {
		SpringApplication.run(ZonaFitApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner sc = new Scanner(System.in);

		boolean exit = false;
		logger.info(nl + nl);

		try {
			while (!exit) {
				Integer option = showMenu(sc);
				exit = executeOption(option, sc);
				logger.info(nl + nl);
			}
		} catch (Exception e) {
			logger.error("Ocurrió un error en la ejecución del app", e);
		}
	}

	private Integer showMenu(Scanner sc) {
		logger.info("""
				*** Bienvenido zona fit (GYM) spring ***
					Menu
					1. Listar todos los clientes
					2. Buscar cliente por ID
					3. Agregar nuevo cliente
					4. Modificar cliente
					5. Eliminar cliente
					6. Salir
					Elige una opcion:\s""");
		return Integer.parseInt(sc.nextLine());
	}

	private boolean executeOption(Integer option, Scanner sc) {
		switch (option) {
			case 1 -> {
				logger.info(nl + "--- Listado de clientes ---" + nl);
				List<Cliente> clients = clienteServicio.obtenerTodosLosClientes();
				clients.forEach(client -> logger.info(client.toString() + nl));
			}
			case 2 -> {
				logger.info(nl + "--- Buscar Cliente por Id ---" + nl);
				logger.info("Ingrese el ID del cliente:");
				Integer id = Integer.parseInt(sc.nextLine());
				Cliente client = clienteServicio.obtenerClientePorId(id);
				if (client == null) {
					logger.info("Cliente no encontrado: " + client + nl);
					break;
				} else {
					logger.info("Cliente encontrado: " + client + nl);
				}
			}
			case 3 -> {
				logger.info(nl + "--- Agregar cliente ---" + nl);
				logger.info("Nombre: ");
				String nombre = sc.nextLine();
				logger.info("Apellido: ");
				String apellido = sc.nextLine();
				logger.info("Membresia: ");
				Integer membresia = Integer.parseInt(sc.nextLine());

				Cliente newClient = new Cliente();
				newClient.setNombre(nombre);
				newClient.setApellido(apellido);
				newClient.setMembresia(membresia);
				clienteServicio.agregarCliente(newClient);

				logger.info("Cliente agregado: " + newClient.toString() + nl);
			}
			case 4 -> {
				logger.info(nl + "--- Modificar cliente ---" + nl);
				logger.info("Ingrese el ID del cliente a modificar: ");
				Integer idToModify = Integer.parseInt(sc.nextLine());
				Cliente clientToModify = clienteServicio.obtenerClientePorId(idToModify);
				if (clientToModify == null) {
					logger.info("Cliente no encontrado" + nl);
					break;
				}
				logger.info("Nuevo nombre: ");
				String newNombre = sc.nextLine();
				logger.info("Nuevo apellido: ");
				String newApellido = sc.nextLine();
				logger.info("Nueva membresia: ");
				Integer newMembresia = Integer.parseInt(sc.nextLine());

				clientToModify.setNombre(newNombre);
				clientToModify.setApellido(newApellido);
				clientToModify.setMembresia(newMembresia);
				clienteServicio.agregarCliente(clientToModify);

				logger.info("Cliente modificado: " + clientToModify.toString() + nl);
			}
			case 5 -> {
				logger.info(nl + "--- Eliminar cliente ---" + nl);
				logger.info("Ingrese el ID del cliente a eliminar: ");
				Cliente clientToDelete = clienteServicio.obtenerClientePorId(Integer.parseInt(sc.nextLine()));
				if (clientToDelete == null) {
					logger.info("Cliente no encontrado" + nl);
					break;
				}
				clienteServicio.eliminarCliente(clientToDelete.getId());
				logger.info("Cliente eliminado: " + clientToDelete.toString() + nl);
			}
			case 6 -> {
				logger.info(nl + "Hasta pronto!..." + nl + nl);
				return true;
			}
			default -> logger.info("Opcion no valida" + option + nl);
		}
		return false;
	}
}
