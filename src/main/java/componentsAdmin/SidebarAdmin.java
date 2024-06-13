package componentsAdmin;

import PainelAdmin.PainelAdmin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SidebarAdmin extends JFrame {
    static JPanel sidebar = new JPanel();
    static Color corSidebar = new Color(59, 12, 145);
    public static void configureSidebar(PainelAdmin admin) {
        sidebar = admin.getSidebar();
        sidebar.setBackground(corSidebar);
        sidebar.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(150, 0, 50, 0);

        JLabel label1 = new JLabel("CADASTRAR JOGOS");
        label1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Deu erro", "erro", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JLabel label2 = new JLabel("JOGOS CADASTRADOS");

        label1.setForeground(Color.WHITE);
        label2.setForeground(Color.WHITE);

        sidebar.add(label1, gbc);
        sidebar.add(label2, gbc);
    }
}
