package ru.geekbrains.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLQuery implements Query {
    private Connection connection;
    private Statement statement;

    private ResultSet resultSet;
    boolean filling;

    public void preparingForWorkInBD() {
        createTableMovies();
        createTableDaysWeek();
        createTableSessions();
        createTableBuyTickets();
        createTableSchedules();
        if (!filling) {
            insertInMovies();
            insertInDayWeek();
            insertInSessions();
            insertInScheduler();
            insertInBuyTickets();
            filling = true;
        }

    }

    @Override
    public void createTable() {
        try {
            statement = connection.createStatement();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dropTable() {

    }

    @Override
    public void getConnection(DBConnection dbConnection) {
        this.connection = dbConnection.getConnection();
    }

    private void createTableMovies() {
        try {
            boolean result;
            statement = connection.createStatement();
            result = statement.execute(PreparingDDL.tableMovies.getValue());
            if (!result) {
                System.out.println("Таблица с фильмами создана.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createTableDaysWeek() {
        try {
            boolean result;
            statement = connection.createStatement();
            result = statement.execute(PreparingDDL.tableDaysWeek.getValue());
            if (!result) {
                System.out.println("Таблица с днями недели создана.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createTableSessions() {
        try {
            boolean result;
            statement = connection.createStatement();
            result = statement.execute(PreparingDDL.tableSessions.getValue());
            if (!result) {
                System.out.println("Таблица сеансов создана.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createTableBuyTickets() {
        try {
            boolean result;
            statement = connection.createStatement();
            result = statement.execute(PreparingDDL.buyTickets.getValue());
            if (!result) {
                System.out.println("Таблица c проданными билетами создана.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createTableSchedules() {
        try {
            boolean result;
            statement = connection.createStatement();
            result = statement.execute(PreparingDDL.schedules.getValue());
            if (!result) {
                System.out.println("Таблица c рассписанием создана.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void insertInMovies() {
        boolean result;
        try {
            result = statement.execute(PreparingDML.insertIntoMovies.getValue());
            if (!result) {
                System.out.println("Таблица movies наполнена данными");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void insertInDayWeek() {
        boolean result;
        try {
            result = statement.execute(PreparingDML.insertIntoDayWeek.getValue());
            if (!result) {
                System.out.println("Таблица days_week наполнена данными");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void insertInSessions() {
        boolean result;
        try {
            result = statement.execute(PreparingDML.insertIntoSessions.getValue());
            if (!result) {
                System.out.println("Таблица sessions наполнена данными");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void insertInBuyTickets() {
        boolean result;
        try {
            result = statement.execute(PreparingDML.insertIntoBuyTickets.getValue());
            if (!result) {
                System.out.println("Таблица byu_tickets наполнена данными");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void insertInScheduler() {
        boolean result;
        try {
            result = statement.execute(PreparingDML.insertIntoSchedules.getValue());
            if (!result) {
                System.out.println("Таблица schedules наполнена данными");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void errorInSchedule() {
//        Сделать запросы, считающие и выводящие в понятном виде:
// ошибки в расписании (фильмы накладываются друг на друга), отсортированные по возрастанию времени. Выводить надо
// колонки «фильм 1», «время начала», «длительность», «фильм 2», «время начала», «длительность»;
        try {
            resultSet = statement.executeQuery(
                    "SELECT " +
                            "movies.title," +
                            "sessions.started_at," +
                            "sessions.end_in, " +
                            "lead(movies.title) over (order by started_at) as movie,  " +
                            "lead(sessions.started_at) over (order by sessions.started_at) as start," +
                            "lead(sessions.end_in) over (order by sessions.started_at) as end_movie " +
                            "from movies " +
                            "inner join sessions on " +
                            "sessions.movie_id = movies.id " +
                            "where sessions.started_at >= (select lead(sessions.started_at) over (order by sessions.started_at) from sessions) " +
                            "Order by started_at;"
            );
            while (resultSet.next()) {
                String result = resultSet.getString(1);
                System.out.println(result);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void breakInSchedule() {
        // перерывы 30 минут и более между фильмами — выводить по уменьшению длительности перерыва. Колонки «фильм 1»,
// «время начала», «длительность», «время начала второго фильма», «длительность перерыва»;
    }

    @Override
    public void movieStats() {
//        список фильмов, для каждого — с указанием общего числа посетителей за все время, среднего числа зрителей за сеанс
// и общей суммы сборов по каждому фильму (отсортировать по убыванию прибыли). Внизу таблицы должна быть строчка
// «итого», содержащая данные по всем фильмам сразу;

    }

    @Override
    public void countVisitors() {
        // число посетителей и кассовые сборы, сгруппированные по времени начала фильма: с 9 до 15, с 15 до 18, с 18 до 21,
// с 21 до 00:00 (сколько посетителей пришло с 9 до 15 часов и т.д.).
    }

}
