package com.tw;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.LinkedList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LibraryTest {
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testSomeLibraryMethod() {
        Library classUnderTest = new Library();
        assertTrue("someLibraryMethod should return 'true'", classUnderTest.someLibraryMethod());
    }

    @Test
    public void testMockClass() throws Exception {
        // you can mock concrete classes, not only interfaces
        LinkedList mockedList = mock(LinkedList.class);

        // stubbing appears before the actual execution
        String value = "first";
        when(mockedList.get(0)).thenReturn(value);

        assertEquals(mockedList.get(0), value);

    }

    @Test
    //第一次添加失败、第二次添加成功后退出
    public void testAddStudentFailThenSucessFinallyExit() {
        String in = "1\n" +
                "小王, 14, 科学,14\n" +
                "小王, 14, 科学: 14, 数学: 89\n" +
                "3";
        try {
            System.setIn(new ByteArrayInputStream(in.getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Library library = new Library();
        library.showPage();
        String result = "1. 添加学生\n" +
                "2. 生成成绩单\n" +
                "3. 退出\n" +
                "请输入你的选择（1～3）：\n" +
                "请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交：\n" +
                "请按正确的格式输入（格式：姓名, 学号, 学科: 成绩, ...）：\n" +
                "学生小王的成绩被添加\n" +
                "1. 添加学生\n" +
                "2. 生成成绩单\n" +
                "3. 退出\n" +
                "请输入你的选择（1～3）：\n";
        assertEquals(systemOut(),result);
    }

    @Test
    //添加了3个学生，第一次查询失败，第2次查询成功（第2次查询50号不存在）
    public void testPrintFailThenSucessFinallyExit() {
        String in = "1\n" +
                "小王, 14, 科学: 14, 数学: 89\n" +
                "1\n" +
                "小李, 25, 科学: 80, 数学: 70\n" +
                "1\n" +
                "小白, 23, 科学: 90, 数学: 70\n" +
                "2\n" +
                "14: 25\n" +
                "14, 25, 23, 50\n" +
                "3";
        try {
            System.setIn(new ByteArrayInputStream(in.getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Library library = new Library();
        library.showPage();
        String result = "1. 添加学生\n" +
                "2. 生成成绩单\n" +
                "3. 退出\n" +
                "请输入你的选择（1～3）：\n" +
                "请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交：\n" +
                "学生小王的成绩被添加\n" +
                "1. 添加学生\n" +
                "2. 生成成绩单\n" +
                "3. 退出\n" +
                "请输入你的选择（1～3）：\n" +
                "请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交：\n" +
                "学生小李的成绩被添加\n" +
                "1. 添加学生\n" +
                "2. 生成成绩单\n" +
                "3. 退出\n" +
                "请输入你的选择（1～3）：\n" +
                "请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交：\n" +
                "学生小白的成绩被添加\n" +
                "1. 添加学生\n" +
                "2. 生成成绩单\n" +
                "3. 退出\n" +
                "请输入你的选择（1～3）：\n"+
                "请输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：\n" +
                "请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：\n" +
                "成绩单\n" +
                "姓名|科学|数学|平均分|总分\n" +
                "========================\n" +
                "小王|14|89|51.50|103\n" +
                "小李|80|70|75.00|150\n" +
                "小白|90|70|80.00|160\n" +
                "========================\n" +
                "全班总分平均数：137.67\n" +
                "全班总分中位数：150.00\n" +
                "1. 添加学生\n" +
                "2. 生成成绩单\n" +
                "3. 退出\n" +
                "请输入你的选择（1～3）：\n";
        assertEquals(systemOut(),result);
    }




    private String systemOut() {
        return outContent.toString();
    }
}
