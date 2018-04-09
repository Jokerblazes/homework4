package com.tw;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * @Author Joker
 * @Description
 * @Date Create in 下午6:36 2018/4/8
 */
public class AddStudentView implements View,ErrorView {

    @Override
    public void showErrorView() {
        System.out.println("请按正确的格式输入（格式：姓名, 学号, 学科: 成绩, ...）：");
    }

    @Override
    public void showView(Map<Integer, Student> studentMap,Set<String> classes,Scanner scanner) {
        System.out.println("请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交：");
        handler(studentMap,classes,scanner);
    }

    private void handler(Map<Integer, Student> studentMap,Set<String> classes,Scanner scanner) {
        String s = scanner.nextLine();
        String[] split = s.split(", ");
        if (split.length < 3) {
            showErrorView();
            handler(studentMap,classes,scanner);
        } else {
            int id = Integer.parseInt(split[1]);
            Map<String, Integer> scoreMap = new HashMap<>();
            for (int i = 2; i < split.length; i++) {
                String[] scores = split[i].split(": ");
                if (scores.length != 2) {
                    showErrorView();
                    handler(studentMap,classes,scanner);
                    return;
                } else {
                    try {
                        scoreMap.put(scores[0], Integer.parseInt(scores[1]));
                    } catch (NumberFormatException e) {
                        handler(studentMap,classes,scanner);
                        showErrorView();
                    }
                }
            }
            scoreMap.forEach((key,value) -> classes.add(key));
            Student student = new Student(split[0],id,scoreMap);
            studentMap.put(id, student);
            System.out.println("学生" + student.getName() + "的成绩被添加");
        }
    }
}
