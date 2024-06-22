package PainelCliente.componentsCliente;

import classesObjetos.Jogo;
import conexaoBD.ConexaoBD;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class JogosPanel extends JPanel {
    private List<Jogo> jogos;
    private JLabel labelNomeJogo;
    private JFrame frameModalCompra = new JFrame("Pagamento - GameHUB");
    public static List<Jogo> jogosFavoritos = new ArrayList<>();

    public JogosPanel() {
        setLayout(new BorderLayout()); // Usando BorderLayout para melhor organização
        JLabel label1 = new JLabel("CATÁLOGO DE JOGOS");
        label1.setHorizontalAlignment(SwingConstants.CENTER); // Centralizando o label
        add(label1, BorderLayout.NORTH);

        configurarCardsPanel();
    }

    public void configurarCardsPanel() {
        jogos = ConexaoBD.getJogosCadastrados();

        JPanel cardsPanel = new JPanel(new GridLayout(0, 3, 20, 20));
        cardsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int i = 0; i < jogos.size(); i++) {
            Jogo jogo = jogos.get(i);
            JPanel card = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            card.setPreferredSize(new Dimension(150, 350));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 3;
            gbc.anchor = GridBagConstraints.CENTER;

            JLabel imgLabel = new JLabel();
            imgLabel.setPreferredSize(new Dimension(150, 200));
            imgLabel.setBorder(new LineBorder(Color.red));
            ImageIcon icon = new ImageIcon(jogo.getImagem());
            Image img = icon.getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(img);
            imgLabel.setIcon(scaledIcon);
            card.add(imgLabel, gbc);

            gbc.gridy++;
            gbc.gridwidth = 3;
            gbc.anchor = GridBagConstraints.CENTER;

            JLabel idLabel = new JLabel("ID: " + jogo.getIdJogo());
            card.add(idLabel, gbc);

            gbc.gridy++;
            JLabel tituloLabel = new JLabel(jogo.getTitulo());
            card.add(tituloLabel, gbc);

            gbc.gridy++;
            JLabel generoLabel = new JLabel("Gênero: " + jogo.getGenero());
            card.add(generoLabel, gbc);

            gbc.gridy++;
            gbc.gridwidth = 1;
            gbc.anchor = GridBagConstraints.CENTER;
            JLabel heartLabel = new JLabel();
            heartLabel.setPreferredSize(new Dimension(25, 25));
            ImageIcon heartIcon = new ImageIcon("src/main/java/PainelCliente/images/heart.png");
            Image imgH = heartIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            ImageIcon scaledHeartIcon = new ImageIcon(imgH);
            heartLabel.setIcon(scaledHeartIcon);
            card.add(heartLabel, gbc);
            heartLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    jogosFavoritos.add(jogo);
                    System.out.println(jogosFavoritos);
                }
            });

            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.CENTER;
            JLabel cartLabel = new JLabel();
            cartLabel.setPreferredSize(new Dimension(25, 25));
            ImageIcon cartIcon = new ImageIcon("src/main/java/PainelCliente/images/shopping-cart.png");
            Image imgC = cartIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            ImageIcon scaledCartIcon = new ImageIcon(imgC);
            cartLabel.setIcon(scaledCartIcon);
            card.add(cartLabel, gbc);

            gbc.gridx = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            JButton btnComprar = new JButton("COMPRAR");
            card.add(btnComprar, gbc);
            int index = i;
            btnComprar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    modalComprarJogo(index);
                    labelNomeJogo.setText(jogos.get(index).getTitulo().toUpperCase());
                }

            });

            card.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            cardsPanel.add(card);
        }

        JScrollPane scrollPane = new JScrollPane(cardsPanel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVisible(true);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void modalComprarJogo(int index) {
        frameModalCompra.setSize(400, 500);
        frameModalCompra.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frameModalCompra.setLocationRelativeTo(null);
        JPanel panelModalCJ = new JPanel();
        panelModalCJ.setLayout(null);
        panelModalCJ.setSize(400, 500);

        labelNomeJogo = new JLabel();
        labelNomeJogo.setText(jogos.get(index).getTitulo().toUpperCase());
        labelNomeJogo.setBounds(100, 20, 300, 30);
        labelNomeJogo.setBorder(new LineBorder(Color.black));
        panelModalCJ.add(labelNomeJogo);

        JLabel labelFormaPag = new JLabel("Escolha a forma de pagamento: ");
        labelFormaPag.setBounds(50, 100, 300, 30);
        panelModalCJ.add(labelFormaPag);

        JRadioButton pix, cartaoCredito, cartaoDebito;
        pix = new JRadioButton("PIX", false);
        pix.setBounds(50, 140, 200, 25);
        cartaoCredito = new JRadioButton("Cartão de Crédito", false);
        cartaoCredito.setBounds(50, 170, 200, 25);
        cartaoDebito = new JRadioButton("Cartão de Débito", false);
        cartaoDebito.setBounds(50, 200, 200, 25);

        ButtonGroup group = new ButtonGroup();
        group.add(pix);
        group.add(cartaoDebito);
        group.add(cartaoCredito);

        panelModalCJ.add(pix);
        panelModalCJ.add(cartaoCredito);
        panelModalCJ.add(cartaoDebito);


        Jogo jogo = jogos.get(index);


//        JOptionPane.showMessageDialog(null, "Compra do jogo " + jogo.getTitulo() + " realizada com sucesso!", "COMPRA",
//                JOptionPane.PLAIN_MESSAGE);

        frameModalCompra.add(panelModalCJ);
        frameModalCompra.setVisible(true);
        System.out.println(index);
        System.out.println(jogos.get(index).getTitulo());

        frameModalCompra.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                labelNomeJogo.setText("");
            }
        });
    }
}
