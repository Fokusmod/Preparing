package ru.geekbrains.db;

// 1. Задача про кинотеатр.
// У фильма, который идет в кинотеатре, есть название, длительность (пусть будет 60, 90 или 120 минут), цена билета
// (в разное время и дни может быть разной), время начала сеанса (один фильм может быть показан несколько раз в разное
// время и с разной ценой билета). Есть информация о купленных билетах (номер билета, на какой сеанс).
// Задания:
// Составить грамотную нормализованную схему хранения этих данных в БД. Внести в нее 4–5 фильмов, расписание на один
// день и несколько проданных билетов.
//
// Сделать запросы, считающие и выводящие в понятном виде:
// ошибки в расписании (фильмы накладываются друг на друга), отсортированные по возрастанию времени. Выводить надо
// колонки «фильм 1», «время начала», «длительность», «фильм 2», «время начала», «длительность»;
//
// перерывы 30 минут и более между фильмами — выводить по уменьшению длительности перерыва. Колонки «фильм 1»,
// «время начала», «длительность», «время начала второго фильма», «длительность перерыва»;

// список фильмов, для каждого — с указанием общего числа посетителей за все время, среднего числа зрителей за сеанс
// и общей суммы сборов по каждому фильму (отсортировать по убыванию прибыли). Внизу таблицы должна быть строчка
// «итого», содержащая данные по всем фильмам сразу;
// число посетителей и кассовые сборы, сгруппированные по времени начала фильма: с 9 до 15, с 15 до 18, с 18 до 21,
// с 21 до 00:00 (сколько посетителей пришло с 9 до 15 часов и т.д.).

import java.sql.SQLException;

public class Main {

    public static void main(String[] args)  {

    DBConnection dbConnection = new SQLiteJDBCConnector();
    dbConnection.connect();
    Query query = new SQLQuery();
    query.getConnection(dbConnection);

    query.preparingForWorkInBD();
    query.errorInSchedule();

    }

//    Calendar calendar = new GregorianCalendar();
//        calendar.setTime(new Date());
//        calendar.set(2022, 11, 21, 9, 00, 00);
//
//    SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    String newDate = date.format(calendar.getTime());
////        System.out.println(newDate);
//        try {
//        Class.forName(className);
//        Connection connection = DriverManager.getConnection(DB_URL);
//
//        Statement statement = connection.createStatement();
//
//        statement.execute("CREATE TABLE if not exists mydata (id integer primary key autoincrement, date_time text not null unique );");
////            statement.execute("INSERT INTO mydata (date_time) values ('"+newDate+"');");
//        ResultSet rs = statement.executeQuery("select substr(date_time,12,19) from mydata;");
//        while (rs.next()){
//            String date1 = rs.getString(1);
//            System.out.println(date1);
//        }



//}
}
