import com.dsvdev.model.User;
import com.dsvdev.repository.UserJdbcRepository;
import com.dsvdev.repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryTest {
    private final UserRepository userRepository = new UserJdbcRepository();

    private final String testName = "Ivan";
    private final String testLastName = "Ivanov";
    private final byte testAge = 5;


    @Test
    public void dropUsersTable() {
        try {
            userRepository.dropUsersTable();
            userRepository.dropUsersTable();
        } catch (Exception e) {
            System.out.println("При тестировании удаления таблицы произошло исключение\n" + e);
        }
    }

    @Test
    public void createUsersTable() {
        try {
            userRepository.dropUsersTable();
            userRepository.createUsersTable();
        } catch (Exception e) {
            System.out.println("При тестировании создания таблицы пользователей произошло исключение\n" + e.getMessage());
        }
    }

    @Test
    public void saveUser() {
        try {
            userRepository.dropUsersTable();
            userRepository.createUsersTable();
            userRepository.saveUser(testName, testLastName, testAge);

            User user = userRepository.getAllUsers().get(0);

            if (!testName.equals(user.getName())
                    || !testLastName.equals(user.getLastName())
                    || testAge != user.getAge()
            ) {
                System.out.println("User был некорректно добавлен в базу данных");
            }

        } catch (Exception e) {
            System.out.println("Во время тестирования сохранения пользователя произошло исключение\n" + e);
        }
    }

    @Test
    public void removeUserById() {
        try {
            userRepository.dropUsersTable();
            userRepository.createUsersTable();
            userRepository.saveUser(testName, testLastName, testAge);
            userRepository.removeUserById(1L);
        } catch (Exception e) {
            System.out.println("При тестировании удаления пользователя по id произошло исключение\n" + e);
        }
    }

    @Test
    public void getAllUsers() {
        try {
            userRepository.dropUsersTable();
            userRepository.createUsersTable();
            userRepository.saveUser(testName, testLastName, testAge);
            List<User> userList = userRepository.getAllUsers();

            if (userList.size() != 1) {
                System.out.println("Проверьте корректность работы метода сохранения пользователя/удаления или создания таблицы");
            }
        } catch (Exception e) {
            System.out.println("При попытке достать всех пользователей из базы данных произошло исключение\n" + e);
        }
    }

    @Test
    public void cleanUsersTable() {
        try {
            userRepository.dropUsersTable();
            userRepository.createUsersTable();
            userRepository.saveUser(testName, testLastName, testAge);
            userRepository.cleanUsersTable();

            if (!userRepository.getAllUsers().isEmpty()) {
                System.out.println("Метод очищения таблицы пользователей реализован не корректно");
            }
        } catch (Exception e) {
            System.out.println("При тестировании очистки таблицы пользователей произошло исключение\n" + e);
        }
    }
}
