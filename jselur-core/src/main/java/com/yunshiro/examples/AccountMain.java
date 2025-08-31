package com.yunshiro.examples;

import com.yunshiro.engine.Engine;
import com.yunshiro.engine.JselurEngine;
import com.yunshiro.engine.model.Template;
import com.yunshiro.utils.Parser;

public class AccountMain {
    public static void main(String[] args) {
        Account account0001 = new Account("Account0001", "yuns", "VIP", 8, 1.0);

        if (account0001.getType().equals("VIP") && account0001.getCredits() > 10) {
            account0001.setDiscount(0.8);
        }

        System.out.println("final discount: " + account0001.getDiscount());

        String path = "examples/account-rule.json";
        Template template = Parser.toTemplate(path, "UTF-8");
        System.out.println(template);

        // 创建引擎
        Engine engine = new JselurEngine();
        // 加载规则
        engine.loadRules(template);
        // 使用规则判断
    }
}
