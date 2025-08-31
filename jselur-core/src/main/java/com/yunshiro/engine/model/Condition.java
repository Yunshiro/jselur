package com.yunshiro.engine.model;

public class Condition {
    private String variable;
    private String value;
    private String sign;
    private String logic;

    public Condition() {
    }

    public Condition(String variable, String value, String sign, String logic) {
        this.variable = variable;
        this.value = value;
        this.sign = sign;
        this.logic = logic;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getLogic() {
        return logic;
    }

    public void setLogic(String logic) {
        this.logic = logic;
    }

    @Override
    public String toString() {
        return "Condition{" +
                "variable='" + variable + '\'' +
                ", value='" + value + '\'' +
                ", sign='" + sign + '\'' +
                ", logic='" + logic + '\'' +
                '}';
    }
}
