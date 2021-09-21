package ru.pavlov.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Set;
import java.util.zip.Inflater;

import ru.pavlov.calculator.calculator.Calculator;
import ru.pavlov.calculator.settings.SettingsApp;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    protected static final String STATE_CALCULATOR = "state_calculator";
    public static final String LOG_TAG = "Calculator";
    private static final int REQUEST_CODE_SETTING_ACTIVITY = 99;
    private Calculator calculator;
    private TextView screen;
    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button buttonClear;
    private Button buttonEqually;
    private Button buttonPercent;
    private Button buttonDivide;
    private Button buttonMultiply;
    private Button buttonMinus;
    private Button buttonPlus;
    private Button buttonPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(new SettingsApp(this).getSavedSystemIdStyle());
        setContentView(R.layout.activity_main_material);
        initView();
        setListeners();
        calculator = new Calculator(null, "", "");
        calculator.setScreen(screen);
        Log.d(LOG_TAG, "onCreate");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_clear:
                calculator.cancelInputSymbol();
                break;
            case R.id.button_plus:
                calculator.setTypeOperationPlus();
                break;
            case R.id.button_minus:
                calculator.setTypeOperation(Calculator.TYPE_OPERATION_MINUS);
                break;
            case R.id.button_multiply:
                calculator.setTypeOperation(Calculator.TYPE_OPERATION_MULTIPLY);
                break;
            case R.id.button_divide:
                calculator.setTypeOperation(Calculator.TYPE_OPERATION_DIVIDE);
                break;
            case R.id.button_percent:
                calculator.setTypeOperation(Calculator.TYPE_OPERATION_PERCENT);
                break;
            case R.id.button_equally:
                calculator.equally();
                break;
            case R.id.button_point:
                calculator.inputOperand(".");
                break;
            case R.id.button_0:
                calculator.inputOperand("0");
                break;
            case R.id.button_1:
                calculator.inputOperand("1");
                break;
            case R.id.button_2:
                calculator.inputOperand("2");
                break;
            case R.id.button_3:
                calculator.inputOperand("3");
                break;
            case R.id.button_4:
                calculator.inputOperand("4");
                break;
            case R.id.button_5:
                calculator.inputOperand("5");
                break;
            case R.id.button_6:
                calculator.inputOperand("6");
                break;
            case R.id.button_7:
                calculator.inputOperand("7");
                break;
            case R.id.button_8:
                calculator.inputOperand("8");
                break;
            case R.id.button_9:
                calculator.inputOperand("9");
                break;
            default:
                break;
        }
    }

    public void onSaveInstanceState(Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        instanceState.putParcelable(STATE_CALCULATOR, calculator);
        Log.d(LOG_TAG, "onSaveInstanceState");
    }

    protected void onRestoreInstanceState(Bundle instanceState) {
        super.onRestoreInstanceState(instanceState);
        calculator = instanceState.getParcelable(STATE_CALCULATOR);
        calculator.setScreen(screen);
        Log.d(LOG_TAG, "onRestoreInstanceState");
    }

    private void setListeners() {
        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        buttonClear.setOnClickListener(this);
        buttonEqually.setOnClickListener(this);
        buttonPercent.setOnClickListener(this);
        buttonDivide.setOnClickListener(this);
        buttonMultiply.setOnClickListener(this);
        buttonMinus.setOnClickListener(this);
        buttonPlus.setOnClickListener(this);
        buttonPoint.setOnClickListener(this);
    }

    private void initView() {
        screen = findViewById(R.id.textViewScreen);
        button0 = findViewById(R.id.button_0);
        button1 = findViewById(R.id.button_1);
        button2 = findViewById(R.id.button_2);
        button3 = findViewById(R.id.button_3);
        button4 = findViewById(R.id.button_4);
        button5 = findViewById(R.id.button_5);
        button6 = findViewById(R.id.button_6);
        button7 = findViewById(R.id.button_7);
        button8 = findViewById(R.id.button_8);
        button9 = findViewById(R.id.button_9);
        buttonClear = findViewById(R.id.button_clear);
        buttonEqually = findViewById(R.id.button_equally);
        buttonPercent = findViewById(R.id.button_percent);
        buttonDivide = findViewById(R.id.button_divide);
        buttonMultiply = findViewById(R.id.button_multiply);
        buttonMinus = findViewById(R.id.button_minus);
        buttonPlus = findViewById(R.id.button_plus);
        buttonPoint = findViewById(R.id.button_point);

        buttonPercent.setEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivityForResult(intent, REQUEST_CODE_SETTING_ACTIVITY);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != REQUEST_CODE_SETTING_ACTIVITY) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }

        if (resultCode == RESULT_OK){
            recreate();
        }
    }
}