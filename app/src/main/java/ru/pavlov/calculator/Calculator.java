package ru.pavlov.calculator;

import android.widget.TextView;

public class Calculator {
    public static final String TYPE_OPERATION_PLUS = "+";
    public static final String TYPE_OPERATION_MINUS = "-";
    public static final String TYPE_OPERATION_MULTIPLY = "x";
    public static final String TYPE_OPERATION_DIVIDE = "/";
    public static final String TYPE_OPERATION_CLEAR = "c";
    public static final String TYPE_OPERATION_EQUALLY = "=";
    public static final String TYPE_OPERATION_PERCENT = "%";
    public static final String CUR_OPERAND_ONE = "one";
    public static final String CUR_OPERAND_SECOND = "second";

    protected TextView screen = null;
    protected String typeOperation = null;
    protected String operandOne = null;
    protected String operandSecond = null;
    protected String curOperand = null;

    public void setScreen(TextView screen) {
        this.screen = screen;
    }

    public Calculator(String typeOperation, String operandOne, String operandSecond) {
        this.typeOperation = typeOperation;
        this.operandOne = operandOne;
        this.operandSecond = operandSecond;
        setCurOperandOne();
    }

    public void setTypeOperation(String typeOperation) {
        this.typeOperation = typeOperation;
    }


    public void addToOperand(String valueBtn) {
        String operand = "";
        switch (getCurOperand()) {
            case CUR_OPERAND_ONE:
                operand = operandOne;
                break;
            case CUR_OPERAND_SECOND:
                operand = operandSecond;
                break;
        }
        if (operand.equals("") || operand.equals("0")) {
            operand = valueBtn;
        } else {
            operand += valueBtn;
        }
        switch (getCurOperand()) {
            case CUR_OPERAND_ONE:
                operandOne = operand;
                break;
            case CUR_OPERAND_SECOND:
                operandSecond = operand;
                break;
        }
        updateScreen(operand);
    }


    public void updateScreen(String value) {
        if (screen != null) {
            screen.setText(value);
        }
    }

    public void clear() {
        this.typeOperation = null;
        this.operandOne = "";
        this.operandSecond = "";
        this.clearCurOperand();
        updateScreen("0");
    }

    private void clearCurOperand() {
        curOperand = CUR_OPERAND_ONE;
    }

    private void setCurOperandOne() {
        curOperand = CUR_OPERAND_ONE;
    }

    private void setCurOperandSecond() {
        curOperand = CUR_OPERAND_SECOND;
    }

    private String getCurOperand() {
        return curOperand;
    }
}
