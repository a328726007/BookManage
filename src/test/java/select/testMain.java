package select;

import com.entity.Borrow;
import com.entity.Student;
import org.junit.jupiter.api.Test;
import com.sql.SqlUtil;

public class testMain {
    @Test
    public void test1(){
        SqlUtil.doSqlWork(bookMapper -> {
            for (Borrow listBorrow : bookMapper.listBorrows()) {
                System.out.println(listBorrow);
            }
        });
    }
    @Test
    public void test2(){
        int bid = 3;
        SqlUtil.doSqlWork(bookMapper -> {
            System.out.println(bookMapper.getBook(bid));
        });
    }
    @Test
    public void test3(){
        int sid = 4;
        SqlUtil.doSqlWork(bookMapper -> {
            System.out.println(bookMapper.getStudent(sid));
        });
    }
    @Test
    public void test4(){
        SqlUtil.doSqlWork(bookMapper -> {
            for (Student student: bookMapper.listStudents()){
                System.out.println(student);
            }
        });
    }
}
