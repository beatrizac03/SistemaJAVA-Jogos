package TelaLogin;

import PainelAdmin.PainelAdmin;
import PainelCliente.PainelCliente;
import classesObjetos.Usuario;
import com.formdev.flatlaf.FlatIntelliJLaf;
import conexaoBD.ConexaoBD;
import paletaDeCores.Cores;
import tipografia.FontManager;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;
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
    private JButton btnCadastrar;
    private JLabel labelUserName = new JLabel("Nome de usuário: ");
    private JTextField inputUserName = new JTextField();
    private JLabel labelPassword = new JLabel("Senha: ");
    private JPasswordField inputPassword = new JPasswordField();

    public TelaLogin() {
        super("GameHUB");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLayout(null);

        FontManager.manageFont();

        inicializarComponentes();
        adicionarComponentes();

        setVisible(true);
    }

    private void inicializarComponentes() {
        campoUsuario = new JTextField(15);
        campoSenha = new JPasswordField(15);
        botaoLogin = new JButton("ENTRAR");
        labelUsuario = new JLabel("Usuário:");
        labelSenha = new JLabel("Senha:");
        mensagem = new JLabel("");
        btnCadastrar = new JButton("Criar conta");
    }

    private void adicionarComponentes() {
        panel.setLayout(null);
        panel.setSize(1000, 600);

        ImageIcon icon = new ImageIcon("src/main/java/TelaLogin/images/capa.png");
        JLabel imagemLabel = new JLabel(icon);
        imagemLabel.setBounds(0, 0, 500, 600);
        imagemLabel.setBorder(new LineBorder(Color.BLACK));
        imagemLabel.setIcon(icon);

        labelUsuario.setBounds(560, 150, 80, 25);
        campoUsuario.setBounds(560, 185, 340, 30);
        labelSenha.setBounds(560, 235, 80, 25);
        campoSenha.setBounds(560, 270, 340, 30);
        botaoLogin.setBounds(560, 350, 130, 35);
        btnCadastrar.setBounds(700, 350, 200, 35);
        mensagem.setBounds(200, 300, 300, 25);

        Font poppinsMedium16 = FontManager.getFontPoppinsMedium(16f);
        labelUsuario.setFont(poppinsMedium16);
        labelSenha.setFont(poppinsMedium16);

        panel.add(imagemLabel);
        panel.add(labelUsuario);
        panel.add(campoUsuario);
        panel.add(labelSenha);
        panel.add(campoSenha);
        panel.add(botaoLogin);
        panel.add(btnCadastrar);

        botaoLogin.setBackground(Cores.getPurpleS());
        botaoLogin.setForeground(Color.white);
        botaoLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                efetuarLogin();
            }
        });

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modalCadastro();
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
                if (username.equals("admin2024") && password.equals("senha.2024")) {
                    new PainelAdmin();
                } else if (username.equals("cliente2024") && password.equals("senha.2024") ) {
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
    public JFrame frame2 = new JFrame("CADASTRO - GameHUB");
    public void modalCadastro() {
        frame2.setSize(500, 350);
        frame2.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame2.setLocationRelativeTo(null);
        JPanel modal = new JPanel();
        modal.setLayout(null);

        labelUserName.setBounds(50, 50, 100, 30);
        inputUserName.setBounds(50, 85, 350, 30);

        labelPassword.setBounds(50, 130, 100, 30);
        inputPassword.setBounds(50, 165, 350, 30);

        JButton btnEfetuarCadastro = new JButton("CADASTRAR");
        btnEfetuarCadastro.setBounds(50, 220, 350, 30);

        modal.add(labelUserName);
        modal.add(inputUserName);
        modal.add(labelPassword);
        modal.add(inputPassword);
        modal.add(btnEfetuarCadastro);

        btnEfetuarCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = inputUserName.getText();
                String password = new String(inputPassword.getPassword());

                efetuarCadastro(userName, password);
            }
        });

        frame2.add(modal);
        frame2.setVisible(true);
    }

    public void efetuarCadastro(String userName, String password) {
        Usuario user = new Usuario(userName, password);

        try {
            ConexaoBD.cadastrarUsuario(user);
            JOptionPane.showMessageDialog(null, "Cadastro Realizado com Sucesso!", "GameHUB", JOptionPane.PLAIN_MESSAGE);
            frame2.dispose();
        } catch (Exception e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "Não foi possível realizar o cadastro :(, tente novamente", "GameHUB",JOptionPane.ERROR_MESSAGE);
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
