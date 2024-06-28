package tipografia;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FontManager {
    private static String fontPathInterRegular = "src/main/java/tipografia/Inter-Regular.ttf";
    private static String fontPathInterSemibold = "src/main/java/tipografia/Inter-Semibold.ttf";
    private static String fontPathPoppinsMedium = "src/main/java/tipografia/Poppins-Medium.ttf";
    private static Font fontInterRegular;
    private static Font fontInterSemiBold;
    private static Font fontPoppinsMedium;

    public static void manageFont() {
        try {
            fontInterRegular = Font.createFont(Font.TRUETYPE_FONT, new File(fontPathInterRegular)).deriveFont(12f);
            fontInterSemiBold = Font.createFont(Font.TRUETYPE_FONT, new File(fontPathInterSemibold)).deriveFont(12f);
            fontPoppinsMedium = Font.createFont(Font.TRUETYPE_FONT, new File(fontPathPoppinsMedium)).deriveFont(12f);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fontInterRegular);
            ge.registerFont(fontInterRegular);
            ge.registerFont(fontPoppinsMedium);

            UIManager.put("Label.font", fontPoppinsMedium);

            for (Window window : Window.getWindows()) {
                SwingUtilities.updateComponentTreeUI(window);
            }

        } catch (IOException e) {
            handleFontException("Erro ao carregar a fonte. Verifique o caminho do arquivo.");
        } catch (FontFormatException e) {
            handleFontException("A tipografia está corrompida ou inválida.");
        }
    }
    public static Font getInterRegularFont(float size) {
        return fontInterRegular.deriveFont(size);
    }

    public static Font getInterSemiboldFont(float size) {
        return fontInterSemiBold.deriveFont(size);
    }

    public static Font getFontPoppinsMedium(float size) {
        return fontPoppinsMedium.deriveFont(size);
    }

    private static void handleFontException(String message) {
        exibirMensagemErro(message);
        // use a fonte padrão do sistema
        UIManager.put("Label.font", UIManager.getDefaults().getFont("Label.font"));
        UIManager.put("Button.font", UIManager.getDefaults().getFont("Button.font"));
    }

    private static void exibirMensagemErro(String message) {
        JOptionPane.showMessageDialog(null, message, "ERRO", JOptionPane.ERROR_MESSAGE);
    }
}
