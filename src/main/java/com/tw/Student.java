package com.tw;

import java.util.Map;

/**
 * @Author Joker
 * @Description
 * @Date Create in 下午6:30 2018/4/8
 */
public class Student {
    private String name;
    private int id;
    private Map<String,Integer> scoreMap;

    public Student(String name, int id, Map<String, Integer> scoreMap) {
        this.name = name;
        this.id = id;
        this.scoreMap = scoreMap;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Map<String, Integer> getScoreMap() {
        return scoreMap;
    }

}
