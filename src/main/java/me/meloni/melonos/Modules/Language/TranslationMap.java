package me.meloni.melonos.Modules.Language;

import me.meloni.melonos.MelonOS;

import java.util.HashMap;
import java.util.Map;

public class TranslationMap {
    private String lang ="de";
    private final Map<String, String> matrix;
    public TranslationMap(MelonOS instance) {
        this.matrix = matrix();
    }

    public Map<String, String> matrix() {
        Map<String, String> map2 = new HashMap<>();
        if(lang.equals("de")) {
            map2.put("welcome-message","MelonOS erfolgreich geladen.");
            map2.put("missingpermission", "Dir fehlt die Berechtigng ");
            map2.put("notenougharguments", "Zu wenig Argumente!");
        }
        return map2;
    }

    public Map<String, String> getMatrix() {
        return matrix;
    }

    public String get(String key) {
        return matrix.get(key);
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
