package componentsAdmin;

import PainelAdmin.PainelAdmin;

import classesObjetos.Jogo;
import conexaoBD.TesteBD;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
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
    public static FileInputStream fis;  // instanciar objeto para o fluxo de bytes
    public static int tamanho; // armazena o tamanho da imagem (bytes)
    public static byte[] imagemBytes;


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
//        panelJogosCadastrados();

//        showPanel("inicial");
    }

    public static void panelCadastrarJogos(PainelAdmin admin) {
        cadastrarJogosPanel.setLayout(null);
        cadastrarJogosPanel.setBackground(Color.white);

        JLabel label = new JLabel("CADASTRO DE JOGOS");
        label.setBounds(100, 20, 200, 20);
        cadastrarJogosPanel.add(label);

        // label e input IDJogo
        JLabel labelIDJogo = new JLabel("ID: ");
        labelIDJogo.setBounds(50, 60, 100, 20);
        cadastrarJogosPanel.add(labelIDJogo);

        inputIDJogo.setBounds(50, 90, 70, 20);
        cadastrarJogosPanel.add(inputIDJogo);

        // label e input Preço
        JLabel labelPreco = new JLabel("PREÇO (DECIMAL SEPARADO POR .): ");
        labelPreco.setBounds(50, 130, 280, 20);
        cadastrarJogosPanel.add(labelPreco);

        inputPreco.setBounds(50, 160, 100, 20);
        cadastrarJogosPanel.add(inputPreco);

        // label e input Titulo
        JLabel labelTitulo = new JLabel("TÍTULO: ");
        labelTitulo.setBounds(50, 210, 100, 20);
        cadastrarJogosPanel.add(labelTitulo);

        inputTitulo.setBounds(50, 240, 280, 20);
        cadastrarJogosPanel.add(inputTitulo);

        // label e input Genero
        JLabel labelGenero = new JLabel("GÊNERO: ");
        labelGenero.setBounds(50, 260, 100, 20);
        cadastrarJogosPanel.add(labelGenero);

        inputGenero.setBounds(50, 290, 280, 20);
        cadastrarJogosPanel.add(inputGenero);

        // label e input Descricao
        JLabel labelDescricao = new JLabel("DESCRIÇÃO: ");
        labelDescricao.setBounds(50, 330, 100, 20);
        cadastrarJogosPanel.add(labelDescricao);

        JScrollPane scrollPaneDescricao = new JScrollPane(inputDescricao);
        inputDescricao.setLineWrap(true);
        scrollPaneDescricao.setBounds(50, 360, 280, 100);
        cadastrarJogosPanel.add(scrollPaneDescricao);

        // LABEL, BOTAO DE SELECIONAR IMAGEM
        labelImagem.setBounds(500, 30, 200, 250);
        labelImagem.setBorder(new LineBorder(Color.black));
        cadastrarJogosPanel.add(labelImagem);

        buttonUploadImagem.setBounds(500, 290, 200, 30);
        cadastrarJogosPanel.add(buttonUploadImagem);

        buttonUploadImagem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carregarFoto();
            }
        });

        // botao cadastrar
        buttonCadastrar.setBounds(250, 500, 150, 35);
        cadastrarJogosPanel.add(buttonCadastrar);

        buttonCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cadastrarJogoBtn();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


    }

