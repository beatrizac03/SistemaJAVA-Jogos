package PainelCliente.componentsCliente;

import javax.swing.*;

public class MeusJogos extends JPanel {
    public MeusJogos() {
        this.setLayout(null);
        JLabel label = new JLabel("pedidos");
        label.setBounds(20, 20, 100, 20);

        this.add(label);
    }
}
