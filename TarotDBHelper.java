import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TarotDBHelper {

    // Константы для подключения к базе данных
    private static final String URL = "jdbc:postgresql://localhost:5432/TarotDB";
    private static final String USER = "postgres";
    private static final String PASSWORD = "12345";

    // Метод для подключения к базе данных и выполнения запроса
    public void connectAndQuery() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            System.out.println("Подключение к серверу PostgreSQL успешно установлено.");

            // SQL-запрос для извлечения данных
            String query = "SELECT name, upright_meaning FROM public.tarot_cards";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String meaning = resultSet.getString("upright_meaning");
                System.out.println("Название: " + name);
                System.out.println("Значение: " + meaning);
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
