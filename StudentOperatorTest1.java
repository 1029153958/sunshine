package day02homework;

import java.text.SimpleDateFormat;
import java.util.List;


public class StudentOperatorTest {

    @org.junit.Test
    public void findByUserNameLike() {
        StudentOperator studentOperator = new StudentOperator();
        List<Student> students = studentOperator.findByUserNameLike("李");
        System.out.println("students的长度：");
        System.out.println(students.size());
        students.forEach(System.out::println);
    }

    @org.junit.Test
    public void findOrderByUserName() {
        StudentOperator studentOperator = new StudentOperator();
        List<Student> students = studentOperator.findOrderByUserName();
        System.out.println("students的长度：");
        System.out.println(students.size());
        students.forEach(System.out::println);
    }

    @org.junit.Test
    public void findByUserNameLikeOrderLimit() {
        StudentOperator studentOperator = new StudentOperator();
        List<Student> students = studentOperator.findByUserNameLikeOrderLimit("王",1,2);
        System.out.println("students的长度：");
        System.out.println(students.size());
        students.forEach(System.out::println);
    }
}