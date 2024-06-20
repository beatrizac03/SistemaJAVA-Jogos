package PainelCliente;

import PainelAdmin.FontManager;
import PainelCliente.componentsAdmin.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PainelCliente extends JFrame {
    private JPanel mainPanel = new JPanel(null);
    private JPanel sidebarCliente = new JPanel();
    private JPanel contentPanel = new JPanel();
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
        btnJogos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        sidebarCliente.add(btnJogos);
        btnJogos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showCard("jogos");
            }
        });

        btnFavoritos.setBounds(50, 140, 100, 20);
        btnFavoritos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        sidebarCliente.add(btnFavoritos);
        btnFavoritos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showCard("favoritos");
            }
        });

        btnPedidos.setBounds(50, 180, 100, 20);
        btnPedidos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        sidebarCliente.add(btnPedidos);
        btnPedidos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showCard("pedidos");
            }
        });

        this.add(mainPanel);
    }

    public void configurePanels() {
        contentPanel.setLayout(new CardLayout());
        contentPanel.setBorder(new LineBorder(Color.CYAN));
        contentPanel.setBounds(270, 0, 730, 600);

        contentPanel.add(new HomePanel(), "perfil");
        contentPanel.add(new JogosPanel(), "jogos");
        contentPanel.add(new FavoritosPanel(), "favoritos");
        contentPanel.add(new PedidosPanel(), "pedidos");

        mainPanel.add(contentPanel);
    }

    private void showCard(String cardName) {
        CardLayout cl = (CardLayout) (contentPanel.getLayout());
        cl.show(contentPanel, cardName);
    }

    public static void main(String[] args) {
        new PainelCliente();
    }
}
