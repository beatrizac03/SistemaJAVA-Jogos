package PainelAdmin;

import TelaLogin.TelaLogin;
import com.formdev.flatlaf.FlatIntelliJLaf;
import PainelAdmin.componentsAdmin.*;
import tipografia.FontManager;
import paletaDeCores.Cores;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Classe PainelAdmin: representa e inicializa o conteúdo base para o portal do admin
 * MainPanel = contém dois outros painéis, sidebar e contentPanel
 * Sidebar = possui as labels de clique (labelPerfil, labelCadastrarJogos, labelJogosCadastrados, labelSair)
 * ContentPanel = configurado com CardLayout, seu conteúdo muda de acordo com a label clicada. Podendo abrir dois outros
 * painéis, Cadastrar Jogos ou Jogos Cadastrados, que estão separados em classes próprias, dentro de componentesAdmin
 */
public class PainelAdmin extends JFrame {
    private JPanel mainPanel = new JPanel(null);
    private JPanel sidebar = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JLabel labelPerfil = new JLabel();
    private JLabel labelJogosCadastrados = new JLabel("JOGOS CADASTRADOS");
    private JLabel labelCadastrarJogos = new JLabel("CADASTRAR JOGOS");
    private JLabel labelSair = new JLabel("Sair");
    private Color selectedLabelColor = Cores.getPurpleS();
    private Color normalLabelColor = Color.white;
    boolean labelClicked = false;

    public PainelAdmin() {
        this.setTitle("GameHUB - Portal Admin");
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        mainPanel.setSize(1000, 600);

        FontManager.manageFont();

        configureSidebar();
        configureContentPanel();
        this.setVisible(true);
    }

    public void configureSidebar() {
        sidebar.setBounds(0, 0, 230, this.getHeight());
        sidebar.setLayout(null);
        sidebar.setBackground(Cores.getOrangeS());
        mainPanel.add(sidebar);

        labelPerfil.setBounds(30, 20, 35, 35);
        ImageIcon icon = new ImageIcon("src/main/java/PainelCliente/images/profile-user.png");
        Image img = icon.getImage().getScaledInstance(labelPerfil.getWidth(), labelPerfil.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(img);
        labelPerfil.setIcon(scaledIcon);
        sidebar.add(labelPerfil);
        labelPerfil.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });

        labelCadastrarJogos.setBounds(30, 100, 150, 20);

        labelCadastrarJogos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        sidebar.add(labelCadastrarJogos);
        labelCadastrarJogos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                labelClicked = true;
                verificarStatusLabel(labelCadastrarJogos);
                showCard("cadastrarJogos");
            }
        });

        labelJogosCadastrados.setBounds(30, 140, 150, 20);
        labelJogosCadastrados.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        sidebar.add(labelJogosCadastrados);
        labelJogosCadastrados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showCard("jogosCadastrados");
            }
        });

        labelSair.setBounds(40, 500, 100, 20);
        labelSair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        sidebar.add(labelSair);
        labelSair.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new TelaLogin();
            }
        });

        this.add(mainPanel);
    }

    public void verificarStatusLabel(JLabel label) {
        if(labelClicked) {
            label.setForeground(selectedLabelColor);
        } else {
            label.setForeground(normalLabelColor);
        }
    }

    public void configureContentPanel() {
        contentPanel.setLayout(new CardLayout());
//        contentPanel.setBorder(new LineBorder(Color.cyan));
        contentPanel.setBounds(230, 0, 770, 600);

        contentPanel.add(new CadastrarJogosPanel(), "cadastrarJogos");
        contentPanel.add(new JogosCadastradosPanel(), "jogosCadastrados");

        mainPanel.add(contentPanel);
    }

    public void showCard(String cardName) {
        CardLayout cl = (CardLayout) (contentPanel.getLayout());
        cl.show(contentPanel, cardName);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch(Exception e) {
            e.getMessage();
        }
        new PainelAdmin();
    }

}
