import day01.Student;
import day01.StudentOperator;

import java.text.SimpleDateFormat;
import java.util.List;


public class StudentOperatorTest {

    @org.junit.Test
    public void findAll() {
        StudentOperator studentOperator = new StudentOperator();
        List<Student> students = studentOperator.findAll();
        students.forEach(System.out::println);
    }

    @org.junit.Test
    public void updateStudent() {
        StudentOperator studentOperator = new StudentOperator();
        Student student = new Student();
        student.setSno("108");
        student.setSname("liulanbo");
        student.setSsex("M");

        String date = "1998-08-20";
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date dt = java.sql.Date.valueOf(date);
        student.setSbirthday(dt);

        student.setSclass("90000");
        int i = studentOperator.updateStudent(student);
        System.out.println(i);
    }

    @org.junit.Test
    public void insertStudent() {
        StudentOperator studentOperator = new StudentOperator();
        Student student = new Student();
        student.setSno(null);
        student.setSname("liulanbo");
        student.setSsex("M");

        String date = "1998-08-20";
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date dt = java.sql.Date.valueOf(date);
        student.setSbirthday(dt);

        student.setSclass("90000");
        int i = studentOperator.insertStudent(student);
        System.out.println(i);
    }

    @org.junit.Test
    public void deleteStudent() {
        StudentOperator studentOperator = new StudentOperator();
        String sno = "109";
        int i = studentOperator.deleteStudent(sno);
        System.out.println(i);
    }
}