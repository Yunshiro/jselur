package com.yunshiro.engine;

import com.yunshiro.engine.model.Template;

public interface Engine {
    // 加载规则
    boolean loadRules(Template template);
}
