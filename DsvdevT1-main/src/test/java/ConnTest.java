import com.dsvdev.repository.UserJdbcRepository;
import com.dsvdev.repository.UserRepository;
import com.dsvdev.utils.Util;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnTest {

    static Connection connection;
    @BeforeAll
    public static void init() throws SQLException {
        connection = Util.getConnection();
    }

    @Test
    public void testConn() {
        try {
            Connection connection = Util.getConnection();
            System.out.println(connection.isValid(1));
            connection.close();
        } catch (SQLException e) {
            System.out.println("Ошибка подключения");
            System.out.println(e.getMessage());;
        }
    }

    @Test
    public void creatingTableTest() {
        UserRepository userRepository = new UserJdbcRepository();
        userRepository.createUsersTable();
    }

    @Test
    public void droppingTableTest() {
        UserRepository userRepository = new UserJdbcRepository();
        userRepository.dropUsersTable();
    }

    @Test
    public void addUserTest() {
        UserRepository userRepository = new UserJdbcRepository();
        userRepository.saveUser("ivan", "ivanov", (byte) 15);
    }

    @Test
    public void deletingUserTest() {
        UserRepository userRepository = new UserJdbcRepository();
        userRepository.removeUserById(2);
    }

    @Test
    public void getAllUsersTest() {
        UserRepository userRepository = new UserJdbcRepository();
        System.out.println(userRepository.getAllUsers());
    }
}
