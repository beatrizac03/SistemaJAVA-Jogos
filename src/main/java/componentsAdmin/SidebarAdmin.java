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
        gbc.gridy = 0;

        gbc.insets = new Insets(0, 0, 20, 0);
        JLabel label1 = new JLabel("CADASTRAR JOGOS");
        label1.setForeground(Color.WHITE);
        label1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sidebar.add(label1, gbc);
        label1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                admin.showCadastrarJogosPanel();

            }
        });

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 100, 0);
        JLabel label2 = new JLabel("JOGOS CADASTRADOS");
        label2.setForeground(Color.WHITE);
        label2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sidebar.add(label2, gbc);
        label2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                admin.showJogosCadastradosPanel();
            }
        });

        gbc.gridy = 5;
        gbc.insets = new Insets(70, 0, 0, 20);
        JLabel label3 = new JLabel("Sair");
        label3.setForeground(Color.RED);
        sidebar.add(label3, gbc);
    }
}
