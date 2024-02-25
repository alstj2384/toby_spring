package springbook.user.dao;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import springbook.user.dao.DaoFactory;
import springbook.user.dao.UserDao;
import springbook.user.domain.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@DirtiesContext
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoFactory.class)
public class UserDaoTest {
    @Autowired
    private UserDao dao;
    private User user1;
    private User user2;
    private User user3;
    private JdbcContext jdbcContext;


    @BeforeEach
    public void setUp(){

//        dao = new UserDao();
        this.user1 = new User("gyumee", "박성철", "1234");
        this.user2 = new User("leegw700", "이길원", "1234");
        this.user3 = new User("bumjin", "박범진", "1234");

//        JdbcContext jdbcContext = new JdbcContext();
//        DataSource dataSource = new SingleConnectionDataSource(
//                "jdbc:mysql://localhost/jdbc", "spring", "book", true);
//        jdbcContext.setDataSource(dataSource);
//        dao.setDataSource(dataSource);
//        dao.setJdbcContext(jdbcContext);
    }

    @Test
    public void addAndGet() throws SQLException{
        // DB 초기화
        dao.deleteAll();

        // DB 초기화 검증
        // 초기화한 DB의 열 개수가 0이면 된다
        Assertions.assertEquals(dao.getCount(), 0);

        // DB에 데이터 등록
        dao.add(user1);
        dao.add(user2);

        // DB에서 데이터 조회
        User userget1 = dao.get(user1.getId());
        // DB에서 조회한 데이터와 등록한 데이터가 같아야 한다
        Assertions.assertEquals(userget1.getName(), user1.getName());
        Assertions.assertEquals(userget1.getPassword(), user1.getPassword());

        // DB에서 데이터 조회
        User userget2  = dao.get(user2.getId());
        // DB에서 조회한 데이터와 등록한 데이터가 같아야 한다
        Assertions.assertEquals(userget2.getName(), user2.getName());
        Assertions.assertEquals(userget2.getPassword(), user2.getPassword());

        // DB getCount 메서드 검증, 열 개수가 1이면 된다
        Assertions.assertEquals(dao.getCount(), 2);
    }

    @Test
    public void count() throws SQLException{
        dao.deleteAll();
        Assertions.assertEquals(dao.getCount(), 0);
        dao.add(user1);
        Assertions.assertEquals(dao.getCount(), 1);
        dao.add(user2);
        Assertions.assertEquals(dao.getCount(), 2);
        dao.add(user3);
        Assertions.assertEquals(dao.getCount(), 3);
    }

    @Test
    public void getUserFailure() throws SQLException{
        Assertions.assertThrows(EmptyResultDataAccessException.class,
            () -> dao.get("-1"));
    }

    @Test
    public void getAll(){
        dao.deleteAll();

        dao.add(user1);
        List<User> users1 = dao.getAll();
        Assertions.assertEquals(users1.size(), 1);
        checkSamUser(user1, users1.get(0));


        dao.add(user2);
        List<User> users2 = dao.getAll();
        Assertions.assertEquals(users2.size(), 2);
        checkSamUser(user1, users2.get(0));
        checkSamUser(user2, users2.get(1));


        dao.add(user3);
        List<User> users3 = dao.getAll();
        Assertions.assertEquals(users3.size(), 3);
        checkSamUser(user3, users3.get(0));
        checkSamUser(user1, users3.get(1));
        checkSamUser(user2, users3.get(2));

    }

    private void checkSamUser(User user1, User user2) {
        Assertions.assertEquals(user1.getId(), user2.getId());
        Assertions.assertEquals(user1.getName(), user2.getName());
        Assertions.assertEquals(user1.getPassword(), user2.getPassword());
    }

}
