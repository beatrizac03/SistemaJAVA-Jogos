package PainelAdmin.componentsAdmin;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileInputStream;

import classesObjetos.Jogo;
import conexaoBD.ConexaoBD;

public class JogosCadastradosPanel extends JPanel {
    public JPanel jogosCadastradosPanel = new JPanel();
    private FileInputStream fis;  // instanciar objeto para o fluxo de bytes
    private int tamanho; // armazena o tamanho da imagem (bytes)
    private byte[] imagemBytes;
    public static JTable tabelaJogos;
    public DefaultTableModel modeloTabela;

    public JogosCadastradosPanel() {
        setLayout(new BorderLayout());
        setBorder(new LineBorder(Color.red));
        configureCadastrarJogosPanel();
    }

    public void configureCadastrarJogosPanel() {
        jogosCadastradosPanel.setBackground(Color.WHITE);
        jogosCadastradosPanel.setLayout(null);

        JLabel label1 = new JLabel("JOGOS CADASTRADOS");
        label1.setBounds(50, 20, 200, 20);
        jogosCadastradosPanel.add(label1);

        String[] colunas = {"ID", "Preço", "Título", "Gênero", "Descrição" };
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaJogos = new JTable(modeloTabela);

        JScrollPane scrollPane = new JScrollPane(tabelaJogos);
        scrollPane.setBounds(20, 60, 600, 400);
        jogosCadastradosPanel.add(scrollPane);

        carregarDadosDB();
        add(jogosCadastradosPanel, BorderLayout.CENTER);
    }

    public static void carregarDadosDB() {
        DefaultTableModel model = (DefaultTableModel) tabelaJogos.getModel();
        ConexaoBD teste = new ConexaoBD();

        for(Jogo j: teste.getJogosCadastrados()) {
            String preco = String.valueOf(j.getPreco()).replace(".", ",");

            model.addRow(new Object[]{
                    j.getIdJogo(),
                    "R$ " + preco,
                    j.getTitulo(),
                    j.getGenero(),
                    j.getDescricao()
            });
        }
    }

}
