package fm.tareas.service;

import fm.tareas.model.Tarea;
import fm.tareas.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TareaService implements ITareaService {

    @Autowired
    private TareaRepository tareaRepository;

    @Override
    public List<Tarea> getTareas() {
        return tareaRepository.findAll();
    }

    @Override
    public Tarea getTareaById(Integer id) {
        return tareaRepository.findById(id).orElse(null);
    }

    @Override
    public void saveTarea(Tarea tarea) {
        tareaRepository.save(tarea);
    }

    @Override
    public void deleteTarea(Integer idTarea) {
        tareaRepository.deleteById(idTarea);
    }
}
