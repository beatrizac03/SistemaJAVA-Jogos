package PainelCliente.componentsAdmin;

import javax.swing.*;

public class PedidosPanel extends JPanel {
    public PedidosPanel() {
        this.setLayout(null);
        JLabel label = new JLabel("pedidos");
        label.setBounds(20, 20, 100, 20);

        this.add(label);
    }
}
