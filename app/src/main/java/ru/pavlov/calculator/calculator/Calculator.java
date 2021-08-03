package ru.pavlov.calculator.calculator;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.TextView;

public class Calculator implements Parcelable {
    public static final String LOG_TAG = "Calculator";

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
    protected String operandOne = "0";
    protected String operandSecond = "0";
    protected String curOperand = null;
    protected boolean isInputNow = false;

    public void setScreen(TextView screen) {
        this.screen = screen;
        showCurOperandOnScreen();
    }

    public Calculator(String typeOperation, String operandOne, String operandSecond) {
        this.typeOperation = typeOperation;
        setOperandOne(operandOne);
        setOperandSecond(operandSecond);
        setCurOperandOne();
    }

    public void setTypeOperation(String typeOperation) {
        if (getCurOperand().equals(CUR_OPERAND_SECOND)) {
            equally();
        }
        this.typeOperation = typeOperation;
        setCurOperandSecond();
        showCurOperandOnScreen();
    }


    public void inputOperand(String valueBtn) {
        isInputNow = true;
        String operand = "";
        switch (getCurOperand()) {
            case CUR_OPERAND_ONE:
                operand = operandOne;
                break;
            case CUR_OPERAND_SECOND:
                operand = operandSecond;
                break;
        }
        if (existPoint(valueBtn) && existPoint(operand)) {
            return;
        }
        if (operand.equals("0")) {
            operand = valueBtn;
        } else {
            operand += valueBtn;
        }
        switch (getCurOperand()) {
            case CUR_OPERAND_ONE:
                setOperandOne(operand);
                break;
            case CUR_OPERAND_SECOND:
                setOperandSecond(operand);
                break;
        }
        showCurOperandOnScreen();
        isInputNow = false;
    }

    public void setTypeOperationPlus() {
        setTypeOperation(TYPE_OPERATION_PLUS);
    }

    public void equally() {
        if (typeOperation == null) {
            return;
        }
        Float resultOperation = new Float(0);
        Float operand1 = Float.valueOf(operandOne);
        Float operand2 = Float.valueOf(operandSecond);

        switch (typeOperation) {
            case TYPE_OPERATION_PLUS:
                resultOperation = new Float(operand1.floatValue() + operand2.floatValue());
                break;
            case TYPE_OPERATION_MINUS:
                resultOperation = new Float(operand1.floatValue() - operand2.floatValue());
                break;
            case TYPE_OPERATION_MULTIPLY:
                resultOperation = new Float(operand1.floatValue() * operand2.floatValue());
                break;
            case TYPE_OPERATION_DIVIDE:
                resultOperation = new Float(operand1.floatValue() / operand2.floatValue());
                break;
        }
        clearData();
        setOperandOne(resultOperation.toString());
        showCurOperandOnScreen();
    }

    protected void setOperandOne(String value) {
        operandOne = filterOperand(value);
    }

    protected void setOperandSecond(String value) {
        operandSecond = filterOperand(value);
    }

    protected void clearData() {
        this.typeOperation = null;
        this.operandOne = "0";
        this.operandSecond = "0";
        setCurOperandOne();
    }

    protected boolean existPointEnd(String value) {
        StringBuilder stringBuilder = new StringBuilder(value);
        String str = stringBuilder.substring(stringBuilder.length() - 1);
        if (str.equalsIgnoreCase(".")) {
            return true;
        }
        return false;
    }

    protected boolean existPoint(String value) {
        if (value.contains(".")) {
            return true;
        }
        return false;
    }

    protected String filterOperand(String value) {
        if (value.isEmpty()) {
            value = "0";
        } else if (value.equals("-")) {
            value = "0";
        }
        if (!existPointEnd(value)) {
            Float valueFloat = Float.valueOf(value);
            if (!isInputNow() && valueFloat.floatValue() % 1 == 0) {
                int temp = valueFloat.intValue();
                return Integer.valueOf(temp).toString();
            }
        }
        return value;
    }

    private boolean isInputNow() {
        return isInputNow;
    }

    protected void showCurOperandOnScreen() {
        String operand = "";
        switch (getCurOperand()) {
            case CUR_OPERAND_ONE:
                operand = operandOne;
                break;
            case CUR_OPERAND_SECOND:
                operand = operandSecond;
                break;
        }
        if (screen != null) {
            screen.setText(operand);
        }
    }

    protected void setCurOperandOne() {
        curOperand = CUR_OPERAND_ONE;
    }

    protected void setCurOperandSecond() {
        curOperand = CUR_OPERAND_SECOND;
    }

    protected String getCurOperand() {
        return curOperand;
    }

    public void cancelInputSymbol() {
        switch (getCurOperand()) {
            case CUR_OPERAND_ONE:
                setOperandOne(String.copyValueOf(operandOne.toCharArray(), 0, operandOne.length() - 1));
                break;
            case CUR_OPERAND_SECOND:
                setOperandSecond(String.copyValueOf(operandSecond.toCharArray(), 0, operandSecond.length() - 1));
                break;
        }
        showCurOperandOnScreen();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(typeOperation);
        dest.writeString(operandOne);
        dest.writeString(operandSecond);
        dest.writeString(curOperand);
    }

    protected Calculator(Parcel in) {
        typeOperation = in.readString();
        operandOne = in.readString();
        operandSecond = in.readString();
        curOperand = in.readString();
    }

    public static final Creator<Calculator> CREATOR = new Creator<Calculator>() {
        @Override
        public Calculator createFromParcel(Parcel in) {
            return new Calculator(in);
        }

        @Override
        public Calculator[] newArray(int size) {
            return new Calculator[size];
        }
    };
}
