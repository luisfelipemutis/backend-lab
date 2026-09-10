package fm.tareas.controller;

import fm.tareas.model.Tarea;
import fm.tareas.service.TareaService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class IndexController implements Initializable {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class.getName());

    @Autowired
    private TareaService tareaService;

    @FXML
    private TableView<Tarea> tareaTable;

    @FXML
    private TableColumn<Tarea, Integer> idTareaColumn;

    @FXML
    private TableColumn<Tarea, String> nombreTareaColumn;

    @FXML
    private TableColumn<Tarea, String> responsableColumn;

    @FXML
    private TableColumn<Tarea, String> estatusColumn;

    // Se adicionan los componentes text area del formulario.
    @FXML
    private TextField nombreTareaField;

    @FXML
    private TextField responsableField;

    @FXML
    private TextField estatusField;

    // ObservableList to hold the list of tasks
    private final ObservableList<Tarea> tareaList = FXCollections.observableArrayList();

    private Integer idTareaSelected;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tareaTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        configureTableColumns();
        loadTareas();
    }

    private void configureTableColumns() {
        idTareaColumn.setCellValueFactory(new PropertyValueFactory<>("idTarea"));
        nombreTareaColumn.setCellValueFactory(new PropertyValueFactory<>("nombreTarea"));
        responsableColumn.setCellValueFactory(new PropertyValueFactory<>("responsable"));
        estatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void loadTareas() {
        logger.info("Loading Tareas...");
        tareaList.clear();
        tareaList.addAll(tareaService.getTareas());
        tareaTable.setItems(tareaList);
    }

    public void saveTarea() {
        logger.info("Saving Tarea...");
        if (nombreTareaField.getText().isEmpty()) {
            showMessage("Error validacion", "El nombre de la tarea es obligatorio.");
            nombreTareaField.requestFocus();
        } else if (idTareaSelected != null) {
            showMessage("Error validacion", "No se puede agregar una " +
                    "tarea con un ID existente. Por favor, use la opción de actualizar.");
        } else {
            Tarea tarea = new Tarea();
            getDataForm(tarea);
            tarea.setIdTarea(null);
            tareaService.saveTarea(tarea);
            showMessage("Informacion", "Tarea agregada");
            cleanForm();
            loadTareas();
        }
    }

    public void updateTarea() {
        if (idTareaSelected != null) {
            if (nombreTareaField.getText().isEmpty()) {
                showMessage("Error validacion", "El nombre de la tarea es obligatorio.");
                nombreTareaField.requestFocus();
                return;
            }
            Tarea tarea = new Tarea();
            getDataForm(tarea);
            tareaService.saveTarea(tarea);
            showMessage("Informacion", "Tarea actualizada");
            cleanForm();
            loadTareas();
        } else {
            showMessage("Error Validacion", "No se ha seleccionado ninguna tarea para actualizar.");
        }
    }

    public void deleteTarea() {
        if (idTareaSelected != null) {
            tareaService.deleteTarea(idTareaSelected);
            showMessage("Informacion", "Tarea eliminada");
            cleanForm();
            loadTareas();
        } else {
            showMessage("Error Validacion", "No se ha seleccionado ninguna tarea para eliminar.");
        }
    }

    // Metodo que se llama cuando se selecciona una tarea en la tabla para cargar sus datos en el formulario.
    public void loadTareaForm() {
        Tarea selectedTarea = tareaTable.getSelectionModel().getSelectedItem();
        if (selectedTarea != null) {
            idTareaSelected = selectedTarea.getIdTarea();
            nombreTareaField.setText(selectedTarea.getNombreTarea());
            responsableField.setText(selectedTarea.getResponsable());
            estatusField.setText(selectedTarea.getStatus());
        }
    }

    public void cleanForm() {
        idTareaSelected = null;
        nombreTareaField.clear();
        responsableField.clear();
        estatusField.clear();
    }

    private void getDataForm(Tarea tarea) {
        if (idTareaSelected != null)
            tarea.setIdTarea(idTareaSelected);
        tarea.setNombreTarea(nombreTareaField.getText());
        tarea.setResponsable(responsableField.getText());
        tarea.setStatus(estatusField.getText());

        logger.info("Obteniendo datos del formulario: {}", tarea);
    }

    private void showMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
