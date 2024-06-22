package PainelCliente.componentsCliente;

import classesObjetos.Jogo;
import conexaoBD.ConexaoBD;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class JogosPanel extends JPanel {
    public JogosPanel() {
        setLayout(new BorderLayout()); // Usando BorderLayout para melhor organização
        JLabel label1 = new JLabel("CATÁLOGO DE JOGOS");
        label1.setHorizontalAlignment(SwingConstants.CENTER); // Centralizando o label
        add(label1, BorderLayout.NORTH);

        configurarCardsPanel();
    }

    public void configurarCardsPanel() {
        List<Jogo> jogos = ConexaoBD.getJogosCadastrados();

        JPanel cardsPanel = new JPanel(new GridLayout(0, 2, 15, 10));
        cardsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (Jogo jogo : jogos) {
//            JPanel card = new JPanel(new BorderLayout());
//            card.setPreferredSize(new Dimension(100, 250));
            JPanel card = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;

            JLabel imgLabel = new JLabel();
            imgLabel.setPreferredSize(new Dimension(150, 200));
            imgLabel.setBorder(new LineBorder(Color.red));
            ImageIcon icon = new ImageIcon(jogo.getImagem());
            Image img = icon.getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(img);
            imgLabel.setIcon(scaledIcon);
            card.add(imgLabel, gbc);

            gbc.gridy++;

            JLabel idLabel = new JLabel("ID: " + jogo.getIdJogo());
            card.add(idLabel, gbc);

            gbc.gridy++;
            JLabel tituloLabel = new JLabel(jogo.getTitulo());
            card.add(tituloLabel, gbc);

            gbc.gridy++;
            JLabel generoLabel = new JLabel("Gênero: " + jogo.getGenero());
            card.add(generoLabel, gbc);

            gbc.gridy++;
            JLabel heartLabel = new JLabel();
            heartLabel.setPreferredSize(new Dimension(25, 25));
            heartLabel.setBorder(new LineBorder(Color.gray));
            ImageIcon heartIcon = new ImageIcon("src/main/java/PainelCliente/images/heart.png");
            Image imgH = heartIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            ImageIcon scaledHeartIcon = new ImageIcon(imgH);
            heartLabel.setIcon(scaledHeartIcon);
            card.add(heartLabel, gbc);

            gbc.gridx = 1;
            JLabel cartLabel = new JLabel();
            cartLabel.setPreferredSize(new Dimension(25, 25));
            cartLabel.setBorder(new LineBorder(Color.gray));
            ImageIcon cartIcon = new ImageIcon("src/main/java/PainelCliente/images/shopping-cart.png");
            Image imgC = cartIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            ImageIcon scaledCartIcon = new ImageIcon(imgC);
            cartLabel.setIcon(scaledCartIcon);
            card.add(cartLabel, gbc);

            gbc.gridx = 2;
            JButton btnComprar = new JButton("COMPRAR");
            card.add(btnComprar, gbc);
            btnComprar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    comprarJogo();
                }
            });

//            gbc.gridy++;
//            JLabel descricaoLabel = new JLabel(jogo.getDescricao());
//            card.add(descricaoLabel, gbc);

//            card.add(tituloLabel, BorderLayout.CENTER);
//            card.add(idLabel, BorderLayout.SOUTH);
//            card.add(imgLabel, BorderLayout.NORTH);
            card.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            cardsPanel.add(card);
        }

        JScrollPane scrollPane = new JScrollPane(cardsPanel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void comprarJogo() {
        JOptionPane.showMessageDialog(null, "Compra de jogo realizada com sucesso!", "COMPRA",
                JOptionPane.PLAIN_MESSAGE);
    }
}
