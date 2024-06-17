package componentsAdmin;

import PainelAdmin.PainelAdmin;

import classesObjetos.Jogo;
import conexaoBD.TesteBD;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    static File imagemSelecionada;
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

        // segunda coluna (upload de imagem)
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 40, 10, 20);
        cadastrarJogosPanel.add(new JLabel("IMAGEM:"), gbc);

        gbc.gridy++;
        gbc.insets = new Insets(0, 20, 10, 20);
        cadastrarJogosPanel.add(inputImagem, gbc);

        gbc.gridy++;
        cadastrarJogosPanel.add(buttonUploadImagem, gbc);
        buttonUploadImagem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    imagemSelecionada = fileChooser.getSelectedFile();
                    inputImagem.setText(imagemSelecionada.getAbsolutePath());
                    ImageIcon icon = new ImageIcon(imagemSelecionada.getAbsolutePath());
                    Image image = icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
                    labelImagem.setIcon(new ImageIcon(image));
                }
            }
        });

        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        labelImagem.setPreferredSize(new Dimension(200, 150));
        labelImagem.setBorder(new LineBorder(Color.green));
        labelImagem.setOpaque(true);
        labelImagem.setBackground(Color.gray);
        cadastrarJogosPanel.add(labelImagem, gbc);


//        // LABEL E INPUT IMAGEM
//
//        gbc.gridy = 0;
//        gbc.gridx = 15;
//        gbc.insets = new Insets(0, 0, 5, 0);
//        labelImagem.setPreferredSize(new Dimension(50, 200));
//        labelImagem.setBorder(new LineBorder(Color.green));
//        labelImagem.setOpaque(true);
//        labelImagem.setBackground(Color.gray);
//        cadastrarJogosPanel.add(labelImagem, gbc);
//
//        gbc.gridy++;
//        gbc.gridx = 15;
//        gbc.insets = new Insets(0, 0, 20, 0);
//        cadastrarJogosPanel.add(inputImagem, gbc);
//
//        gbc.gridy++;
//        gbc.gridx = 15;
//        cadastrarJogosPanel.add(buttonUploadImagem, gbc);
//        buttonUploadImagem.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JFileChooser fileChooser = new JFileChooser();
//                int returnValue = fileChooser.showOpenDialog(null);
//                if (returnValue == JFileChooser.APPROVE_OPTION) {
//                    imagemSelecionada = fileChooser.getSelectedFile();
//                    inputImagem.setText(imagemSelecionada.getAbsolutePath());
//                    ImageIcon icon = new ImageIcon(imagemSelecionada.getAbsolutePath());
//                    Image image = icon.getImage().getScaledInstance(labelImagem.getWidth(), labelImagem.getHeight(), Image.SCALE_SMOOTH);
//                    labelImagem.setIcon(new ImageIcon(image));
//                }
//            }
//        });



