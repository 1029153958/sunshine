package com.zgy2.HWork86;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class StudentMain {
    public static void main(String[] args) {
        StudentOperator so = new StudentOperator();
        Student stu = new Student();
//        List list = so.findAllStu();
        List list = null;
        try {
//            String str = "sname";
//            list = so.findOrderByUserName();
           list = so.findLikeOrderByUserName("王",1,2);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
//            list = so.findBySnameLike("王");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Iterator<Student> it = list.iterator();
        // 遍历查看
        while(it.hasNext()){
            System.out.println(it.next());
        }

        // 根据学号修改
//        Student stu = new Student();
//        stu.setSno();
//        so.updateStuById(stu);
        // 插入
//        stu.setSno("110");
//        stu.setSsex("F");
//        stu.setClz("95033");
//        stu.setSname("王华");
//        stu.setSbirthday(null);
//        int i = so.insertStu(stu);
//        System.out.println(i);
    }
}
