import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class testando extends JFrame {
    public testando(){
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        configurePanels();
        this.setVisible(true);
    }

    public void configurePanels() {
        JPanel main = new JPanel();
        main.setSize(800, 600);
        main.setLayout(null);
        main.setBackground(Color.gray);

        JLabel label1 = new JLabel("ID JOGO");
        label1.setBounds(10, 30, 100, 20);
        JTextField idJogo = new JTextField();
        idJogo.setBounds(10, 55, 60, 20);

        JLabel labelImg = new JLabel();
        labelImg.setBorder(new LineBorder(Color.red));
        labelImg.setBounds(800, 30, 100, 150);

        main.add(label1);
        main.add(idJogo);
        main.add(labelImg);

        this.add(main);
    }

    public static void main(String[] args) {
        new testando();
    }
}
