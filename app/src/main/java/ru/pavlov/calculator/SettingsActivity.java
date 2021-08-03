package ru.pavlov.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsActivity extends AppCompatActivity {

    private static final String NameSharedPreference = "SETTINGS";
    private static final String appTheme = "APP_THEME";
    private static final int ThemeLight = 0;
    private static final int ThemeDark = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initThemeChooser();
    }

    // Инициализация радиокнопок
    private void initThemeChooser() {
        initRadioButton(findViewById(R.id.radioThemeLight),
                ThemeLight);
        initRadioButton(findViewById(R.id.radioThemeDark),
                ThemeDark);
        RadioGroup rg = findViewById(R.id.radioGroup);
        ((RadioButton) rg.getChildAt(getSavedCodeStyle(ThemeLight))).setChecked(true);
    }

    private void initRadioButton(View button, final int codeStyle) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAppTheme(codeStyle);
//                recreate();
            }
        });
    }

    private int getSavedCodeStyle(int defaultCode) {
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        return sharedPref.getInt(appTheme, defaultCode);
    }

    private void setAppTheme(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(appTheme, codeStyle);
        editor.apply();
    }
}