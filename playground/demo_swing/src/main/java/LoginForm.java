import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;

public class LoginForm extends JFrame {
    private JPanel panelPrincipal;
    private JTextField userText;
    private JPasswordField passwordText;
    private JButton sendButton;

    public LoginForm() {
        inicializarForm();
        sendButton.addActionListener(e -> validar());
    }

    private void inicializarForm() {
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        FlatDarculaLaf.setup(); // Cambiar look and feel a modo dark.
        LoginForm loginForm = new LoginForm();
        loginForm.setVisible(true);
    }

    private void validar() {
        String user = userText.getText();
        String password = new String(passwordText.getPassword());

        if (user.equals("root") && password.equals("root")) {
            JOptionPane.showMessageDialog(this, "Login exitoso, bienvenido!");
        } else if (user.equals("root")) {
            JOptionPane.showMessageDialog(this, "Contraseña incorrecta");
        } else if (password.equals("root")) {
            JOptionPane.showMessageDialog(this, "Usuario incorrecto");
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos");
        }
    }
}
