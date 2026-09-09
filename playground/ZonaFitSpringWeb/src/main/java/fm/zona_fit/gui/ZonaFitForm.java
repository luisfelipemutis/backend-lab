package fm.zona_fit.gui;

import fm.zona_fit.modelo.Cliente;
import fm.zona_fit.servicio.ClienteServicio;
import fm.zona_fit.servicio.IClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

// Anotacion para convertir la clase como un componente spring para poder utilizar spring
//@Component
public class ZonaFitForm extends JFrame {
    private JPanel mainPanel;
    private JTable tableClient;
    private JTextField firstNameText;
    private JTextField lastNameText;
    private JTextField membershipText;
    private JButton guardarButton;
    private JButton eliminarButton;
    private JButton limpiarButton;
    IClienteServicio clienteServicio;

    private DefaultTableModel tableModelClient;
    private Integer selectedClientId;

    // Esta inyección de dependencias se hace a través del constructor
    // Ya que si se hace a través de la anotación @Autowired en el atributo
    // no se puede utilizar el servicio antes de que cargue el contexto de spring, y en este caso se necesita utilizar el
    // servicio en el constructor para inicializar la interfaz gráfica.
    @Autowired
    public ZonaFitForm(ClienteServicio clienteServicio) {
        this.clienteServicio = clienteServicio;
        initForm();
        guardarButton.addActionListener(e -> addClient());
        eliminarButton.addActionListener(e -> deleteClient());
        limpiarButton.addActionListener(e -> cleanTextFields());
        tableClient.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                loadClientSelected();
            }
        });
    }

    private void initForm() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
    }

    private void createUIComponents() {
        this.tableModelClient = new DefaultTableModel(0, 4) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer que las celdas de la tabla no sean editables
            }
        };
        String[] columnNames = {"ID", "Nombre", "Apellido", "Membresia"};
        this.tableModelClient.setColumnIdentifiers(columnNames);
        this.tableClient = new JTable(tableModelClient); // Inicializar la tabla con el modelo de datos
        // Restringir la seleccion a 1 solo registro
        this.tableClient.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Cargar los datos de los clientes en la tabla
        showClients();
    }

    private void showClients() {
        this.tableModelClient.setRowCount(0); // Limpiar la tabla antes de cargar los datos
        List<Cliente> clients = this.clienteServicio.obtenerTodosLosClientes();
        clients.forEach(cliente -> {
            Object[] rowData = {
                    cliente.getId(),
                    cliente.getNombre(),
                    cliente.getApellido(),
                    cliente.getMembresia()
            };
            this.tableModelClient.addRow(rowData); // Agregar la fila a la tabla
        });
    }

    private void addClient() {
        String firstName = firstNameText.getText();
        String lastName = lastNameText.getText();
        String membershipStr = membershipText.getText();
        if (firstName == null || firstName.isEmpty()) {
            showMessages("El nombre no puede estar vacío, por favor ingrese un nombre válido.");
            firstNameText.requestFocusInWindow();
            return;
        }
        if (membershipStr == null || membershipStr.isEmpty()) {
            showMessages("La membresía no puede estar vacía, por favor ingrese una membresía válida.");
            membershipText.requestFocusInWindow();
            return;
        }

        // Establecer el ID del cliente seleccionado (si es una actualización)
        Cliente client = new Cliente(this.selectedClientId, firstName, lastName, Integer.parseInt(membershipStr));
        this.clienteServicio.agregarCliente(client);

        if (this.selectedClientId == null) {
            showMessages("Cliente agregado correctamente.");
        } else {
            showMessages("Cliente actualizado correctamente.");
        }
        cleanTextFields(); // Limpiar los campos de texto después de agregar un cliente
        showClients(); // Actualizar la tabla después de agregar un cliente
    }

    private void loadClientSelected() {
        int selectedRow = tableClient.getSelectedRow();
        if (selectedRow >= 0) { // -1 No se ha seleccionado ningun registro
            String id = tableClient.getModel().getValueAt(selectedRow, 0).toString();
            this.selectedClientId = Integer.parseInt(id);
            String firstName = tableClient.getModel().getValueAt(selectedRow, 1).toString();
            String lastName = tableClient.getModel().getValueAt(selectedRow, 2).toString();
            String membership = tableClient.getModel().getValueAt(selectedRow, 3).toString();
            firstNameText.setText(firstName);
            lastNameText.setText(lastName);
            membershipText.setText(membership);
        }
    }

    private void deleteClient() {
        if (this.selectedClientId != null) {
            this.clienteServicio.eliminarCliente(this.selectedClientId);
            showMessages("Cliente con ID " + this.selectedClientId + " eliminado correctamente.");
            cleanTextFields();
            showClients(); // Actualizar la tabla después de eliminar un cliente
        } else {
            showMessages("Por favor seleccione un cliente para eliminar.");
        }
    }

    private void showMessages(String sms) {
        JOptionPane.showMessageDialog(this, sms);
    }

    private void cleanTextFields() {
        firstNameText.setText("");
        lastNameText.setText("");
        membershipText.setText("");
        this.selectedClientId = null; // Limpiar el ID del cliente seleccionado
        this.tableClient.getSelectionModel().clearSelection(); // Limpiar la selección de la tabla
    }
}
