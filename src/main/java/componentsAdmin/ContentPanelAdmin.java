package componentsAdmin;

import PainelAdmin.PainelAdmin;

import javax.swing.*;
import java.awt.*;

public class ContentPanelAdmin extends JFrame {
    static JPanel contPanel = new JPanel();
    static Color corPainel = new Color(45, 45, 45);
    public static void configureContentPanel(PainelAdmin admin) {
        contPanel = admin.getContentPanel();

        contPanel.setBackground(corPainel);

    }
}
