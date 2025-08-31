package com.yunshiro.engine;

import com.yunshiro.engine.model.Template;

public interface Engine {
    // 加载规则
    <T> boolean loadRules(Template template, T targetInstance);
}
