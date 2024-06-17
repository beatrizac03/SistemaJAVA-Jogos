package componentsAdmin;

import PainelAdmin.PainelAdmin;

import classesObjetos.Jogo;
import conexaoBD.TesteBD;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;

public class ContentPanelAdmin extends JFrame {
    static JPanel contPanel = new JPanel(new CardLayout());
    static Color corPainel = new Color(45, 45, 45);
    static JPanel cadastrarJogosPanel = new JPanel();
    static JPanel jogosCadastradosPanel = new JPanel();
    static JButton buttonCadastrar = new JButton("CADASTRAR");
    static JTextField inputIDJogo = new JTextField();
    static JTextField inputTitulo = new JTextField(40);
    static JTextField inputGenero = new JTextField(40);
    static JTextField inputPreco = new JTextField(40);
    static JTextArea inputDescricao = new JTextArea(3, 20);
    static JTextField inputImagem = new JTextField(40);
    static JLabel labelImagem = new JLabel();
    static JButton buttonUploadImagem = new JButton("SELECIONAR IMAGEM");


    public static void configureContentPanel(PainelAdmin admin) {
        contPanel = admin.getContentPanel();
        contPanel.setLayout(new CardLayout());

        JPanel panelInicial = new JPanel();
        panelInicial.setBackground(Color.orange);
        panelInicial.add(new JLabel("INICIO"));

        contPanel.setBackground(corPainel);
        contPanel.add(panelInicial, "inicial");
        contPanel.add(cadastrarJogosPanel, "cadastrarJogos");
        contPanel.add(jogosCadastradosPanel, "jogosCadastrados");

        panelCadastrarJogos(admin);
        panelJogosCadastrados();

        showPanel("inicial");
    }

    public static void panelCadastrarJogos(PainelAdmin admin) {
        cadastrarJogosPanel.setBackground(Color.WHITE);
        cadastrarJogosPanel.setLayout(new GridBagLayout());
        cadastrarJogosPanel.setBorder(new LineBorder(Color.RED, 2));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.LINE_START;

        //  COLUNA 0 LINHA 0
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 20, 40, 0);
        cadastrarJogosPanel.add(new JLabel("CADASTRO DE JOGOS"), gbc);

        // label e input IDJOGO
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy++;    // linha 1
        gbc.insets = new Insets(0, 20, 10, 0);
        JLabel labelIDJogo = new JLabel("ID JOGO: ");
        cadastrarJogosPanel.add(labelIDJogo, gbc);

        gbc.gridx = 0;
        gbc.gridy++;  // linha 2
        gbc.insets = new Insets(0, 20, 20, 0);
        cadastrarJogosPanel.add(inputIDJogo, gbc);

        // label e input titulo
        gbc.gridx = 0;
        gbc.gridy++;  // linha 3
        gbc.insets = new Insets(0, 20, 10, 0);
        JLabel labelTitulo = new JLabel("TÍTULO:");
        cadastrarJogosPanel.add(labelTitulo, gbc);

        gbc.gridy++;  // linha 4
        gbc.insets = new Insets(0, 20, 20,0);
        cadastrarJogosPanel.add(inputTitulo, gbc);

        // label e input gênero
        gbc.gridx = 0;
        gbc.gridy++; // linha 5
        gbc.insets = new Insets(0, 20, 10, 0);
        cadastrarJogosPanel.add(new JLabel("GÊNERO:"), gbc);

        gbc.gridy++; // linha 6
        gbc.insets = new Insets(0, 20, 20, 0);
        cadastrarJogosPanel.add(inputGenero, gbc);

        // label e input preço
        gbc.gridx = 0;
        gbc.gridy++;  // linha 7
        gbc.insets = new Insets(0, 20, 10, 0);
        cadastrarJogosPanel.add(new JLabel("PREÇO (DECIMAL SEPARADO POR .): "), gbc);

        gbc.gridy++;  // linha 8
        gbc.insets = new Insets(0, 20, 20, 0);
        cadastrarJogosPanel.add(inputPreco, gbc);

        // label e input descricao
        gbc.gridx = 0;
        gbc.gridy++;  // linha 9
        gbc.insets = new Insets(0, 20, 10, 0);
        cadastrarJogosPanel.add(new JLabel("DESCRIÇÃO: "), gbc);

        gbc.gridy++;
        inputDescricao.setLineWrap(true);
        inputDescricao.setWrapStyleWord(true);
        gbc.insets = new Insets(0, 20, 20, 0);
        gbc.fill = GridBagConstraints.BOTH;  // Preenche o espaço vertical e horizontal
        cadastrarJogosPanel.add(new JScrollPane(inputDescricao), gbc);

        // BOTAO CADASTRAR
        gbc.gridx = 0;
        gbc.gridy++;
        cadastrarJogosPanel.add(buttonCadastrar, gbc);

        // segunda coluna (upload de imagem)
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        labelImagem.setPreferredSize(new Dimension(50, 100));
        labelImagem.setOpaque(true);
        gbc.insets = new Insets(0, 40, 10, 20);
        JLabel labelImage = new JLabel();
        labelImage.setBorder(new LineBorder(Color.green));
        cadastrarJogosPanel.add(labelImage, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(0, 20, 10, 20);
        cadastrarJogosPanel.add(inputImagem, gbc);

        gbc.gridy++;
        cadastrarJogosPanel.add(buttonUploadImagem, gbc);
        buttonUploadImagem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(null);
                File f = chooser.getSelectedFile();
                String pathImage = f.getAbsolutePath();
                try {
                    BufferedImage bi = ImageIO.read(new File(pathImage));
                    Image imgRedimensionada = bi.getScaledInstance(50, 100, Image.SCALE_SMOOTH);
                    ImageIcon icon = new ImageIcon(imgRedimensionada);
                    labelImage.setIcon(icon);
                    TesteBD.path2 = pathImage;
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        buttonCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cadastrarJogo();
                } catch (IOException | SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar o jogo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void cadastrarJogo() throws IOException, SQLException {
        int idJogo = Integer.parseInt(inputIDJogo.getText());
        String titulo = inputTitulo.getText();
        String genero = inputGenero.getText();
        double preco = Double.parseDouble(inputPreco.getText());
        String descricao = inputDescricao.getText();
        byte[] imagem = inputImagem.getText().getBytes();

        Jogo jogo = new Jogo(idJogo, titulo, genero, preco, descricao, imagem);
        TesteBD.cadastrarJogo(jogo);
    }

    public static void panelJogosCadastrados() {
        jogosCadastradosPanel.setBackground(Color.WHITE);
        jogosCadastradosPanel.add(new JLabel("CADASTRADOS"));
    }

    public static void showPanel(String panelName) {
        CardLayout cl = (CardLayout) (contPanel.getLayout());
        cl.show(contPanel, panelName);
    }
}
