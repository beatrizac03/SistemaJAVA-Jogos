package PainelCliente.componentsCliente;

import classesObjetos.Jogo;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

public class FavoritosPanel extends JPanel {
    public static List<Jogo> jogosFav = JogosPanel.jogosFavoritos;
    public FavoritosPanel() {
        this.setLayout(null);
        JLabel label = new JLabel("FAVORITOS");
        label.setBounds(20, 20, 100, 20);

        configureFavoritosPanel();
        this.add(label);
    }

    public void configureFavoritosPanel() {
        JPanel cardsPanel = new JPanel(new GridLayout(0, 3, 20, 20));
        cardsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for(int i = 0; i < jogosFav.size(); i++) {
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
        }

    }
}
