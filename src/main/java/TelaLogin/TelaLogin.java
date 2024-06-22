package TelaLogin;

import PainelAdmin.PainelAdmin;
import PainelCliente.PainelCliente;
import classesObjetos.Usuario;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import conexaoBD.ConexaoBD;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaLogin extends JFrame {
    private JPanel panel = new JPanel();
    private JTextField campoUsuario;
    private JPasswordField campoSenha;
    private JButton botaoLogin;
    private JLabel labelUsuario;
    private JLabel labelSenha;
    private JLabel mensagem;

    public TelaLogin() {
        super("gameHUB");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLayout(null);

        inicializarComponentes();
        adicionarComponentes();

        setVisible(true);
    }

    private void inicializarComponentes() {
        campoUsuario = new JTextField(15);
        campoSenha = new JPasswordField(15);
        botaoLogin = new JButton("Login");
        labelUsuario = new JLabel("Usuário:");
        labelSenha = new JLabel("Senha:");
        mensagem = new JLabel("");
    }

    private void adicionarComponentes() {
        panel.setLayout(null);
        panel.setSize(1000, 600);

        // Definindo posições e tamanhos dos componentes

        ImageIcon icon = new ImageIcon("gameHUB.png");
        JLabel imagemLabel = new JLabel(icon);
        imagemLabel.setBounds(0, 0, 500, 600);
        imagemLabel.setBorder(new LineBorder(Color.BLACK));
        imagemLabel.setIcon(icon);

        labelUsuario.setBounds(600, 150, 80, 25);
        campoUsuario.setBounds(600, 190, 250, 25);
        labelSenha.setBounds(600, 235, 80, 25);
        campoSenha.setBounds(600, 275, 250, 25);
        botaoLogin.setBounds(600, 330, 80, 25);
        mensagem.setBounds(200, 300, 300, 25);

        // Adicionando componentes ao frame
        panel.add(imagemLabel);
        panel.add(labelUsuario);
        panel.add(campoUsuario);
        panel.add(labelSenha);
        panel.add(campoSenha);
        panel.add(botaoLogin);

        botaoLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                efetuarLogin();
            }
        });

        this.setBackground(Color.WHITE);
        this.add(panel);
    }

    public void efetuarLogin() {
        String username = campoUsuario.getText();
        String password = new String(campoSenha.getPassword());

        List<Usuario> users = ConexaoBD.autenticarUsuario();

        boolean loginSuccess = false;
        for (Usuario user : users) {
            if (user.getNomeUsuario().equals(username) && user.getSenhaUsuario().equals(password)) {
                loginSuccess = true;
                if (username.equals("admin2024") && password.equals("senha.admin")) {
                    new PainelAdmin();
                } else if (username.equals("cliente2024") && password.equals("senha.cliente") ) {
                    new PainelCliente();
                }
                this.dispose();
                break;
            }
        }

        if (!loginSuccess) {
            mensagem.setText("Usuário ou senha incorretos.");
        }

    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception e) {
            e.getMessage();
        }
        new TelaLogin();
    }
}
