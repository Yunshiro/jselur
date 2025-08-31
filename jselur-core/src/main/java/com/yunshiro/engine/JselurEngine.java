package com.yunshiro.engine;

import com.yunshiro.engine.model.Condition;
import com.yunshiro.engine.model.Rule;
import com.yunshiro.engine.model.Template;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class JselurEngine implements Engine{

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public boolean loadRules(Template template) {
        String id = template.getId();
        String description = template.getDescription();
        List<Rule> rules = template.getRules();

        LOGGER.info("rules loading now: {}", rules);

        // deal the first rule
        boolean flag = checkConditions(rules.get(0).getConditions());

        // if it has 1 more rules, from second to start.
        for (int i = 1; i < rules.size(); i++) {
            boolean currentResult = checkConditions(rules.get(i).getConditions());
            String last = rules.get(i - 1).getLogic();
            switch (last) {
                case "AND":
                    flag = flag && currentResult;
                    break;
                case "OR":
                    flag = flag || currentResult;
                    break;
                default:
                    throw new RuntimeException("Don't have the Logic sign: " + last);
            }
        }
        return flag;
    }

    /**
     * judge the result of a list of conditions with logic sign.
     * @param conditions a list of conditions.
     * @return the boolean result.
     */
    private boolean checkConditions(List<Condition> conditions) {
        if (conditions == null || conditions.isEmpty()) {
            return false;
        }

        boolean flag = compareValue(
                conditions.get(0).getVariable(),
                conditions.get(0).getSign(),
                conditions.get(0).getValue()
        );

        // from the second condition start.
        for (int i = 1; i < conditions.size(); i++) {
            Condition currCondition = conditions.get(i);
            String variable = currCondition.getVariable();
            String sign = currCondition.getSign();
            String value = currCondition.getValue();

            // judge the current condition's result.
            boolean currentResult = compareValue(variable, sign, value);

            // get the last condition logic sign.
            String lastLogic = conditions.get(i - 1).getLogic();
            switch (lastLogic) {
                case "AND":
                    flag = flag && currentResult;
                    break;
                case "OR":
                    flag = flag || currentResult;
                    break;
                default:
                    throw new RuntimeException("Don't have the Logic sign: " + lastLogic);
            }


        }
        return flag;
    }

    private boolean compareValue(String variable, String sign, String value) {
        boolean flag = false;
        switch (sign) {
            case "eq":
                flag = variable.equals(value);
                break;
            case "lt":
                flag = !variable.equals(value);;
                break;
            default:
                throw new RuntimeException("Don't match the sign: " + sign);
        }
        return flag;
    }
}
