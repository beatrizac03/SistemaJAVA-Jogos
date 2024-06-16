import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class testeGD extends JFrame {
    JPanel main = new JPanel();
    JLabel label1 = new JLabel("LABEL1");
    JTextField text1 = new JTextField();
    JLabel label2 = new JLabel("LABEL2");
    JTextField text2 = new JTextField();

    public testeGD() {
        this.setSize(900, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        configurePanels();
        this.setVisible(true);
    }

    public void configurePanels() {
        main.setLayout(new GridBagLayout());
        main.setBorder(new LineBorder(Color.red));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;  // define a coluna
        gbc.gridy = 0;  // define a linha
        gbc.gridwidth = 1;  //define quantas colunas o comp vai ocupar
        gbc.gridheight = 1; //define quantas linhas o comp vai ocupar
        main.add(label1, gbc);

        gbc.gridx= 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 10, 0);
        text1.setPreferredSize(new Dimension(200, 30));
        main.add(text1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(30, 0, 10, 0);
        main.add(label2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weighty = 1.0;  // O peso y faz com que este componente empurre os outros para cima
        gbc.fill = GridBagConstraints.BOTH;
        main.add(new JPanel(), gbc);
        this.add(main);

    }

    public static void main(String[] args) {
        new testeGD();
    }
}
