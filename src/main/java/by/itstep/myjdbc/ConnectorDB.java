package by.itstep.myjdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class ConnectorDB {

    public static final String URL = "jdbc:mysql://localhost:3306/fitnessclub";
    public static final String USER = "root";
    public static final String PASSWORD = "12345678";
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static Connection createConnection(String url, String user, String pass){
        Connection connection;

        try {
            System.out.println("Регистрация JDBC драйвера...");
            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("Подключение к БД...");
            connection = DriverManager.getConnection(url, user, pass);


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

    public static void findTrainersMore30Years() {
        Connection connection = createConnection(URL, USER, PASSWORD);
        Statement statement;
        ResultSet resultSet;
        String request = "SELECT fio, age, type_gym\n" +
                "FROM trainer\n" +
                "         INNER JOIN section ON trainer.id_section = section.id_section\n" +
                "         INNER JOIN gym ON section.id_type_gym = gym.id_gym\n" +
                "WHERE age > 30 AND (type_gym = 'Зал А' OR type_gym = 'Бассейн');";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(request);
            System.out.println("\nТренера больше 30 лет, преподающие в Зале А или Бассейне: ");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("fio") +
                        ", " + resultSet.getInt("age") + " лет - " +
                        resultSet.getString("type_gym"));
            }
            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void addNewTrainer() {
        Connection connection = createConnection(URL, USER, PASSWORD);
        PreparedStatement preparedStatement;
        String request = "INSERT trainer(fio, age, id_section, workexperience, education)\n" +
                "VALUES (?, ?, (SELECT section.id_section FROM section WHERE name_section = ?), ?, ?);";

        try {
            System.out.println("Добавление нового тренера:");
            System.out.print("Введите ФИО: ");
            String fioInput = reader.readLine();
            System.out.print("Введите возраст: ");
            int ageInput = Integer.parseInt(reader.readLine());
            System.out.print("Введите секцию: ");
            String sectionInput = reader.readLine();
            System.out.print("Введите стаж: ");
            int experienceInput = Integer.parseInt(reader.readLine());
            System.out.print("Введите образование: ");
            String educationInput = reader.readLine();

            preparedStatement = connection.prepareStatement(request);
            preparedStatement.setString(1, fioInput);
            preparedStatement.setInt(2, ageInput);
            preparedStatement.setString(3, sectionInput);
            preparedStatement.setInt(4, experienceInput);
            preparedStatement.setString(5, educationInput);

            int row = preparedStatement.executeUpdate();

            if (row > 0) {
                System.out.println("Новый тренер добавлен!");
            } else {
                System.out.println("Тренер не добавлен, что-то пошло не так...");
            }

            preparedStatement.close();
            connection.close();

        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void findTrainer() {
        Connection connection = createConnection(URL, USER, PASSWORD);
        PreparedStatement preparedStatement;
        ResultSet trainer;
        String request = "SELECT fio, age, name_section, workexperience, education\n" +
                "FROM trainer\n" +
                "         INNER JOIN section ON trainer.id_section = section.id_section\n" +
                "WHERE fio = ?;";

        try {
            System.out.println("Найти тренера по ФИО:");
            System.out.print("Введите ФИО тренера: ");
            String fioInput = reader.readLine();

            preparedStatement = connection.prepareStatement(request);
            preparedStatement.setString(1, fioInput);

            trainer = preparedStatement.executeQuery();

            boolean isHas = false;
            System.out.println("\nИнформация о тренере " + fioInput + ":");
            while (trainer.next()) {
                isHas = true;
                System.out.println(
                                "Возраст - " + trainer.getInt("age") + " лет;\n" +
                                "Секция - " + trainer.getString("name_section") + ";\n" +
                                "Стаж - " + trainer.getInt("workexperience") + " лет;\n" +
                                "Образование - " + trainer.getString("education") + "."
                );
                }
            if (!isHas) {
                System.out.println("Такого тренера не существует!");
            }



            trainer.close();
            preparedStatement.close();
            connection.close();

        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
