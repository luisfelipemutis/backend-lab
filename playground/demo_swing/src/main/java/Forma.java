import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Forma extends JFrame {

    private JPanel PanelPrincipal;
    private JTextField campoTexto;
    private JLabel replicadorLabel;

    public Forma() {
        inicializarForma();
        //campoTexto.addActionListener(e -> replicarTexto());
        campoTexto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                replicarTexto();
            }
        });
    }

    private void inicializarForma() {
        setContentPane(PanelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
    }

    public static void main(String[] args) {
        // Establecer el tema de FlatLaf
        FlatDarculaLaf.setup(); // Setear tema de FlatLaf
        Forma forma = new Forma();
        forma.setVisible(true);
    }

    private void replicarTexto() {
        this.replicadorLabel.setText(this.campoTexto.getText());
    }
}
