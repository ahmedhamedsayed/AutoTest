package configuration.uiConfiguration;

import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Locale;

public class Format {
    public static Font getFont(int fontSize) {
        return new Font("Arabic Typesetting", Font.BOLD, fontSize);
    }

    public static ComponentOrientation getArabicLanguage() {
        Locale arabic = new Locale("ar", "KW");
        return ComponentOrientation.getOrientation(arabic);
    }

    public static int screenWidth() {
        return (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    }

    public static int screenHeight() {
        return (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    }
}
