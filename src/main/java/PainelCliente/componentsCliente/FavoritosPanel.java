package PainelCliente.componentsCliente;

import javax.swing.*;

public class FavoritosPanel extends JPanel {
    public FavoritosPanel() {
        this.setLayout(null);
        JLabel label = new JLabel("LABELFAV");
        label.setBounds(20, 20, 100, 20);

        this.add(label);
    }
}
