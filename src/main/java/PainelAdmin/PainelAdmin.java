package PainelAdmin;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PainelAdmin extends JFrame {
    String fontPath = "src/fonts/Poppins-Medium.ttf";
    private JPanel panel = new JPanel();

    public PainelAdmin() {
        this.setSize(900, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        manageFont();
        configurePanels();
        this.setVisible(true);
    }

    public void configurePanels() {
        panel.setSize(500, 500);

        JLabel label = new JLabel("NOME");

        configureSidebarAndMainPanel();

        panel.add(label);
        this.add(panel);
    }

    public void configureSidebarAndMainPanel() {
        JPanel sidebar = new JPanel();
        sidebar.setBackground(Color.lightGray);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.YELLOW);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, sidebar, mainPanel);
        splitPane.setDividerLocation(200);

        panel.add(sidebar);
        panel.add(mainPanel);
        this.add(splitPane);
    }

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
