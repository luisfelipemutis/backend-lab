package fm.tareas.service;

import fm.tareas.model.Tarea;

import java.util.List;

public interface ITareaService {

    public List<Tarea> getTareas();

    public Tarea getTareaById(Integer id);

    public void saveTarea(Tarea tarea);

    public void deleteTarea(Integer idTarea);

}
