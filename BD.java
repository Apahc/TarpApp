public class BD {
    public static void main(String[] args) {
        // Создание экземпляра вспомогательного класса для работы с БД
        TarotDBHelper dbHelper = new TarotDBHelper();

        // Вызов метода для подключения и выполнения запроса
        dbHelper.connectAndQuery();
    }
}
