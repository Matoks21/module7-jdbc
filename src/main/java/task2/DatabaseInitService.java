package task2;

import task1.Database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/*
Завдання №2 - створити клас для ініціалізації структури БД
Створи клас з назвою task2.DatabaseInitService.
У цьому класі має бути метод public static void main(String[] args), який зчитуватиме файл sql/init_db.sql
і виконуватиме запити з цього класу у БД.

Для роботи з БД використовуй написаний раніше тобою клас task1.Database.

Результат запуску цього класу - проініцалізована база даних з коректно створеними таблицями та зв'язками між цими таблицями.
 */
public class DatabaseInitService {

    public static void main(String[] args) {
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = new String(Files.readAllBytes(Paths.get("sql/init_db.sql")));
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.executeUpdate();
            }
        } catch (IOException | SQLException ex) {
            System.out.println("Error occurred: " + ex.getMessage());
        }

    }
}
