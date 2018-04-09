package com.tw;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author Joker
 * @Description
 * @Date Create in 下午6:38 2018/4/8
 */
public class PrintScoreView implements View, ErrorView {
    @Override
    public void showErrorView() {
        System.out.println("请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：");
    }

    @Override
    public void showView(Map<Integer, Student> studentMap, Set<String> classes, Scanner scanner) {
        System.out.println("请输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：");
        handle(studentMap,classes,scanner);
    }

    private void handle(Map<Integer, Student> studentMap,Set<String> classes, Scanner scanner) {
        String s = scanner.nextLine();
        String[] ids = s.split(", ");
        List<Student> students = new ArrayList<>();
        for (String id : ids) {
            Student student;
            try {
                student = studentMap.get(Integer.parseInt(id));
            } catch (NumberFormatException e) {
                showErrorView();
                handle(studentMap,classes,scanner);
                return;
            }
            if (student != null)
                students.add(student);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("成绩单\n")
                .append("姓名|");
        classes.forEach(value -> stringBuilder.append(value).append("|"));
        stringBuilder.append("平均分|总分\n")
                .append("========================\n");
        int everySum = 0;
        int classSum = 0;
        int classSize = classes.size();
        int studentSize = students.size();
        DecimalFormat format = new DecimalFormat(".00");
        List<Integer> evertTotal = new ArrayList<>();
        for (Student student : students) {
            stringBuilder.append(student.getName())
                    .append("|");
            Map<String, Integer> scoreMap = student.getScoreMap();
            for (String klass : classes) {
                Integer score = scoreMap.get(klass);
                if (score != null) {
                    stringBuilder.append(score);
                    everySum += score;
                } else {
                    stringBuilder.append(0);
                }
                stringBuilder.append("|");
            }
            evertTotal.add(everySum);
            classSum += everySum;
            stringBuilder.append(format.format((float) everySum / classSize))
                    .append("|").append(everySum).append("\n");
            everySum = 0;
        }
        List<Integer> collect = evertTotal.stream().sorted().collect(Collectors.toList());
        float mid;
        int index = studentSize / 2;
        if (studentSize % 2 == 0) {
            mid = (float) (collect.get(index) + collect.get(index - 1)) / 2;
        } else {
            mid = collect.get(index);
        }
        stringBuilder.append("========================\n")
                .append("全班总分平均数：").append(format.format((float)classSum / students.size()))
                .append("\n全班总分中位数：").append(format.format(mid));
        System.out.println(stringBuilder.toString());

    }

}
