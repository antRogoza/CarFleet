package view;

import java.util.Locale;
import java.util.ResourceBundle;

public class Messages {

    public static final Locale ENGLISH = new Locale("en");
    public static final Locale UKRAINIAN = new Locale("ua");

    private static final String BUNDLE_NAME = "/i18n/messages";
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, ENGLISH);

    public static void setLocale(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
    }

    public static String getMessage(String key) {
        return resourceBundle.getString(key);
    }
}
