package com.tw;

import java.util.*;

public class Library {
    private final List<View> views;
    private Map<Integer, Student> studentMap;
    private Set<String> classes;
    public Library() {
        views = Arrays.asList(new AddStudentView(), new PrintScoreView());
        studentMap = new HashMap<>();
        classes = new LinkedHashSet<>();
    }

    public boolean someLibraryMethod() {
        return true;
    }

    public void showPage() {
        Scanner scanner = new Scanner(System.in);
        int n;

        while (true) {
            System.out.println("1. 添加学生\n" +
                    "2. 生成成绩单\n" +
                    "3. 退出\n" +
                    "请输入你的选择（1～3）：");
            n = Integer.parseInt(scanner.nextLine());
            if (n == 3)
                break;
            View view = views.get(n - 1);
            view.showView(studentMap,classes,scanner);
        }
    }


}
