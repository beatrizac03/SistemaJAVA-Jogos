package PainelCliente;

import PainelAdmin.FontManager;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PainelCliente extends JFrame {
    private JPanel mainPanel = new JPanel(null);
    private JPanel sidebarCliente = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JTabbedPane tabbedPane = new JTabbedPane();
    public JLabel btnPerfil = new JLabel("MEU PERFIL");
    public JLabel btnJogos = new JLabel("JOGOS");
    public JLabel btnFavoritos = new JLabel("FAVORITOS");
    public JLabel btnPedidos = new JLabel("PEDIDOS");
    public JLabel btnSair = new JLabel("Sair");

    public PainelCliente() {
        this.setTitle("GameHUB");
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        mainPanel.setSize(1000, 600);

        FontManager.manageFont();
        configureSidebar();
        configurePanels();
        this.setVisible(true);
    }

    public void configureSidebar() {
        // configurando sidebar
        sidebarCliente.setBounds(0, 0, 270, this.getHeight());
        sidebarCliente.setLayout(null);
        sidebarCliente.setBackground(Color.BLUE);

        mainPanel.add(sidebarCliente);

        btnJogos.setBounds(50, 100, 100, 20);
        sidebarCliente.add(btnJogos);

        btnFavoritos.setBounds(50, 140, 100, 20);
        sidebarCliente.add(btnFavoritos);

        btnPedidos.setBounds(50, 180, 100, 20);
        sidebarCliente.add(btnPedidos);

        this.add(mainPanel);
    }

    public void configurePanels() {
//        tabbedPane.add("Meu Perfil", new HomePanel());
//        tabbedPane.add("Jogos", new JogosPanel());
//        tabbedPane.add("Favoritos", new FavoritosPanel());
//        tabbedPane.add("Meus pedidos", new PedidosPanel());

        tabbedPane.setBounds(270, 0, 730, 600);
        tabbedPane.setBorder(new LineBorder(Color.red));
        mainPanel.add(tabbedPane);
    }

    public static void main(String[] args) {
        new PainelCliente();
    }
}
