package componentsAdmin;

import PainelAdmin.PainelAdmin;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ContentPanelAdmin extends JFrame {
    static JPanel contPanel = new JPanel(new CardLayout());
    static Color corPainel = new Color(45, 45, 45);
    static JPanel cadastrarJogosPanel = new JPanel();
    static JPanel jogosCadastradosPanel = new JPanel();

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
        cadastrarJogosPanel.setBackground(Color.green);
        cadastrarJogosPanel.setLayout(new GridBagLayout());
        cadastrarJogosPanel.setBorder(new LineBorder(Color.RED, 2));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaçamento entre os componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Ocupa duas colunas
        cadastrarJogosPanel.add(new JLabel("Cadastrar"), gbc);

        // Label "Título"
        gbc.gridy++;
        gbc.gridwidth = 1; // Volta a ocupar uma coluna
        JLabel labelTitulo = new JLabel("TÍTULO:");
        labelTitulo.setBorder(new LineBorder(Color.BLUE, 1));
        cadastrarJogosPanel.add(labelTitulo, gbc);

        // Input "Título"
        gbc.gridy++;
        JTextField inputTitulo = new JTextField(20);
        inputTitulo.setBorder(new LineBorder(Color.BLACK, 1));
        cadastrarJogosPanel.add(inputTitulo, gbc);

        // Label "Desenvolvedores"
        gbc.gridy++;
        cadastrarJogosPanel.add(new JLabel("DESENVOLVEDORES:"), gbc);

        // Input "Desenvolvedores"
        gbc.gridy++;
        JTextField inputDesenvolvedores = new JTextField(20);
        cadastrarJogosPanel.add(inputDesenvolvedores, gbc);

        // Label "Preço"
        gbc.gridy++;
        cadastrarJogosPanel.add(new JLabel("PREÇO:"), gbc);

        // Input "Preço"
        gbc.gridy++;
        JTextField inputPreco = new JTextField(20);
        cadastrarJogosPanel.add(inputPreco, gbc);

        // Label "Descrição"
        gbc.gridy++;
        cadastrarJogosPanel.add(new JLabel("DESCRIÇÃO:"), gbc);

        // Input "Descrição"
        gbc.gridy++;
        JTextField inputDescricao = new JTextField(20);
        cadastrarJogosPanel.add(inputDescricao, gbc);

        // Label "Imagem"
        gbc.gridy++;
        cadastrarJogosPanel.add(new JLabel("IMAGEM:"), gbc);

        // Input "Imagem"
        gbc.gridy++;
        JTextField inputImagem = new JTextField(20);
        cadastrarJogosPanel.add(inputImagem, gbc);
    }

    public static void panelJogosCadastrados() {
        jogosCadastradosPanel.setBackground(Color.BLUE);
        jogosCadastradosPanel.add(new JLabel("CADASTRADOS"));
    }

    public static void showPanel(String panelName) {
        CardLayout cl = (CardLayout) (contPanel.getLayout());
        cl.show(contPanel, panelName);
    }
}
