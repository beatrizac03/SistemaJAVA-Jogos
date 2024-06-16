package PainelAdmin;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FontManager extends JFrame {
    static String fontPath = "src/fonts/Inter-SemiBold.ttf";
    static Font fontInterRegular;

    public static void manageFont() {
        try {
            Font fontInterRegular = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(12f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fontInterRegular);

            UIManager.put("Label.font", fontInterRegular);

            for (Window window : Window.getWindows()) {
                SwingUtilities.updateComponentTreeUI(window);
            }

        } catch (IOException e) {
            e.printStackTrace();
            exibirMensagemErro("Erro ao carregar a fonte. Verifique o caminho do arquivo.");
        } catch (FontFormatException e) {
            exibirMensagemErro("A tipografia está corrompida ou inválida.");
        }
    }

    private static void exibirMensagemErro(String message){
        JOptionPane.showMessageDialog(null, message, "ERRO", JOptionPane.ERROR_MESSAGE);
    }
}
