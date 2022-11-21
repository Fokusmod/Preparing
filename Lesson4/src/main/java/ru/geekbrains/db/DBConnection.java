package ru.geekbrains.db;

import java.sql.Connection;

public interface DBConnection {

    void connect();

    void close();

    Connection getConnection();


}
