package ru.geekbrains.db.test;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class main {

    private final static String className = "org.sqlite.JDBC";
    private final static String DB_URL = "jdbc:sqlite:Main.s3db";


    public static void main(String[] args) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.set(2022, 11, 21, 9, 00, 00);

        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String newDate = date.format(calendar.getTime());
//        System.out.println(newDate);
        try {
            Class.forName(className);
            Connection connection = DriverManager.getConnection(DB_URL);

            Statement statement = connection.createStatement();

            statement.execute("CREATE TABLE if not exists mydata (id integer primary key autoincrement, date_time date not null );");
            statement.execute("INSERT INTO mydata (date_time) values (datetime('2022-11-20 16:38:00'));");



        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
