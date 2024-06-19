package PainelAdmin;

import classesObjetos.Jogo;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import componentsAdmin.ContentPanelAdmin;
import componentsAdmin.SidebarAdmin;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import com.formdev.flatlaf.FlatLightLaf;

public class PainelAdmin extends JFrame {
    private JPanel mainPanel = new JPanel(new BorderLayout());
    private JPanel sidebar = new JPanel();
    private JPanel contentPanel = new JPanel();

    public PainelAdmin() {
//        FlatLightLaf.setup();
//
//        // customizar a cor da barra de título do frame
//        UIManager.put("TitlePane.background", Color.BLUE);
//        UIManager.put("TitlePane.foreground", Color.WHITE);

        this.setTitle("GameHUB");
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        FontManager.manageFont();
        configurePanels();
        this.setVisible(true);
    }

    public void configurePanels() {
        mainPanel.setSize(500, 500);
//
//        JLabel label = new JLabel("NOME");

        configureSidebarAndContentPanel();

//        mainPanel.add(label);
        this.add(mainPanel);
    }

    public void configureSidebarAndContentPanel() {
        sidebar.setBackground(Color.lightGray);
        sidebar.setPreferredSize(new Dimension(200, this.getHeight()));

        contentPanel.setBackground(Color.YELLOW);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, sidebar, contentPanel);
        splitPane.setDividerLocation(200);

        mainPanel.add(sidebar,BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        this.add(splitPane);

//        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sidebar, contentPanel);
//        splitPane.setDividerLocation(200);
//
//        mainPanel.add(splitPane, BorderLayout.CENTER);

        SidebarAdmin.configureSidebar(this);
        ContentPanelAdmin.configureContentPanel(this);
    }

    public JPanel getSidebar() { return sidebar; }
    public JPanel getContentPanel() { return contentPanel; }

    public void showCadastrarJogosPanel() {
        ContentPanelAdmin.showPanel("cadastrarJogos");
    }

    public void showJogosCadastradosPanel() {
        ContentPanelAdmin.showPanel("jogosCadastrados");
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
