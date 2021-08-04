package ru.pavlov.calculator.settings;

import android.content.Context;
import android.content.SharedPreferences;

import ru.pavlov.calculator.R;

public class SettingsApp {
    private static final String NAME_SHARED_PREFERENCE = "SETTINGS";
    private static final String APP_THEME = "APP_THEME";
    public static final int THEME_LIGHT = 0;
    public static final int THEME_DARK = 1;

    private final Context context;

    public SettingsApp(Context context) {
        this.context = context;
    }

    public int getSavedSystemIdStyle() {
        int codeStyle = getSavedCodeStyle();
        switch (codeStyle) {
            case THEME_DARK:
                return R.style.Theme_Calculator_Dark;
            default:
                return R.style.Theme_Calculator_Light;
        }
    }

    public int getSavedCodeStyle() {
        SharedPreferences sharedPref = context.getSharedPreferences(NAME_SHARED_PREFERENCE, Context.MODE_PRIVATE);
        return sharedPref.getInt(APP_THEME, THEME_LIGHT);
    }

    public void setAppTheme(int codeStyle) {
        SharedPreferences sharedPref = context.getSharedPreferences(NAME_SHARED_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(APP_THEME, codeStyle);
        editor.apply();
    }
}
