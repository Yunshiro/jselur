package com.yunshiro.engine.model;

import java.util.List;

public class Rule {
    private List<Condition> conditions;
    private String logic;

    public Rule() {
    }

    public Rule(List<Condition> conditions, String logic) {
        this.conditions = conditions;
        this.logic = logic;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public String getLogic() {
        return logic;
    }

    public void setLogic(String logic) {
        this.logic = logic;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "conditions=" + conditions +
                ", logic='" + logic + '\'' +
                '}';
    }
}
