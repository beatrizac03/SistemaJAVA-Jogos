package PainelCliente.componentsAdmin;

import classesObjetos.Jogo;
import conexaoBD.TesteBD;

import javax.swing.*;
import java.awt.*;
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
        List<Jogo> jogos = TesteBD.getJogosCadastrados();

        JPanel cardsPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        cardsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (Jogo jogo : jogos) {
            JPanel card = new JPanel(new BorderLayout());
            JLabel tituloLabel = new JLabel(jogo.getTitulo());
            JLabel idLabel = new JLabel("ID do Jogo: " + jogo.getGenero());

            card.add(tituloLabel, BorderLayout.CENTER);
            card.add(idLabel, BorderLayout.SOUTH);
            card.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            cardsPanel.add(card);
        }

        JScrollPane scrollPane = new JScrollPane(cardsPanel);
        add(scrollPane, BorderLayout.CENTER);
    }
}
