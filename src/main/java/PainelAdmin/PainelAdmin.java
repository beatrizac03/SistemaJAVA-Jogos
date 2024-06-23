package PainelAdmin;

import com.formdev.flatlaf.FlatIntelliJLaf;
import PainelAdmin.componentsAdmin.CadastrarJogosPanel;
import PainelAdmin.componentsAdmin.JogosCadastradosPanel;
import fonts.FontManager;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PainelAdmin extends JFrame {
    private JPanel mainPanel = new JPanel(null);
    private JPanel sidebar = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JLabel labelPerfil = new JLabel();
    private JLabel labelJogosCadastrados = new JLabel("JOGOS CADASTRADOS");
    private JLabel labelCadastrarJogos = new JLabel("CADASTRAR JOGOS");
    private JLabel labelSair = new JLabel("Sair");

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
        sidebar.setBackground(Color.BLUE);
        mainPanel.add(sidebar);

        labelPerfil.setBounds(50, 20, 35, 35);
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

        labelCadastrarJogos.setBounds(50, 100, 150, 20);
        labelCadastrarJogos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        sidebar.add(labelCadastrarJogos);
        labelCadastrarJogos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showCard("cadastrarJogos");
            }
        });

        labelJogosCadastrados.setBounds(50, 140, 150, 20);
        labelJogosCadastrados.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        sidebar.add(labelJogosCadastrados);
        labelJogosCadastrados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showCard("jogosCadastrados");
            }
        });

        labelSair.setBounds(50, 500, 100, 20);
        labelSair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        sidebar.add(labelSair);

        this.add(mainPanel);
    }

    public void configureContentPanel() {
        contentPanel.setLayout(new CardLayout());
        contentPanel.setBorder(new LineBorder(Color.cyan));
        contentPanel.setBounds(230, 0, 770, 600);

        contentPanel.add(new CadastrarJogosPanel(), "cadastrarJogos");
        contentPanel.add(new JogosCadastradosPanel(), "jogosCadastrados");

        mainPanel.add(contentPanel);
    }

    public void showCard(String cardName) {
        CardLayout cl = (CardLayout) (contentPanel.getLayout());
        cl.show(contentPanel, cardName);
    }

//    public void configurePanels() {
//        mainPanel.setSize(500, 500);
////
////        JLabel label = new JLabel("NOME");
//
//        configureSidebarAndContentPanel();
//
////        mainPanel.add(label);
//        this.add(mainPanel);
//    }
//
//    public void configureSidebarAndContentPanel() {
//        sidebar.setBackground(Color.lightGray);
//        sidebar.setPreferredSize(new Dimension(200, this.getHeight()));
//
//        contentPanel.setBackground(Color.YELLOW);
//
//        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, sidebar, contentPanel);
//        splitPane.setDividerLocation(200);
//
//        mainPanel.add(sidebar,BorderLayout.WEST);
//        mainPanel.add(contentPanel, BorderLayout.CENTER);
//        this.add(splitPane);
//
//        SidebarAdmin.configureSidebar(this);
//        ContentPanelAdmin.configureContentPanel(this);
//    }
//
//    public JPanel getSidebar() { return sidebar; }
//    public JPanel getContentPanel() { return contentPanel; }
//
//    public void showCadastrarJogosPanel() {
//        ContentPanelAdmin.showPanel("cadastrarJogos");
//    }
//
//    public void showJogosCadastradosPanel() {
//        ContentPanelAdmin.showPanel("jogosCadastrados");
//    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch(Exception e) {
            e.getMessage();
        }
        new PainelAdmin();
    }


}
