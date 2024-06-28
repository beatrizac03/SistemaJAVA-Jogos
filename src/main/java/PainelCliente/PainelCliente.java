package PainelCliente;

import TelaLogin.TelaLogin;
import tipografia.FontManager;
import PainelCliente.componentsCliente.*;
import com.formdev.flatlaf.FlatIntelliJLaf;
import paletaDeCores.Cores;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Classe PainelCliente: representa e inicializa o conteúdo base para o portal do cliente
 * mainPanel = contém dois outros painéis, sidebarCliente e contentPanel
 * sidebarCliente = possui as labels de clique (labelPerfil, btnJogos, btnFavoritos, btnMeusJogos)
 * ContentPanel = configurado com CardLayout, seu conteúdo muda de acordo com a label clicada. Podendo abrir três outros
 * painéis, JogosPanel, FavoritosPanel, MeuPerfil ou MeusJogos, que estão separados em classes próprias, dentro de componentsCliente
 */

public class PainelCliente extends JFrame {
    private JPanel mainPanel = new JPanel(null);
    private JPanel sidebarCliente = new JPanel();
    private JPanel contentPanel = new JPanel();
    public JLabel labelPerfil = new JLabel();
    public JLabel btnJogos = new JLabel("JOGOS");
    public JLabel btnFavoritos = new JLabel("FAVORITOS");
    public JLabel btnMeusJogos = new JLabel("MEUS JOGOS");
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
        sidebarCliente.setBounds(0, 0, 230, this.getHeight());
        sidebarCliente.setLayout(null);
        sidebarCliente.setBackground(Cores.getPurpleS());

        mainPanel.add(sidebarCliente);

        labelPerfil.setBounds(50, 20, 35, 35);
        ImageIcon icon = new ImageIcon("src/main/java/PainelCliente/images/profile-user.png");
        Image img = icon.getImage().getScaledInstance(labelPerfil.getWidth(), labelPerfil.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(img);
        labelPerfil.setIcon(scaledIcon);
        sidebarCliente.add(labelPerfil);
        labelPerfil.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });

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

        btnMeusJogos.setBounds(50, 180, 100, 20);
        btnMeusJogos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        sidebarCliente.add(btnMeusJogos);
        btnMeusJogos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showCard("meusJogos");
            }
        });

        btnSair.setBounds(50, 500, 100, 20);
        btnSair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        sidebarCliente.add(btnSair);
        btnSair.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new TelaLogin();
            }
        });

        this.add(mainPanel);
    }

    public void configurePanels() {
        contentPanel.setLayout(new CardLayout());
        contentPanel.setBorder(new LineBorder(Cores.getBlackL()));
        contentPanel.setBounds(230, 0, 770, 600);

        contentPanel.add(new MeuPerfil(), "perfil");
        contentPanel.add(new JogosPanel(), "jogos");
        contentPanel.add(new FavoritosPanel(), "favoritos");
        contentPanel.add(new MeusJogos(), "meusJogos");

        mainPanel.add(contentPanel);
    }

    private void showCard(String cardName) {
        CardLayout cl = (CardLayout) (contentPanel.getLayout());
        cl.show(contentPanel, cardName);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception e) {
            e.getMessage();
        }
        new PainelCliente();
    }
}