//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.anchor = GridBagConstraints.NORTHWEST;
//        gbc.weightx = 1;
//
//        gbc.gridx = 0;   // define a coluna
//        gbc.gridy = 0;   // define a linha
//        gbc.gridwidth = 2;   // define quantas colunas o componente vai ocupar
//        gbc.insets = new Insets(10, 10, 40, 10);
//        cadastrarJogosPanel.add(new JLabel("CADASTRO DE JOGOS"), gbc);
//
//        // label IDJogo
//        gbc.gridy = 1;
//        JLabel labelIDJogo = new JLabel("ID JOGO: ");
//        gbc.insets = new Insets(0, 0, 5, 0);
//        cadastrarJogosPanel.add(labelIDJogo, gbc);
//
//        // input IDJogo
//        gbc.gridy++;
//        gbc.insets = new Insets(0, 0, 20, 0);
//        cadastrarJogosPanel.add(inputIDJogo, gbc);
//
//        // Label "Título"
//        gbc.gridy++;
//        JLabel labelTitulo = new JLabel("TÍTULO:");
//        gbc.insets = new Insets(0, 0, 5, 0);
//        cadastrarJogosPanel.add(labelTitulo, gbc);
//
//        // Input "Título"
//        gbc.gridy++;
//        gbc.insets = new Insets(0, 0, 20, 0);
//        cadastrarJogosPanel.add(inputTitulo, gbc);
//
//        // Label "Desenvolvedores"
//        gbc.gridy++;
//        gbc.insets = new Insets(0, 0, 5, 0);
//        cadastrarJogosPanel.add(new JLabel("GÊNERO:"), gbc);
//
//        // Input "Desenvolvedores"
//        gbc.gridy++;
//        gbc.insets = new Insets(0, 0, 20, 0);
//        cadastrarJogosPanel.add(inputGenero, gbc);
//
//        // Label "Preço"
//        gbc.gridy++;
//        gbc.insets = new Insets(0, 0, 5, 0);
//        cadastrarJogosPanel.add(new JLabel("PREÇO:"), gbc);
//
//        // Input "Preço"
//        gbc.gridy++;
//        gbc.insets = new Insets(0, 0, 20, 0);
//        cadastrarJogosPanel.add(inputPreco, gbc);
//
//        // Label "Descrição"
//        gbc.gridy++;
//        gbc.insets = new Insets(0, 0, 5, 0);
//        cadastrarJogosPanel.add(new JLabel("DESCRIÇÃO:"), gbc);
//
//        // Input "Descrição"
//        gbc.gridy++;
//        inputDescricao.setLineWrap(true);
//        inputDescricao.setWrapStyleWord(true);
//        gbc.insets = new Insets(0, 0, 20, 0);
//        gbc.fill = GridBagConstraints.BOTH;  // Preenche o espaço vertical e horizontal
//        cadastrarJogosPanel.add(new JScrollPane(inputDescricao), gbc);
//
//        // Label "Imagem"
//        gbc.gridy = 0;
//        gbc.gridx = 15;
//        gbc.insets = new Insets(0, 0, 5, 0);
//        labelImagem.setPreferredSize(new Dimension(50, 200));
//        labelImagem.setBorder(new LineBorder(Color.green));
//        labelImagem.setOpaque(true);
//        labelImagem.setBackground(Color.gray);
//        cadastrarJogosPanel.add(labelImagem, gbc);
//
//        gbc.gridx = 0;
//
//        // Input "Imagem"
//        gbc.gridy++;
//        gbc.gridx = 15;
//        gbc.insets = new Insets(0, 0, 20, 0);
//        cadastrarJogosPanel.add(inputImagem, gbc);
//
//        gbc.gridy++;
//        gbc.gridx = 15;
//        cadastrarJogosPanel.add(buttonUploadImagem, gbc);
//        buttonUploadImagem.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JFileChooser fileChooser = new JFileChooser();
//                int returnValue = fileChooser.showOpenDialog(null);
//                if (returnValue == JFileChooser.APPROVE_OPTION) {
//                    imagemSelecionada = fileChooser.getSelectedFile();
//                    inputImagem.setText(imagemSelecionada.getAbsolutePath());
//                    ImageIcon icon = new ImageIcon(imagemSelecionada.getAbsolutePath());
//                    Image image = icon.getImage().getScaledInstance(labelImagem.getWidth(), labelImagem.getHeight(), Image.SCALE_SMOOTH);
//                    labelImagem.setIcon(new ImageIcon(image));
//                }
//            }
//        });
//
//        gbc.gridx = 0;
//
//        buttonCadastrar.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    cadastrarJogo();
//                } catch (IOException | SQLException ex) {
//                    ex.printStackTrace();
//                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar o jogo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
//                }
//            }
//        });
//        gbc.gridy++;
//        cadastrarJogosPanel.add(buttonCadastrar, gbc);
    }

    public static void cadastrarJogo() throws IOException, SQLException {
        int idJogo = Integer.parseInt(inputIDJogo.getText());
        String titulo = inputTitulo.getText();
        String genero = inputGenero.getText();
        double preco = Double.parseDouble(inputPreco.getText());
        String descricao = inputDescricao.getText();
        String imagem = inputImagem.getText();

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
