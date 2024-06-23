package fonts;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FontManager {
    private static String fontPath = "src/main/java/fonts/Inter-SemiBold.ttf";
    private static Font fontInterRegular;

    public static void manageFont() {
        try {
            fontInterRegular = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(12f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fontInterRegular);

            UIManager.put("Label.font", fontInterRegular);

            for (Window window : Window.getWindows()) {
                SwingUtilities.updateComponentTreeUI(window);
            }

        } catch (IOException e) {
            handleFontException("Erro ao carregar a fonte. Verifique o caminho do arquivo.");
        } catch (FontFormatException e) {
            handleFontException("A tipografia está corrompida ou inválida.");
        }
    }

    public static Font getCustomFont(float size) {
        if (fontInterRegular != null) {
            return fontInterRegular.deriveFont(size);
        } else {
            return UIManager.getFont("Label.font").deriveFont(size);
        }
    }

    private static void handleFontException(String message) {
        exibirMensagemErro(message);
        // Use a fonte padrão do sistema
        UIManager.put("Label.font", UIManager.getDefaults().getFont("Label.font"));
    }

    private static void exibirMensagemErro(String message) {
        JOptionPane.showMessageDialog(null, message, "ERRO", JOptionPane.ERROR_MESSAGE);
    }
}
