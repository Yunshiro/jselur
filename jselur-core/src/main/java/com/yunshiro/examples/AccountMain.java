package com.yunshiro.examples;

import com.yunshiro.engine.Engine;
import com.yunshiro.engine.JselurEngine;
import com.yunshiro.engine.model.Template;
import com.yunshiro.utils.Parser;

import java.lang.reflect.Field;

public class AccountMain {
    public static void main(String[] args) throws Exception {
        Account account0001 = new Account("Account0001", "yuns", "VIP", 8, 1.0);

        if (account0001.getaccountType().equals("VIP") && account0001.getCredits() > 10) {
            account0001.setDiscount(0.8);
        }

        System.out.println("final discount: " + account0001.getDiscount());

        String path = "examples/account-rule.json";
        Template template = Parser.toTemplate(path, "UTF-8");
        System.out.println(template);

        // create engine
        Engine engine = new JselurEngine();
        // load rules and judge with the template.
        boolean res = engine.loadRules(template, account0001);
        System.out.println(res);

    }
}
