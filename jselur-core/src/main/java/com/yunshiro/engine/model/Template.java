package com.yunshiro.engine.model;

import java.util.List;

/**
 * Template is the core type for engine to read the rules from json file.
 */
public class Template {
    private String id;
    private String description;
    private List<Rule> rules;

    public Template() {
    }

    public Template(String id, String description, List<Rule> rules) {
        this.id = id;
        this.description = description;
        this.rules = rules;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    @Override
    public String toString() {
        return "Template{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", rules=" + rules +
                '}';
    }
}
