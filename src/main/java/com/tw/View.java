package com.tw;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * @Author Joker
 * @Description
 * @Date Create in 下午6:34 2018/4/8
 */
public interface View {
    public void showView(Map<Integer,Student> studentMap,Set<String> classes, Scanner scanner);
}
