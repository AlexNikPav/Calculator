package ru.pavlov.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.google.android.material.radiobutton.MaterialRadioButton;

import ru.pavlov.calculator.settings.SettingsApp;

public class SettingsActivity extends AppCompatActivity {

    private SettingsApp settingsApp;
    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settingsApp = new SettingsApp(this);
        setTheme(settingsApp.getSavedSystemIdStyle());

        setContentView(R.layout.activity_settings);
        initThemeChooser();
        initView();
        setListeners();
    }

    private void initThemeChooser() {
        initRadioButton(findViewById(R.id.radioThemeLight), SettingsApp.THEME_LIGHT);
        initRadioButton(findViewById(R.id.radioThemeDark), SettingsApp.THEME_DARK);
        RadioGroup rg = findViewById(R.id.radioGroup);
        ((MaterialRadioButton) rg.getChildAt(settingsApp.getSavedCodeStyle())).setChecked(true);
    }

    private void initRadioButton(View button, final int codeStyle) {
        button.setOnClickListener(v -> {
            settingsApp.setAppTheme(codeStyle);
            recreate();
        });
    }

    private void setListeners() {
        buttonBack.setOnClickListener(v -> {
            Intent intentResult = new Intent();
            setResult(RESULT_OK, intentResult);
            finish();
        });
    }

    private void initView() {
        buttonBack = findViewById(R.id.buttonBack);
    }
}