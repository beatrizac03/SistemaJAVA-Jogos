package PainelAdmin.componentsAdmin;

import classesObjetos.Jogo;
import conexaoBD.ConexaoBD;

import PainelAdmin.PainelAdmin;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CadastrarJogosPanel extends JPanel {
    public JPanel cadastrarJogosPanel = new JPanel(null);
    static Color corPainel = new Color(45, 45, 45);
    static JButton buttonCadastrar = new JButton("CADASTRAR");
    static JTextField inputIDJogo = new JTextField();
    static JTextField inputTitulo = new JTextField(40);
    static JTextField inputGenero = new JTextField(40);
    static JTextField inputPreco = new JTextField(40);
    static JTextArea inputDescricao = new JTextArea(3, 20);
    static JLabel labelImagem = new JLabel();
    static JButton buttonUploadImagem = new JButton("SELECIONAR IMAGEM");
    public static FileInputStream fis;  // instanciar objeto para o fluxo de bytes
    public static int tamanho; // armazena o tamanho da imagem (bytes)
    public static byte[] imagemBytes;

    public CadastrarJogosPanel() {
        setLayout(new BorderLayout());
        setBorder(new LineBorder(Color.black));
        configureCadastrarJogosPanel();
    }

    public void configureCadastrarJogosPanel() {
        cadastrarJogosPanel.setLayout(null);
        cadastrarJogosPanel.setBackground(Color.white);

        JLabel label = new JLabel("CADASTRO DE JOGOS");
        label.setBounds(50, 20, 200, 20);
        cadastrarJogosPanel.add(label);

        // label e input IDJogo
        JLabel labelIDJogo = new JLabel("ID: ");
        labelIDJogo.setBounds(50, 60, 100, 20);   // 15 de espaço entre id e input
        cadastrarJogosPanel.add(labelIDJogo);

        inputIDJogo.setBounds(50, 85, 70, 25);
        cadastrarJogosPanel.add(inputIDJogo);

        // label e input Preço                     //  20 de espaço entre o ultimo id e outro label
        JLabel labelPreco = new JLabel("PREÇO: ");
        labelPreco.setBounds(50, 130, 280, 20);
        cadastrarJogosPanel.add(labelPreco);

        inputPreco.setBounds(50, 165, 100, 25);
        cadastrarJogosPanel.add(inputPreco);

        // label e input Titulo
        JLabel labelTitulo = new JLabel("TÍTULO: ");
        labelTitulo.setBounds(50, 210, 100, 20);
        cadastrarJogosPanel.add(labelTitulo);

        inputTitulo.setBounds(50, 245, 280, 25);
        cadastrarJogosPanel.add(inputTitulo);

        // label e input Genero
        JLabel labelGenero = new JLabel("GÊNERO: ");
        labelGenero.setBounds(50, 290, 100, 20);
        cadastrarJogosPanel.add(labelGenero);

        inputGenero.setBounds(50, 325, 280, 25);
        cadastrarJogosPanel.add(inputGenero);

        // label e input Descricao
        JLabel labelDescricao = new JLabel("DESCRIÇÃO: ");
        labelDescricao.setBounds(50, 370, 100, 20);
        cadastrarJogosPanel.add(labelDescricao);

        JScrollPane scrollPaneDescricao = new JScrollPane(inputDescricao);
        inputDescricao.setLineWrap(true);
        scrollPaneDescricao.setBounds(50, 385, 280, 100);
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
        buttonCadastrar.setBounds(520, 500, 180, 35);
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

        cadastrarJogosPanel.setBounds(230, 0, 770, 600);
        add(cadastrarJogosPanel, BorderLayout.CENTER);
    }

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

    static ArrayList<Jogo> arrJogos = new ArrayList<>();
    public static void cadastrarJogoBtn() throws IOException, SQLException {
        int idJogo = Integer.parseInt(inputIDJogo.getText());
        String titulo = inputTitulo.getText();
        String genero = inputGenero.getText();
        double preco = Double.parseDouble(inputPreco.getText().replace(",", "."));
        String descricao = inputDescricao.getText();

        Jogo jogo = new Jogo(idJogo, titulo, genero, preco, descricao, imagemBytes);
        arrJogos.add(jogo);
        System.out.println(arrJogos);
        try {
            ConexaoBD.cadastrarJogo(jogo);
            JOptionPane.showMessageDialog(null, "SUCESSO", "CADASTRO REALIZADO!", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "ERRO", "ERRO: " + e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }

        cleanInputs();
    }

    public static  void cleanInputs() {
        inputIDJogo.setText("");
        inputPreco.setText("");
        inputTitulo.setText("");
        inputGenero.setText("");
        inputDescricao.setText("");
        labelImagem.setIcon(null);
    }


}
