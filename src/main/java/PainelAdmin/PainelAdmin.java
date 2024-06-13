package PainelAdmin;

import componentsAdmin.ContentPanelAdmin;
import componentsAdmin.SidebarAdmin;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PainelAdmin extends JFrame {
    String fontPath = "src/fonts/Inter-SemiBold.ttf";
    private JPanel mainPanel = new JPanel(new BorderLayout());
    private JPanel sidebar = new JPanel();
    private JPanel contentPanel = new JPanel();

    public PainelAdmin() {
        this.setSize(900, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        manageFont();
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

        SidebarAdmin.configureSidebar(this);
        ContentPanelAdmin.configureContentPanel(this);
    }

    public JPanel getSidebar() { return sidebar; }
    public JPanel getContentPanel() { return contentPanel; }

    public void manageFont() {
        try {
            Font fontInterRegular = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(12f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fontInterRegular);

            UIManager.put("Label.font", fontInterRegular);

        } catch (IOException e) {
            e.printStackTrace();
            exibirMensagemErro("Erro ao carregar a fonte. Verifique o caminho do arquivo.");
        } catch (FontFormatException e) {
            exibirMensagemErro("A tipografia está corrompida ou inválida.");
        }
    }

    private void exibirMensagemErro(String message){
        JOptionPane.showMessageDialog(null, message, "ERRO", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        new PainelAdmin();
    }


}