//    public static void panelCadastrarJogos(PainelAdmin admin) {
//        cadastrarJogosPanel.setBackground(Color.WHITE);
//        cadastrarJogosPanel.setLayout(new GridBagLayout());
//        cadastrarJogosPanel.setBorder(new LineBorder(Color.RED, 2));
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.anchor = GridBagConstraints.LINE_START;
//
//        //  COLUNA 0 LINHA 0
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.insets = new Insets(10, 20, 40, 0);
//        cadastrarJogosPanel.add(new JLabel("CADASTRO DE JOGOS"), gbc);
//
//        // label e input IDJOGO
//        gbc.gridwidth = 2;
//        gbc.gridx = 0;
//        gbc.gridy++;    // linha 1
//        gbc.insets = new Insets(0, 20, 10, 0);
//        JLabel labelIDJogo = new JLabel("ID JOGO: ");
//        cadastrarJogosPanel.add(labelIDJogo, gbc);
//
//        gbc.gridx = 0;
//        gbc.gridy++;  // linha 2
//        gbc.insets = new Insets(0, 20, 20, 0);
//        cadastrarJogosPanel.add(inputIDJogo, gbc);
//
//        // label e input titulo
//        gbc.gridx = 0;
//        gbc.gridy++;  // linha 3
//        gbc.insets = new Insets(0, 20, 10, 0);
//        JLabel labelTitulo = new JLabel("TÍTULO:");
//        cadastrarJogosPanel.add(labelTitulo, gbc);
//
//        gbc.gridy++;  // linha 4
//        gbc.insets = new Insets(0, 20, 20, 0);
//        cadastrarJogosPanel.add(inputTitulo, gbc);
//
//        // label e input gênero
//        gbc.gridx = 0;
//        gbc.gridy++; // linha 5
//        gbc.insets = new Insets(0, 20, 10, 0);
//        cadastrarJogosPanel.add(new JLabel("GÊNERO:"), gbc);
//
//        gbc.gridy++; // linha 6
//        gbc.insets = new Insets(0, 20, 20, 0);
//        cadastrarJogosPanel.add(inputGenero, gbc);
//
//        // label e input preço
//        gbc.gridx = 0;
//        gbc.gridy++;  // linha 7
//        gbc.insets = new Insets(0, 20, 10, 0);
//        cadastrarJogosPanel.add(new JLabel("PREÇO (DECIMAL SEPARADO POR .): "), gbc);
//
//        gbc.gridy++;  // linha 8
//        gbc.insets = new Insets(0, 20, 20, 0);
//        cadastrarJogosPanel.add(inputPreco, gbc);
//
//        // label e input descricao
//        gbc.gridx = 0;
//        gbc.gridy++;  // linha 9
//        gbc.insets = new Insets(0, 20, 10, 0);
//        cadastrarJogosPanel.add(new JLabel("DESCRIÇÃO: "), gbc);
//
//        gbc.gridy++;
//        inputDescricao.setLineWrap(true);
//        inputDescricao.setWrapStyleWord(true);
//        gbc.insets = new Insets(0, 20, 20, 0);
//        gbc.fill = GridBagConstraints.BOTH;  // Preenche o espaço vertical e horizontal
//        cadastrarJogosPanel.add(new JScrollPane(inputDescricao), gbc);
//
//        // BOTAO CADASTRAR
//        gbc.gridx = 0;
//        gbc.gridy++;
//        cadastrarJogosPanel.add(buttonCadastrar, gbc);
//
//        // segunda coluna (upload de imagem)
//        gbc.gridx = 2;
//        gbc.gridy = 1;
//        gbc.gridwidth = 1;
//        gbc.fill = GridBagConstraints.NONE;
//        labelImagem.setPreferredSize(new Dimension(100, 150));
//        labelImagem.setOpaque(true);
//        gbc.insets = new Insets(0, 40, 10, 20);
//        JLabel labelImage = new JLabel();
//        labelImage.setBorder(new LineBorder(Color.green));
//        cadastrarJogosPanel.add(labelImage, gbc);
//
//        gbc.gridy++;
//        gbc.insets = new Insets(0, 20, 10, 20);
//        cadastrarJogosPanel.add(inputImagem, gbc);
//
//        gbc.gridy++;
//        cadastrarJogosPanel.add(buttonUploadImagem, gbc);
//        buttonUploadImagem.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                carregarFoto();
//            }
//        });
//
//        buttonCadastrar.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    cadastrarJogoBtn();
//                } catch (IOException | SQLException ex) {
//                    ex.printStackTrace();
//                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar o jogo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
//                }
//            }
//        });
//    }

    public static void carregarFoto() {
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("Selecionar arquivo");
        jfc.setFileFilter(new FileNameExtensionFilter("Arquivo de imagens (*.PNG, *.JPG, *.JPEG)",
                "png", "jpg", "jpeg"));
        int resultado = jfc.showOpenDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            try {
                File file = jfc.getSelectedFile();
                fis = new FileInputStream(file);
                tamanho = (int) file.length();

                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                int readNum;
                while ((readNum = fis.read(buf)) != -1) {
                    bos.write(buf, 0, readNum);
                }
                imagemBytes = bos.toByteArray();

                Image foto = ImageIO.read(file).getScaledInstance(labelImagem.getWidth(), labelImagem.getHeight(),
                        Image.SCALE_SMOOTH);
                labelImagem.setIcon(new ImageIcon(foto));
                labelImagem.updateUI();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public static void cadastrarJogoBtn() throws IOException, SQLException {
        int idJogo = Integer.parseInt(inputIDJogo.getText());
        String titulo = inputTitulo.getText();
        String genero = inputGenero.getText();
        double preco = Double.parseDouble(inputPreco.getText().replace(",", "."));
        String descricao = inputDescricao.getText();

        Jogo jogo = new Jogo(idJogo, titulo, genero, preco, descricao, imagemBytes);
        TesteBD.cadastrarJogo(jogo);
    }

//    public static void panelJogosCadastrados() {
//        jogosCadastradosPanel.setBackground(Color.WHITE);
//        jogosCadastradosPanel.add(new JLabel("CADASTRADOS"));
//    }

    public static void showPanel(String panelName) {
        CardLayout cl = (CardLayout) (contPanel.getLayout());
        cl.show(contPanel, panelName);
    }
}

