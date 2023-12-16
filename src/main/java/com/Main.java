package com;

import com.entity.Book;
import com.entity.Student;
import lombok.extern.java.Log;
import org.apache.ibatis.io.Resources;
import com.sql.SqlUtil;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.LogManager;

@Log
public class Main {
    public static void main(String[] args) {

        try(Scanner scanner = new Scanner(System.in)){
            //读取日志配置文件
            LogManager logManager= LogManager.getLogManager();
            logManager.readConfiguration(Resources.getResourceAsStream("logging.properties"));
            while(true) {
                System.out.println("=========================");
                System.out.println("1.录入学生信息");
                System.out.println("2.录入书籍信息");
                System.out.println("3.录入借阅信息");
                System.out.println("4.查询借阅信息");
                System.out.println("5.查询学生信息");
                System.out.println("6.查询书籍信息");
                System.out.println("输入您想要执行的操作，输入其他任意数字推出");
                int op;
                try {
                     op = scanner.nextInt();
                } catch (Exception e) {
                    return;
                }
                scanner.nextLine();
                switch (op){
                    case 1:
                        addStudent(scanner);
                        break;
                    case 2:
                        addBook(scanner);
                        break;
                    case 3:
                        addBorrow(scanner);
                        break;
                    case 4:
                        showBorrows();
                        break;
                    case 5:
                        showStudent(scanner);
                        break;
                    case 6:
                        showBook(scanner);
                        break;
                    default:
                        return;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void showStudents(){

    }

    private static void showBorrows(){
    }
    private static void showBook(Scanner scanner){
        System.out.println("请输入要查询的书籍号");
        String id = scanner.nextLine();
        SqlUtil.doSqlWork(bookMapper -> {
            Book book = bookMapper.getBook(Integer.parseInt(id));
            System.out.println(book);
        });
    }
    private static void showStudent(Scanner scanner){
        System.out.println("请输入要查询的学生学号");
        String id = scanner.nextLine();
        SqlUtil.doSqlWork(bookMapper -> {
            Student student = bookMapper.getStudent(Integer.parseInt(id));
            System.out.println(student);
            log.info("查询了" + student.getName() + "的信息");
        });
    }
    private static void addBorrow(Scanner scanner){
        System.out.println("请输入书籍号：");
        String id = scanner.nextLine();
        int bid = Integer.parseInt(id);
        System.out.println("请输入借阅人学号：");
        String ssid = scanner.nextLine();
        int sid = Integer.parseInt(ssid);
        SqlUtil.doSqlWork(bookMapper -> {
            if (bookMapper.addBorrow(bid,sid)>0){
                System.out.println("录入借阅信息成功！");
                log.info("新添加了一条借阅信息" );
            }else{
                System.out.println("录入借阅失败，请重试！");
            }

        });
    }

    private static void addBook(Scanner scanner){
        System.out.println("请输入书籍名字：");
        String title = scanner.nextLine();
        System.out.println("请输入书籍描述：");
        String desc = scanner.nextLine();
        System.out.println("请输入书籍价格：");
        String price = scanner.nextLine();
        double p = Double.parseDouble(price);
        Book book = new Book(title,desc,p);
        SqlUtil.doSqlWork(bookMapper -> {
            if (bookMapper.addBook(book)>0){
                System.out.println("录入书籍信息成功！");
                log.info("新添加了一条书籍信息" + book);
            }else{
                System.out.println("录入书籍信息失败，请重试！");
            }

        });
    }

    private static void addStudent(Scanner scanner){
        System.out.println("请输入学生名字：");
        String name = scanner.nextLine();
        System.out.println("请输入学生性别：");
        String sex = scanner.nextLine();
        System.out.println("请输入学生年级：");
        String grade = scanner.nextLine();
        int g = Integer.parseInt(grade);
        Student student = new Student(name,sex,g);
        SqlUtil.doSqlWork(bookMapper -> {
            if (bookMapper.addStudent(student)>0){
                System.out.println("录入学生信息成功！");
                log.info("新添加了一条学生信息" + student);
            }else{
                System.out.println("录入学生信息失败，请重试！");
            }

        });
    }

}
