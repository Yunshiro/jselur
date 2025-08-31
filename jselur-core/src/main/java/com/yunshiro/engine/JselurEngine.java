package com.yunshiro.engine;

import com.yunshiro.engine.model.Condition;
import com.yunshiro.engine.model.Rule;
import com.yunshiro.engine.model.Template;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.util.List;

public class JselurEngine implements Engine{

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public <T> boolean loadRules(Template template, T targetInstance) {
        String id = template.getId();
        String description = template.getDescription();
        List<Rule> rules = template.getRules();

        LOGGER.info("rules loading now: {}", rules);

        // deal the first rule
        boolean flag = checkConditions(rules.get(0).getConditions(), targetInstance);

        // if it has 1 more rules, from second to start.
        for (int i = 1; i < rules.size(); i++) {
            boolean currentResult = checkConditions(rules.get(i).getConditions(), targetInstance);
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
    private <T> boolean checkConditions(List<Condition> conditions, T targetInstance) {
        if (conditions == null || conditions.isEmpty()) {
            return false;
        }

        boolean flag = compareValue(
                conditions.get(0).getVariable(),
                conditions.get(0).getSign(),
                conditions.get(0).getValue(),
                targetInstance
        );

        // from the second condition start.
        for (int i = 1; i < conditions.size(); i++) {
            Condition currCondition = conditions.get(i);
            String variable = currCondition.getVariable();
            String sign = currCondition.getSign();
            String value = currCondition.getValue();

            // judge the current condition's result.
            boolean currentResult = compareValue(variable, sign, value, targetInstance);

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

    private <T> boolean compareValue(String variable, String sign, String value, T targetInstance) {
        // get the type of variable.
        Class<?> clazz = targetInstance.getClass();
        Class<?> fieldType;
        try {
            Field field = clazz.getDeclaredField(variable);
            field.setAccessible(true);
            fieldType = field.getType();
            Object fieldValue = field.get(targetInstance);
            boolean flag = false;
            switch (sign) {
                case "eq":
                    if (fieldType == String.class) {
                        flag = ((String) fieldValue).equals(value);
                    } else if (fieldType == int.class) {
                        flag = ((int) fieldValue) == Integer.parseInt(value);
                    } else if (fieldType == double.class) {
                        flag = ((double) fieldValue) == Double.parseDouble(value);
                    } else if (fieldType == float.class) {
                        flag = ((float) fieldValue) == Float.parseFloat(value);
                    }

                    break;
                case "lt":
                    if (fieldType == int.class) {
                        flag = ((int) fieldValue) > Integer.parseInt(value);
                    } else if (fieldType == double.class) {
                        flag = ((double) fieldValue) > Double.parseDouble(value);
                    } else if (fieldType == float.class) {
                        flag = ((float) fieldValue) > Float.parseFloat(value);
                    }
                    break;
                default:
                    throw new RuntimeException("Don't match the sign: " + sign);
            }
            return flag;

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }


    }

    public static <T> T bindTemplate(T targetInstance, String variable, T value) {
        Class<?> clazz = targetInstance.getClass();

        try {
            Field field = clazz.getDeclaredField(variable);
            LOGGER.info("field type: {}", field.getType());
            field.setAccessible(true);
            field.set(targetInstance ,value);

            return targetInstance;
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
