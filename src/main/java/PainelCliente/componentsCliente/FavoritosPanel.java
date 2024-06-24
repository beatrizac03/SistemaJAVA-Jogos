package PainelCliente.componentsCliente;

import classesObjetos.*;
import paletaDeCores.Cores;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

public class FavoritosPanel extends JPanel implements Favoritos.FavoritosListener {
    private JPanel cardsPanel;

    public FavoritosPanel() {
        setLayout(new BorderLayout());
        JLabel labelFav = new JLabel("FAVORITOS");
        labelFav.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelFav, BorderLayout.NORTH);

        cardsPanel = new JPanel();
        cardsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Ajuste para FlowLayout com alinhamento à esquerda
        cardsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane2 = new JScrollPane(cardsPanel);
        scrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane2, BorderLayout.CENTER);

        Favoritos.adicionarListener(this);
        updateCardsPanel(Favoritos.getJogosFavoritos());
    }

    public void updateCardsPanel(List<Jogo> favoritos) {
        cardsPanel.removeAll();

        for (int i = 0; i < favoritos.size(); i++) {
            Jogo jogo = favoritos.get(i);
            JPanel card = new JPanel(new GridBagLayout());
            card.setPreferredSize(new Dimension(180, 280));
            card.setMaximumSize(new Dimension(180, 280));
            card.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            card.setBackground(Cores.getOrangeS());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            gbc.anchor = GridBagConstraints.CENTER;

            JLabel imgLabel = new JLabel();
            ImageIcon icon = new ImageIcon(jogo.getImagem());
            Image img = icon.getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(img);
            imgLabel.setIcon(scaledIcon);
            card.add(imgLabel, gbc);

            gbc.gridy++;
            gbc.anchor = GridBagConstraints.WEST;

            JLabel idLabel = new JLabel("ID: " + jogo.getIdJogo());
            card.add(idLabel, gbc);

            gbc.gridy++;
            JLabel tituloLabel = new JLabel(jogo.getTitulo());
            tituloLabel.setFont(new Font("Arial", Font.BOLD, 14));
            card.add(tituloLabel, gbc);

            gbc.gridy++;
            JLabel generoLabel = new JLabel("Gênero: " + jogo.getGenero());
            card.add(generoLabel, gbc);

            cardsPanel.add(card);
        }

        revalidate();
        repaint();
    }

    @Override
    public void favoritosAtualizados(List<Jogo> favoritos) {
        updateCardsPanel(favoritos);
    }
}
