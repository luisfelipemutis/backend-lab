package fm.zona_fit;

import com.formdev.flatlaf.FlatDarculaLaf;
import fm.zona_fit.gui.ZonaFitForm;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

//@SpringBootApplication
public class ZonaFitSwing {

    public static void main(String[] args) {
        // Configurar el modo oscuro en la interfaz gráfica
        FlatDarculaLaf.setup();

        // Instanciar la fabrica de spring
        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(ZonaFitSwing.class)
                        .headless(false) // Indica que la aplicación no es headless (tiene interfaz gráfica)
                        .web(WebApplicationType.NONE) // Indica que no es una aplicación web
                        .run(args);

        // Crear un objeto de Swing
        SwingUtilities.invokeLater(() -> {
            ZonaFitForm zonaFitForm = context.getBean(ZonaFitForm.class);
            zonaFitForm.setVisible(true);
        });
    }
}
