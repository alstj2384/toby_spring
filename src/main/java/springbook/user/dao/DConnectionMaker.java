package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DConnectionMaker implements ConnectionMaker{

    @Override
    public Connection makeConnection() throws SQLException {
        // D사의 독자적인 방법으로 Connection을 생성하는 코드
        Connection c = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/jdbc", "spring", "book");
        System.out.println("DConnectionMaker 실행");

        return c;
    }
}
