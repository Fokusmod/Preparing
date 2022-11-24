package ru.geekbrains.db;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.*;
import java.util.*;


public class SQLQuery implements Query {
    private Connection connection;
    private Statement statement;

    private ResultSet resultSet;
    boolean filling;

    private List<Statistics> list = new ArrayList<>();

    public void preparingForWorkInBD() {
        createTableMovies();
        createTableDaysWeek();
        createTableSessions();
        createTableBuyTickets();
        createTableSchedules();

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
            statement.execute("drop table if exists movies");
            result = statement.execute(PreparingDDL.tableMovies.getValue());
            if (!result) {
                System.out.println("Таблица с фильмами создана.");
            }
            insertInMovies();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createTableDaysWeek() {
        try {
            boolean result;
            statement = connection.createStatement();
            statement.execute("drop table if exists days_week");
            result = statement.execute(PreparingDDL.tableDaysWeek.getValue());
            if (!result) {
                System.out.println("Таблица с днями недели создана.");
            }
            insertInDayWeek();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createTableSessions() {
        try {
            boolean result;
            statement = connection.createStatement();
            statement.execute("drop table if exists sessions");
            result = statement.execute(PreparingDDL.tableSessions.getValue());
            if (!result) {
                System.out.println("Таблица сеансов создана.");
            }
            insertInSessions();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createTableBuyTickets() {
        try {
            boolean result;
            statement = connection.createStatement();
            statement.execute("drop table if exists buy_tickets");
            result = statement.execute(PreparingDDL.buyTickets.getValue());
            if (!result) {
                System.out.println("Таблица c проданными билетами создана.");
            }

            insertInBuyTickets();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createTableSchedules() {
        try {
            boolean result;
            statement = connection.createStatement();
            statement.execute("drop table if exists schedules");
            result = statement.execute(PreparingDDL.schedules.getValue());
            if (!result) {
                System.out.println("Таблица c рассписанием создана.");
            }
            insertInScheduler();
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
        try {
            checkResultSet();
            resultSet = statement.executeQuery(
                    "select " +
                            "movies.title," +
                            "sessions.started_at," +
                            "sessions.end_in, " +
                            "lead(movies.title) over (order by started_at) as imposedMovie," +
                            "lead(sessions.started_at) over (order by started_at) as imposedStart," +
                            "lead(sessions.end_in) over (order by started_at) as imposedEnd " +
                            "from movies " +
                            "left join sessions on movies.id = sessions.movie_id " +
                            "inner join schedules on sessions.id = schedules.session_id "
            );
            while (resultSet.next()) {
                String movie = resultSet.getString(1);
                String start = resultSet.getString(2);
                String end = resultSet.getString(3);
                String impMovie = resultSet.getString(4);
                String impStart = resultSet.getString(5);
                String impEnd = resultSet.getString(6);

                if (impStart != null) {
                    DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
                    long endMovie = DateTime.parse(end, dateTimeFormatter).getMillis();
                    long imnStartMovie = DateTime.parse(impStart, dateTimeFormatter).getMillis();
                    if (endMovie > imnStartMovie) {
                        System.out.println("Найдено наложение фильмов друг на друга в рассписании.");
                        System.out.println("----------------------------");
                        System.out.println("movie " + movie);
                        System.out.println("started_at " + start);
                        System.out.println("end_in " + end);
                        System.out.println("imposedMovie " + impMovie);
                        System.out.println("imposedStart " + impStart);
                        System.out.println("imposedEnd " + impEnd);
                        System.out.println("----------------------------");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void breakInSchedule() {
        checkResultSet();
        try {
            resultSet = statement.executeQuery(
                    "select " +
                            "movies.title, " +
                            "sessions.started_at, " +
                            "movies.duration, " +
                            "lead(sessions.started_at) over (order by started_at) as start_next_movie  " +
                            "from movies " +
                            "left join sessions on movies.id = sessions.movie_id " +
                            "inner join schedules on sessions.id = schedules.session_id;"
            );
            List<BreakMovie> list = new ArrayList<>();

            while (resultSet.next()) {
                String startNow = resultSet.getString(2);
                String startNext = resultSet.getString(4);
                int duration = resultSet.getInt(3);

                if (startNext != null) {
                    DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
                    long endMovie = DateTime.parse(startNow, dateTimeFormatter).getMillis() + (duration * 60000L);
                    long startNxt = DateTime.parse(startNext, dateTimeFormatter).getMillis();
                    if (((startNxt - endMovie) / 60000L) > 0) {
                        int brktime = (int) ((startNxt - endMovie) / 60000L);
                        BreakMovie movie = new BreakMovie();
                        movie.title = resultSet.getString(1);
                        movie.started_at = startNow;
                        movie.nextMoveTime = startNext;
                        movie.breakTime = brktime;
                        movie.duration = duration;
                        list.add(movie);
                    }
                }
            }
            list.sort((o1, o2) -> o2.breakTime - o1.breakTime);
            System.out.println("Перерывы между фильми в рассписании");
            System.out.println("----------------------------");
            System.out.println("название  время_старта  длительность  время_старта_следующего  перерыв");
            for (BreakMovie breakMovie : list) {
                System.out.println(
                        breakMovie.title + "   " +
                                breakMovie.started_at + "   " +
                                breakMovie.duration + "m   " +
                                breakMovie.nextMoveTime + "   " +
                                breakMovie.breakTime + "m");
            }
            System.out.println("----------------------------");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void movieStats() {
//        - список фильмов, для каждого —
//        - с указанием общего числа посетителей за все время,
//        - среднего числа зрителей за сеанс
//        - и общей суммы сборов по каждому фильму (отсортировать по убыванию прибыли).
//        - Внизу таблицы должна быть строчка «итого», содержащая данные по всем фильмам сразу;
        checkResultSet();
        try {
            resultSet = statement.executeQuery(
                    "select " +
                            "schedules.session_id, " +
                            "movies.title," +
                            "price, " +
                            "count(buy_tickets.session_id) " +
                            "from movies " +
                            "left join sessions on movies.id = sessions.movie_id " +
                            "join schedules on sessions.id = schedules.session_id " +
                            "join buy_tickets on sessions.id = buy_tickets.session_id " +
                            "group by movies.title, price, schedules.session_id "
            );
            while (resultSet.next()) {
                String title = resultSet.getString(2);
                int price = resultSet.getInt(3);
                int countVisitors = resultSet.getInt(4);

                Statistics statistics = new Statistics();
                statistics.title = title;
                statistics.countVisitors += countVisitors;
                statistics.fees += (price * countVisitors);
                statistics.countSession++;
                statistics.averageVisitorForSession = (statistics.countVisitors + 0.0f) / statistics.countSession;
                addToList(statistics);
            }
            int countFilms = 0;
            int countVisitors = 0;
            int countSessions = 0;
            float averageVisitorsForSesn;
            int cache = 0;
            System.out.println("Статистика по фильмам");
            System.out.println("----------------------------");
            for (Statistics statistics : list) {
                System.out.println("Название  - " + statistics.title);
                countFilms++;
                System.out.println("Число посетителей  - " + statistics.countVisitors);
                countVisitors += statistics.countVisitors;
                countSessions += statistics.countSession;
                System.out.println("Число Среднее кол-во посетителей на сеанс  - " + statistics.averageVisitorForSession);
                System.out.println("Кассовые сборы  - " + statistics.fees + "р");
                cache += statistics.fees;
                System.out.println("----------------------------");
            }
            averageVisitorsForSesn = (countVisitors + 0.0f) / countSessions;


            System.out.println("ИТОГО:======================");
            System.out.println("Фильмы - " + countFilms);
            System.out.println("Общее кол-во посетителей - " + countVisitors);
            System.out.print("Ср. кол-во посетителей за сеансы - ");
            System.out.printf("%.2f", averageVisitorsForSesn);
            System.out.println();
            System.out.println("Общий кассовый сбор - " + cache);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void addToList(Statistics statistics) {
        if (list.isEmpty()) {
            list.add(statistics);
            return;
        }
        for (Statistics statis : list) {
            if (statis.title.equals(statistics.title)) {
                statis.countSession++;
                statis.fees += statistics.fees;
                statis.countVisitors += statistics.countVisitors;
                statis.averageVisitorForSession = (statis.countVisitors + 0.0f) / statis.countSession;
                return;
            }
        }
        list.add(statistics);
    }

    @Override
    public void countVisitors() {
        // число посетителей и кассовые сборы, сгруппированные по времени начала фильма: с 9 до 15, с 15 до 18, с 18 до 21,
// с 21 до 00:00 (сколько посетителей пришло с 9 до 15 часов и т.д.).
    }

    private void checkResultSet() {
        if (statement == null) {
            try {
                statement = connection.createStatement();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

private static class BreakMovie {

    private String title;

    private String started_at;

    private int duration;

    private String nextMoveTime;

    private int breakTime;

    @Override
    public String toString() {
        return "BreakMovie{" +
                "title='" + title + '\'' +
                ", started_at='" + started_at + '\'' +
                ", duration=" + duration +
                ", nextMoveTime='" + nextMoveTime + '\'' +
                ", breakTime=" + breakTime +
                '}';
    }
}

private static class Statistics {

    private String title;

    private int countVisitors;

    private float averageVisitorForSession;

    private int fees;

    private int countSession;

    @Override
    public String toString() {
        return "Statistics{" +
                "title='" + title + '\'' +
                ", countVisitors=" + countVisitors +
                ", averageVisitorForSession=" + averageVisitorForSession +
                ", fees=" + fees +
                ", countSession=" + countSession +
                '}';
    }
}
}
