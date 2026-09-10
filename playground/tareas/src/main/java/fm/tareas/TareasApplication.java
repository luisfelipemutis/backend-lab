package fm.tareas;

import fm.tareas.presentation.SistemasTareasFx;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TareasApplication {

    public static void main(String[] args) {

        // SpringApplication.run(TareasApplication.class, args);

        // Launch the JavaFX application
        Application.launch(SistemasTareasFx.class, args);
    }
}
